package com.kartik.blogapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartik.blogapi.entities.Category;
import com.kartik.blogapi.exceptions.ResourceNotFoundException;
import com.kartik.blogapi.payloads.CategoryDto;
import com.kartik.blogapi.repositories.CategoryRepo;
import com.kartik.blogapi.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category item = this.modelMapper.map(categoryDto, Category.class);
		Category createdItem = this.categoryRepo.save(item);

		return this.modelMapper.map(createdItem, CategoryDto.class);
	}
	

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		
		Category item = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		item.setCategoryTitle(categoryDto.getCategoryTitle());
		item.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedItem = this.categoryRepo.save(item);
		
		return this.modelMapper.map(updatedItem, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {

		Category item = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return this.modelMapper.map(item, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> items = this.categoryRepo.findAll();
		
		List<CategoryDto> dtoItems = items.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return dtoItems;
	}

	@Override
	public void deleteCategory(int categoryId) {

		Category item = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.delete(item);
	}

}
