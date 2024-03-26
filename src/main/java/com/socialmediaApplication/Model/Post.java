package com.socialmediaApplication.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(nullable=false)
	private String content;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime timestamp;
	
	private String location;
	
	private int likesCounter;
	
	private int commentCounter;
	
	@OneToMany
	private List<User> likes = new ArrayList<>();
	
	@OneToMany
	private List<User> comments = new ArrayList<>();
}
