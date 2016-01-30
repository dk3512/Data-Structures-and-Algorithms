import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PaulJUnit6 {
    private AVL<Integer> avl;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        avl = new AVL<Integer>();
    }

    // constructor
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void constructorException1() {
        ArrayList<Integer> list = null;
        AVL<Integer> avl2 = new AVL<Integer>(list);
    }

    @Test(timeout = TIMEOUT)
    public void constructor1() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        AVL<Integer> avl2 = new AVL<Integer>(list);

        //test data
        assertEquals((Integer) 4, avl2.getRoot().getData());
        assertEquals((Integer) 2, avl2.getRoot().getLeft().getData());
        assertEquals((Integer) 5, avl2.getRoot().getRight().getData());
        assertEquals((Integer) 1, avl2.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl2.getRoot().getLeft().getRight().getData());
        //test height
        assertEquals(2, avl2.getRoot().getHeight());
        assertEquals(1, avl2.getRoot().getLeft().getHeight());
        assertEquals(0, avl2.getRoot().getRight().getHeight());
        assertEquals(0, avl2.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl2.getRoot().getLeft().getRight().getHeight());
        //test balance factor
        assertEquals(1, avl2.getRoot().getBalanceFactor());
        assertEquals(0, avl2.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl2.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl2.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl2.getRoot().getLeft().getRight().getBalanceFactor());
        //nulls
        assertNull(avl2.getRoot().getLeft().getLeft().getLeft());
        assertNull(avl2.getRoot().getLeft().getLeft().getRight());
        assertNull(avl2.getRoot().getLeft().getRight().getLeft());
        assertNull(avl2.getRoot().getLeft().getRight().getRight());
        assertNull(avl2.getRoot().getRight().getLeft());
        assertNull(avl2.getRoot().getRight().getRight());


    }

    // add

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addException1() {
        avl.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void add1() {
        avl.add(1);

        //data
        assertEquals((Integer) 1, avl.getRoot().getData());
        //height
        assertEquals(0, avl.getRoot().getHeight());
        //balance factor
        assertEquals(0, avl.getRoot().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft());
        assertNull(avl.getRoot().getRight());
    }

    @Test(timeout = TIMEOUT)
    public void add2() {
        avl.add(1);
        avl.add(2);

        //data
        assertEquals((Integer) 1, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor 
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft());
        assertNull(avl.getRoot().getRight().getRight());
        assertNull(avl.getRoot().getRight().getLeft());
    }

    @Test(timeout = TIMEOUT)
    public void add3() {
        avl.add(2);
        avl.add(1);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        //balance factor 
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getRight());
        assertNull(avl.getRoot().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getRight());
    }

    // 1 right rotate
    @Test(timeout = TIMEOUT)
    public void add4() {
        avl.add(3);
        avl.add(2);
        avl.add(1);

        //test data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getData());
        //test height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //test balance factor
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getRight());
        assertNull(avl.getRoot().getRight().getLeft());
        assertNull(avl.getRoot().getRight().getRight());
    }

    // 1 left rotation
    @Test(timeout = TIMEOUT)
    public void add5() {
        avl.add(1);
        avl.add(2);
        avl.add(3);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor 
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getRight());
        assertNull(avl.getRoot().getRight().getLeft());
        assertNull(avl.getRoot().getRight().getRight());
    }

    // 1 right-left rotation
    @Test(timeout = TIMEOUT)
    public void add6() {
        avl.add(1);
        avl.add(3);
        avl.add(2);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor 
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getRight());
        assertNull(avl.getRoot().getRight().getLeft());
        assertNull(avl.getRoot().getRight().getRight());
    }

    // 1 left-right rotation
    @Test(timeout = TIMEOUT)
    public void add7() {
        avl.add(3);
        avl.add(1);
        avl.add(2);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor 
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getRight());
        assertNull(avl.getRoot().getRight().getLeft());
        assertNull(avl.getRoot().getRight().getRight());
    }

    // 2 right rotations
    @Test(timeout = TIMEOUT)
    public void add8() {
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        //test data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        //test balance factor
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getLeft().getRight());
        assertNull(avl.getRoot().getLeft().getRight().getLeft());
        assertNull(avl.getRoot().getLeft().getRight().getRight());
        assertNull(avl.getRoot().getRight().getLeft());
        assertNull(avl.getRoot().getRight().getRight());
    }

    // 1 right-left rotation
    @Test(timeout = TIMEOUT)
    public void add9() {
        avl.add(3);
        avl.add(2);
        avl.add(1);
        avl.add(4);
        avl.add(5);

        //test data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //test balance factor
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
        //nulls
        assertNull(avl.getRoot().getLeft().getLeft());
        assertNull(avl.getRoot().getLeft().getRight());
        assertNull(avl.getRoot().getRight().getLeft().getLeft());
        assertNull(avl.getRoot().getRight().getLeft().getRight());
        assertNull(avl.getRoot().getRight().getRight().getLeft());
        assertNull(avl.getRoot().getRight().getRight().getRight());
    }

    // 1 right and 1 right-left rotation
    @Test(timeout = TIMEOUT)
    public void add10() {
        avl.add(3);
        avl.add(2);
        avl.add(1);
        avl.add(5);
        avl.add(4);

        //test data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //test balance factor
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 1 right and 1 left-right rotation
    @Test(timeout = TIMEOUT)
    public void add11() {
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(1);
        avl.add(2);

        //test data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());

        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());

        //test balance factor
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    // 2 left rotations
    @Test(timeout = TIMEOUT)
    public void add12() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);

        //test data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //test balance factor
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 1 left and 1 right rotation
    @Test(timeout = TIMEOUT)
    public void add13() {
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(2);
        avl.add(1);

        //test data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());

        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());

        //test balance factor
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    // 1 left and 1 right-left rotation
    @Test(timeout = TIMEOUT)
    public void add14() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(5);
        avl.add(4);

        //test data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //test balance factor
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 1 left and 1 left-right rotation
    @Test(timeout = TIMEOUT)
    public void add15() {
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(1);
        avl.add(2);

        //test data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());

        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());

        //test balance factor
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    // 2 right-left rotations
    @Test(timeout = TIMEOUT)
    public void add16() {
        avl.add(1);
        avl.add(3);
        avl.add(2);
        avl.add(5);
        avl.add(4);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 1 right-left and 1 right rotation
    @Test(timeout = TIMEOUT)
    public void add17() {
        avl.add(3);
        avl.add(5);
        avl.add(4);
        avl.add(2);
        avl.add(1);

        //test data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());

        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());

        //test balance factor
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    // 1 right-left and 1 left rotation
    @Test(timeout = TIMEOUT)
    public void add18() {
        avl.add(1);
        avl.add(3);
        avl.add(2);
        avl.add(4);
        avl.add(5);

        //test data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //test height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //test balance factor
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 1 right-left and 1 left-right rotation
    public void add19() {
        avl.add(1);
        avl.add(5);
        avl.add(2);
        avl.add(3);
        avl.add(4);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 2 left-right rotations
    @Test(timeout = TIMEOUT)
    public void add20() {
        avl.add(5);
        avl.add(3);
        avl.add(4);
        avl.add(1);
        avl.add(2);

        //data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor 
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    // 1 left-right and 1 right rotation
    @Test(timeout = TIMEOUT)
    public void add21() {
        avl.add(5);
        avl.add(3);
        avl.add(4);
        avl.add(2);
        avl.add(1);

        //data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor 
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    // 1 left-right and 1 left rotation
    @Test(timeout = TIMEOUT)
    public void add22() {
        avl.add(3);
        avl.add(1);
        avl.add(2);
        avl.add(4);
        avl.add(5);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // 1 left-right and 1 right-left rotation
    @Test(timeout = TIMEOUT)
    public void add23() {
        avl.add(3);
        avl.add(1);
        avl.add(2);
        avl.add(5);
        avl.add(4);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    // remove

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeException1() {
        avl.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeException2() {
        avl.remove(1);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeException3() {
        avl.add(1);
        avl.remove(2);
    }

    @Test(timeout = TIMEOUT)
    public void remove1() {
        avl.add(1);
        avl.remove(1);

        assertNull(avl.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void remove2() {
        avl.add(1);
        avl.add(2);

        avl.remove(2);

        //data
        assertEquals((Integer) 1, avl.getRoot().getData());
        //height
        assertEquals(0, avl.getRoot().getHeight());
        //balance factor
        assertEquals(0, avl.getRoot().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove3() {
        avl.add(1);
        avl.add(2);

        avl.remove(1);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        //height
        assertEquals(0, avl.getRoot().getHeight());
        //balance factor
        assertEquals(0, avl.getRoot().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove4() {
        avl.add(2);
        avl.add(1);
        avl.add(3);

        avl.remove(2);

        //data
        assertEquals((Integer) 1, avl.getRoot().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor
        assertEquals(-1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove6() {
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        avl.remove(4);

        //data
        assertEquals((Integer) 2, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove7() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);

        avl.remove(2);

        //data
        assertEquals((Integer) 3, avl.getRoot().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getRight().getData());
        //height
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove8() {
        avl.add(2);
        avl.add(1);
        avl.add(4);
        avl.add(3);
        avl.add(5);

        avl.remove(1);

        //data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        //balance factor
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(-1, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove9() {
        avl.add(3);
        avl.add(1);
        avl.add(7);
        avl.add(2);
        avl.add(5);
        avl.add(8);
        avl.add(4);
        avl.add(6);

        avl.remove(2);

        //data
        assertEquals((Integer) 5, avl.getRoot().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 7, avl.getRoot().getRight().getData());
        assertEquals((Integer) 6, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 8, avl.getRoot().getRight().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove10() {
        avl.add(6);
        avl.add(2);
        avl.add(8);
        avl.add(1);
        avl.add(4);
        avl.add(7);
        avl.add(3);
        avl.add(5);

        avl.remove(7);

        //data
        assertEquals((Integer) 4, avl.getRoot().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 1, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 6, avl.getRoot().getRight().getData());
        assertEquals((Integer) 5, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 8, avl.getRoot().getRight().getRight().getData());
        //height
        assertEquals(2, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(1, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void remove11() {
        avl.add(5);
        avl.add(2);
        avl.add(8);
        avl.add(1);
        avl.add(3);
        avl.add(7);
        avl.add(10);
        avl.add(4);
        avl.add(6);
        avl.add(9);
        avl.add(11);
        avl.add(12);

        avl.remove(1);

        //data
        assertEquals((Integer) 8, avl.getRoot().getData());
        assertEquals((Integer) 5, avl.getRoot().getLeft().getData());
        assertEquals((Integer) 3, avl.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 2, avl.getRoot().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 4, avl.getRoot().getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 7, avl.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 6, avl.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 10, avl.getRoot().getRight().getData());
        assertEquals((Integer) 9, avl.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 11, avl.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 12, avl.getRoot().getRight().getRight().getRight().getData());
        //height
        assertEquals(3, avl.getRoot().getHeight());
        assertEquals(2, avl.getRoot().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getRight().getHeight());
        assertEquals(1, avl.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getRight().getLeft().getHeight());
        assertEquals(2, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getLeft().getHeight());
        assertEquals(1, avl.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getRight().getRight().getHeight());
        //balance factor 
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getRight().getBalanceFactor());
        assertEquals(1, avl.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getRight().getLeft().getBalanceFactor());
        assertEquals(-1, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals(-1, avl.getRoot().getRight().getRight().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getRight().getRight().getBalanceFactor());
    }

    // get

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getException() {
        avl.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getException2() {
        avl.get(1);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getException3() {
        avl.add(5);
        avl.get(1);
    }

    @Test(timeout = TIMEOUT)
    public void testGet1() {
        avl.add(7);
        assertEquals((Integer) 7, avl.get(7));
    }

    @Test(timeout = TIMEOUT)
    public void testGet2() {
        avl.add(7);
        avl.add(5);
        avl.add(10);
        avl.add(6);
        avl.add(9);
        avl.add(11);

        assertEquals((Integer) 7, avl.get(7));
        assertEquals((Integer) 5, avl.get(5));
        assertEquals((Integer) 10, avl.get(10));
        assertEquals((Integer) 6, avl.get(6));
        assertEquals((Integer) 9, avl.get(9));
        assertEquals((Integer) 11, avl.get(11));
    }

    // contains

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsException1() {
        avl.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContains1() {
        assertFalse(avl.contains(1));
    }

    @Test(timeout = TIMEOUT)
    public void testContains2() {
        avl.add(5);
        assertFalse(avl.contains(1));
    }

    @Test(timeout = TIMEOUT)
    public void testContains3() {
        avl.add(7);
        assertTrue(avl.contains(7));
    }

    @Test(timeout = TIMEOUT)
    public void testContains4() {
        avl.add(7);
        avl.add(5);
        avl.add(10);
        avl.add(6);
        avl.add(9);
        avl.add(11);

        assertTrue(avl.contains(7));
        assertTrue(avl.contains(5));
        assertTrue(avl.contains(10));
        assertTrue(avl.contains(6));
        assertTrue(avl.contains(9));
        assertTrue(avl.contains(11));
    }

    @Test(timeout = TIMEOUT)
    public void testContains5() {
        avl.add(7);
        avl.add(5);
        avl.add(10);
        avl.add(6);
        avl.add(9);
        avl.add(11);

        assertFalse(avl.contains(1));
        assertFalse(avl.contains(2));
        assertFalse(avl.contains(3));
        assertFalse(avl.contains(4));
        assertFalse(avl.contains(8));
        assertFalse(avl.contains(12));
    }

    // size

    @Test(timeout = TIMEOUT)
    public void testSize1() {
        assertEquals(0, avl.size());
    }


    @Test(timeout = TIMEOUT)
    public void testSize2() {
        avl.add(7);
        assertEquals(1, avl.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize3() {
        avl.add(7);
        avl.add(5);

        assertEquals(2, avl.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize4() {
        avl.add(7);
        avl.add(5);
        avl.add(10);
        avl.add(6);
        avl.add(9);
        avl.add(11);

        assertEquals(6, avl.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize5() {
        avl.add(7);
        avl.remove(7);

        assertEquals(0, avl.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize6() {
        avl.add(7);
        avl.add(5);
        avl.add(10);
        avl.add(6);
        avl.add(9);
        avl.add(11);

        assertEquals(6, avl.size());
        avl.remove(7);
        assertEquals(5, avl.size());
        avl.remove(5);
        assertEquals(4, avl.size());
        avl.remove(10);
        assertEquals(3, avl.size());
        avl.remove(6);
        assertEquals(2, avl.size());
        avl.remove(9);
        assertEquals(1, avl.size());
        avl.remove(11);
        assertEquals(0, avl.size());
    }

    // preorder

    @Test(timeout = TIMEOUT)
    public void testPreorder1() {
        List<Integer> preorder = new ArrayList<>();
        assertEquals(preorder, avl.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder2() {
        avl.add(1);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(1);

        assertEquals(preorder, avl.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder3() {
        avl.add(1);
        avl.add(2);
        avl.add(3);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(2);
        preorder.add(1);
        preorder.add(3);

        assertEquals(preorder, avl.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder4() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);
        avl.add(10);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(4);
        preorder.add(2);
        preorder.add(1);
        preorder.add(3);
        preorder.add(8);
        preorder.add(6);
        preorder.add(5);
        preorder.add(7);
        preorder.add(9);
        preorder.add(10);


        assertEquals(preorder, avl.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder5() {
        avl.add(10);
        avl.add(9);
        avl.add(8);
        avl.add(7);
        avl.add(6);
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(7);
        preorder.add(3);
        preorder.add(2);
        preorder.add(1);
        preorder.add(5);
        preorder.add(4);
        preorder.add(6);
        preorder.add(9);
        preorder.add(8);
        preorder.add(10);

        assertEquals(preorder, avl.preorder());
    }

    // postorder

    @Test(timeout = TIMEOUT)
    public void testPostorder1() {
        List<Integer> postorder = new ArrayList<>();
        assertEquals(postorder, avl.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder2() {
        avl.add(1);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(1);

        assertEquals(postorder, avl.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder3() {
        avl.add(1);
        avl.add(2);
        avl.add(3);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(1);
        postorder.add(3);
        postorder.add(2);

        assertEquals(postorder, avl.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder4() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);
        avl.add(10);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(1);
        postorder.add(3);
        postorder.add(2);
        postorder.add(5);
        postorder.add(7);
        postorder.add(6);
        postorder.add(10);
        postorder.add(9);
        postorder.add(8);
        postorder.add(4);

        assertEquals(postorder, avl.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder5() {
        avl.add(10);
        avl.add(9);
        avl.add(8);
        avl.add(7);
        avl.add(6);
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(1);
        postorder.add(2);
        postorder.add(4);
        postorder.add(6);
        postorder.add(5);
        postorder.add(3);
        postorder.add(8);
        postorder.add(10);
        postorder.add(9);
        postorder.add(7);

        assertEquals(postorder, avl.postorder());
    }

    // inorder

    @Test(timeout = TIMEOUT)
    public void testInorder1() {
        List<Integer> inorder = new ArrayList<>();
        assertEquals(inorder, avl.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder2() {
        avl.add(1);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);

        assertEquals(inorder, avl.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder3() {
        avl.add(1);
        avl.add(2);
        avl.add(3);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);

        assertEquals(inorder, avl.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder4() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);
        avl.add(10);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);
        inorder.add(9);
        inorder.add(10);

        assertEquals(inorder, avl.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder5() {
        avl.add(10);
        avl.add(9);
        avl.add(8);
        avl.add(7);
        avl.add(6);
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);
        inorder.add(9);
        inorder.add(10);

        assertEquals(inorder, avl.inorder());
    }

    // levelorder

    @Test(timeout = TIMEOUT)
    public void testLevelorder1() {
        List<Integer> levelorder = new ArrayList<>();
        assertEquals(levelorder, avl.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder2() {
        avl.add(1);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(1);

        assertEquals(levelorder, avl.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder3() {
        avl.add(1);
        avl.add(2);
        avl.add(3);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(2);
        levelorder.add(1);
        levelorder.add(3);

        assertEquals(levelorder, avl.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder4() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);
        avl.add(10);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(4);
        levelorder.add(2);
        levelorder.add(8);
        levelorder.add(1);
        levelorder.add(3);
        levelorder.add(6);
        levelorder.add(9);
        levelorder.add(5);
        levelorder.add(7);
        levelorder.add(10);

        assertEquals(levelorder, avl.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder5() {
        avl.add(10);
        avl.add(9);
        avl.add(8);
        avl.add(7);
        avl.add(6);
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(7);
        levelorder.add(3);
        levelorder.add(9);
        levelorder.add(2);
        levelorder.add(5);
        levelorder.add(8);
        levelorder.add(10);
        levelorder.add(1);
        levelorder.add(4);
        levelorder.add(6);

        assertEquals(levelorder, avl.levelorder());
    }

    // clear
    @Test(timeout = TIMEOUT)
    public void testClear1() {
        avl.clear();

        assertEquals(0, avl.size());
        assertNull(avl.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testClear2() {
        avl.add(1);
        assertEquals(1, avl.size());
        assertEquals((Integer)1, avl.getRoot().getData());

        avl.clear();

        assertEquals(0, avl.size());
        assertNull(avl.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testClear3() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);
        avl.add(10);

        assertEquals(10, avl.size());
        assertEquals((Integer)4, avl.getRoot().getData());

        avl.clear();

        assertEquals(0, avl.size());
        assertNull(avl.getRoot());
    }

    // height

    @Test(timeout = TIMEOUT)
    public void testHeight1() {
        assertEquals(-1, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight2() {
        avl.add(1);

        assertEquals(0, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight3() {
        avl.add(1);
        avl.add(2);

        assertEquals(1, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight4() {
        avl.add(1);
        avl.add(2);
        avl.add(3);

        assertEquals(1, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight5() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);

        assertEquals(2, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight6() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);

        assertEquals(2, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight7() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);


        assertEquals(2, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight8() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);


        assertEquals(2, avl.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight9() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);

        assertEquals(3, avl.height());
    }

    // depth

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void depthException1() {
        avl.depth(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void depthException2() {
        avl.depth(1);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void depthException3() {
        avl.add(5);
        avl.depth(1);
    }

    @Test(timeout = TIMEOUT)
    public void depth1() {
        avl.add(1);

        assertEquals(1, avl.depth(1));
    }

    @Test(timeout = TIMEOUT)
    public void depth2() {
        avl.add(1);
        avl.add(2);

        assertEquals(1, avl.depth(1));
        assertEquals(2, avl.depth(2));
    }


    @Test(timeout = TIMEOUT)
    public void depth3() {
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);
        avl.add(10);

        assertEquals(3, avl.depth(1));
        assertEquals(2, avl.depth(2));
        assertEquals(3, avl.depth(3));
        assertEquals(1, avl.depth(4));
        assertEquals(4, avl.depth(5));
        assertEquals(3, avl.depth(6));
        assertEquals(4, avl.depth(7));
        assertEquals(2, avl.depth(8));
        assertEquals(3, avl.depth(9));
        assertEquals(4, avl.depth(10));

    }

    @Test(timeout = TIMEOUT)
    public void depth4() {
        avl.add(10);
        avl.add(9);
        avl.add(8);
        avl.add(7);
        avl.add(6);
        avl.add(5);
        avl.add(4);
        avl.add(3);
        avl.add(2);
        avl.add(1);

        assertEquals(4, avl.depth(1));
        assertEquals(3, avl.depth(2));
        assertEquals(2, avl.depth(3));
        assertEquals(4, avl.depth(4));
        assertEquals(3, avl.depth(5));
        assertEquals(4, avl.depth(6));
        assertEquals(1, avl.depth(7));
        assertEquals(3, avl.depth(8));
        assertEquals(2, avl.depth(9));
        assertEquals(3, avl.depth(10));
    }
}