package com.kartik.blogapi.payloads;

import java.util.Date;

import com.kartik.blogapi.entities.Category;
import com.kartik.blogapi.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogPostDto {
	
	private int id;
	
	private String title;
	
	private String content;

	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
}
