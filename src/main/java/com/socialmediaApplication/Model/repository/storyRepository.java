package com.socialmediaApplication.Model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmediaApplication.Model.Story;

public interface storyRepository extends JpaRepository<Story, Integer>{

}
