package com.softserveinc.edu.ita.enums.administration_page;

public enum SearchConditions {
    EQUALS("equals"),
    NOT_EQUALS_TO("not equals to"),
    STARTS_WITH("starts with"),
    CONTAINS("contains"),
    DOES_NOT_CONTAINS("does not contain");

    private String condition;

    SearchConditions(String name) {
        this.condition = name;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}

