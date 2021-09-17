package com.fengshuoliu;

import com.fengshuoliu.list.LinkedList;
import com.fengshuoliu.list.List;

/**
 * 双链表实现双端队列
 * @param <E>
 */
public class Deque<E> {

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
     * 队头进
     * @param element
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /**
     * 队头出
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 队尾进
     * @param element
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 队尾出
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    /**
     * 从队头获取
     * @return
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 从队尾获取
     * @return
     */
    public E rear() {
        return list.get(list.size() - 1);
    }
}
