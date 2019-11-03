package org.vgecalumni.Model;

public class ExprHolder {
    private static final ExprHolder exprHolder = new ExprHolder();
    private Experience experience = null;

    public static ExprHolder getInstance() {
        return exprHolder;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
