package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialmediaApplication.Model.User;

public interface userRepository extends JpaRepository<User,Integer> {
	
	@Query(value = "SELECT * FROM user WHERE email=:content",nativeQuery = true)
	public User getUserByEmail(String content);

}	
 