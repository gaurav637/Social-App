package com.socialmediaApplication.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.Comments;
import com.socialmediaApplication.Model.Post;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.demoUser;
import com.socialmediaApplication.Model.repository.commentRepository;
import com.socialmediaApplication.Model.repository.postRepository;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.allServices.commentServices;


@Service
public class commentsServiceImple implements commentServices {
	
	
	@Autowired
	private commentRepository cRepository;
	
	@Autowired
	private userRepository uRepository;
	
	@Autowired
	private postRepository pRepository;
	
	@Autowired
	private Comments comments;

	@Override
	public Comments getCommentById(int commentId) {
		Comments comment = cRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments","Id",commentId));
		return comment;
	}

	@Override
	public List<Comments> getAllComments() {
		List<Comments> allComments = cRepository.findAll();
		return allComments;
	}

	@Override
	public Comments createComments(int userId, int postId, String content) throws Exception {
		User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		int n = user.getUserId();
		int m = post.getUser().getUserId();
		if(n==m) {
			 post.setCommentCounter(post.getCommentCounter()+1);
			 post.setContent(content);
			 comments.setAuthor(user.getUserName());
			 comments.setUserId(user.getUserId());
			 comments.setPost(post.getContent());
			 comments.setPostId(post.getPostId());
			 comments.setTimestamp(LocalDateTime.now());
			 cRepository.save(comments);
			 return comments;
		}
	   
		throw new Exception(String.format("post - postId : %d , and user : %s or , userId : %d not create this post"+ post.getPostId(),user.getUserName(),user.getUserId()));
	}

	@Override
	public Comments updateComments(int commentId, int userId, int postId, String content)throws Exception {
		User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		Comments comm = cRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments","CommentId",commentId));
		int n = user.getUserId();
		int m = post.getUser().getUserId();
		if(n==m) {
			comm.setContent(content);
			comm.setUpdateDate(LocalDateTime.now());
			cRepository.save(comm);
			return comm;
		}
		
		throw new Exception(String.format("post - postId : %d , and user : %s or , userId : %d not create this post"+ post.getPostId(),user.getUserName(),user.getUserId()));
	}

	@Override
	public void deleteComment(int userId, int postId, int commentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void likeComment(int userId, int commentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unLikeComment(int userId, int commentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<demoUser> allUserLikeInComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pinComments() {
		// TODO Auto-generated method stub
		
	}

}
