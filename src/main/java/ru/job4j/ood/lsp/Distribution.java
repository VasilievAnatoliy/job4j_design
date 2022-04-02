package ru.job4j.ood.lsp;

import java.time.LocalDate;

public class Distribution {
    public static int percentageLive(Food food) {
        long totalDays = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long daysLeft = totalDays - (food.getExpiryDate().toEpochDay() - LocalDate.now().toEpochDay());
        return (int) (daysLeft * 100 / totalDays);
    }
}
