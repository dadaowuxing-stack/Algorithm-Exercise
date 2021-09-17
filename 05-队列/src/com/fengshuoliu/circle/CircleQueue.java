package com.fengshuoliu.circle;

/**
 * 循环队列(使用数组实现)
 * @param <E>
 */
public class CircleQueue<E> {

    private int front;// 队头
    private int size; // 大小
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(1)] = null;
        }
        front = 0;
        size = 0;
    }

    /**
     * 队尾入队
     * @param element
     */
    public void enQueue(E element) {
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size++;
    }

    /**
     * 队头出队
     * @return
     */
    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    public E front() {
        return elements[front];
    }

    /**
     * 字符串拼接
     * @return
     */
    public String toString() {
        StringBuilder string  = new StringBuilder();
        string.append("capacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

    private int index(int index) {
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍(1 + 0.5)
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;

        // 重置front
        front = 0;
    }
}
