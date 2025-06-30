/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau6;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.util.Random;

import java.io.*;
import java.util.Random;

public class GenerateFile {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("50k.txt"));
        Random rand = new Random();

        for (int i = 0; i < 50000; i++) {
            int num = rand.nextInt(Integer.MAX_VALUE);
            writer.write(num + "\n");
        }

        writer.close();
        System.out.println("Đã tạo file 50k.txt");
    }
}

