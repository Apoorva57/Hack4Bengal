package com.example.hack4bengal;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVModel implements Parcelable {
    private String courseName;
    private String coursePrice;
    private String courseDescription;
    private String courseSuitedFor;
    private String courseImg;
    private String courseLink;
    private String courseID;

    public CourseRVModel(){

    }

    public CourseRVModel(String courseName, String coursePrice, String courseDescription, String courseSuitedFor, String courseImg, String courseLink, String courseID) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.courseDescription = courseDescription;
        this.courseSuitedFor = courseSuitedFor;
        this.courseImg = courseImg;
        this.courseLink = courseLink;
        this.courseID = courseID;
    }

    protected CourseRVModel(Parcel in) {
        courseName = in.readString();
        coursePrice = in.readString();
        courseDescription = in.readString();
        courseSuitedFor = in.readString();
        courseImg = in.readString();
        courseLink = in.readString();
        courseID = in.readString();
    }

    public static final Creator<CourseRVModel> CREATOR = new Creator<CourseRVModel>() {
        @Override
        public CourseRVModel createFromParcel(Parcel in) {
            return new CourseRVModel(in);
        }

        @Override
        public CourseRVModel[] newArray(int size) {
            return new CourseRVModel[size];
        }
    };

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseSuitedFor() {
        return courseSuitedFor;
    }

    public void setCourseSuitedFor(String courseSuitedFor) {
        this.courseSuitedFor = courseSuitedFor;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseName);
        parcel.writeString(coursePrice);
        parcel.writeString(courseDescription);
        parcel.writeString(courseSuitedFor);
        parcel.writeString(courseImg);
        parcel.writeString(courseLink);
        parcel.writeString(courseID);
    }
}
