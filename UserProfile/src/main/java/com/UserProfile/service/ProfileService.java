package com.UserProfile.service;

import java.util.List;

import com.UserProfile.entity.UserProfile;

public interface ProfileService {

    public List<UserProfile> getUserProfiles();

    public UserProfile getUserProfile(Long pid);

    public List<String> isUserProfilenameExist(String uname);
    public boolean isUserProfilenameExistAlready(String uname);
    
    public UserProfile getUserProfileByUname(String uname);

    public UserProfile addUserProfile(UserProfile userProfile);

    public List<UserProfile> addUserProfiles(List<UserProfile> ls);

    public UserProfile updateUserProfile(UserProfile userProfile);

    public UserProfile updateUserProfileBio(String username, String bio);

    public String deleteUserProfile(Long pid);    
    
    public String deleteUserProfileByUname(String uname);

    // New methods for the posts field
    public byte[] getPostsByUsername(String username);
    public byte[] getPostsById(Long pid);
    public UserProfile updatePostsByUsername(String username, byte[] posts);
    public String deletePostsByUsername(String username);
}
