package com.microsoft.generics.demo;

public class MyDate implements Comparable {
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        MyDate o1 = (MyDate)o;
        if (this.year != o1.year) {
            return this.year - o1.year;
        }

        if (this.month != o1.month) {
            return this.month - o1.month;
        }

        return this.day - o1.day;
    }
}
