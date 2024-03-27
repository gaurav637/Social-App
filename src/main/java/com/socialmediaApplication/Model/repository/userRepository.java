package com.socialmediaApplication.Model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Payload.userDto;

public interface userRepository extends JpaRepository<User,Integer> {
	
	@Query(value="SELECT all_followers_user_id FROM  user_all_followers WHERE user_user_id=userId",nativeQuery = true)
	public List<userDto> getAllFollowersInUser(int userId);
	
	@Query(value="SELECT all_followings_user_id FROM  user_all_followings WHERE user_user_id=userId",nativeQuery = true)
	public List<userDto> getAllFollowingInUser(int userId);
	

}	
 