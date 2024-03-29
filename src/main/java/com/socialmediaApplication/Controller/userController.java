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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaApplication.Model.Follow;
import com.socialmediaApplication.Model.User;
import com.socialmediaApplication.Payload.ApiResponse;
import com.socialmediaApplication.Payload.userDto;
import com.socialmediaApplication.allServices.userService;
import jakarta.validation.Valid;

@RestController
public class userController {
	
	@Autowired
	private userService uService;
	
	
	@PostMapping("new/user")
	public ResponseEntity<userDto> createUserInController(@Valid @RequestBody userDto uDto){
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
	public ResponseEntity<userDto> updateUserInController(@Valid @RequestBody userDto udto,@PathVariable("userId") int id){
		userDto updateUser = this.uService.updateUser(udto, id);
		return new ResponseEntity<userDto>(updateUser,HttpStatus.OK);
	}
	
	@DeleteMapping("delete-user/{userId}")
	public ApiResponse deleteUserInController(@PathVariable("userId") int id) {
		this.uService.deleteUser(id);
		return new ApiResponse("user is deleted...", true);
	}
	
    @PostMapping("/users/{followersId}/follow/{followingId}")
	public ResponseEntity<String> followingInUserInController(@PathVariable("followersId") int userId1,@PathVariable("followingId") int userId2) {
		String str = this.uService.followInUser(userId1, userId2);
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
    @GetMapping("all-followers-user/{userId}")
    public ResponseEntity<List<Follow>> getAllFollowersInUserInController(@PathVariable("userId") int userId){
    	List<Follow> allfollowers = this.uService.allFollowers(userId);
    	return new ResponseEntity<List<Follow>>(allfollowers,HttpStatus.OK);
    }
	
    @GetMapping("all-following-user/{userId}")
    public ResponseEntity<List<Follow>> getAllFollowingsInUserInController(@PathVariable("userId") int userId){
    	List<Follow> allfollowings = this.uService.allFollowing(userId);
    	return new ResponseEntity<List<Follow>>(allfollowings,HttpStatus.OK);
    }
    
    @GetMapping("get-user-by-email")
    public ResponseEntity<User> getUserByEmailAddressInController(@RequestParam String email){
    	User user = this.uService.getUserByEmailAddress(email);
    	return new ResponseEntity<User>(user,HttpStatus.OK);
    }
	
}