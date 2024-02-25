package com.UserAccount.service;

import java.util.List;

import com.UserAccount.pojo.User;

public interface UserService {

	public List<User> getUsers();

	public User getUser(Long uid);

	public List<String> isUsernameExist(String uname);
	public boolean isUsernameExistAlready(String uname);
	
	public List<String> getAllUsernames();

	public User addUser(User User);

	public List<User> addUsers(List<User> ls);

	public String deleteUser(Long uid);

	public User updateUser(User User);

	public User getUserByUnameAndPassword(String uname,String password);

}
