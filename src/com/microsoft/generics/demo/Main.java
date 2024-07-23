package com.microsoft.generics.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Tom", new MyDate(1998, 10, 2)));
        employees.add(new Employee("Jack", new MyDate(2003, 9, 4)));
        employees.add(new Employee("Tom", new MyDate(1998, 1, 2)));

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (!(o1 instanceof Employee && o2 instanceof Employee)) {
                    System.out.println("类型不正确");
                    return 0;
                }

                if (o1.getName() != o2.getName()) {
                    return o1.getName().compareTo(o2.getName());
                }

                return o1.getDate().compareTo(o2.getDate());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
