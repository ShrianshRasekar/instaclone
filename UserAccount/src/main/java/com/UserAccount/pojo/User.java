package com.UserAccount.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
/*import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;*/

@Entity
@Table(name="users")
public class User {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long uid;
	/*
	 * @NotEmpty
	 * 
	 * @Size(min=2,message = "Username must have atleast 2 characters")
	 */
	private String uname;
	
	/*
	 * @NotEmpty
	 * 
	 * @Size(min=2,message = "FullName must have atleast 2 characters")
	 */
	private String fullName;	
	
	/*
	 * @Email
	 * 
	 * @NotEmpty
	 */
	private String email;
	
	//@NotEmpty
	//@Size(min=4,message = "Password must have atleast 4 characters")
	private String password;	

	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	transient private UserProfile userprofile; // transitent -not to store in DB
	
	public UserProfile getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public User(long uid, String uname, String fullName, String email, String password, UserProfile userprofile) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.userprofile = userprofile;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", fullName=" + fullName + ", email=" + email + ", password="
				+ password + "]";
	}

	


	
}
