package com.socialmediaApplication.allServices;

import java.util.List;

import com.socialmediaApplication.Model.Reels;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.reelsDto;

public interface reelsServices {
   
	public Reels createReels(int userId,Reels reel);
	public Reels getReelsByid(int userId,int reelId)throws Exception;
	public List<Reels> getAllReels();
	public Reels updateReels(int reelsId,int userId,Reels newReels)throws Exception;
	public ApiResponse deleteReels(int reelsId,int userId);
	public ApiResponse likeInReels(int userId ,int reelsId);
	public ApiResponse unlikeReels(int userId,int reelsId);
	public reelsDto commentInReels(int userId,int reelsId,String content);
	public ApiResponse deslikeInReels(int userId,int reelId);
	
}
