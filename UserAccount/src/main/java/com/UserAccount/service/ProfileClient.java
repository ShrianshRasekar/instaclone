package com.UserAccount.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UserAccount.pojo.UserProfile;

//@FeignClient(url = "http://localhost:5002",value = "user-profile")
@FeignClient(name="USER-PROFILE")
public interface ProfileClient {
	
	@GetMapping("/userprofile/profileid/{pid}")
	UserProfile getUserProfileOfUser(@PathVariable Long pid);

}
