 package com.socialmediaApplication.ServiceImplementation;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.Follow;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.demoUser;
import com.socialmediaApplication.Model.repository.followRepository;
import com.socialmediaApplication.Model.repository.userDemoRepo;
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

	@Autowired
	private followRepository followRepo;
	
	@Autowired
	private userDemoRepo udRepo;
	
	demoUser dUser = new demoUser();
	
	
	Follow follow = new Follow();
	
	
	
	@Override
	public userDto createUser(userDto userdto) {
	    User user1 = DtoToUser(userdto);
	    follow.setUserId(user1.getUserId());
	    follow.setUserName(user1.getUserName());
	    follow.setProfilePicture(user1.getProfilePicture());
	    follow.setFull_name(user1.getFull_name());
	    follow.setFollowing(user1.getFollowing());
	    follow.setFollowers(user1.getFollowers());
	    follow.setContact(user1.getContact());
	    follow.setBio(user1.getBio());
	    
	    dUser.setUserId(user1.getUserId());
	    dUser.setUserName(user1.getUserName());
	    dUser.setProfilePicture(user1.getProfilePicture());
	    dUser.setFull_name(user1.getFull_name());
	    dUser.setFollowing(user1.getFollowing());
	    dUser.setFollowers(user1.getFollowers());
	    dUser.setContact(user1.getContact());
	    dUser.setBio(user1.getBio());
	    
	    udRepo.save(dUser);
	    User user2 = uRepository.save(user1);
	    followRepo.save(follow);
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
        upUser.setProfilePicture(user.getProfilePicture());
        upUser.setPassword(user.getPassword());
        upUser.setLocation(user.getLocation());
        upUser.setGender(user.getGender());
        upUser.setFull_name(user.getFull_name());
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
		Follow user1 = followRepo.findById(followingUserId).orElseThrow(()-> new ResourceNotFoundException("Follow","id",followingUserId));
		Follow user2 = followRepo.findById(followUserId).orElseThrow(()-> new ResourceNotFoundException("Follow","Id",followUserId));
		User user3 = uRepository.findById(followingUserId).orElseThrow(()-> new ResourceNotFoundException("User","id",followingUserId));
		User user4 = uRepository.findById(followUserId).orElseThrow(()-> new ResourceNotFoundException("User","Id",followUserId));	
		
		demoUser user5 = udRepo.findById(followingUserId).orElseThrow(()-> new ResourceNotFoundException("User","id",followingUserId));
		demoUser user6 = udRepo.findById(followUserId).orElseThrow(()-> new ResourceNotFoundException("User","Id",followUserId));	
		
		if(user2.getAllFollowers().contains(user1)) {
			user2.getAllFollowers().remove(user1);
			user2.setFollowers(user2.getFollowers()-1);
			user1.setFollowing(user1.getFollowing()-1);
			user4.setFollowers(user4.getFollowers()-1);
			user3.setFollowing(user3.getFollowing()-1);
			user6.setFollowers(user6.getFollowers()-1);
			user5.setFollowing(user5.getFollowing()-1);
			followRepo.save(user1);
			followRepo.save(user2);
			uRepository.save(user3);
			uRepository.save(user4);
			udRepo.save(user5);
			udRepo.save(user6);
			
			return String.format("user : %s-%d unfollow this user : %s-%d", user1.getFull_name(),user1.getUserId(), user2.getFull_name(),user2.getUserId());
		}
		else {
			user2.setFollowers(user2.getFollowers()+1);
			user1.setFollowing(user1.getFollowing()+1);
			user4.setFollowers(user4.getFollowers()+1);
			user3.setFollowing(user3.getFollowing()+1);	
			user6.setFollowers(user6.getFollowers()+1);
			user5.setFollowing(user5.getFollowing()+1);
			user2.getAllFollowers().add(user5);// user3 
			user1.getAllFollowings().add(user6);
			followRepo.save(user1);
			followRepo.save(user2);
			uRepository.save(user3);
			uRepository.save(user4);
			return String.format("user : %s follow this user : %s ",user1.getFull_name(),user2.getFull_name());
		}
		
	}

	@Override
	public List<demoUser> allFollowers(int userId) {
		List<demoUser> allfollowers = followRepo.getAllFollowersInUser(userId);
		return allfollowers;
	}

	@Override
	public List<demoUser> allFollowing(int userId) {
		List<demoUser> allfollowings = followRepo.getAllFollowingInUser(userId);
		return allfollowings;
	}

	@Override
	public User getUserByEmailAddress(String content) {
		User user = uRepository.getUserByEmail(content);
		return user;
	}

}
