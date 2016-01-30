import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class BasicSortingTests {
    private static final int TIMEOUT = 2500;

    class DValue implements Comparable<DValue> {
        public Integer val;
        public Integer count;
        public DValue(Integer a, Integer b) {
            val = new Integer(a.intValue());
            count = new Integer(b.intValue());
        }
        public int compareTo(DValue d) {
            return val - d.val;
        }

        public boolean equals(Object d) {
            return val.intValue() == ((DValue)d).val.intValue();
        }
    }

    class BasicComparator implements Comparator<DValue> {
        public int compare(DValue a, DValue b) {
            return a.val - b.val;
        }
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailShakerSort() {
        DValue[] arrZero = new DValue[0];
        Sorting.cocktailShakerSort(arrZero, new BasicComparator());
        assertEquals(0, arrZero.length);
        DValue[] arrOne = new DValue[1];
        arrOne[0] = new DValue(4, 0);
        Sorting.cocktailShakerSort(arrOne, new BasicComparator());
        assertEquals(1, arrOne.length);
        assertEquals(4, arrOne[0].val.intValue());

        Random rand = new Random();
        HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
        for(int i = 0; i < 50; i++) {
            int arrlen = rand.nextInt(1000) + 2;
            DValue[] arrMany = new DValue[arrlen];
            DValue[] arrManySorted = new DValue[arrlen];
            for(int j = 0; j < arrlen; j++) {
                arrMany[j] = new DValue(rand.nextInt(200)-90, 0);
                if(values.containsKey(arrMany[j].val)) {
                    arrMany[j].count = values.get(arrMany[j].val) + 1;
                    values.put(arrMany[j].val, arrMany[j].count);
                } else {
                    values.put(arrMany[j].val, 0);
                }
            }
            System.arraycopy(arrMany, 0, arrManySorted, 0, arrlen);
            Arrays.sort(arrManySorted);
            Sorting.cocktailShakerSort(arrMany, new BasicComparator());
            assertArrayEquals(arrManySorted, arrMany);
            for(int j = 0; j < arrMany.length - 1; j++) {
                if(arrMany[j].val.equals(arrMany[j+1].val)) {
                    assertTrue(arrMany[j].count < arrMany[j+1].count);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        DValue[] arrZero = new DValue[0];
        Sorting.insertionSort(arrZero, new BasicComparator());
        assertEquals(0, arrZero.length);
        DValue[] arrOne = new DValue[1];
        arrOne[0] = new DValue(4, 0);
        Sorting.insertionSort(arrOne, new BasicComparator());
        assertEquals(1, arrOne.length);
        assertEquals(4, arrOne[0].val.intValue());

        Random rand = new Random();
        HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
        for(int i = 0; i < 50; i++) {
            int arrlen = rand.nextInt(1000) + 2;
            DValue[] arrMany = new DValue[arrlen];
            DValue[] arrManySorted = new DValue[arrlen];
            for(int j = 0; j < arrlen; j++) {
                arrMany[j] = new DValue(rand.nextInt(200)-90, 0);
                if(values.containsKey(arrMany[j].val)) {
                    arrMany[j].count = values.get(arrMany[j].val) + 1;
                    values.put(arrMany[j].val, arrMany[j].count);
                } else {
                    values.put(arrMany[j].val, 0);
                }
            }
            System.arraycopy(arrMany, 0, arrManySorted, 0, arrlen);
            Arrays.sort(arrManySorted);
            Sorting.insertionSort(arrMany, new BasicComparator());
            assertArrayEquals(arrManySorted, arrMany);
            for(int j = 0; j < arrMany.length - 1; j++) {
                if(arrMany[j].val.equals(arrMany[j+1].val)) {
                    assertTrue(arrMany[j].count < arrMany[j+1].count);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        DValue[] arrZero = new DValue[0];
        Sorting.selectionSort(arrZero, new BasicComparator());
        assertEquals(0, arrZero.length);
        DValue[] arrOne = new DValue[1];
        arrOne[0] = new DValue(4, 0);
        Sorting.selectionSort(arrOne, new BasicComparator());
        assertEquals(1, arrOne.length);
        assertEquals(4, arrOne[0].val.intValue());

        Random rand = new Random();
        HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
        for(int i = 0; i < 50; i++) {
            int arrlen = rand.nextInt(1000) + 2;
            DValue[] arrMany = new DValue[arrlen];
            DValue[] arrManySorted = new DValue[arrlen];
            for(int j = 0; j < arrlen; j++) {
                arrMany[j] = new DValue(rand.nextInt(200)-90, 0);
                if(values.containsKey(arrMany[j].val)) {
                    arrMany[j].count = values.get(arrMany[j].val) + 1;
                    values.put(arrMany[j].val, arrMany[j].count);
                } else {
                    values.put(arrMany[j].val, 0);
                }
            }
            System.arraycopy(arrMany, 0, arrManySorted, 0, arrlen);
            Arrays.sort(arrManySorted);
            Sorting.selectionSort(arrMany, new BasicComparator());
            assertArrayEquals(arrManySorted, arrMany);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        DValue[] arrZero = new DValue[0];
        Sorting.quickSort(arrZero, new BasicComparator(), new Random());
        assertEquals(0, arrZero.length);
        DValue[] arrOne = new DValue[1];
        arrOne[0] = new DValue(4, 0);
        Sorting.quickSort(arrOne, new BasicComparator(), new Random());
        assertEquals(1, arrOne.length);
        assertEquals(4, arrOne[0].val.intValue());

        Random rand = new Random();
        HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
        for(int i = 0; i < 50; i++) {
            int arrlen = rand.nextInt(1000) + 2;
            DValue[] arrMany = new DValue[arrlen];
            DValue[] arrManySorted = new DValue[arrlen];
            for(int j = 0; j < arrlen; j++) {
                arrMany[j] = new DValue(rand.nextInt(200)-90, 0);
                if(values.containsKey(arrMany[j].val)) {
                    arrMany[j].count = values.get(arrMany[j].val) + 1;
                    values.put(arrMany[j].val, arrMany[j].count);
                } else {
                    values.put(arrMany[j].val, 0);
                }
            }
            System.arraycopy(arrMany, 0, arrManySorted, 0, arrlen);
            Arrays.sort(arrManySorted);
            Sorting.quickSort(arrMany, new BasicComparator(), new Random());
            assertArrayEquals(arrManySorted, arrMany);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        DValue[] arrZero = new DValue[0];
        Sorting.mergeSort(arrZero, new BasicComparator());
        assertEquals(0, arrZero.length);
        DValue[] arrOne = new DValue[1];
        arrOne[0] = new DValue(4, 0);
        Sorting.mergeSort(arrOne, new BasicComparator());
        assertEquals(1, arrOne.length);
        assertEquals(4, arrOne[0].val.intValue());

        Random rand = new Random();
        HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
        for(int i = 0; i < 50; i++) {
            int arrlen = rand.nextInt(1000) + 2;
            DValue[] arrMany = new DValue[arrlen];
            DValue[] arrManySorted = new DValue[arrlen];
            for(int j = 0; j < arrlen; j++) {
                arrMany[j] = new DValue(rand.nextInt(200)-90, 0);
                if(values.containsKey(arrMany[j].val)) {
                    arrMany[j].count = values.get(arrMany[j].val) + 1;
                    values.put(arrMany[j].val, arrMany[j].count);
                } else {
                    values.put(arrMany[j].val, 0);
                }
            }
            System.arraycopy(arrMany, 0, arrManySorted, 0, arrlen);
            Arrays.sort(arrManySorted);
            Sorting.mergeSort(arrMany, new BasicComparator());
            assertArrayEquals(arrManySorted, arrMany);
            for(int j = 0; j < arrMany.length - 1; j++) {
                if(arrMany[j].val.equals(arrMany[j+1].val)) {
                    assertTrue(arrMany[j].count < arrMany[j+1].count);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRadixSort() {
        int[] arrZero = new int[0];
        arrZero = Sorting.radixSort(arrZero);
        assertEquals(0, arrZero.length);
        int[] arrOne = new int[1];
        arrOne[0] = 4;
        arrOne = Sorting.radixSort(arrOne);
        assertEquals(1, arrOne.length);
        assertEquals(4, arrOne[0]);

        Random rand = new Random();
        for(int i = 0; i < 50; i++) {
            int arrlen = rand.nextInt(1000) + 2;
            int[] arrMany = new int[arrlen];
            int[] arrManySorted = new int[arrlen];
            for(int j = 0; j < arrlen; j++) {
                arrMany[j] = rand.nextInt(200)-90;
            }
            System.arraycopy(arrMany, 0, arrManySorted, 0, arrlen);
            Arrays.sort(arrManySorted);
            arrMany = Sorting.radixSort(arrMany);
            assertArrayEquals(arrManySorted, arrMany);
        }
    }
}