package com.socialmediaApplication.allServices;

import java.util.List;
import com.socialmediaApplication.Model.User;

public interface userService {
	public User createUser(User user);
	public List<User> getAllUser();
	public User getUserById(int id);
	public User updateUser(User user,int id);
	public void deleteUser(int id);
}
