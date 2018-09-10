package com.springbootstudy.tomikosJta1.service;


import com.springbootstudy.tomikosJta1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryA extends JpaRepository<User,Integer> {

    List<User> findByLocation(String location);
}
