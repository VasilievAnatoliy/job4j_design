package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
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
        if (percent >= 25 && percent <= 75) {
            addFood(food);
        } else if (percent > 75 && percent < 100) {
            food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
            addFood(food);
        }
    }
}
