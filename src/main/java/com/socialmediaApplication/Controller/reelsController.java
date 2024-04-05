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
import com.socialmediaApplication.Model.Reels;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.reelsDto;
import com.socialmediaApplication.allServices.reelsServices;

@RestController
@RequestMapping("reels-api")
public class reelsController {
	
	@Autowired
	private reelsServices rServices;
	
	@PostMapping("create-reel-in-user/{userId}")
	public ResponseEntity<Reels> createReelsInUser(
			@PathVariable("userId") int userId ,
			@RequestBody Reels reel){
		Reels newReel = this.rServices.createReels(userId, reel);
		return new ResponseEntity<Reels>(newReel,HttpStatus.CREATED);
	}
	
	@GetMapping("get-reel-user/{userId}/reels/{reelId}")
	public ResponseEntity<Reels> getReelsById(
			@PathVariable("userId") int userId,
			@PathVariable("reelId") int reelId)throws Exception{
		Reels rls = this.rServices.getReelsByid(userId, reelId);
		return new ResponseEntity<Reels>(rls,HttpStatus.OK);
	}
	
	@GetMapping("get-all-reels")
	public ResponseEntity<List<Reels>> getAllReelsInUser(){
		List<Reels> allReels = this.rServices.getAllReels();
		return new ResponseEntity<List<Reels>>(allReels,HttpStatus.OK);
	}
	
	@PutMapping("update-reels/{reelId}/{userId}")
	public ResponseEntity<Reels> updateReelsInUser(
			@PathVariable("reelId") int reelId,
			@PathVariable("userId") int userId,
			@RequestBody Reels reel)throws Exception{
		Reels rls = this.rServices.updateReels(reelId, userId, reel);
		return new ResponseEntity<Reels>(rls,HttpStatus.OK);
	}
	
	@DeleteMapping("delete-reels/{reelsId}/user/{userId}")
	public ResponseEntity<ApiResponse> deleteReelsInUser(
			@PathVariable("reelsId") int reelId,
			@PathVariable("userId") int userId){
		ApiResponse response = this.rServices.deleteReels(reelId, userId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("like-in-reels/user/{userId}/reels/{reelsId}")
	public ResponseEntity<ApiResponse> lieksInReels(
			@PathVariable("userId") int userId,
			@PathVariable("reelsId") int reelsId){
		ApiResponse response = this.rServices.likeInReels(userId, reelsId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("unlike-in-reels/user/{userId}/reels/{reelsId}")
	public ResponseEntity<ApiResponse> unlieksInReels(
			@PathVariable("userId") int userId,
			@PathVariable("reelsId") int reelsId){
		ApiResponse response = this.rServices.unlikeReels(userId, reelsId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("comment-in-likes/user/{userId}/reels/{reelsId}")
	public ResponseEntity<reelsDto> commentInReels(
			@PathVariable("userId") int userId,
			@PathVariable("reelsId") int reelId,
			@RequestParam(value="content") String content){
		
		reelsDto rd = this.rServices.commentInReels(userId, reelId, content);
		return new ResponseEntity<reelsDto>(rd,HttpStatus.OK);
		
	}
	
	@PostMapping("dislike-in-reels/user/{userId}/reels/{reelsId}")
	public ResponseEntity<ApiResponse> disLikeInReels(
			@PathVariable("userId") int userId,
			@PathVariable("reelsId") int reelsId){
		ApiResponse response = this.rServices.deslikeInReels(userId, reelsId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
}
