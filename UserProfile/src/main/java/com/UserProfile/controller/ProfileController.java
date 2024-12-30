package com.UserProfile.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import java.util.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserProfile.exception.ProfileDetailAlreadyExist;
import com.UserProfile.exception.ProfileNotFoundException;
import com.UserProfile.entity.UserProfile;
import com.UserProfile.service.ProfileService;

@RestController
@RequestMapping("/userprofile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@GetMapping(path = "/{num1}/{num2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public static long add(@PathVariable long num1, @PathVariable long num2) {
		/*
		 * UserProfile user=profileService.getUserProfile(uid); if(user==null) { return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
		 * ResponseEntity.of(Optional.of(user));
		 */
		// logger.info("Get user with id "+pid);
		return (num1 + num2);
	}

	// ALL GET
	// requests------------------------------------------------------------------------***GET**------------
	@GetMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getUserProfiles() {
		logger.info("Getting all users");

		List<UserProfile> userProfiles = profileService.getUserProfiles();
		List<Map<String, Object>> response = userProfiles.stream().map(userProfile -> {
			Map<String, Object> profileData = Map.of("pid", userProfile.getPid(), "uname", userProfile.getUname(),
					"fullName", userProfile.getFullName(), "bio", userProfile.getBio(), "followers",
					userProfile.getFollowers(), "following", userProfile.getFollowing(), "uid", userProfile.getUid(),
					"posts",
					userProfile.getPosts() != null ? Base64.getEncoder().encodeToString(userProfile.getPosts()) : null);
			return profileData;
		}).toList();

		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/profileid/{pid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getUserProfile(@PathVariable Long pid) {
		/*
		 * UserProfile user=profileService.getUserProfile(uid); if(user==null) { return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
		 * ResponseEntity.of(Optional.of(user));
		 */
		logger.info("Get user with id " + pid);
		return profileService.getUserProfile(pid);
	}

	@GetMapping(path = "/{uname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getUserProfileByUsername(@PathVariable String uname) {
		/*
		 * UserProfile user=profileService.getUserProfile(uid); if(user==null) { return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
		 * ResponseEntity.of(Optional.of(user));
		 */
		logger.info("Get user with uname " + uname);
		return profileService.getUserProfileByUname(uname);
	}

	// Check if a username exists
	@GetMapping(path = "/exists/{uname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isUserProfilenameExist(@PathVariable String uname) {
		logger.info("Checking if username exists: {}", uname);
		boolean exists = profileService.isUserProfilenameExistAlready(uname);
		return ResponseEntity.ok(exists);
	}

	// Get posts by username
	@GetMapping(path = "/{uname}/posts", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPostsByUsername(@PathVariable String uname) {
		logger.info("Fetching posts for username: {}", uname);
		try {
			byte[] posts = profileService.getPostsByUsername(uname);
			return ResponseEntity.ok(posts);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
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
	// requests-----------------------------------------------------------------------***POST**-----SIGNUP------
	@PostMapping("/addProfileInfo")
	public ResponseEntity<String> addUserProfile(@Validated @RequestBody Map<String, Object> userProfileData) {
		String s = "Adding Profile-Info Successful";

		// Extract data from the JSON request
		String uname = (String) userProfileData.get("uname");
		String fullName = (String) userProfileData.get("fullName");
		String bio = (String) userProfileData.get("bio");
		long followers = Long.parseLong(userProfileData.get("followers").toString());
		long following = Long.parseLong(userProfileData.get("following").toString());
		long uid = Long.parseLong(userProfileData.get("uid").toString());
		String postsBase64 = (String) userProfileData.get("posts"); // Base64 string

		// Decode Base64 string to byte[]
		byte[] posts = postsBase64 != null ? Base64.getDecoder().decode(postsBase64) : null;

		// Create UserProfile object
		UserProfile userProfile = new UserProfile();
		userProfile.setUname(uname);
		userProfile.setFullName(fullName);
		userProfile.setBio(bio);
		userProfile.setFollowers(followers);
		userProfile.setFollowing(following);
		userProfile.setUid(uid);
		userProfile.setPosts(posts);

		// Save UserProfile
		try {
			if (profileService.isUserProfilenameExistAlready(uname)) {
				throw new ProfileDetailAlreadyExist("UserProfile already exists with username " + uname);
			}
			profileService.addUserProfile(userProfile);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Bad request: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}

	@PostMapping(path = "/addUserProfiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUserProfiles(@Validated @RequestBody List<UserProfile> ls) {
		profileService.addUserProfiles(ls);
		String s = "UserProfiles added";
		return ResponseEntity.status(HttpStatus.CREATED).body(s);

	}

	// ALL PUT
	// requests--------------------------------------------------------------------------***PUT**----------
	@PutMapping(path = "/updateUserProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile updateUserProfile(@Validated @RequestBody UserProfile UserProfile) {
		String s = "UserProfiles updated";
		UserProfile u = profileService.updateUserProfile(UserProfile);
		return u;

	}

	// Update posts by username
	@PutMapping(path = "/{uname}/posts", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<String> updatePostsByUsername(@PathVariable String uname, @RequestBody byte[] posts) {
		logger.info("Updating posts for username: {}", uname);
		try {
			profileService.updatePostsByUsername(uname, posts);
			return ResponseEntity.ok("Posts updated successfully for username: " + uname);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	// ALL Patch
	// requests-------------------------------------------------------------------------***Patch**-------------
	@PatchMapping("/update-bio")
	public ResponseEntity<String> updateUserProfileBio(@RequestBody Map<String, String> updates) {
		String username = updates.get("uname");
		String bio = updates.get("bio");

		if (username == null || username.isBlank()) {
			return ResponseEntity.badRequest().body("Username must be provided");
		}

		// Check if bio is null, empty, or contains only whitespace
		if (bio == null || bio.isBlank()) {
			return ResponseEntity.badRequest().body("Bio cannot be empty");
		}

		// Call service to update bio
		profileService.updateUserProfileBio(username, bio);
		return ResponseEntity.ok("User bio updated successfully");
	}// ALL Delete
		// requests------------------------------------------------------------------------***Delete**-------------

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

	@DeleteMapping(path = "/{uname}/posts")
	public ResponseEntity<String> deletePostsByUsername(@PathVariable String uname) {
		logger.info("Deleting posts for username: {}", uname);
		try {
			String response = profileService.deletePostsByUsername(uname);
			return ResponseEntity.ok(response);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}