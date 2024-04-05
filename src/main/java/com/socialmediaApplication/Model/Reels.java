package com.socialmediaApplication.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.socialmediaApplication.Payload.demoUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reels {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reelId;
	
	private int UserId;
	
	private String username;
	
	private String reelsContent;
	
	private String title;
	
	private String description;
	
	private LocalDateTime timestamp;
	
	private int likesCounter;
	
	private int dislike;
	
	private int commentCounter;
	
	private int viewsCounter;
	
	private String location;
	
	//@Transient
	@ManyToMany
	private List<demoUser> likes = new ArrayList<>();
	
	//@Transient
	@ManyToMany
	private List<demoUser> dislikes = new ArrayList<>();
	
	//@Transient
	@ManyToMany
	private List<demoUser> viewReels = new ArrayList<>();
	

}
