package com.softserveinc.edu.ita.enums.ordering_page;

public enum NUMBER_SHOWN_ELEMENTS {
    ELEMENTS_1("Show 1 elements", 1),
    ELEMENTS_2("Show 2 elements", 2),
    ELEMENTS_5("Show 5 elements", 5),
    ELEMENTS_10("Show 10 elements", 10),
    ELEMENTS_25("Show 25 elements", 25),
    ELEMENTS_50("Show 50 elements", 50);

    private String name;
    private int number;

    NUMBER_SHOWN_ELEMENTS(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return  name +
                number ;
    }
}
