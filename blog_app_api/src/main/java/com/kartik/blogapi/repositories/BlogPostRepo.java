package com.kartik.blogapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.blogapi.entities.BlogPost;
import com.kartik.blogapi.entities.User;
import com.kartik.blogapi.entities.Category;



public interface BlogPostRepo extends JpaRepository<BlogPost, Integer>{

	List<BlogPost> findByUser(User user);
	List<BlogPost> findByCategory(Category category);
	
}
