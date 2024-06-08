package com.serbi.sampleprofilepicture;

import android.graphics.Bitmap;

public class UserModel {

    private String firstName;
    private String lastName;
    private Bitmap profile;

    public UserModel(String firstName, String lastName, Bitmap profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profile = profile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }
}