package com.socialmediaApplication.allServices;

import java.util.List;
import com.socialmediaApplication.Model.Follow;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.demoUser;
import com.socialmediaApplication.Payload.userDto;

public interface userService {
	public userDto createUser(userDto user);
	public List<userDto> getAllUser();
	public userDto getUserById(int id);
	public userDto updateUser(userDto user,int id);
	public void deleteUser(int id);	
	public String followInUser(int followUserId,int followingUserId);
	public List<demoUser> allFollowers(int userId);
	public List<demoUser> allFollowing(int userId);
	public User getUserByEmailAddress(String content);
	
}
