package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmediaApplication.Model.Comments;

public interface commentRepository extends JpaRepository<Comments, Integer>{
	
}
