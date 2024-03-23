package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmediaApplication.Model.User;

public interface userRepository extends JpaRepository<User,Integer> {

}
