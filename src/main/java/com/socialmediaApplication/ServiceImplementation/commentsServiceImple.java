package com.socialmediaApplication.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.Comments;
import com.socialmediaApplication.Model.Post;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.repository.commentRepository;
import com.socialmediaApplication.Model.repository.postRepository;
import com.socialmediaApplication.Model.repository.userDemoRepo;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.demoUser;
import com.socialmediaApplication.allServices.commentServices;


@Service
public class commentsServiceImple implements commentServices {
	
	
	@Autowired
	private commentRepository cRepository;
	
	@Autowired
	private userRepository uRepository;
	
	@Autowired
	private postRepository pRepository;
	
	
	private Comments comments = new Comments();
	
	@Autowired
	private userDemoRepo dUser;

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
			 Comments comm = cRepository.save(comments);
			 return comm;
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
			Comments updatedComments =  cRepository.save(comm);
			return updatedComments ;
		}
		
		throw new Exception(String.format("post - postId : %d , and user : %s or , userId : %d not create this post"+ post.getPostId(),user.getUserName(),user.getUserId()));
	}

	@Override
	public ApiResponse deleteComment(int userId, int postId, int commentId) {
		User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		Comments comm = cRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments","CommentId",commentId));
		
		int a1 = user.getUserId();
		int b1 = post.getUser().getUserId();
		
		if(a1==b1) {
			cRepository.delete(comm);
		}
		return new ApiResponse(String.format("post - postId : %d , and user : %s or , userId : %d not create this post"+ post.getPostId(),user.getUserName(),user.getUserId()),false);
	}

	@Override
	public ApiResponse likeComment(int userId, int commentId) {
		demoUser user = dUser.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","Id",userId));
		Comments comm = cRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments","CommentId",commentId));
		if(!comm.getAllLikesUser().contains(user)) {
			comm.setLikesCounter(comm.getLikesCounter()+1);
			comm.getAllLikesUser().add(user);
			cRepository.save(comm);
			return new ApiResponse("likes comment",true);

		}
		comm.setLikesCounter(comm.getLikesCounter()-1);
		comm.getAllLikesUser().remove(comm);
		cRepository.save(comm);
        return 	new ApiResponse("unlike comment because user already like comment",false);
		
	}

	@Override
	public ApiResponse unLikeComment(int userId, int commentId) {
		demoUser user = dUser.findById(userId).orElseThrow(()-> new ResourceNotFoundException("demoUser","Id",userId));
		Comments comm = cRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments","CommentId",commentId));
		if(!comm.getAllLikesUser().contains(user)) {
			comm.setLikesCounter(comm.getLikesCounter()+1);
			comm.getAllLikesUser().add(user);
			cRepository.save(comm);
			return new ApiResponse("likes comment because user not like this comments",false);

		}
		comm.setLikesCounter(comm.getLikesCounter()-1);
		comm.getAllLikesUser().remove(comm);
		cRepository.save(comm);
        return 	new ApiResponse("unlike comment",true);
		
	}

	@Override
	public List<demoUser> allUserLikeInComments(int commentId) {
		//List<demoUser> allUser = cRepository.
		return null;
	}


}
