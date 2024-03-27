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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.socialmediaApplication.Model.Post;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.allServices.postServices;

@RestController
@RequestMapping("post")
public class postController {
	
	@Autowired
	private postServices pServices;
	
	@PostMapping("create-new-post")
	public ResponseEntity<Post> createPostInController(@RequestBody Post post){
		Post newPost = this.pServices.createPost(post);	
		return new ResponseEntity<Post>(newPost,HttpStatus.CREATED);
	}
	
	@GetMapping("get-post-by-id/{postId}")
	public ResponseEntity<Post> getPostByIdInController(@PathVariable("postId") int id){
		Post post = this.pServices.getPostById(id);
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
	
	@GetMapping("get-all-posts")
	public ResponseEntity<List<Post>> getAllPostInController(){
		List<Post> allPosts = pServices.getAllPosts();
		return new ResponseEntity<List<Post>>(allPosts,HttpStatus.OK);
	}
	
	@PutMapping("update-post/{postId}/{userId}")
	public ResponseEntity<Post> updatePostInController(@RequestBody Post post,@PathVariable("postId") int postId,@PathVariable("userId") int userId){
		Post uPost = this.pServices.updatePost(post, postId, userId);
		return new ResponseEntity<Post>(uPost,HttpStatus.OK);
	}
	
	@DeleteMapping("delete-post/{postId}/{userId}")
	public ApiResponse deletePostInController(@PathVariable("postId") int postId,@PathVariable("userId") int userId) {
		this.pServices.deletePost(postId, userId);
		return new ApiResponse("post delete successfull",true);
	}
	
	@PostMapping("like-post/{postId}/{userId}")
	public ApiResponse likesPostInController(@PathVariable("postId") int postId,@PathVariable("userId") int userId) {
		this.pServices.likesInPost(postId, userId);
		return new ApiResponse(String.format("like is post.. with user Id' : %d and post Id' : %d"+userId,postId),true);
	}
	
	@PostMapping("comment-post/{postId}/{userId}")
	public ApiResponse commentsInPostInController(@PathVariable("postId") int postId,@PathVariable("userId") int userId,@RequestParam(value="content") String content){
		this.pServices.commentInPost(postId, userId, content);
		return new ApiResponse(String.format("comment in post : %s userID : %d postId : %d"+content,userId,postId),true);
	}
	
	@PostMapping("unlike-in-post")
	public ApiResponse unlikeInPostInController(@PathVariable("postId") int postId,@PathVariable("userId") int userId) {
		this.pServices.unLikedPost(postId, userId);
		return new ApiResponse(String.format("unlike in post..  with user Id' : %d and post Id' : %d"+userId,postId),true);
	}
	
	@PostMapping("update-in-post")
	public ApiResponse updateConmmentInController(@PathVariable("postId") int postId,@PathVariable("userId") int userId,@RequestParam(value="content") String content){
		this.pServices.updateCommentPost(postId, userId, content);
	    return new ApiResponse(String.format("updated in post comment. new comment : %s and user Id' : %d , post Id' : %d",content,userId,postId),true);
	}
	
	@PostMapping("delete-in-comment-post")
	public ApiResponse deleteCommentInController(@PathVariable("postId") int postId,@PathVariable("userId") int userId) {
		this.pServices.deleteCommentPost(postId, userId);
		return new ApiResponse(String.format("delete in post comment. with user Id' : %d and post Id' : %d"+userId,postId),true);
	}
}
























