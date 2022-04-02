package ru.job4j.ood.lsp;

import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        int percent = Distribution.percentageLive(food);
        for (Store store : stores) {
            store.sort(food, percent);
        }
    }

}
