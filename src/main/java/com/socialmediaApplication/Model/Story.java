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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storyId;
	
	private int userId;
	
	private LocalDateTime timestamp;
	
	private String storyContent;
	
	private int viewCounter;
	
	private int reactionCounter;
	
	@ManyToMany
	private List<demoUser> views = new ArrayList<>();
	
	@ManyToMany
	private List<demoUser> reaction = new ArrayList<>();
	

}
