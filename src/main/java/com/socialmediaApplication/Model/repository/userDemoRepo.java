package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmediaApplication.Payload.demoUser;

public interface userDemoRepo extends JpaRepository<demoUser, Integer> {

}
