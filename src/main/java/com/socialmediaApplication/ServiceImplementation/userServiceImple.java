package com.socialmediaApplication.ServiceImplementation;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.Payload.userDto;
import com.socialmediaApplication.allServices.userService;

@Service
public class userServiceImple implements userService{
	
	private static final Logger logger = Logger.getLogger(userServiceImple.class.getName());
	
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
        //upUser.setFollowing(user.getFollowing());
       // upUser.setFollowers(user.getFollowers());
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
	public String followInUser(int followUserId, int followingUserId) {
		User user1 = uRepository.findById(followingUserId).orElseThrow(()-> new ResourceNotFoundException("User","id",followingUserId));
		User user2 = uRepository.findById(followUserId).orElseThrow(()-> new ResourceNotFoundException("User","Id",followUserId));
		if(user2.getAllFollowers().contains(user1)) {
			user2.getAllFollowers().remove(user1);
			user2.setFollowers(user2.getFollowers()-1);
			user1.setFollowing(user1.getFollowing()-1);
			uRepository.save(user2);
			uRepository.save(user1);
			return String.format("user : %s unfollow this user : %s", user1.getFull_name(), user2.getFull_name());

		}
		else {
			user2.setFollowers(user2.getFollowers()+1);
			user1.setFollowing(user1.getFollowing()+1);
			user2.getAllFollowers().add(user1);
			user1.getAllFollowings().add(user2);
			uRepository.save(user2);
			return String.format("user : %s follow this user : %s ",user1.getFull_name(),user2.getFull_name());
		}
		
	}

	@Override
	public List<userDto> allFollowers(int userId) {
		List<userDto> allfollowers = uRepository.getAllFollowersInUser(userId);
		return allfollowers;
	}

	@Override
	public List<userDto> allFollowing(int userId) {
		List<userDto> allfollowings = uRepository.getAllFollowingInUser(userId);
		return allfollowings;
	}

	
}
