package com.UserAccount.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserAccount.dao.UserDAO;
import com.UserAccount.exception.UserDetailsMissingException;
import com.UserAccount.exception.UserNotFoundException;
import com.UserAccount.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO Userdao;

	// ALL GET
	// requests----------------------------------------------------------------GET----
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		if (Userdao.findAll().isEmpty()) {
			throw new UserNotFoundException("No User Exist till now");
		}
		return Userdao.findAll();
	}

	@Override
	public User getUser(Long uid) {
		if (Userdao.findById(uid).isEmpty()) {
			throw new UserNotFoundException("User not exist with id " + uid);
		}
		return Userdao.findById(uid).get();
	}

	public List<String> isUsernameExist(String ename) {
		if (Userdao.isUsernameExist(ename).isEmpty()) {
			throw new UserNotFoundException("User not exist with username '" + ename + "' ");
		}
		List<String> u = Userdao.isUsernameExist(ename);
		// List<String> Usernames = User.stream().filter(e ->
		// e.equals(e.getUname()).collect(Collectors.toList()));
		return u;
	}
	
	public boolean isUsernameExistAlready(String ename) {
		String s=Userdao.isUsernameExistAlready(ename);
		if(s==null) {
			s="";
		}
		if(s.equals(ename)) {
			return true;
		}
		// List<String> Usernames = User.stream().filter(e ->
		// e.equals(e.getUname()).collect(Collectors.toList()));
		return false;
	}

	public List<String> getAllUsernames() {
		if (Userdao.getAllUsername().isEmpty()) {
			throw new UserNotFoundException("No User Exist till now");
		}

		return Userdao.getAllUsername();
	}

	@Override
	public User getUserByUnameAndPassword(String uname, String password) {
		// TODO Auto-generated method stub
		if (uname.isEmpty() || password.isEmpty()) {
			throw new UserDetailsMissingException("Enter All User details");
		}
		if (Userdao.getUserByUnameAndPassword(uname, password) == null) {
			throw new UserNotFoundException("Login Failed!! Enter Correct Details");
		}

		return Userdao.getUserByUnameAndPassword(uname, password);
	}
	/*
	 * @Override
	 * 
	 * @Query("select u From User u WHERE u.uname=:un and u.password=:pass") public
	 * User getUserByUnameAndPassword(@Param("un")String uname, @Param("pass")String
	 * password) { // TODO Auto-generated method stub return User; }
	 */

	// ALL POST
	// requests-------------------------------------------------------------POST-------
	@Override
	public User addUser(User user) {
		Userdao.save(user);
		return user;
	}

	@Override
	public List<User> addUsers(List<User> ls) {
		// l.addAll(ls);

		/*
		 * for (User user : ls) { if (user.getEmail().isEmpty() ||
		 * user.getFullName().isEmpty() || user.getUname().isEmpty() ||
		 * user.getPassword().isEmpty()) { throw new
		 * UserDetailsMissingException("Enter All User details"); } }
		 */
		Userdao.saveAll(ls);
		return ls;
	}

	// ALL PUT
	// requests-----------------------------------------------------------------PUT---
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub		
		Userdao.save(user);
		return user;
	}

	// ALL DELETE
	// requests----------------------------------------------------------------DELETE----
	@Override
	public String deleteUser(Long uid) {
		// TODO Auto-generated method stub
		// User e=Userdao.getById(eid);

		if (Userdao.findById(uid).isEmpty()) {
			throw new UserNotFoundException("User not exist with id " + uid);
		}
		Userdao.deleteById(uid);
		return "Deleted User with id " + uid;
	}

}
