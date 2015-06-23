package com.softserveinc.edu.ita.enums.ordering_page;


public enum OrderSearchCondition {
    ORDER_NAME("Order Name"),
    STATUS("Status"),
    ASSIGNEE("Assignee");

    private String searchCondition;

    OrderSearchCondition(String filterName) {
        this.searchCondition = filterName;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    @Override
    public String toString() {
        return searchCondition;
    }
}
