package com.kartik.blogapi.services;

import java.util.List;

import com.kartik.blogapi.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto , int categoryId);
	
	CategoryDto getCategoryById(int categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(int categoryId);

}
