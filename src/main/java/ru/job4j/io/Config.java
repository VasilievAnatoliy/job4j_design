package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = read.readLine()) != null) {
                if (validation(line)) {
                    String[] str = line.split("=");
                    if (str.length != 2
                            || str[0].equals("") || str[1].equals("")) {
                        throw new IllegalArgumentException();
                    }
                    values.put(str[0], str[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean validation(String str) {
        if ((!str.contains("=") && !str.equals("") && !str.startsWith("#"))
                || (str.endsWith("=") || str.startsWith("="))) {
            throw new IllegalArgumentException();
        }
        return str.contains("=");
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
