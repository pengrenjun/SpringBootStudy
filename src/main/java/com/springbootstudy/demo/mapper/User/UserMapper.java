package com.springbootstudy.demo.mapper.User;

import com.springbootstudy.demo.entity.User;
import org.springframework.stereotype.Component;


import java.util.List;

public interface UserMapper {

    /**
      * @Author:  pengrj
      * @Description: Mybatis方式返回所有用户列表数据
      * @param   * @param
      * @Return java.util.List<com.springbootstudy.demo.entity.User>
      * @Date: Created in 2018/7/29 0029 20:30
      */
    List <User>  getAllUsers();

    Integer insertNewUser(User user);

}
