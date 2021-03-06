package com.springbootstudy.tomikosJta1.mapper.User;


import com.springbootstudy.tomikosJta1.entity.User;

import java.util.List;

public interface UserMapperA {

    /**
      * @Author:  pengrj
      * @Description: Mybatis方式返回所有用户列表数据
      * @param   * @param
      * @Return java.util.List<com.springbootstudy.demo.entity.User>
      * @Date: Created in 2018/7/29 0029 20:30
      */
    List <User>  getAllUsers();

    /**
     * 保存后返回主键id
     * @param user
     * @return
     */
    Integer  saveorupdateUserB(User user);
}
