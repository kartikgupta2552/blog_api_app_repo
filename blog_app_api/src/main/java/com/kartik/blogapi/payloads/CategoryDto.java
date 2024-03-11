package com.kartik.blogapi.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int id;
	
	@NotEmpty(message = "category title must not be empty")
	private String categoryTitle;
	
	@NotEmpty
	private String categoryDescription;

}
