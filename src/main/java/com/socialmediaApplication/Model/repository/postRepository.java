package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmediaApplication.Model.Post;

public interface postRepository extends JpaRepository<Post, Integer> {

}
