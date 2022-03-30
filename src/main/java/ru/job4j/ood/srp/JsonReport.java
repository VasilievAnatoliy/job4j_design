package ru.job4j.ood.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class JsonReport implements Report {
    private Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var gson = new GsonBuilder().create();
        return gson.toJson(store.findBy(filter));
    }
}
