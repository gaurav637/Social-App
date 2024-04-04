package com.socialmediaApplication.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmediaApplication.Exception.ResourceNotFoundException;
import com.socialmediaApplication.Model.Post;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Model.repository.postRepository;
import com.socialmediaApplication.Model.repository.userRepository;
import com.socialmediaApplication.allServices.postServices;

@Service
public class postServiceImple implements postServices {

	
    private static final Logger logger = Logger.getLogger(postServiceImple.class.getName());

    @Autowired
    private postRepository pRepository;
    
    @Autowired
    private userRepository uRepository;

    @Override
    public Post createPost(Post post) {
        try {
        	
            Post newPost = pRepository.save(post);
            newPost.setTimestamp(LocalDateTime.now());
            Post nPost =  pRepository.save(newPost);
            logger.info("Post Created Successfully with Id : " + newPost.getPostId());
            return nPost;
        } catch (Exception e) {
            logger.severe("Failed to create post: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Post getPostById(int id) {
    	Post post = pRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Id",id));
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
    	try {
    		List<Post> allPosts = pRepository.findAll();
    		logger.info("Successfully get all post ");
        	return allPosts;
    	}catch(Exception e) {
    		logger.severe("Failed to get all post");
    		throw e;
    	}
     }

    @Override
    public Post updatePost(Post post, int postId,int userId) {
        try {
        	Post updatedPost = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
            	updatedPost.setUser(post.getUser());
                updatedPost.setTimestamp(post.getTimestamp());
                updatedPost.setLocation(post.getLocation());
                updatedPost.setContent(post.getContent());
                Post post2 = pRepository.save(updatedPost);
                logger.info(" post Updated Successfully.. with post Id' : "+postId);
                return post2;
            
        }catch(Exception e) {
        	logger.severe("post failed to updated ! with post Id' :"+postId);
        	throw e;
        }
    }

    @Override
    public void likesInPost(int postId, int userId) {
    	Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
    	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	if(post.getLikes().contains(user)) {
    		post.getLikes().remove(user);
    		post.setLikesCounter(post.getLikesCounter()-1);
    		pRepository.save(post);
    		
    	}
    	else {
    		post.getLikes().add(user);
    		post.setLikesCounter(post.getLikesCounter()+1);
    		pRepository.save(post);
    	}
    }

    @Override
    public void commentInPost(int postId, int userId,String content) {
    	Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
    	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	post.setUser(user);
    	post.getComments().add(user);
    	//post.setContent(content);
    	post.setCommentCounter(post.getCommentCounter()+1);
    	pRepository.save(post);

    }

    @Override
    public void unLikedPost(int postId, int userId) {
    	Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
    	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	if(post.getLikes().contains(user)) {
    		post.getLikes().remove(user);
    		post.setLikesCounter(post.getLikesCounter()-1);
    		pRepository.save(post);
    		
    	}
    	else {
    		post.getLikes().add(user);
    		post.setLikesCounter(post.getLikesCounter()+1);
    		pRepository.save(post);
    	}
    }

    @Override
    public void updateCommentPost(int postId, int userId,String content) {
    	Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
    	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	try {
    		if(post.getUser()==user) {
        		//post.setContent(content);
    			post.getComments().add(user);
            	pRepository.save(post);
            	logger.info(String.format("post updated.. successfully. with userId : %d and userName : %s and postId : %d", user.getUserId(), user.getUserName(), post.getPostId()));

        	}
    	}catch(Exception e) {
    		logger.severe("post updation is failed!");
    	}
    	
    	
    }
    
    public void deletePost(int postId,int userId) {
    	Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
    	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	if(post.getUser()==user) {
    		pRepository.delete(post);
    		logger.info(String.format("post is deleted successfully. with userId : %d and userName : %s and postId : %d", user.getUserId(), user.getUserName(), post.getPostId()));

    	}
    	else {
    		logger.info("post deletion failed.!");
    	}
    }

    @Override
    public void deleteCommentPost(int postId, int userId) {
    	Post post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
    	User user = uRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	try {
    		  if(post.getUser()==user) {
    			    post.getComments().remove(post.getComments());
    			    post.setCommentCounter(post.getCommentCounter()-1);
    			    pRepository.save(post);
    			    logger.info(String.format("post comment deleted.. successfully. with userId : %d and userName : %s and postId : %d ",user.getUserId(),user.getUserName(),post.getPostId()));
    		  }
    	}catch(Exception e) {
    		logger.info("post comment deletion unsuccessfull. !");
    	}
    }
}
