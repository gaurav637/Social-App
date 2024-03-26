package com.socialmediaApplication.allServices;

import java.util.List;

import com.socialmediaApplication.Model.Post;

public interface postServices {
	
	public Post createPost(Post post);
	public Post getPostById(int id);
	public List<Post> getAllPosts();
	public Post updatePost(Post post,int id);
	public void likesInPost(int postId,int userId);
	public void commentInPost(int postId,int userId,String content);
	public void unLikedPost(int postId,int userId);
	public void updateCommentPost(int postId,int userId,String content);
	public void deleteCommentPost(int postId,int userId);

}
