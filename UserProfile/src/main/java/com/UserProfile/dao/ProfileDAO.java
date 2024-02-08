package com.UserProfile.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.UserProfile.entity.UserProfile;

@EnableJpaRepositories
public interface ProfileDAO extends JpaRepository<UserProfile, Long>{

	@Query("select uname From UserProfile u WHERE u.uname=:un")
	public List<String> isUserProfilenameExist(@Param("un") String uname);
	
	@Query("select uname From UserProfile u WHERE u.uname=:un")
	public String  isUserProfilenameExistAlready(@Param("un") String uname);
}
