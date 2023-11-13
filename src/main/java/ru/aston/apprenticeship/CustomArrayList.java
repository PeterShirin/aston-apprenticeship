package ru.aston.apprenticeship;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Object[] elements;

    public CustomArrayList() {
        this.size = 0;
        this.elements = new Object[DEFAULT_SIZE];
    }

    public CustomArrayList(int initialCapacity) {
        this.size = 0;
        this.elements = new Object[initialCapacity];
    }

    public void add(int index, E element) {
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public boolean addAll(Collection<? extends E> collection) {
        ensureCapacity(size + collection.size());
        for (E element : collection) {
            elements[size++] = element;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == obj || (obj != null && obj.equals(elements[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void sort(Comparator<? super E> comparator) {
        quickSort(0, size / 2 - 1, comparator);
        mergeSort(size / 2, size - 1, comparator);
    }

    private void quickSort(int start, int fin, Comparator<? super E> comparator) {
        if (start < fin) {
            int pivotIndex = division(start, fin, comparator);
            quickSort(start, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, fin, comparator);
        }
    }

    private int division(int start, int fin, Comparator<? super E> comparator) {
        E pivot = (E) elements[fin];
        int i = start - 1;
        for (int j = start; j < fin; j++) {
            if (comparator.compare((E) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, fin);
        return i + 1;
    }

    private void mergeSort(int start, int fin, Comparator<? super E> comparator) {
        if (start < fin) {
            int mid = (start + fin) / 2;
            mergeSort(start, mid, comparator);
            mergeSort(mid + 1, fin, comparator);
            merge(start, mid, fin, comparator);
        }
    }

    private void merge(int start, int mid, int fin, Comparator<? super E> comparator) {
        Object[] temp = new Object[fin - start + 1];
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= fin) {
            if (comparator.compare((E) elements[i], (E) elements[j]) <= 0) {
                temp[k++] = elements[i++];
            } else {
                temp[k++] = elements[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = elements[i++];
        }

        while (j <= fin) {
            temp[k++] = elements[j++];
        }

        System.arraycopy(temp, 0, elements, start, temp.length);
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.elements);
    }
}