package com.socialmediaApplication.ServiceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.allServices.userService;

public class userServiceImple implements userService{
	
	@Autowired
	private userRepository uRepository;

	@Override
	public User createUser(User user) {
		
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int id) {
		return null;
	}

	@Override
	public User updateUser(User user, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

}
