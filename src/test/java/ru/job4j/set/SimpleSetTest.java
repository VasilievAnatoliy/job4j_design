package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenIterator() {
        Set<String> set = new SimpleSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        Iterator<String> iterator = set.iterator();
        assertThat(iterator.next(), is("a"));
        assertThat(iterator.next(), is("b"));
        assertThat(iterator.next(), is("c"));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenCapacity1ThenAdd5AndIterator() {
        Set<Integer> set = new SimpleSet<>(1);
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(4));
        assertTrue(set.add(5));
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        assertFalse(iterator.hasNext());
    }
}
