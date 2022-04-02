package ru.job4j.ood.lsp;

import java.util.List;

public interface Store {
    void addFood(Food food);

    List<Food> getAll();

    void sort(Food food, int percent);
}
