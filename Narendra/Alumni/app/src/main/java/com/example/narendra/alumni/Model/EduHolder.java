package com.example.narendra.alumni.Model;

public class EduHolder {
    private Education education = null;
    private static final EduHolder eduHolder = new EduHolder();

    public void setEducation(Education education) {
        this.education = education;
    }

    public Education getEducation() {
        return education;
    }

    public static EduHolder getInstance() {
        return eduHolder;
    }
}
