package collections.implementation;

import collections.interfaces.ListADT;
import collections.interfaces.UnorderedListADT;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Class that sorts using {@link Comparator comparators}.
 */
public class SortComparator {

    /**
     * Sorts the specific list using selection sort algorithm and {@link Comparator comparator} instance.
     */
    public static <T> Iterator<T> selectionSort(ListADT<T> list, Comparator<? super T> comparator) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }
        T[] data = SortComparator.toArray(list);
        int min;
        T temp;
        for (int index = 0; index < data.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++) {
                if (comparator.compare(data[scan], data[min]) < 0) {
                    min = scan;
                }
            }
            temp = data[min];
            data[min] = data[index];
            data[index] = temp;
        }
        return SortComparator.getIterator(data);
    }

    /**
     * Sorts the specific list using insert sort algorithm and {@link Comparator comparator} instance.
     */
    public static <T> Iterator<T> insertionSort(ListADT<T> list, Comparator<? super T> comparator) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }
        boolean sorted = false;
        T[] data = SortComparator.toArray(list);
        T tmp;
        for (int index = 0; index < data.length - 1; index++, sorted = false) {
            for (int position = index + 1; !sorted && position >= 1; position--) {
                if (comparator.compare(data[position - 1], data[position]) > 0) {
                    tmp = data[position - 1];
                    data[position - 1] = data[position];
                    data[position] = tmp;
                } else {
                    sorted = true;
                }
            }
        }
        return SortComparator.getIterator(data);
    }

    /**
     * Returns an array with the content of a list.
     *
     * @param list to be transformed in array.
     * @return array with the content of list.
     * @implNote It's not made verifications inside, all verifications need to be done outside this method.
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] toArray(ListADT<T> list) {
        T[] array = (T[]) (new Object[list.size()]);
        int count = 0;
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            array[count++] = iterator.next();
        }
        return array;
    }

    /**
     * Returns the iterator given an array.
     *
     * @param array to be "transformed" in an iterator.
     * @return iterator of the data in array.
     */
    private static <T> Iterator<T> getIterator(T[] array) {
        UnorderedListADT<T> list = new ArrayUnorderedList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.addToRear(array[i]);
        }
        return list.iterator();
    }
}
