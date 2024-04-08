package com.server;
/**
 * Represents a user with necessary attributes.
 */
public class User {
   private String userName, password, email, nickName;

    /**
     * Default constructor for User.
     */
   public User (){}

    /**
     * Constructs a User object with provided attributes.
     * 
     * @param userName The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param userNickName The nickname of the user.
     */
    public User(String userName, String password, String email, String userNickName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickName = userNickName;
    }

    //getters 
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNickname(){
        return this.nickName;
    }

}
