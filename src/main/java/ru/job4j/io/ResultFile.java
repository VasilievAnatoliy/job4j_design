package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int row = 0; row < size; row++) {
                for (int cell = 0; cell < size; cell++) {
                    String rsl = (row + 1) * (cell + 1) + "\t ";
                    out.write(rsl.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(10);
    }
}
