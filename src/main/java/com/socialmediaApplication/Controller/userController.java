package com.socialmediaApplication.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.userDto;
import com.socialmediaApplication.allServices.userService;

@RestController
public class userController {
	
	@Autowired
	private userService uService;
	
	@PostMapping("new/user")
	public ResponseEntity<userDto> createUserInController(@RequestBody userDto uDto){
		userDto createdUserDto = this.uService.createUser(uDto);
		return new ResponseEntity<userDto>(createdUserDto,HttpStatus.CREATED);
	}
	
	@GetMapping("get-all-users")
	public ResponseEntity<List<userDto>> getAllUserInController(){
		List<userDto> allUser = this.uService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(allUser);
	}		
	
	@GetMapping("get-user/{userId}")
	public ResponseEntity<userDto> getUserByIdInController(@PathVariable("userId") int id){
		userDto user = this.uService.getUserById(id);
		return new ResponseEntity<userDto>(user,HttpStatus.OK);
	}
	
	@PutMapping("update-user/{userId}")
	public ResponseEntity<userDto> updateUserInController(@RequestBody userDto udto,@PathVariable("userId") int id){
		userDto updateUser = this.uService.updateUser(udto, id);
		return new ResponseEntity<userDto>(updateUser,HttpStatus.OK);
	}
	
	@DeleteMapping("delete-user/{userId}")
	public ApiResponse deleteUserInController(@PathVariable("userId") int id) {
		this.uService.deleteUser(id);
		return new ApiResponse("user is deleted...", true);
	}

}