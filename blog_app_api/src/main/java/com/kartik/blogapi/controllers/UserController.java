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
import com.kartik.blogapi.payloads.UserDto;
import com.kartik.blogapi.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		 
		UserDto user = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(user , HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable Integer userId){
		 
		UserDto user = this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(user);
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
		this.userService.deleteUser(userId);
		
		return ResponseEntity.ok(new ApiResponse("user deleted Succesfully", true))	;	
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		
		UserDto user = this.userService.getUserById(userId);
		
		return ResponseEntity.ok(user);
	}
	
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<UserDto> users = this.userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
	
	
}









