package com.regent.tech.med_manager.Model;

public class User {

    private String fullName;
    private String photo;
    private String email;


    public User(){}


    /**
     * This contructor works for a new user
     * @param mFullName
     * @param mPhoto
     * @param mEmail
     */
    public User(String mFullName, String mPhoto, String mEmail){
        this.fullName = mFullName;
        this.photo = mPhoto;
        this.email = mEmail;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

}
