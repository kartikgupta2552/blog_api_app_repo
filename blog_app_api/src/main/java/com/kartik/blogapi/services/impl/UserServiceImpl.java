package com.kartik.blogapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartik.blogapi.entities.User;
import com.kartik.blogapi.payloads.UserDto;
import com.kartik.blogapi.repositories.UserRepo;
import com.kartik.blogapi.services.UserService;
import com.kartik.blogapi.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		User createdUser = this.userRepo.save(user);
		
		return this.modelMapper.map(createdUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
	
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","userId",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(int userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

		this.userRepo.delete(user);
		
	}

}
