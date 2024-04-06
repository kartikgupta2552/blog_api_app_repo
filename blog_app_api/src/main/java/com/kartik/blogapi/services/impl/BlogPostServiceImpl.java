package com.kartik.blogapi.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartik.blogapi.entities.BlogPost;
import com.kartik.blogapi.entities.Category;
import com.kartik.blogapi.entities.User;
import com.kartik.blogapi.exceptions.ResourceNotFoundException;
import com.kartik.blogapi.payloads.BlogPostDto;
import com.kartik.blogapi.repositories.BlogPostRepo;
import com.kartik.blogapi.repositories.CategoryRepo;
import com.kartik.blogapi.repositories.UserRepo;
import com.kartik.blogapi.services.BlogPostService;

@Service
public class BlogPostServiceImpl implements BlogPostService {

	
	@Autowired
	private BlogPostRepo blogPostRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public BlogPostDto createPost(BlogPostDto post , int userId , int categoryId) {

		User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		BlogPost blogPost = this.modelMapper.map(post, BlogPost.class);
		blogPost.setImageName("default.png");
		blogPost.setAddedDate(new Date());
		blogPost.setUser(user);
		blogPost.setCategory(category);
		
		BlogPost savedPost = this.blogPostRepo.save(blogPost);
		
		return this.modelMapper.map(savedPost, BlogPostDto.class);
	}

	@Override
	public BlogPostDto updatePost(BlogPostDto post, int postId) {

		BlogPost blogPost = this.blogPostRepo.findById(postId).orElseThrow( () -> new ResourceNotFoundException("post", "postId", postId));
		
		blogPost.setTitle(post.getTitle());
		blogPost.setContent(post.getContent());
		
		BlogPost updatedPost = this.blogPostRepo.save(blogPost);
		
		return this.modelMapper.map(updatedPost, BlogPostDto.class);
	}

	@Override
	public void deletePost(int postId) {

		BlogPost blogPost = this.blogPostRepo.findById(postId).orElseThrow( () -> new ResourceNotFoundException("post", "postId", postId));
		this.blogPostRepo.delete(blogPost);

	}

	@Override
	public BlogPostDto getPostById(int postId) {

		BlogPost blogPost = this.blogPostRepo.findById(postId).orElseThrow( () -> new ResourceNotFoundException("post", "postId", postId));
		
		return this.modelMapper.map(blogPost, BlogPostDto.class);
	}

	@Override
	public List<BlogPostDto> getAllPost() {

		List<BlogPost> posts = this.blogPostRepo.findAll();
		List<BlogPostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, BlogPostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<BlogPostDto> getPostByCategory(int categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category", "categoryId", categoryId));
		List<BlogPost> posts = this.blogPostRepo.findByCategory(cat);
		
	 	List<BlogPostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, BlogPostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<BlogPostDto> getPostByUser(int userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		List<BlogPost> posts = this.blogPostRepo.findByUser(user);
		
		List<BlogPostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, BlogPostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<BlogPostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
