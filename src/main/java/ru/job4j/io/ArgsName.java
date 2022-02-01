package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("no arguments");
        }
        for (String value : args) {
            String[] str = check(value).split("=", 2);
            if (validation(str)) {
                values.put(str[0], str[1]);
            }
        }
    }

    public static String check(String value) {
        String rsl;
        if (value.startsWith("-")) {
            rsl = value.substring(1);
        } else {
            rsl = value;
        }
        return rsl;
    }

    public static Boolean validation(String[] str) {
        if (str.length != 2
                || str[0].equals("") || str[1].equals("")) {
            throw new IllegalArgumentException("invalid arguments");
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}