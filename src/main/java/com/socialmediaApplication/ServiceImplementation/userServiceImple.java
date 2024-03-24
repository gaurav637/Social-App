package com.socialmediaApplication.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.Payload.getUserByEmailDto;
import com.socialmediaApplication.Payload.userDto;
import com.socialmediaApplication.allServices.userService;

@Service
public class userServiceImple implements userService{
	
	@Autowired
	private  userRepository uRepository;
	
	@Autowired
	private  ModelMapper modelMap;

	@Override
	public userDto createUser(userDto userdto) {
	    User user1 = DtoToUser(userdto);
	    User user2 = uRepository.save(user1);
		return userToDto(user2);
	}

	@Override
	public List<userDto> getAllUser() {
		List<User> users = this.uRepository.findAll();
		List<userDto> userdto = users.stream().map(eachUser -> userToDto(eachUser)).collect(Collectors.toList());
		return userdto;
	}

	@Override
	public userDto getUserById(int id) {
		User user = uRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		return userToDto(user);
	}

	@Override
	public userDto updateUser(userDto user, int id) {
		User upUser = uRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        upUser.setUserName(user.getUserName());
       // upUser.setUserId(user.getUserId());
        upUser.setProfilePicture(user.getProfilePicture());
        upUser.setPassword(user.getPassword());
        upUser.setLocation(user.getLocation());
        upUser.setGender(user.getGender());
        upUser.setFull_name(user.getFull_name());
        upUser.setFollowing(user.getFollowing());
        upUser.setFollowers(user.getFollowers());
        upUser.setEmail(user.getEmail());
        upUser.setDob(user.getDob());
        upUser.setContact(user.getContact());
        upUser.setBio(user.getBio());
        User user2 = uRepository.save(upUser);
        
		return userToDto(user2);
	}

	@Override
	public void deleteUser(int id) {
		User user = uRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		uRepository.delete(user);
		
	}

	public User DtoToUser(userDto udto) {
		User user = this.modelMap.map(udto,User.class);
		return user;	
	}
	public userDto userToDto(User user) {
		userDto userdto = this.modelMap.map(user, userDto.class);
		return userdto;
	}

	@Override
	public getUserByEmailDto getUserByemail(getUserByEmailDto email) {
		getUserByEmailDto user = uRepository.findUserByEmail(email);
		return user;
	}

	
}
