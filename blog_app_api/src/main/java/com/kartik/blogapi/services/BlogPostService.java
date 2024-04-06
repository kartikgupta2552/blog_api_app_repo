package com.kartik.blogapi.services;

import java.util.List;

import com.kartik.blogapi.payloads.BlogPostDto;

public interface BlogPostService {

	// create
	BlogPostDto createPost(BlogPostDto post , int userId , int categoryId);
	
	// update
	BlogPostDto updatePost(BlogPostDto post , int postId);
	
	// delete
	void deletePost(int postId);
	
	// get single post
	BlogPostDto getPostById(int postId);
	
	// get all post by post id
	List<BlogPostDto> getAllPost();
	
	// get all post by category id
	List<BlogPostDto> getPostByCategory(int categoryId);
	
	// get all post by user id
	List<BlogPostDto> getPostByUser(int userId);
	
	// search post 
	List<BlogPostDto> searchPosts(String keyword);
	
}
