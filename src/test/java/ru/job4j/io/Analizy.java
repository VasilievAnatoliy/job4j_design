package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String start = null;
            String line;
            while ((line = read.readLine()) != null) {
                String[] str = line.split(" ");
                if (start == null && str[0].equals("400") || str[0].equals("500")) {
                    start = str[1];
                }
                if (start != null && str[0].equals("200") || str[0].equals("300")) {
                    out.printf("%s%n", start + ";" + str[1] + ";");
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "target.txt");
    }
}