package com.socialmediaApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.socialmediaApplication.Model.Post;
import com.socialmediaApplication.allServices.postServices;

@RestController
public class postController {
	
	@Autowired
	private postServices pServices;
	
	public ResponseEntity<Post> createPostInController(@RequestBody Post post){
		
		return null;
	}
}
