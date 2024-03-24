package com.socialmediaApplication.allServices;

import java.util.List;

import com.socialmediaApplication.Payload.getUserByEmailDto;
import com.socialmediaApplication.Payload.userDto;

public interface userService {
	public userDto createUser(userDto user);
	public List<userDto> getAllUser();
	public userDto getUserById(int id);
	public userDto updateUser(userDto user,int id);
	public void deleteUser(int id);
	public getUserByEmailDto getUserByemail(getUserByEmailDto email);
	
}
