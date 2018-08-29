package com.springbootstudy.demo.Utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:   全局异常捕捉
 * @Author：pengrj
 * @Date : 2018/7/25 0025 22:31
 * @version:1.0
 */
@ControllerAdvice
public class GlobalDefaultException  {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest httpServletRequest, Exception e){
        return "service is busy,please try again ";
    }
}
