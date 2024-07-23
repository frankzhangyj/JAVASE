package com.microsoft.IoStream;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalGrade {
    @Test
    public void average() throws IOException {
        FileReader fileReader = new FileReader("D:\\desktop\\1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String trans;
        int sum = 0;
        int idx = 0;
        while ((trans = bufferedReader.readLine()) != null) {
            idx++;
            sum += Integer.parseInt(trans);
        }

        FileReader fileReader2 = new FileReader("D:\\desktop\\2.txt");
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

        while ((trans = bufferedReader2.readLine()) != null) {
            idx++;
            sum += Integer.parseInt(trans);
        }
        String s = "";
        System.out.println("average = " + (double)sum / idx);
    }
}
