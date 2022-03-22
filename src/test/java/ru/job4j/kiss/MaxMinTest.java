package ru.job4j.kiss;


import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class MaxMinTest {

    @Test
    public void whenMaxInt() {
        MaxMin maxMin = new MaxMin();
        int result = maxMin.max(List.of(2, 4, 3, 1), Integer::compareTo);
        assertThat(result, is(4));
    }

    @Test
    public void whenMaxString() {
        MaxMin maxMin = new MaxMin();
        String result = maxMin.max(List.of("age", "boy", "max"), String::compareTo);
        assertThat(result, is("max"));
    }

    @Test
    public void whenMinInt() {
        MaxMin maxMin = new MaxMin();
        int result = maxMin.min(List.of(2, 4, 3, 1), Integer::compareTo);
        assertThat(result, is(1));
    }

    @Test
    public void whenMinString() {
        MaxMin maxMin = new MaxMin();
        String result = maxMin.min(List.of("age", "boy", "max"), String::compareTo);
        assertThat(result, is("age"));
    }

    @Test
    public void whenListEmptyThenNull() {
        MaxMin maxMin = new MaxMin();
        String result = maxMin.max(List.of(), String::compareTo);
        assertNull(result);
    }
}