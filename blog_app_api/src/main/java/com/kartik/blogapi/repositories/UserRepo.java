package com.kartik.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.blogapi.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
