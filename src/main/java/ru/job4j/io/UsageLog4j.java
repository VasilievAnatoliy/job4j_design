package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        char c = 'c';
        byte b = 100;
        short s = 30000;
        long l = 1000000000;
        float f = 5.45f;
        double d = 3453245.32;
        LOG.debug("User info name : {}, age : {}, c : {}, b : {}, s : {}, l : {}, f : {}, d : {}",
                name, age, c, b, s, l, f, d);
    }
}