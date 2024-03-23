package com.socialmediaApplication.Model;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
