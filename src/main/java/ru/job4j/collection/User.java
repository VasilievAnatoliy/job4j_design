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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime()
                + '}';
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
            int hashCode = entry.getKey().hashCode();
            int hash = Objects.hash(hashCode);
            int index = hash & (16 - 1);
            System.out.println(entry.getKey());
            System.out.println("hash = " + Objects.hash(entry.getKey()));
            System.out.println("index = " + index);
            System.out.println(hashCode);
        }
    }
}
