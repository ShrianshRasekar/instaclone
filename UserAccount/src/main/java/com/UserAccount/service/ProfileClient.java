package com.UserAccount.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.UserAccount.pojo.UserProfile;

//@FeignClient(url = "http://localhost:5002",value = "user-profile")
@FeignClient(name="USER-PROFILE")
public interface ProfileClient {
	
	@GetMapping("/userprofile/profileid/{pid}")
	UserProfile getUserProfileOfUser(@PathVariable Long pid);
	
	
	@GetMapping("/userprofile/{uname}")
	UserProfile getUserProfileByUsername(@PathVariable String uname);
	
	
	@PutMapping("/userprofile/updateUserProfile")
	UserProfile updateUserProfileOfUser(UserProfile UserProfile);
	
	@DeleteMapping("/userprofile/{pid}")
	String deleteUserProfile(@PathVariable Long pid);

	@DeleteMapping("/userprofile/username/{uname}")
	String deleteUserProfileByUname(@PathVariable String uname);
}
