package com.example.it.android_nadongbin;

public class User{

    String userID;
    String userPwd;
    String userName;
    String userAge;

    public User(String userID, String userPwd, String userName, String userAge) {
        this.userID = userID;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
