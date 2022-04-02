package ru.job4j.ood.lsp;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class ControlQualityTest {
    Shop shop;
    Trash trash;
    Warehouse warehouse;
    List<Store> stores;
    ControlQuality controlQuality;

    @Before
    public void date() {
        shop = new Shop();
        trash = new Trash();
        warehouse = new Warehouse();
        stores = List.of(warehouse, shop, trash);
        controlQuality = new ControlQuality(stores);
    }

    @Test
    public void whenAddBreadToWarehouse() {
        Food food = new Bread("rustic", LocalDate.now().plusDays(4),
                LocalDate.now().minusDays(1), 100, 50);
        controlQuality.control(food);
        List<Food> expected = List.of(food);
        assertThat(expected, is(warehouse.getAll()));
    }

    @Test
    public void whenAddMeatToTrash() {
        Food food = new Meat("Pig", LocalDate.now().plusDays(0),
                LocalDate.now().minusDays(7), 400, 50);
        controlQuality.control(food);
        List<Food> expected = List.of(food);
        assertThat(expected, is(trash.getAll()));
    }

    @Test
    public void whenDiscount() {
        Food food = new Food("milk", LocalDate.now().plusDays(1),
                LocalDate.now().minusDays(5), 100, 40);
        controlQuality.control(food);
        int newPrice = 60;
        List<Food> expected = List.of(food);
        assertThat(expected, is(shop.getAll()));
        assertEquals(newPrice, shop.getAll().get(0).getPrice(), 0.0);

    }
}