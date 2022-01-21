package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();

    @Before
    public void initData() {
        simpleMap.put(1, 2);
        simpleMap.put(2, 4);
        simpleMap.put(3, 6);
    }

    @Test
    public void whenPutAndExpandThenTrue() {
        assertTrue(simpleMap.put(4, 8));
        assertTrue(simpleMap.put(5, 10));
        assertTrue(simpleMap.put(6, 12));
        assertThat(6, is(simpleMap.count()));
        assertTrue(simpleMap.put(7, 14));
        assertTrue(simpleMap.put(8, 16));
        assertTrue(simpleMap.put(9, 18));
        assertThat(9, is(simpleMap.count()));
    }

    @Test
    public void whenPutThenFalse() {
        assertThat(3, is(simpleMap.count()));
        assertFalse(simpleMap.put(1, 2));
        assertFalse(simpleMap.put(2, 4));
        assertFalse(simpleMap.put(3, 6));
        assertThat(3, is(simpleMap.count()));
    }

    @Test
    public void whenGetTrue() {
        assertThat(2, is(simpleMap.get(1)));
        assertThat(4, is(simpleMap.get(2)));
        assertThat(6, is(simpleMap.get(3)));
    }

    @Test
    public void whenGetNull() {
        assertNull(simpleMap.get(20));
        assertNull(simpleMap.get(15));
    }

    @Test
    public void whenRemoveTrue() {
        assertThat(3, is(simpleMap.count()));
        assertTrue(simpleMap.remove(1));
        assertTrue(simpleMap.remove(2));
        assertNull(simpleMap.get(1));
        assertNull(simpleMap.get(2));
        assertThat(1, is(simpleMap.count()));
    }

    @Test
    public void whenRemoveFalse() {
        assertTrue(simpleMap.remove(1));
        assertTrue(simpleMap.remove(3));
        assertFalse(simpleMap.remove(1));
        assertFalse(simpleMap.remove(3));
        assertFalse(simpleMap.remove(15));
    }
}