package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Vova", 2,
                new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2 = new User("Vova", 2,
                new GregorianCalendar(2000, Calendar.JANUARY, 1));

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> entry : map.entrySet()) {
            int h = entry.getKey().hashCode();
            int hash = Objects.hash(h);
            int i = hash & (16 - 1);
            System.out.println("index = " + i);
        }
    }
}
