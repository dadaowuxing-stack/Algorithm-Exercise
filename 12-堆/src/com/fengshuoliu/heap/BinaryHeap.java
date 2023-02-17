package com.fengshuoliu.heap;

import com.fengshuoliu.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆(最大堆)
 *
 * @param <E>
 */
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
    private int size;
    private E[] elements;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;

        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }

//    private void siftUp(int index) {
//        E e = elements[index];
//        while (index > 0) {
//            int pIndex = (index - 1) >> 1;
//            E p = elements[pIndex];
//            if (compare(e, p) <= 0) return;
//
//            // 交换 index,pIndex位置的内容
//            E tmp = elements[index];
//            elements[index] = elements[pIndex];
//            elements[pIndex] = tmp;
//
//            // 重新赋值 index
//            index = pIndex;
//        }
//    }
private void siftUp(int index) {
    E e = elements[index];
    while (index > 0) {
        int pIndex = (index - 1) >> 1;
        E p = elements[pIndex];
        if (compare(e, p) <= 0) break;

        // 将父元素存储在 index 位置
        elements[index] = p;

        // 重新赋值 index
        index = pIndex;
    }
    elements[index] = e;
}

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的 1.5 倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must no be null");
        }
    }

    public Object root() {
        return 0;
    }

    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    public Object string(Object node) {
        Integer index = (Integer) node;
        return elements[index];
    }
}
