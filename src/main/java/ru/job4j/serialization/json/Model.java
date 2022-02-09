package ru.job4j.serialization.json;

public class Model {
    private final String model;

    public Model(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Model{"
               + "model='" + model + '\''
               + '}';
    }
}
