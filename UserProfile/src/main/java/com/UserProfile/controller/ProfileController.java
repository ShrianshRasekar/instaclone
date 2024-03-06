package com.UserProfile.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserProfile.exception.ProfileDetailAlreadyExist;
import com.UserProfile.entity.UserProfile;
import com.UserProfile.service.ProfileService;


@RestController
@RequestMapping("/userprofile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	Logger logger = LoggerFactory.getLogger(ProfileController.class);


	// ALL GET
	// requests--------------------------------------------------------***GET**------------
	@GetMapping(path="/profiles",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserProfile>> getUserProfiles() {
		logger.info("Getting all users");
		return ResponseEntity.of(Optional.of(profileService.getUserProfiles()));

	}

	@GetMapping(path="/profileid/{pid}",produces=MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getUserProfile(@PathVariable Long pid) {
		/*
		 * UserProfile user=profileService.getUserProfile(uid); if(user==null) { return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
		 * ResponseEntity.of(Optional.of(user));
		 */
		logger.info("Get user with id "+pid);
		return profileService.getUserProfile(pid);
	}
	
	@GetMapping(path="/{uname}",produces=MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getUserProfileByUsername(@PathVariable String uname) {
		/*
		 * UserProfile user=profileService.getUserProfile(uid); if(user==null) { return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
		 * ResponseEntity.of(Optional.of(user));
		 */
		logger.info("Get user with uname "+uname);
		return profileService.getUserProfileByUname(uname);
	}

	
	/*
	 * @GetMapping("/{uname}") public ResponseEntity<List<String>>
	 * isUserProfilenameExist(@Validated @PathVariable String uname) { List<String>
	 * usernames = profileService.isUserProfilenameExist(uname);
	 * 
	 * return ResponseEntity.of(Optional.of(usernames)); }
	 * 
	 * @GetMapping("/AllUserProfilenames") public ResponseEntity<List<String>>
	 * getAllUserProfilenames() { return
	 * ResponseEntity.of(Optional.of(profileService.getAllUserProfilenames())); }
	 */
	 

	// -----------------------------------------------------------------------------------------------------LOGIN
	// REQUESTS---------
	//
	// using pathvariable
	// http://localhost:8000/user/login/me@15/me1115
	/*
	 * @GetMapping("/login/{uname}/{password}") public ResponseEntity<UserProfile>
	 * getUserProfileForLogin(@Validated @PathVariable String
	 * uname, @Validated @PathVariable String password) { UserProfile user =
	 * profileService.getUserProfileByUnameAndPassword(uname, password); if (user ==
	 * null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
	 * ResponseEntity.of(Optional.of(user));
	 * 
	 * }
	 */

	/*
	 * // http://localhost:8000/user/login/user?uname=me@15&password=me1115
	 * 
	 * @GetMapping("/login/user") public ResponseEntity<String>
	 * getUserProfileForLoginRp(@Validated @RequestParam String
	 * uname, @Validated @RequestParam String password) { String s =
	 * "Login Successful"; // String f="Login Failed!! Enter Correct Details";
	 * profileService.getUserProfileByUnameAndPassword(uname, password); return
	 * ResponseEntity.of(Optional.of(s));
	 * 
	 * }
	 */

	// ALL POST
	// requests---------------------------------------------------------***POST**-----SIGNUP------
	@PostMapping("/addProfileInfo")
	public ResponseEntity<String> addUserProfile(@Validated @RequestBody UserProfile userProfile) {
		/*
		 * String s="Signup Successful"; String
		 * f="Something went Wrong, Please try again"; UserProfile u=null; try {
		 */
		String s = "Adding Profile-Info Successful";
		if(profileService.isUserProfilenameExistAlready(userProfile.getUname())) {
			throw new ProfileDetailAlreadyExist("UserProfile already exist with username " + userProfile.getUname());
		}
		try {
		profileService.addUserProfile(userProfile);
		}catch (Exception e) {
			return ResponseEntity.status(400).body("Bad request "+e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
		/*
		 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); return
		 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(f); }
		 */

	}

	@PostMapping(path="/addUserProfiles",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUserProfiles(@Validated @RequestBody List<UserProfile> ls) {
		profileService.addUserProfiles(ls);
		String s = "UserProfiles added";
		return ResponseEntity.status(HttpStatus.CREATED).body(s);

	}

	// ALL PUT
	// requests----------------------------------------------------------***PUT**----------
	@PutMapping(path="/updateUserProfile",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUserProfile(@Validated @RequestBody UserProfile UserProfile) {
		String s = "UserProfiles updated";
		profileService.updateUserProfile(UserProfile);
		return ResponseEntity.status(HttpStatus.CREATED).body(s);

	}

	// ALL Delete
	// requests-------------------------------------------------------***Delete**-------------
	@DeleteMapping("/{pid}")
	public ResponseEntity<String> deleteUserProfile(@PathVariable Long pid) {
		profileService.deleteUserProfile(pid);
		String s = "UserProfile deleted having id " + pid;
		return ResponseEntity.status(HttpStatus.OK).body(s);

	}
	
	@DeleteMapping("/username/{uname}")
	public ResponseEntity<String> deleteUserProfileByUname(@PathVariable String uname) {
		profileService.deleteUserProfileByUname(uname);
		String s = "UserProfile deleted having id " + uname;
		return ResponseEntity.status(HttpStatus.OK).body(s);

	}
}