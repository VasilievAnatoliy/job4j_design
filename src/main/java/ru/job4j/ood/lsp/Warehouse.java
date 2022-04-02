package ru.job4j.ood.lsp;


import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();

    @Override
    public void addFood(Food food) {
        list.add(food);
    }

    @Override
    public List<Food> getAll() {
        return list;
    }

    @Override
    public void sort(Food food, int percent) {
        if (percent < 25) {
            addFood(food);
        }
    }
}
