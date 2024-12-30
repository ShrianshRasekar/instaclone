package com.UserProfile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "uprofiles")
public class UserProfile {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long pid;

    private String uname;

    private String fullName;

    private String bio;

    @Lob // Large Object Annotation for storing binary data
    private byte[] posts; // Changed from long to byte[] to store image/video data

    private long followers;

    private long following;

    private long uid;

    public UserProfile() {
        super();
    }

    public UserProfile(long pid, String uname, String fullName, String bio, byte[] posts, long followers,
                       long following, long uid) {
        super();
        this.pid = pid;
        this.uname = uname;
        this.fullName = fullName;
        this.bio = bio;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getPosts() { // Updated getter for posts
        return posts;
    }

    public void setPosts(byte[] posts) { // Updated setter for posts
        this.posts = posts;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserProfile [pid=" + pid + ", uname=" + uname + ", fullName=" + fullName + ", bio=" + bio + ", posts="
                + (posts != null ? posts.length + " bytes" : "null") + ", followers=" + followers + ", following="
                + following + ", uid=" + uid + "]";
    }
}
