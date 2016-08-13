package com.hnkntoc.navigationdrawerv2;

/**
 * Created by Nikita on 13.08.2016.
 */
public class Lesson {
    private final String name;
    private final String description;
    private final String time1;
    private final String time2;
    private final int number;
    private final boolean numerator;

    public Lesson(String name, String description, String time1, String time2, int number, boolean numerator) {
        this.name = name;
        this.description = description;
        this.time1 = time1;
        this.time2 = time2;
        this.number = number;
        this.numerator = numerator;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public int getNumber() {
        return number;
    }

    public boolean isNumerator() {
        return numerator;
    }
}
