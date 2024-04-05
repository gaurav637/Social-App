package com.socialmediaApplication.allServices;

import java.util.List;
import com.socialmediaApplication.Model.Story;
import com.socialmediaApplication.Payload.ApiResponse;

public interface storyServices {
	
	public Story createStory(int userId,Story story);
	public Story getStoryById(int userId,int storyId)throws Exception;
	public List<Story> getAllStory();
	public Story updateStory(int userId,int storyId,Story newStory)throws Exception;
	public ApiResponse deleteStory(int userId,int storyId);
	public ApiResponse viewInStory(int userId,int storyId);
	public ApiResponse reactionInStory(int userId,int storyId);

}
