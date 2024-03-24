package com.socialmediaApplication.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getUserByEmailDto {
	
	@NotBlank(message = "user name is mandatory.")
	private String userName;
	
    @Size(min = 4,max = 8, message="password must be minimum 4 character and at least 8 character")
	private String password;
	
	@Email
    private String email;
	
	@NotBlank(message="name is mandatory.")
	private String full_name;
	
	@NotBlank(message = "contact is mandotory")
	private String contact;
	

}
