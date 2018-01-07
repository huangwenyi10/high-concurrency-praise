package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户Repository
 * Created by Ay on 2018/1/6.
 */
public interface UserRepository extends JpaRepository<User,String> {

}
