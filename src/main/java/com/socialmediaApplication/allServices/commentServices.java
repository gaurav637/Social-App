package com.socialmediaApplication.allServices;

import java.util.List;
import com.socialmediaApplication.Model.Comments;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.demoUser;

public interface commentServices {
	
	public Comments getCommentById(int commentId);
	public List<Comments> getAllComments();
	public Comments createComments(int userId,int postId,String content) throws Exception;
	public Comments updateComments(int commentId,int userId,int postId,String content) throws Exception;
	public ApiResponse deleteComment(int userId,int postId,int commentId);
	public ApiResponse likeComment(int userId,int commentId);
	public ApiResponse unLikeComment(int userId ,int commentId);
    public List<demoUser> allUserLikeInComments(int commentId);
   
}
