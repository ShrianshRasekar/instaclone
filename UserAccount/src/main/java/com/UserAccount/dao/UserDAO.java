package com.UserAccount.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.UserAccount.pojo.User;

@EnableJpaRepositories
public interface UserDAO extends JpaRepository<User, Long>{
	
//	@Query("SELECT CASE WHEN COUNT(s)>0 THEN TRUE ELSE FALSE END FROM User s WHERE s.eid= ?1")
//	Boolean isEmpExistById(Long id);

	@Query("select u From User u WHERE u.uname=:un and u.password=:pass")
	public User getUserByUnameAndPassword(@Param("un") String uname, @Param("pass") String password);
	
	@Query("select uname From User u WHERE u.uname=:un")
	public List<String> isUsernameExist(@Param("un") String uname);
	
	@Query("select uname From User u WHERE u.uname=:un")
	public String  isUsernameExistAlready(@Param("un") String uname);
	
	@Query("select uname From User u")
	public List<String> getAllUsername();
}