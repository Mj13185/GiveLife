package com.example.mj.givelife;

/**
 * Created by Mj on 3/9/2018.
 */

public class Users {

    public String name;
    public String email;
    public String dob,location,bloodGroup,contact,profileImage;

    public Users(String name, String email, String dob, String location, String bloodGroup, String contact, String profileImage) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.location = location;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getLocation() {
        return location;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getContact() {
        return contact;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
