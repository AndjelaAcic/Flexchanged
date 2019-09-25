package com.example.bottomnavigationview;

import com.google.firebase.database.Exclude;

public class UploadProject {
    private String mProjectName;
    private String mInvolvedAlumni;
    private String mProjectDescription;


    private String mImageName;
    private String mImageUrl;
    private String mKey;

    public UploadProject() {
        //empty constructor needed
    }

    public UploadProject(String mProjectName, String mInvolvedAlumni, String mProjectDescription, String mImageName, String mImageUrl) {
        if (mProjectName.trim().equals("")) {
            mProjectName = "No Name";
        }
        if (mProjectDescription.trim().equals("")) {
            mProjectDescription = "No Description";
        }
        if (mInvolvedAlumni.trim().equals("")) {
            mInvolvedAlumni = User.Email;
        }
        if (mImageName.trim().equals("")) {
            mImageName = "No Image Name";
        }
        this.mProjectName = mProjectName;
        this.mInvolvedAlumni = mInvolvedAlumni;
        this.mProjectDescription = mProjectDescription;
        this.mImageName = mImageName;
        this.mImageUrl = mImageUrl;
        this.mKey = mKey;
    }

    public String getmProjectName() {
        return mProjectName;
    }

    public void setmProjectName(String mProjectName) {
        this.mProjectName = mProjectName;
    }

    public String getmInvolvedAlumni() {
        return mInvolvedAlumni;
    }

    public void setmInvolvedAlumni(String mInvolvedAlumni) {
        this.mInvolvedAlumni = mInvolvedAlumni;
    }

    public String getmProjectDescription() {
        return mProjectDescription;
    }

    public void setmProjectDescription(String mProjectDescription) {
        this.mProjectDescription = mProjectDescription;
    }

    public String getmImageName() {
        return mImageName;
    }

    public void setmImageName(String mImageName) {
        this.mImageName = mImageName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }






}
