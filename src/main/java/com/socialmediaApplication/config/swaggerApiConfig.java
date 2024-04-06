package com.socialmediaApplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		
		info = @Info(
				
			title = "Social Media App (ShareZone)",
			description = "A social media app built with Spring Boot offers a scalable and efficient platform for users to connect.",
			contact = @Contact(
					name = "Gaurav Negi",
					url = "https://www.linkedin.com/in/gauravnegi91/",
					email = "negigaurav637@gmail.com"
					
		    ),
			version = "v1"
		),
		
		servers = {
				@Server(
					description = "dev",
					url = "http://localhost:8081"
			    ) ,
				
				@Server(
						description = "test",
						url = "http://localhost:8081"
				)
				
		}
)

public class swaggerApiConfig {
	

}
