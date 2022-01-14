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
        if (outSize == 0) {
            if (inSize == 0) {
                throw new NoSuchElementException();
            }
            while (inSize > 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        outSize--;
        return out.pop();
    }

    public boolean outIsEmpty() {
        return outSize == 0;
    }
/*
Верно, теперь устраним последний недочет - смотрите, вы два раза проверяете одно условие - outSize == 0.
 Переработайте код, чтобы это условие , как и условие inSize == 0, выполнялось один раз.
 */
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
