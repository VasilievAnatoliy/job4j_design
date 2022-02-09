package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final String color;
    private final boolean suv;
    private final int doors;
    private final Model model;
    private final String[] options;

    public Car(String color, boolean suv, int doors, Model model, String[] options) {
        this.color = color;
        this.suv = suv;
        this.doors = doors;
        this.model = model;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "color='" + color + '\''
                + ", suv=" + suv
                + ", doors=" + doors
                + ", model=" + model
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public static void main(String[] args) {
        Car car = new Car("black", true, 5,
                new Model("Niva"), new String[]{"ABS", "winter package"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        Car fromJson = gson.fromJson(gson.toJson(car), Car.class);
        System.out.println(fromJson);
    }
}
