package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод вставки в случае отсутствия места возвращает false.
     * Номер ячейки определяем через hashCode.
     * Разрешение коллизий не реализовываем.
     * При заполнение согласно условию LOAD_FACTOR = 0.75 увеличивает вместимость в двое.
     * Счётчик элиментов count++
     * Счётчик изменений modCount++
     *
     * @param key   Ключ значения.
     * @param value Значение.
     * @return Если ячейка занята возвращает false.
     */
    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = false;
        if (table[index(key)] == null) {
            var element = new MapEntry<>(key, value);
            table[index(key)] = element;
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return Objects.hash(hashCode);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int index(K key) {
        return indexFor(hash(key.hashCode()));
    }

    public int count() {
        return count;
    }

    /**
     * Увеличивает вместимость значений в двое.
     */
    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> oldValue : oldTable) {
            if (oldValue != null) {
                put(oldValue.key, oldValue.value);
                count--;
            }
        }
        modCount++;
    }

    /**
     * Получаем значение по ключу.
     *
     * @param key Ключ значения.
     * @return В случае отсутствия значения возвращает null, в противном случае само значение.
     */
    @Override
    public V get(K key) {
        return table[index(key)] == null || !Objects.equals(key, table[index(key)].key)
                ? null : table[index(key)].value;
    }

    /**
     * Удаление значения по ключу.
     *
     * @param key Ключ значения.
     * @return В случае успешного удаления возвращает true, в противном случае false.
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = true;
        if (table[index(key)] == null
                || !Objects.equals(key, table[index(key)].key)) {
            rsl = false;
        } else {
            table[index(key)] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
