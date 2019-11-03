package org.vgecalumni.Model;

public class EduHolder {
    private static final EduHolder eduHolder = new EduHolder();
    private Education education = null;

    public static EduHolder getInstance() {
        return eduHolder;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }
}
