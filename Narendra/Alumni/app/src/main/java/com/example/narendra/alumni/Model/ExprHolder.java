package com.example.narendra.alumni.Model;

public class ExprHolder {
    private Experience experience = null;
    private static final ExprHolder exprHolder = new ExprHolder();

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Experience getExperience() {
        return experience;
    }

    public static ExprHolder getInstance() {
        return exprHolder;
    }
}
