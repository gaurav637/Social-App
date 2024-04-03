package com.socialmediaApplication.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="comments")
public class Comments {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	private String content;
	
	private String author;
	
	private int userId;
	
	private LocalDateTime timestamp;
	
	private int likesCounter;
	
	private LocalDateTime updateDate;
	
	private String post;
	
	private int postId;
	
	private List<demoUser> allLikesUser = new ArrayList<>();
	
	private List<demoUser> pinComments = new ArrayList<>();

}
