package com.socialmediaApplication.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.Reels;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.repository.reelsRepository;
import com.socialmediaApplication.Model.repository.userDemoRepo;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.demoUser;
import com.socialmediaApplication.Payload.reelsDto;
import com.socialmediaApplication.allServices.reelsServices;


@Service
public class reelsServiceImple implements reelsServices{
	
	@Autowired
	private reelsRepository eRepository;
	
	@Autowired
	private userRepository uRepository;
	
	@Autowired
	private userDemoRepo ud;

	@Override
	public Reels createReels(int userId,Reels reels) {
		User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		Reels reel1 = new Reels();
		reel1.setViewsCounter(reels.getViewsCounter());
		reel1.setUsername(user.getUserName());
		reel1.setUserId(user.getUserId());
		reel1.setTitle(reels.getTitle());
		reel1.setTimestamp(LocalDateTime.now());
		reel1.setReelsContent(reels.getReelsContent());
		reel1.setLocation(reels.getLocation());
		reel1.setLikesCounter(reels.getLikesCounter());
		reel1.setDescription(reels.getDescription());
		reel1.setCommentCounter(reels.getCommentCounter());
		reel1.setDislike(reels.getDislike());
		reel1.setViewsCounter(reels.getViewsCounter());
		Reels newReels = eRepository.save(reel1);
		return newReels;
	}

	@Override
	public Reels getReelsByid(int userId,int reelId)throws Exception {
		Reels reel = eRepository.findById(reelId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelId));
		int u = reel.getUserId();
		
		if(u==userId) {
			return reel;
		}
		throw new Exception(String.format("Reels not found with userId : %d , and reels Id : %d ",userId,reelId));
	}

	@Override
	public List<Reels> getAllReels() {
		List<Reels> allList  = eRepository.findAll();
		return allList;
	}

	@Override
	public Reels updateReels(int reelsId, int userId, Reels newReels)throws Exception {
		Reels reel = eRepository.findById(reelsId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelsId));
        int u = reel.getUserId();
        if(u==userId) {
    		reel.setTitle(newReels.getTitle());
    		reel.setReelsContent(newReels.getReelsContent());
    		reel.setLocation(newReels.getLocation());
    		reel.setDescription(newReels.getDescription());
    		eRepository.save(reel);
            return reel;
        }
		throw new Exception(String.format("Reels not found with userId : %d , and reels Id : %d ",userId,reelsId));
	}

	@Override
	public ApiResponse deleteReels(int reelsId, int userId) {
		Reels reel = eRepository.findById(reelsId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelsId));
		int u = reel.getUserId();
		if(u==userId) {
			eRepository.delete(reel);
			return new ApiResponse("Reels is deleted successfully.",true) ;
		}
		else {
			return new ApiResponse("Reels not deleted. because reels not found  with userId",true);
		}
	}

	@Override
	public ApiResponse likeInReels(int userId, int reelsId) {
		demoUser user = ud.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
		Reels reel = eRepository.findById(reelsId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelsId));
		if(reel.getLikes().contains(user)) {
			reel.getLikes().remove(user);
			reel.setLikesCounter(reel.getLikesCounter()-1);
			eRepository.save(reel);
			return new ApiResponse("unlike post because you already like in post",true);
		}
		else {
			reel.setLikesCounter(reel.getLikesCounter()+1);
			reel.getLikes().add(user);
			eRepository.save(reel);
			return new ApiResponse("like  post",true);
		}	
	}

	@Override
	public ApiResponse unlikeReels(int userId, int reelsId) {
		demoUser user = ud.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
		Reels reel = eRepository.findById(reelsId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelsId));
		if(reel.getLikes().contains(user)) {
			reel.getLikes().remove(user);
			reel.setLikesCounter(reel.getLikesCounter()-1);
			eRepository.save(reel);
			return new ApiResponse("unlike post",true);
		}
		else {
			reel.setLikesCounter(reel.getLikesCounter()+1);
			reel.getLikes().add(user);
			eRepository.save(reel);
			return new ApiResponse("like  post because you are not liked in post",true);
		}	
		
	}

	@Override
	public reelsDto commentInReels(int userId, int reelsId,String content) {
		reelsDto rd = new reelsDto();
		Reels reel = eRepository.findById(reelsId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelsId));
		demoUser user = ud.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
		reel.setCommentCounter(reel.getCommentCounter()+1);
		rd.setComment(content);
		rd.setUserName(user.getUserName()); 
		eRepository.save(reel);
		return rd;
	}

	@Override
	public ApiResponse deslikeInReels(int userId, int reelId) {
		demoUser user = ud.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
		Reels reel = eRepository.findById(reelId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelId));
		if(reel.getLikes().contains(user)) {
			reel.getLikes().remove(user);
			reel.setLikesCounter(reel.getLikesCounter()-1);
			reel.setDislike(reel.getDislike()+1);
			reel.getDislikes().add(user);		
			eRepository.save(reel);
			return new ApiResponse("Reels Dislike",true);
		}
		else if(reel.getDislikes().contains(user)) {
			reel.setDislike(reel.getDislike()-1);
			reel.getDislikes().remove(user);
			eRepository.save(reel);
			return new ApiResponse("Reels unDislike",true);
		}
		else {
			reel.setDislike(reel.getDislike()+1);
			reel.getDislikes().add(user);		
			eRepository.save(reel);
			return new ApiResponse("Reels Dislike",true);
		}
	}

	@Override
	public ApiResponse viewInReels(int userId, int reelId) {
		demoUser user = ud.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
		Reels reel = eRepository.findById(reelId).orElseThrow(()-> new ResourceNotFoundException("Reels","ReelsId",reelId));
		if(!reel.getViewReels().contains(user)) {
			reel.setViewsCounter(reel.getViewsCounter()+1);
			return new ApiResponse("user views reels",true);
		}
		return new ApiResponse("user views reels",true);
	}
	
	

}
