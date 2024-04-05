package com.socialmediaApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.socialmediaApplication.Model.Story;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.allServices.storyServices;

@RestController
@RequestMapping("story-api")
public class storyController {
	
	@Autowired
	private storyServices srServices;
	
	@PostMapping("create-story/user/{userId}")
	public ResponseEntity<Story> createStoryInUser(@PathVariable("userId") int userId,@RequestBody Story story){
		Story st = this.srServices.createStory(userId, story);
		return new ResponseEntity<Story>(st,HttpStatus.CREATED);
	}

	@GetMapping("get-story-by-id/user/{userId}/story/{storyId}")
	public ResponseEntity<Story> getStoryById(
			@PathVariable("userId") int userId,
			@PathVariable("storyId") int storyId)throws Exception{
		Story story = srServices.getStoryById(userId, storyId);
		return new ResponseEntity<Story>(story,HttpStatus.OK);
	}
	
	@GetMapping("get-all-story")
	public ResponseEntity<List<Story>> getAllStory(){
		List<Story> allStory = this.srServices.getAllStory();
		return new ResponseEntity<List<Story>>(allStory,HttpStatus.OK);
	}
	
	@PutMapping("update-story/user/{userId}/story/{storyId}")
	public ResponseEntity<Story> updateStoryInUser(
			@PathVariable("userId") int userId,
			@PathVariable("storyId") int storyId,
			@RequestBody Story story)throws Exception{
		Story updateStory = this.srServices.updateStory(userId, storyId, story);
		return new ResponseEntity<Story>(updateStory,HttpStatus.OK);	
	}
	
	@DeleteMapping("delete-story/user/{userId}/story/{storyId}")
	public ResponseEntity<ApiResponse> deleteStoryInUser(
			@PathVariable("userId") int userId,
			@PathVariable("storyId") int storyId){
		ApiResponse response = this.srServices.deleteStory(userId, storyId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("view-in-story/user/{userId}/story/{storyId}")
	public ResponseEntity<ApiResponse> viewInStoryInUser(
			@PathVariable("userId") int userId,
			@PathVariable("storyId") int storyId){
			ApiResponse response = this.srServices.viewInStory(userId, storyId);
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);				
	}
	
	@PostMapping("reaction-in-story/user/{userId}/story/{storyId}")
	public ResponseEntity<ApiResponse> reactionInStoryInUser(
			@PathVariable("userId") int userId,
			@PathVariable("storyId") int storyId){
			ApiResponse response = this.srServices.reactionInStory(userId, storyId);
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);				
	}
	        
	
}
