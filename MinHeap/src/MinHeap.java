import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 * @author Daniel Kim
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a Heap.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        //size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item should not be null.");
        } else if (backingArray.length - 1 == size) {
            T[] copy = backingArray;
            backingArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i <= size; i++) {
                backingArray[i] = copy[i];
            }
        }
        size++;
        backingArray[size] = item;
        int j = size;
        while (j > 1 && backingArray[j / 2].compareTo(backingArray[j]) > 0) {
            backingArray[j] = backingArray[j / 2];
            backingArray[j / 2] = item;
            j = j / 2;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap should not be empty.");
        } else {
            T removed = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            if (size == 0) {
                backingArray[1] = null;
            } else {
                removeHelper(1, backingArray[1]);

                //removeHelper(1, backingArray[1]);
            }
            return removed;
        }
    }

//    private void removeHelper(int j, T data) {
//        while (j < size ) {
//            if (backingArray[2 * j] != null && backingArray[2 * j].compareTo(backingArray[2 * j + 1]) < 0) {
//                if (backingArray[j].compareTo(backingArray[2 * j]) > 0) {
//                    backingArray[j] = backingArray[2 * j];
//                    backingArray[2 * j] = data;
//                    j = 2 * j;
//                }
//            } else if (backingArray[2 * j + 1] != null && backingArray[2 * j].compareTo(backingArray[2 * j + 1]) > 0) {
//                if (backingArray[j].compareTo(backingArray[2 * j + 1]) > 0) {
//                    backingArray[j] = backingArray[2 * j + 1];
//                    backingArray[2 * j + 1] = data;
//                    j = 2 * j + 1;
//                }
//            }
//        }
//    }
//    public T remove() {
//        if (isEmpty()) {
//            throw new NoSuchElementException("Heap should not be empty.");
//        } else {
//            T removed = backingArray[1];
//            backingArray[1] = backingArray[size];
//            backingArray[size] = null;
//            size--;
//            if (size == 0) {
//                backingArray[1] = null;
//            } else {
//                removeHelper(1, backingArray[1]);
//            }
//            return removed;
//        }
//    }
//
//    /**
//     * Recursive helper method for remove
//     * Runs until data are in order
//     * @param i Current index that will be compared
//     * @param movedData The data that is moving through the array
//     */
    private void removeHelper(int i, T movedData) {
        if (2 * i <= size) {
            if (backingArray[2 * i] != null
                    && backingArray[2 * i + 1] == null) {
                if (backingArray[i].compareTo(backingArray[2 * i]) > 0) {
                    backingArray[i] = backingArray[2 * i];
                    backingArray[2 * i] = movedData;
                    removeHelper(2 * i, movedData);
                }
            } else if (backingArray[2 * i] != null
                    && backingArray[2 * i]
                    .compareTo(backingArray[2 * i + 1]) < 0) {
                if (backingArray[i].compareTo(backingArray[2 * i]) > 0) {
                    backingArray[i] = backingArray[2 * i];
                    backingArray[2 * i] = movedData;
                    removeHelper(2 * i, movedData);
                }
            } else if (backingArray[2 * i] != null
                    && backingArray[2 * i]
                    .compareTo(backingArray[2 * i + 1]) > 0) {
                if (backingArray[(i * 2 + 1) / 2].compareTo(backingArray[i * 2 + 1]) > 0) {
                    backingArray[(i * 2 + 1) / 2] = backingArray[i * 2 + 1];
                    backingArray[i * 2 + 1] = movedData;
                    removeHelper(i * 2 + 1, movedData);
                }

            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing array
     */
    public Comparable[] getBackingArray() {
        return backingArray;
    }

}
