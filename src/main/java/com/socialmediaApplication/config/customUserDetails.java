package com.socialmediaApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.socialmediaApplication.Comman.AppConstant;

@Configuration
public class customUserDetails {
		
	AppConstant ac = new AppConstant();
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username(ac.APP_USER_NAME)
				.password(passwordEncoder().encode(ac.APP_PASSWORD))
				.roles(ac.APP_ROLES)
				.build();
				
		return new InMemoryUserDetailsManager(user);
	}
}
