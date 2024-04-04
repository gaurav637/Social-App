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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.socialmediaApplication.Model.Comments;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.allServices.commentServices;

@RestController
@RequestMapping("comment-api")
public class commentsController {
	
	@Autowired
	private commentServices cServices;
	
	
	@PostMapping("post/user/{userId}/post/{postId}")
	public ResponseEntity<Comments> putCommentInPost(
			@PathVariable("userId") int userId, 
			@PathVariable("postId") int postId,
			@RequestParam(value="content") String content)throws Exception{
		Comments comm = this.cServices.createComments(userId, postId, content);
		return new ResponseEntity<Comments>(comm,HttpStatus.CREATED);
	}
	
	@GetMapping("get-by-id/{commentId}")
	public ResponseEntity<Comments> getCommentsById(@PathVariable("commentId") int commentId ){
		Comments comm = this.cServices.getCommentById(commentId);
		return new ResponseEntity<Comments>(comm,HttpStatus.OK);
	}
	
	@GetMapping("get-all-comments")
	public ResponseEntity<List<Comments>> getAllComments(){
		List<Comments> allComments = this.cServices.getAllComments();
		return new ResponseEntity<List<Comments>>(allComments,HttpStatus.OK);
	}
	
	@PutMapping("update-post-comments/{commentId}/user/{userId}/post/{postId}")
	public ResponseEntity<Comments> updateComments(
			@PathVariable("commentId") int commentId,
			@PathVariable("userId") int userId, 
			@PathVariable("postId") int postId, 
			@RequestParam(value = "content") String content)throws Exception{
		Comments comments = this.cServices.updateComments(commentId, userId, postId, content);
		return new ResponseEntity<Comments>(comments,HttpStatus.OK);
	}
	
	@DeleteMapping("delete-comment/user/{userId}/post/{postId}/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteCommentsInPost(
			@PathVariable("userId") int userId,
			@PathVariable("postId") int postId,
			@PathVariable("commentId") int commentId){
		ApiResponse response = this.cServices.deleteComment(userId, postId, commentId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes-in-comments/user/{userId}/comment/{commentId}")
	public ResponseEntity<ApiResponse> likesInComments(
			@PathVariable("userId") int userId,
			@PathVariable("commentId") int commentId){
		ApiResponse response = this.cServices.likeComment(userId, commentId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);				
	}
	
	@PostMapping("unlikes-in-comments/user/{userId}/comment/{commentId}")
	public ResponseEntity<ApiResponse> unlikesInComments(
			@PathVariable("userId") int userId,
			@PathVariable("commentId") int commentId){
		ApiResponse response = this.cServices.unLikeComment(userId, commentId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);				
	}
	
	

}
