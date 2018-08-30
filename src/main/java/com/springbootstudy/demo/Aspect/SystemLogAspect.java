package com.springbootstudy.demo.Aspect;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootstudy.demo.Annotation.SystemControllerLog;
import com.springbootstudy.demo.Annotation.SystemServiceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.UUID;


/**
 * 日志管理 Controller Service 切点类
 */
@Aspect
@Component
public class SystemLogAspect {

    //本地异常日志记录对象
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);

    private static ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private static ThreadLocal<String> key = new ThreadLocal<String>();
    private static ObjectMapper objectMapper = new ObjectMapper();

    //Service层切点
    @Pointcut("@annotation(com.springbootstudy.demo.Annotation.SystemServiceLog)")
    public  void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.springbootstudy.demo.Annotation.SystemControllerLog)")
    public  void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作  拦截请求参数,请求头信息,响应结果,响应时间
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        System.out.println("=====前置通知开始=====");
        //读取session中的用户
        //User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            // 请求开始时间
            startTime.set(System.currentTimeMillis());

            // 获取请求头
            Enumeration<String> enumeration = request.getHeaderNames();
            StringBuffer headers = new StringBuffer();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                headers.append(name + ":" + value).append(",");
            }
            String uri = UUID.randomUUID() + "|" + request.getRequestURI();

            String method = request.getMethod();
            StringBuffer params = new StringBuffer();
            if (HttpMethod.GET.toString().equals(method)) {// get请求
                String queryString = request.getQueryString();
                if (!StringUtils.isEmpty(queryString)) {
                    params.append(queryString);//URLEncodedUtils.encode(queryString, "UTF-8")
                }
            } else {//其他请求
                Object[] paramsArray = joinPoint.getArgs();
                if (paramsArray != null && paramsArray.length > 0) {
                    for (int i = 0; i < paramsArray.length; i++) {
                        if (paramsArray[i] instanceof Serializable) {
                            params.append(paramsArray[i].toString()).append(",");
                        } else {
                            //使用json系列化 反射等等方法 反系列化会影响请求性能建议重写tostring方法实现系列化接口
                            try {
                                String param= objectMapper.writeValueAsString(paramsArray[i]);
                                if(!StringUtils.isEmpty(param))
                                    params.append(param).append(",");
                            } catch (JsonProcessingException e) {
                                logger.error("doBefore obj to json exception obj={},msg={}",paramsArray[i],e);
                            }
                        }
                    }
                }
            }
            key.set(uri);
            logger.info("request params>>>>>>uri={},method={},params={},headers={}", uri, method, params, headers);


            //*========控制台输出=========*//

            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
            //System.out.println("请求人:" + user.getName());
            System.out.println("请求IP:" + ip);
            //*========数据库日志=========*//
          /*  Log log = SpringContextHolder.getBean("logxx");
            log.setDescription(getControllerMethodDescription(joinPoint));
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setType("0");
            log.setRequestIp(ip);
            log.setExceptionCode( null);
            log.setExceptionDetail( null);
            log.setParams( null);
            log.setCreateBy(user);
            log.setCreateDate(DateUtil.getCurrentDate());
            //保存数据库
            logService.add(log);*/
            System.out.println("=====前置通知结束=====");
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * Controller层 在方法执行后打印返回内容
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "controllerAspect()()")
    public void doAfterReturing(Object obj) {
        long costTime = System.currentTimeMillis() - startTime.get();
        String uri = key.get();
        startTime.remove();
        key.remove();
        String result= null;
        if(obj instanceof Serializable){
            result =  obj.toString();
        }else {
            if(obj != null) {
                try {
                    result = objectMapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    logger.error("doAfterReturing obj to json exception obj={},msg={}",obj,e);
                }
            }
        }
        System.out.println(" Controller @AfterReturning:后置通知获取返回结果 执行时间： ");
        logger.info("response result<<<<<<uri={},result={},costTime={}ms", uri, JSONObject.toJSONString(result), costTime);
    }


    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        //User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSONObject.toJSONString(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
           // System.out.println("请求人:" + user.getName());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/
            /*Log log = SpringContextHolder.getBean("logxx");
            log.setDescription(getServiceMthodDescription(joinPoint));
            log.setExceptionCode(e.getClass().getName());
            log.setType("1");
            log.setExceptionDetail(e.getMessage());
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setParams(params);
            log.setCreateBy(user);
            log.setCreateDate(DateUtil.getCurrentDate());
            log.setRequestIp(ip);
            //保存数据库
            logService.add(log);*/
            System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
        /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog. class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog. class).description();
                    break;
                }
            }
        }
        return description;
    }


}
