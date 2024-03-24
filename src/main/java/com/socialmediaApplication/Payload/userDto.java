package com.socialmediaApplication.Payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userDto {
	
    private Integer userId;
	
	private String userName;
	
	private String password;
	
    private String email;
	
	private String full_name;
	
	private String profilePicture;
	
	private String bio;
	
	private String dob;
	
	private String gender;
	
	private String location;
	
	private String contact;
	
	private List<Integer> following = new ArrayList<>();
	
	private List<Integer> followers = new ArrayList<>();
	

}
