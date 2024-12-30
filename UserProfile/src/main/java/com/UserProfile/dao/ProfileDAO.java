package com.UserProfile.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.UserProfile.entity.UserProfile;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface ProfileDAO extends JpaRepository<UserProfile, Long> {

    @Query("select uname From UserProfile u WHERE u.uname=:un")
    public List<String> isUserProfilenameExist(@Param("un") String uname);
    
    @Query("select uname From UserProfile u WHERE u.uname=:un")
    public String isUserProfilenameExistAlready(@Param("un") String uname);
    
    @Query("select u From UserProfile u WHERE u.uname=:un")
    public UserProfile getUserProfileByUsername(@Param("un") String uname);
    
    @Modifying
    @Query("UPDATE UserProfile u SET u.bio=:bio WHERE u.uname=:un")
    public int updateUserProfileBio(@Param("un") String username, @Param("bio") String bio);

    @Query("SELECT u.posts FROM UserProfile u WHERE u.uname = :un")
    public byte[] getPostsByUsername(@Param("un") String username);

    @Query("SELECT u.posts FROM UserProfile u WHERE u.pid = :pid")
    public byte[] getPostsById(@Param("pid") Long pid);

    @Modifying
    @Query("UPDATE UserProfile u SET u.posts = :posts WHERE u.uname = :un")
    @Transactional
    public int updatePostsByUsername(@Param("un") String username, @Param("posts") byte[] posts);

    @Modifying
    @Query("UPDATE UserProfile u SET u.posts = null WHERE u.uname = :un")
    @Transactional
    public int deletePostsByUsername(@Param("un") String username);
    
    @Query("DELETE FROM UserProfile u WHERE u.uname = :un")
    @Modifying
    @Transactional
    void deleteByUsername(@Param("un") String uname);
}
