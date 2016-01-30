import java.util.*;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Daniel Kim
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail shaker sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * When writing your sort, don't recheck already sorted items. The amount of
     * items you are comparing should decrease by 1 for each pass of the array
     * (in either direction). See the PDF for more info.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailShakerSort(T[] arr,
                                              Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException(
                    "Comparator should not be null.");
        }
        boolean b = true;
        int begin = -1;
        int end = arr.length - 1;
        while (b) {
            b = false;
            begin++;
            for (int i = begin; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    b = true;
                }
            }
            if (b) {
                b = false;
                end--;
                for (int j = end; j > begin; j--) {
                    if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                        T temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                        b = true;
                    }
                }
            }
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException(
                    "Comparator should not be null.");
        }
        for (int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            for (int j = i - 1; j >= 0
                    && comparator.compare(arr[j], temp) > 0; j--) {
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException(
                    "Comparator should not be null.");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[index], arr[j]) > 0) {
                    index = j;
                }
            }
            //if (index != i) {
                T temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            //}
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException(
                    "Comparator should not be null.");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Random should not be null.");
        }
        if (arr.length - 1 > 0) {
            quickSort(arr, comparator, rand, 0, arr.length - 1);
        }
    }

    /**
     * Helper method for quick sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param left The left index of subarray
     * @param right The right index of subarray
     * @param <T> data type to sort
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                      Random rand, int left, int right) {
        int i = left;
        int j = right;
        int pivot = rand.nextInt(right - left) + left;
        while (i <= j) {
            while (comparator.compare(arr[i], arr[pivot]) < 0) {
                i++;
            }
            while (comparator.compare(arr[j], arr[pivot]) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j) {
            quickSort(arr, comparator, rand, left, j);
        }
        if (i < right) {
            quickSort(arr, comparator, rand, i, right);
        }
    }
    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException(
                    "Comparator should not be null.");
        }
        T[] temp = (T[]) new Object[arr.length];
        mergeSort(arr, temp, comparator, 0, arr.length - 1);
    }

    /**
     * Helper method for merge sort
     * @param arr the array to be sorted
     * @param temp temporary array
     * @param comparator the Comparator used to compare the data in arr
     * @param left The left index
     * @param right The right index
     * @param <T> data type to sort
     */
    public static <T> void mergeSort(T[] arr, T[] temp, Comparator<T>
            comparator, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, comparator, left, mid);
            mergeSort(arr, temp, comparator, mid + 1, right);
            merge(arr, temp, comparator, left, mid + 1, right);
        }
    }

    /**
     * Helper method to merge for merge sort
     * @param arr the array to be sorted
     * @param temp temporary array
     * @param comparator the Comparator used to compare the data in arr
     * @param left the first of left array
     * @param right first of right array
     * @param rightEnd last of right array
     * @param <T> data type to sort
     */
    private static <T> void merge(T[] arr, T[] temp, Comparator<T> comparator,
                                  int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(comparator.compare(arr[left], arr[right]) <= 0)
                temp[k++] = arr[left++];
            else
                temp[k++] = arr[right++];

        while(left <= leftEnd)    // Copy rest of first half
            temp[k++] = arr[left++];

        while(right <= rightEnd)  // Copy rest of right half
            temp[k++] = arr[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            arr[rightEnd] = temp[rightEnd];
//        int leftEnd = rightStart - 1;
//        int i = leftStart;
//        int k = rightEnd - leftStart + 1;
//        while (leftStart <= leftEnd && rightStart <= rightEnd) {
//            if (comparator.compare(arr[leftStart], arr[rightStart]) <= 0) {
//                temp[i] = arr[leftStart];
//                i++;
//                leftStart++;
//            } else {
//                temp[i] = arr[rightStart];
//                i++;
//                rightStart++;
//            }
//        }
//        while (leftStart <= leftEnd) {
//            temp[i] = arr[leftStart];
//            i++;
//            leftStart++;
//        }
//        while (rightStart <= rightEnd) {
//            temp[i] = arr[rightStart];
//            i++;
//            rightStart++;
//        }
//        for (int j = 0; j < k; j++, rightEnd--) {
//            arr[rightEnd] = temp[rightEnd];
//        }
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * DO NOT USE {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use an ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        List<Integer>[] bucket = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            bucket[i] = new ArrayList<Integer>();
        }
        List<Integer> pos = new ArrayList();
        List<Integer> neg = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                neg.add(arr[i]);
            } else {
                pos.add(arr[i]);
            }
        }
        boolean b = false;
        int digit = 1;
        while (!b) {
            b = true;
            int temp;
            for (Integer integer: neg) {
                temp = integer / digit;
                bucket[Math.abs(temp % 10)].add(integer);
                if (temp > 0) {
                    b = false;
                }
            }
            neg.clear();
            //int j = 0;
            for (int k = 0; k < 10; k++) {
                neg.addAll(bucket[k]);
                bucket[k].clear();
            }
            digit *= 10;
        }
        b = false;
        digit = 1;
        while (!b) {
            b = true;
            int temp;
            for (Integer integer: pos) {
                temp = integer / digit;
                bucket[Math.abs(temp % 10)].add(integer);
                if (temp > 0) {
                    b = false;
                }
            }
            pos.clear();
            //int j = 0;
            for (int k = 0; k < 10; k++) {
                pos.addAll(bucket[k]);
                bucket[k].clear();
            }
            digit *= 10;
        }
        for (int i = 0; i < neg.size(); i++) {
            arr[i] = neg.get(neg.size() - 1 - i);
        }
        for (int i = 0; i < pos.size(); i++) {
            arr[i + neg.size()] = pos.get(i);
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sort instead of {@code Math.pow()}. DO NOT MODIFY THIS METHOD.
     *
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power.
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Invalid exponent.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
