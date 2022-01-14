package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Очередь на двух стеках
 * работает по принципу FIFO (first in - first out)
 * Данные очереди храним в SimpleStack in и SimpleStack out
 */

public class SimpleQueue<T> {
    private int inSize;
    private int outSize;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод poll() - Реализация FIFO
     * Если Stack out пустой то
     * перемещает все элименты из Stack in в
     * Stack out в обратной последовательноси.
     *
     * @return Возвращает первое значение из коллекции out
     * и удалят его.
     */
    public T poll() {
        if (inSize == 0
                && outSize == 0) {
            throw new NoSuchElementException();
        }
        if (outSize == 0) {
            while (inSize > 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        outSize--;
        return out.pop();
    }

    /**
     * Метод push(T value) - Помещает значение в конец очереди Stack in.
     *
     * @param value Значение помещаемое в конец очереди Stack in.
     */
    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
