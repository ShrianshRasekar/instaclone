package com.UserProfile.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserProfile.dao.ProfileDAO;
import com.UserProfile.exception.ProfileNotFoundException;

import jakarta.transaction.Transactional;

import com.UserProfile.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDAO Profiledao;

	// ALL GET
	// requests----------------------------------------------------------------GET----
	@Override
	public List<UserProfile> getUserProfiles() {
		// TODO Auto-generated method stub
		if (Profiledao.findAll().isEmpty()) {
			throw new ProfileNotFoundException("No UserProfile Exist till now");
		}
		return Profiledao.findAll();
	}

	@Override
	public UserProfile getUserProfile(Long pid) {
		if (Profiledao.findById(pid).isEmpty()) {
			throw new ProfileNotFoundException("UserProfile not exist with id " + pid);
		}
		return Profiledao.findById(pid).get();
	}

	public List<String> isUserProfilenameExist(String ename) {
		if(Profiledao.isUserProfilenameExist(ename).isEmpty()) { 
			throw new ProfileNotFoundException("UserProfile not exist with UserProfilename '" +ename + "' "); 
			}
		List<String> u = Profiledao.isUserProfilenameExist(ename);
		/*
		 * List<String> UserProfilenames = u.stream().filter(e -> //
		 * e.equals(e.getUname()).collect(Collectors.toList()));
		 */
	  return u; }

	public boolean isUserProfilenameExistAlready(String ename) {
		String s = Profiledao.isUserProfilenameExistAlready(ename);
		if (s == null) {
			s = "";
		}
		if (s.equals(ename)) {
			return true;
		} // List<String> UserProfilenames =
		//UserProfile.stream().filter(e -> //
		//e.equals(e.getUname()).collect(Collectors.toList()));
		return false;
	}
	
	@Override
	public UserProfile getUserProfileByUname(String uname) {
		
		
		return Profiledao.getUserProfileByUsername(uname);
	}

	/*
	 * public List<String> getAllUserProfilenames() { if
	 * (UserProfiledao.getAllUserProfilename().isEmpty()) { throw new
	 * UserProfileNotFoundException("No UserProfile Exist till now"); }
	 * 
	 * return UserProfiledao.getAllUserProfilename(); }
	 * 
	 * @Override public UserProfile getUserProfileByUnameAndPassword(String uname,
	 * String password) { // TODO Auto-generated method stub if (uname.isEmpty() ||
	 * password.isEmpty()) { throw new
	 * UserProfileDetailsMissingException("Enter All UserProfile details"); } if
	 * (UserProfiledao.getUserProfileByUnameAndPassword(uname, password) == null) {
	 * throw new
	 * UserProfileNotFoundException("Login Failed!! Enter Correct Details"); }
	 * 
	 * return Profiledao.getUserProfileByUnameAndPassword(uname, password); }
	 */
	/*
	 * @Override
	 * 
	 * @Query("select u From UserProfile u WHERE u.uname=:un and u.password=:pass")
	 * public UserProfile getUserProfileByUnameAndPassword(@Param("un")String
	 * uname, @Param("pass")String password) { // TODO Auto-generated method stub
	 * return UserProfile; }
	 */

	// ALL POST
	// requests-------------------------------------------------------------POST-------
	@Override
	public UserProfile addUserProfile(UserProfile UserProfile) {
		Profiledao.save(UserProfile);
		UserProfile.setPid(UserProfile.getUid());
		return UserProfile;
	}

	@Override
	public List<UserProfile> addUserProfiles(List<UserProfile> ls) {
		// l.addAll(ls);

		/*
		 * for (UserProfile UserProfile : ls) { if (UserProfile.getEmail().isEmpty() ||
		 * UserProfile.getFullName().isEmpty() || UserProfile.getUname().isEmpty() ||
		 * UserProfile.getPassword().isEmpty()) { throw new
		 * UserProfileDetailsMissingException("Enter All UserProfile details"); } }
		 */
		Profiledao.saveAll(ls);
		return ls;
	}

	// ALL PUT
	// requests-----------------------------------------------------------------PUT---
	@Override
	public UserProfile updateUserProfile(UserProfile UserProfile) {
		// TODO Auto-generated method stub
		Profiledao.save(UserProfile);
		
		return UserProfile;
	}
	// ALL Patch
	// requests-----------------------------------------------------------------Patch---
	
	@Transactional
	@Override
	public UserProfile updateUserProfileBio(String username, String bio) {
		if(Profiledao.isUserProfilenameExist(username).isEmpty()) { 
			throw new ProfileNotFoundException("UserProfile not exist with UserProfilename '" +username + "' "); 
			}
		Profiledao.updateUserProfileBio( username, bio);
		return Profiledao.getUserProfileByUsername(username);
	}
		
	// ALL DELETE
	// requests----------------------------------------------------------------DELETE----
	@Override
	public String deleteUserProfile(Long pid) {
		// TODO Auto-generated method stub
		// UserProfile e=UserProfiledao.getById(eid);

		if (Profiledao.findById(pid).isEmpty()) {
			throw new ProfileNotFoundException("UserProfile not exist with id " + pid);
		}
		Profiledao.deleteById(pid);
		return "Deleted UserProfile with id " + pid;
	}

	@Override
	public String deleteUserProfileByUname(String uname) {
		// TODO Auto-generated method stub
		// UserProfile e=UserProfiledao.getById(eid);

		if (Profiledao.isUserProfilenameExistAlready(uname).isEmpty()) {
			throw new ProfileNotFoundException("UserProfile not exist with id " + uname);
		}
		
		Profiledao.deleteByUsername(uname);
		return "Deleted UserProfile with id " + uname;
	}

	
	
	

}
