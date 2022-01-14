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
     * Перемещает все элименты из Stack in в
     * Stack out в обратной последовательноси.
     * @return  Возвращает первое значение из коллекции out
     * и удалят его.
     */
    public T poll() {
        if (inSize == 0
                && outSize == 0) {
            throw new NoSuchElementException();
        }
        while (inSize > 0) {
            out.push(in.pop());
            inSize--;
            outSize++;
        }
        outSize--;
        return out.pop();
    }

    /**
     * Метод push(T value) - Помещает значение в конец очереди Stack in.
     * Если в коллеции Stack out есть элименты то сначала перемещает их
     * в коллекцию Stack in, а потом добавляет новый элимент.
     * @param value Значение помещаемое в конец очереди Stack in.
     */
    public void push(T value) {
        while (outSize > 0) {
            in.push(out.pop());
            outSize--;
            inSize++;
        }
        in.push(value);
        inSize++;
    }
}
