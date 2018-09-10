package com.springbootstudy.tomikosJta2.service;


import com.springbootstudy.tomikosJta2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryB extends JpaRepository<User,Integer> {

    List<User> findByLocation(String location);
}
