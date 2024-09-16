package com.microsoft.clonable;

public class AppRun {
    public static void main(String[] args) {
        Student student = new Student();
        Student clonedStudent = student.clone(); // 使用 public 的 clone 方法

        System.out.println("Original: " + student);
        System.out.println("Cloned: " + clonedStudent);
    }
}
