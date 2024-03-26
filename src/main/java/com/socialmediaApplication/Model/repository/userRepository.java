package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Payload.getUserByEmailDto;

public interface userRepository extends JpaRepository<User,Integer> {

	public getUserByEmailDto findUserByEmail(getUserByEmailDto email);
}
 