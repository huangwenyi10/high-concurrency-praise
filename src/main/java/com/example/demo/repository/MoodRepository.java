package com.example.demo.repository;

import com.example.demo.model.Mood;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说说Repository
 * Created by Ay on 2018/1/6.
 */
public interface MoodRepository extends JpaRepository<Mood,String> {

}
