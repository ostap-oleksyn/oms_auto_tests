package com.softserveinc.edu.ita.enums;

public enum AdministrationTabConditions {
    EQUALS("equals"),
    NOT_EQUALS_TO("not equals to"),
    STARTS_WITH("starts with"),
    CONTAINS("contains"),
    DOES_NOT_CONTAINS("does not contain");

    private String condition;

    AdministrationTabConditions(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}

