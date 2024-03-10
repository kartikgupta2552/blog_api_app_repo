package com.kartik.blogapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
	
	private int id;
	
	@NotEmpty(message = "name should not empty")
	private String name;
	
	@Email(message = "email is not valid!!")
	private String email;
	
	@NotNull @Size(min = 6 , message = "password must be minimum of 6 characters")
	private String password;
	
	@NotNull
	private String about;

}
