package com.socialmediaApplication.Model;

import java.time.LocalDate;
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
	
	private LocalDate dob;
	
	private String gender;
	
	private String location;
	
	private String contact;
	
	private int followers;
	
	private int following;
	
}
