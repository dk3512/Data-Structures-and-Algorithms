import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class PaulJUnit7 {
    private Student[] studentArray;
    private String[] nameArray;
    private Student[] sortedStudentArray;
    private Comparator<Student> comparator;
    private Random rand;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        comparator = Student.getIDComparator();
        rand = new Random();
    }

    // Cocktail Shaker Sort

    // comparator is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void coctailShakerSortException1() {
        studentArray = new Student[1];
        studentArray[0] = new Student(1, "a");
        Sorting.cocktailShakerSort(studentArray, null);
    }

    // array is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void coctailShakerSortException2() {
        Sorting.cocktailShakerSort(null, comparator);
    }

    // test array already sorted
    @Test(timeout = TIMEOUT)
    public void coctailShakerSort1() {
        studentArray = new Student[5];
        studentArray[0] = new Student(1, "a");
        studentArray[1] = new Student(2, "b");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(4, "d");
        studentArray[4] = new Student(5, "e");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[0];
        sortedStudentArray[1] = studentArray[1];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[3];
        sortedStudentArray[4] = studentArray[4];

        Sorting.cocktailShakerSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test array in reverse sorted order
    @Test(timeout = TIMEOUT)
    public void coctailShakerSort2() {
        studentArray = new Student[5];
        studentArray[0] = new Student(5, "e");
        studentArray[1] = new Student(4, "d");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(2, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[4];
        sortedStudentArray[1] = studentArray[3];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[1];
        sortedStudentArray[4] = studentArray[0];

        Sorting.cocktailShakerSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test for stability
    @Test(timeout = TIMEOUT)
    public void coctailShakerSort3() {
        studentArray = new Student[5];
        studentArray[0] = new Student(2, "e");
        studentArray[1] = new Student(1, "d");
        studentArray[2] = new Student(1, "c");
        studentArray[3] = new Student(1, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[1];
        sortedStudentArray[1] = studentArray[2];
        sortedStudentArray[2] = studentArray[3];
        sortedStudentArray[3] = studentArray[4];
        sortedStudentArray[4] = studentArray[0];

        Sorting.cocktailShakerSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // Insertion Sort

    // comparator is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void insertionSortException1() {
        studentArray = new Student[1];
        studentArray[0] = new Student(1, "a");
        Sorting.insertionSort(studentArray, null);
    }

    // array is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void insertionSortException2() {
        Sorting.insertionSort(null, comparator);
    }

    // test array already sorted
    @Test(timeout = TIMEOUT)
    public void insertionSort1() {
        studentArray = new Student[5];
        studentArray[0] = new Student(1, "a");
        studentArray[1] = new Student(2, "b");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(4, "d");
        studentArray[4] = new Student(5, "e");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[0];
        sortedStudentArray[1] = studentArray[1];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[3];
        sortedStudentArray[4] = studentArray[4];

        Sorting.insertionSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test array in reverse sorted order
    @Test(timeout = TIMEOUT)
    public void insertionSort2() {
        studentArray = new Student[5];
        studentArray[0] = new Student(5, "e");
        studentArray[1] = new Student(4, "d");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(2, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[4];
        sortedStudentArray[1] = studentArray[3];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[1];
        sortedStudentArray[4] = studentArray[0];

        Sorting.insertionSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test for stability
    @Test(timeout = TIMEOUT)
    public void insertionSort3() {
        studentArray = new Student[5];
        studentArray[0] = new Student(2, "e");
        studentArray[1] = new Student(1, "d");
        studentArray[2] = new Student(1, "c");
        studentArray[3] = new Student(1, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[1];
        sortedStudentArray[1] = studentArray[2];
        sortedStudentArray[2] = studentArray[3];
        sortedStudentArray[3] = studentArray[4];
        sortedStudentArray[4] = studentArray[0];

        Sorting.insertionSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // Selection Sort

    // comparator is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void selectionSortException1() {
        studentArray = new Student[1];
        studentArray[0] = new Student(1, "a");
        Sorting.selectionSort(studentArray, null);
    }

    // array is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void selectionSortException2() {
        Sorting.selectionSort(null, comparator);
    }

    // test array already sorted
    @Test(timeout = TIMEOUT)
    public void selectionSort1() {
        studentArray = new Student[5];
        studentArray[0] = new Student(1, "a");
        studentArray[1] = new Student(2, "b");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(4, "d");
        studentArray[4] = new Student(5, "e");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[0];
        sortedStudentArray[1] = studentArray[1];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[3];
        sortedStudentArray[4] = studentArray[4];

        Sorting.selectionSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test array in reverse sorted order
    @Test(timeout = TIMEOUT)
    public void selectionSort2() {
        studentArray = new Student[5];
        studentArray[0] = new Student(5, "e");
        studentArray[1] = new Student(4, "d");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(2, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[4];
        sortedStudentArray[1] = studentArray[3];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[1];
        sortedStudentArray[4] = studentArray[0];

        Sorting.selectionSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // Quick Sort

    // comparator is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void quickSortException1() {
        studentArray = new Student[1];
        studentArray[0] = new Student(1, "a");
        Sorting.quickSort(studentArray, null, rand);
    }

    // array is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void quickSortException2() {
        Sorting.quickSort(null, comparator, rand);
    }

    // rand is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void quickSortException3() {
        studentArray = new Student[1];
        studentArray[0] = new Student(1, "a");
        Sorting.quickSort(studentArray, comparator, null);
    }

    // test array already sorted
    @Test(timeout = TIMEOUT)
    public void quickSort1() {
        studentArray = new Student[5];
        studentArray[0] = new Student(1, "a");
        studentArray[1] = new Student(2, "b");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(4, "d");
        studentArray[4] = new Student(5, "e");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[0];
        sortedStudentArray[1] = studentArray[1];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[3];
        sortedStudentArray[4] = studentArray[4];

        Sorting.quickSort(studentArray, comparator, rand);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test array in reverse sorted order
    @Test(timeout = TIMEOUT)
    public void quickSort2() {
        studentArray = new Student[5];
        studentArray[0] = new Student(5, "e");
        studentArray[1] = new Student(4, "d");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(2, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[4];
        sortedStudentArray[1] = studentArray[3];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[1];
        sortedStudentArray[4] = studentArray[0];

        Sorting.quickSort(studentArray, comparator, rand);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // Merge Sort

    // comparator is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void mergeSortException1() {
        studentArray = new Student[1];
        studentArray[0] = new Student(1, "a");
        Sorting.mergeSort(studentArray, null);
    }

    // array is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void mergeSortException2() {
        Sorting.mergeSort(null, comparator);
    }

    // test array already sorted
    @Test(timeout = TIMEOUT)
    public void mergeSort1() {
        studentArray = new Student[5];
        studentArray[0] = new Student(1, "a");
        studentArray[1] = new Student(2, "b");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(4, "d");
        studentArray[4] = new Student(5, "e");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[0];
        sortedStudentArray[1] = studentArray[1];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[3];
        sortedStudentArray[4] = studentArray[4];

        Sorting.mergeSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // test array in reverse sorted order
    @Test(timeout = TIMEOUT)
    public void mergeSort2() {
        studentArray = new Student[5];
        studentArray[0] = new Student(5, "e");
        studentArray[1] = new Student(4, "d");
        studentArray[2] = new Student(3, "c");
        studentArray[3] = new Student(2, "b");
        studentArray[4] = new Student(1, "a");

        sortedStudentArray = new Student[5];
        sortedStudentArray[0] = studentArray[4];
        sortedStudentArray[1] = studentArray[3];
        sortedStudentArray[2] = studentArray[2];
        sortedStudentArray[3] = studentArray[1];
        sortedStudentArray[4] = studentArray[0];

        Sorting.mergeSort(studentArray, comparator);

        assertArrayEquals(studentArray, sortedStudentArray);
    }

    // Radix Sort

    // array is null
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void radixSortException1() {
        int[] unsortedArray;
        Sorting.radixSort(null);
    }

    // test minimum interger value
    // I initialy fail this test and had to change my divisor variable from an int to a
    // double
    @Test(timeout = TIMEOUT)
    public void radixSort1() {
        int[] unsortedArray = new int[1];
        unsortedArray[0] = Integer.MIN_VALUE; //-2,147,483,648

        int[] sortedArray = new int[1];

        sortedArray[0] = unsortedArray[0];

        Sorting.radixSort(unsortedArray);

        assertArrayEquals(unsortedArray, sortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void radixSort2() {
        int[] unsortedArray = new int[5];
        unsortedArray[0] = Integer.MIN_VALUE;       //-2,147,483,648
        unsortedArray[1] = Integer.MIN_VALUE + 1;   //-2,147,483,647 
        unsortedArray[2] = Integer.MIN_VALUE + 2;   //-2,147,483,646
        unsortedArray[3] = Integer.MIN_VALUE + 3;   //-2,147,483,645
        unsortedArray[4] = Integer.MIN_VALUE + 4;   //-2,147,483,644

        int[] sortedArray = new int[5];

        sortedArray[0] = unsortedArray[0];
        sortedArray[1] = unsortedArray[1];
        sortedArray[2] = unsortedArray[2];
        sortedArray[3] = unsortedArray[3];
        sortedArray[4] = unsortedArray[4];

        Sorting.radixSort(unsortedArray);

        assertArrayEquals(unsortedArray, sortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void radixSort3() {
        int[] unsortedArray = new int[5];
        unsortedArray[0] = Integer.MIN_VALUE + 4;   //-2,147,483,644
        unsortedArray[1] = Integer.MIN_VALUE + 3;   //-2,147,483,645
        unsortedArray[2] = Integer.MIN_VALUE + 2;   //-2,147,483,646
        unsortedArray[3] = Integer.MIN_VALUE + 1;   //-2,147,483,647
        unsortedArray[4] = Integer.MIN_VALUE;       //-2,147,483,648

        int[] sortedArray = new int[5];

        sortedArray[0] = unsortedArray[4];
        sortedArray[1] = unsortedArray[3];
        sortedArray[2] = unsortedArray[2];
        sortedArray[3] = unsortedArray[1];
        sortedArray[4] = unsortedArray[0];

        Sorting.radixSort(unsortedArray);

        assertArrayEquals(unsortedArray, sortedArray);
    }

    // test maximum interger value
    @Test(timeout = TIMEOUT)
    public void radixSort4() {
        int[] unsortedArray = new int[1];
        int[] sortedArray = new int[1];

        unsortedArray[0] = Integer.MAX_VALUE;   //2,147,483,647

        sortedArray[0] = unsortedArray[0];

        Sorting.radixSort(unsortedArray);

        assertArrayEquals(unsortedArray, sortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void radixSort5() {
        int[] unsortedArray = new int[5];
        unsortedArray[0] = Integer.MAX_VALUE - 4;   //2,147,483,643
        unsortedArray[1] = Integer.MAX_VALUE - 3;   //2,147,483,644
        unsortedArray[2] = Integer.MAX_VALUE - 2;   //2,147,483,645
        unsortedArray[3] = Integer.MAX_VALUE - 1;   //2,147,483,646
        unsortedArray[4] = Integer.MAX_VALUE;       //2,147,483,647

        int[] sortedArray = new int[5];

        sortedArray[0] = unsortedArray[0];
        sortedArray[1] = unsortedArray[1];
        sortedArray[2] = unsortedArray[2];
        sortedArray[3] = unsortedArray[3];
        sortedArray[4] = unsortedArray[4];

        Sorting.radixSort(unsortedArray);

        assertArrayEquals(unsortedArray, sortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void radixSort6() {
        int[] unsortedArray = new int[5];
        unsortedArray[0] = Integer.MAX_VALUE;       //2,147,483,647
        unsortedArray[1] = Integer.MAX_VALUE - 1;   //2,147,483,646
        unsortedArray[2] = Integer.MAX_VALUE - 2;   //2,147,483,645    
        unsortedArray[3] = Integer.MAX_VALUE - 3;   //2,147,483,644
        unsortedArray[4] = Integer.MAX_VALUE - 4;   //2,147,483,643

        int[] sortedArray = new int[5];

        sortedArray[0] = unsortedArray[4];
        sortedArray[1] = unsortedArray[3];
        sortedArray[2] = unsortedArray[2];
        sortedArray[3] = unsortedArray[1];
        sortedArray[4] = unsortedArray[0];

        Sorting.radixSort(unsortedArray);

        assertArrayEquals(unsortedArray, sortedArray);
    }

    private static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static Comparator<Student> getIDComparator() {
            return new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getID() - s2.getID();
                }
            };
        }
    }


}