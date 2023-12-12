package com.aston.tasks;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public void add(T arg) {
        elementData[size] = arg;
        if (elementData.length >= size)
            expand();
        size++;
    }

    public boolean addAll(MyArrayList collection) {
        int numNew = collection.size;
        if (numNew == 0)
            return false;
        T[] elementData = this.elementData;
        if (numNew > elementData.length - size)
            expand();
        System.arraycopy(collection, 0, elementData, size, numNew);
        size += numNew;
        return true;
    }

    public T get(int index) {
        return elementData[index];
    }

    public int get(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                index = i;
            }
        }
        return index;
    }

    public void remove(int index) {
        for (int i = index; i < size; i++)
            elementData[i] = elementData[i + 1];
        elementData[size] = null;
        size--;
    }

    public int size() {
        return size;
    }

    private void expand() {
        elementData = Arrays.copyOf(elementData, newCapacity());
    }

    private int newCapacity() {
        return elementData.length * 2 + 1;
    }


    public MyArrayList() {
        this.elementData = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elementData = (T[]) new Comparable[capacity];
        } else new MyArrayList();
    }

    public MyArrayList(Collection<T> collection) {
        this.elementData = (T[]) new Comparable[collection.size()];
        for (T el : collection) {
            add(el);
        }
    }

    public void sort() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < size - 1; i++) {
                if (elementData[i].compareTo(elementData[i + 1]) > 0) {
                    T tmp = elementData[i];
                    elementData[i] = elementData[i + 1];
                    elementData[i + 1] = tmp;
                    flag = true;
                }
            }
        }
    }
}