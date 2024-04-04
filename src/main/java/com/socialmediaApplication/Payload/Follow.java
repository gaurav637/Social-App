package com.socialmediaApplication.Payload;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	private String userName;
	
	private String full_name;
	
	private String profilePicture;
	
	private String bio;
	
	private String contact;
	
    private int followers;
	
	private int following ;
	
	@ManyToMany
	private List<demoUser> allFollowers = new ArrayList<>();
	
	@ManyToMany
	private List<demoUser> allFollowings = new ArrayList<>();
	
}
