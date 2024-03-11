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
import org.springframework.web.bind.annotation.RestController;

import com.kartik.blogapi.payloads.ApiResponse;
import com.kartik.blogapi.payloads.CategoryDto;
import com.kartik.blogapi.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	
	@PostMapping("")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto entity) {
		
		CategoryDto item = this.service.createCategory(entity);
		
		return new ResponseEntity<CategoryDto>(item , HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto entity , @PathVariable int id){
		
		CategoryDto item = this.service.updateCategory(entity, id);
		
		return ResponseEntity.ok(item);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id){
		
		CategoryDto item = this.service.getCategoryById(id);
		
		return ResponseEntity.ok(item);
	}
	
	
	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> item = this.service.getAllCategories();
		
		return ResponseEntity.ok(item);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id){
		
		this.service.deleteCategory(id);
		
		return ResponseEntity.ok(new ApiResponse("category deleted Successfully", true));
	}
	

}








