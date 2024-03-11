package com.kartik.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.blogapi.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
