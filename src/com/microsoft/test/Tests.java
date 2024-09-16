package com.microsoft.test;

import com.microsoft.clonable.Student;
import org.junit.Test;

public class Tests {
   @Test
   public void testClone() {
      Student student = new Student();
      Student clonedStudent = student.clone(); // 使用 public 的 clone 方法

      System.out.println("Original: " + student);
      System.out.println("Cloned: " + clonedStudent);
   }

}
