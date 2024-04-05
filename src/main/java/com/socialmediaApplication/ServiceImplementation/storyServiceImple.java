package com.socialmediaApplication.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.Story;
import com.socialmediaApplication.Model.repository.storyRepository;
import com.socialmediaApplication.Model.repository.userDemoRepo;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.demoUser;
import com.socialmediaApplication.allServices.storyServices;

    @Service
    public class storyServiceImple implements storyServices{
	
	@Autowired
	private userDemoRepo dm;
	
	@Autowired
	private storyRepository sRepository;

	@Override
	public Story createStory(int userId, Story story) {
		demoUser user = dm.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
		Story st = new Story();
		st.setViews(story.getViews());
		st.setViewCounter(story.getViewCounter());
		st.setUserId(user.getUserId());
		st.setTimestamp(LocalDateTime.now());
		st.setStoryId(story.getStoryId());
		st.setStoryContent(story.getStoryContent());
		st.setReactionCounter(story.getReactionCounter());
		st.setReaction(story.getReaction());
		Story newStory = sRepository.save(st);
		return newStory;
	}

	@Override
	public Story getStoryById(int userId, int storyId)throws Exception {
		demoUser user = dm.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
        Story story = sRepository.findById(storyId).orElseThrow(()-> new ResourceNotFoundException("Story","storyId",storyId));
		if(story.getUserId()==user.getUserId()) {
			return story;
		} 
        throw new Exception("user story not found with userId");		
	}

	@Override
	public List<Story> getAllStory() {
		List<Story> allStory = sRepository.findAll();
		return allStory;
	}

	@Override
	public Story updateStory(int userId, int storyId, Story newStory)throws Exception {
		demoUser user = dm.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
        Story story = sRepository.findById(storyId).orElseThrow(()-> new ResourceNotFoundException("Story","storyId",storyId));
		if(user.getUserId()==story.getUserId()) {
			story.setViewCounter(newStory.getViewCounter());
			story.setUserId(userId);
			story.setTimestamp(LocalDateTime.now());
			story.setStoryId(storyId);
			story.setStoryContent(newStory.getStoryContent());
			story.setReactionCounter(newStory.getReactionCounter());
			Story st1 = sRepository.save(story);
			return st1;
		}
        
       throw new Exception("user story not found with userId");
	}

	@Override
	public ApiResponse deleteStory(int userId, int storyId) {
		demoUser user = dm.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
        Story story = sRepository.findById(storyId).orElseThrow(()-> new ResourceNotFoundException("Story","storyId",storyId));
		if(user.getUserId()==story.getUserId()) {
			sRepository.delete(story);
			return new ApiResponse("story deleted successfully",true);
		}
        
        return new ApiResponse("story not found with userId",false);
	}

	@Override
	public ApiResponse viewInStory(int userId, int storyId) {
		demoUser user = dm.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
        Story story = sRepository.findById(storyId).orElseThrow(()-> new ResourceNotFoundException("Story","storyId",storyId));
		if(!story.getViews().contains(user)) {
			story.setViewCounter(story.getViewCounter()+1);
			story.getViews().add(user);
			return new ApiResponse("user views story",true);
		}
        return new ApiResponse("user views story",true);
	}

	@Override
	public ApiResponse reactionInStory(int userId, int storyId) {
		demoUser user = dm.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","userId",userId));
        Story story = sRepository.findById(storyId).orElseThrow(()-> new ResourceNotFoundException("Story","storyId",storyId));
		if(!story.getReaction().contains(user)) {
			story.setReactionCounter(story.getReactionCounter()+1);
			story.getReaction().add(user);
			return new ApiResponse("user reaction",true);
		}
		return new ApiResponse("user reaction",true);  
	}

}
