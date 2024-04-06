package com.kartik.blogapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;import com.kartik.blogapi.payloads.ApiResponse;
import com.kartik.blogapi.payloads.BlogPostDto;
import com.kartik.blogapi.services.BlogPostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class BlogPostController {
	
	@Autowired
	private BlogPostService service;

	
	@PostMapping("/user/{userId}/category/{categoryId}/blogpost")
	public ResponseEntity<BlogPostDto> createBlogPost(@Valid @RequestBody BlogPostDto postDto, @PathVariable int userId ,@PathVariable  int categoryId){
		
		BlogPostDto post = this.service.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<BlogPostDto>(post , HttpStatus.CREATED);
	}
	
	
	@GetMapping("blogPost/{postId}")
	public ResponseEntity<BlogPostDto> getPostByPostId(@PathVariable int postId){
		
		BlogPostDto postDto = this.service.getPostById(postId);
		
		return new ResponseEntity<BlogPostDto>(postDto , HttpStatus.OK);
	}
	
	
	@GetMapping("blogPost")
	public ResponseEntity<List<BlogPostDto>> getAllPosts(){
		
		return new ResponseEntity<List<BlogPostDto>>(this.service.getAllPost() , HttpStatus.OK);
		
	}
	
	
	@PutMapping("blogPost/{postId}")
	public ResponseEntity<BlogPostDto> updatePost(@Valid @RequestBody BlogPostDto postDto , @PathVariable int postId){
		
		BlogPostDto post = this.service.updatePost(postDto, postId);
		
		return new ResponseEntity<BlogPostDto>(post , HttpStatus.OK);
	}
	
	
	@DeleteMapping("blogPost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
		
		this.service.deletePost(postId);
		
		return  ResponseEntity.ok(new ApiResponse("post deleted successfully" , true));
	}
	
	
	@GetMapping("/user/{userId}/blogpost")
	public ResponseEntity<List<BlogPostDto>> getPostByUser(@PathVariable int userId){
		
		List<BlogPostDto> postDtos = this.service.getPostByUser(userId);
		
		return new ResponseEntity<List<BlogPostDto>>(postDtos , HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}/blogpost")
	public ResponseEntity<List<BlogPostDto>> getPostsByCategory(@PathVariable int categoryId){
		
		List<BlogPostDto> posts = this.service.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<BlogPostDto>>(posts , HttpStatus.OK);		
	}
	
}
