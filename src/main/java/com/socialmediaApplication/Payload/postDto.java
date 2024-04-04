package com.socialmediaApplication.Payload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class postDto {

	private List<String> likes = new ArrayList<>();
	
	
	private Map<List<String>,List<String>> comments = new HashMap<>();
}
