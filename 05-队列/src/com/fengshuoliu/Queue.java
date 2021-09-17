package com.fengshuoliu;

import com.fengshuoliu.list.LinkedList;
import com.fengshuoliu.list.List;

/**
 * 双向链表实现队列
 * @param <E>
 */
public class Queue<E> {
    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    /**
     * 队尾入队
     * @param element
     */
    public void enQueue(E element) {
        list.add(element);
    }

    /**
     * 队头出队
     */
    public E deQueue() {
        return list.remove(0);
    }

    /**
     * 获取队头数据
     * @return
     */
    public E front() {
        return list.get(0);
    }
}
