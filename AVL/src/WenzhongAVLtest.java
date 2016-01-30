/**
 * @author Wenzhong Jin
 * @version 1.0
 * @update 10/9/2015 22:52
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class WenzhongAVLtest {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void ConstructorTest_null() {
        assertNull(avlTree.getRoot());
        assertEquals(0, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void ConstructorTest_collection() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        avlTree = new AVL<>(list);
        assertEquals(5, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void ConstructorTest_collection_null() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree = new AVL<>(list);
        assertEquals(0, avlTree.size());
        assertNull(avlTree.getRoot());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void AddTest_null() {
        avlTree.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_root() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(100);
        assertEquals((Integer) 100, avlTree.getRoot().getData());
        assertNull(avlTree.getRoot().getLeft());
        assertNull(avlTree.getRoot().getRight());
        assertEquals(0, avlTree.height());
        assertEquals(0, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_existed() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(100);
        assertEquals((Integer) 100, avlTree.getRoot().getData());
        assertNull(avlTree.getRoot().getLeft());
        assertNull(avlTree.getRoot().getRight());
        assertEquals(0, avlTree.height());
        assertEquals(0, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        avlTree.add(100);
        assertEquals((Integer) 100, avlTree.getRoot().getData());
        assertNull(avlTree.getRoot().getLeft());
        assertNull(avlTree.getRoot().getRight());
        assertEquals(0, avlTree.height());
        assertEquals(0, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_withoutRotation() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(1);
        avlTree.add(5);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 3, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 2, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(1, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(-1, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_leftRotate_once() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);

        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 2, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void AddTest_leftRotate_twice_right() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.add(5);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 2, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(-1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_leftRotate_twice_left() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(5);
        avlTree.add(10);
        avlTree.add(15);
        avlTree.add(6);
        avlTree.add(7);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 6, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 7, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_rightRotate_once() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(1);

        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 2, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_rightRotate_twice_left() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(5);
        avlTree.add(4);
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(1);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 4, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 2, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_rightRotate_twice_right() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(14);
        avlTree.add(13);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(-1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 14, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 13, avlTree.getRoot().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_doubleLeftRotate_once() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(5);
        avlTree.add(4);

        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 4, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_doubleLeftRotate_twice_right() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(17);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(-1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 17, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 20, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_doubleLeftRotate_twice_left() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(7);
        avlTree.add(6);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 6, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 7, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_doubleRightRotate_twice_right() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(15);
        avlTree.add(5);
        avlTree.add(10);
        avlTree.add(13);
        avlTree.add(14);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(-1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 14, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 13, avlTree.getRoot().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void AddTest_doubleRightRotate_twice_left() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(15);
        avlTree.add(5);
        avlTree.add(10);
        avlTree.add(3);
        avlTree.add(4);

        assertEquals(5, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void RemoveTest_null() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void RemoveTest_notInTree() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.remove(4);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void RemoveTest_removed() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.remove(3);
        avlTree.remove(3);
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_return() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        Integer returned = avlTree.remove(3);
        assertEquals((Integer) 3, returned);
        returned = avlTree.remove(1);
        assertEquals((Integer) 1, returned);
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_withoutRotation_root() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(1);
        avlTree.remove(1);
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        assertNull(avlTree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_withoutRotation_leaf() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.remove(1);
        assertEquals(2, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 2, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(-1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_withoutRotation_oneChild_right() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.remove(3);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 2, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_withoutRotation_oneChild_left() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(4);
        avlTree.remove(5);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_withoutRotation_twoChild() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(8);
        avlTree.add(16);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(9);
        avlTree.remove(5);
        assertEquals(9, avlTree.size());
        assertEquals(3, avlTree.height());
        assertEquals((Integer) 10, avlTree.getRoot().getData());
        assertEquals(3, avlTree.getRoot().getHeight());
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(-1, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 16, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getLeft().getData());
        assertEquals(2, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(1, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 8, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getLeft().getBalanceFactor());
        assertNull(avlTree.getRoot().getLeft().getLeft().getRight());
        assertEquals((Integer) 6, avlTree.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 9, avlTree.getRoot().getLeft().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_leftRotate_equal() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.remove(5);
        assertEquals(4, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 15, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 20, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(-1, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 13, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_leftRotate_leftNull() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(20);
        avlTree.remove(5);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 15, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 20, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_leftRotate_leftLess() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(20);
        avlTree.add(13);
        avlTree.add(25);
        avlTree.remove(5);
        assertEquals(6, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 15, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 20, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(-1, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 25, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 13, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_doubleLeftRotate() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(13);
        avlTree.remove(5);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 13, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_rightRotate_equal() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(6);
        avlTree.remove(15);
        assertEquals(4, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 5, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(-1, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(1, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 6, avlTree.getRoot().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_rightRotate_rightNull() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.remove(15);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 5, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_rightRotate_rightLess() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(8);
        avlTree.add(20);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.remove(15);
        assertEquals(7, avlTree.size());
        assertEquals(2, avlTree.height());
        assertEquals((Integer) 5, avlTree.getRoot().getData());
        assertEquals(2, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getRight().getData());
        assertEquals(1, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 20, avlTree.getRoot().getRight().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertEquals((Integer) 8, avlTree.getRoot().getRight().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 3, avlTree.getRoot().getLeft().getData());
        assertEquals(1, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertEquals((Integer) 1, avlTree.getRoot().getLeft().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals((Integer) 4, avlTree.getRoot().getLeft().getRight().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void RemoveTest_doubleRightRotate() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(20);
        avlTree.add(6);
        avlTree.remove(20);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        assertEquals((Integer) 6, avlTree.getRoot().getData());
        assertEquals(1, avlTree.getRoot().getHeight());
        assertEquals(0, avlTree.getRoot().getBalanceFactor());
        assertEquals((Integer) 10, avlTree.getRoot().getRight().getData());
        assertEquals(0, avlTree.getRoot().getRight().getHeight());
        assertEquals(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getData());
        assertEquals(0, avlTree.getRoot().getLeft().getHeight());
        assertEquals(0, avlTree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void GetTest_null() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(1);
        avlTree.add(5);
        avlTree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void GetTest_notInTree() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(1);
        avlTree.add(5);
        avlTree.get(6);
    }

    @Test(timeout = TIMEOUT)
    public void GetTest() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(1);
        avlTree.add(5);
        assertEquals((Integer) 3, avlTree.get(3));
        assertEquals((Integer) 2, avlTree.get(2));
        assertEquals((Integer) 4, avlTree.get(4));
        assertEquals((Integer) 1, avlTree.get(1));
        assertEquals((Integer) 5, avlTree.get(5));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void ContainsTest_null() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(1);
        avlTree.add(5);
        avlTree.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void ContainsTest() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(4);
        avlTree.add(1);
        avlTree.add(5);
        assertTrue(avlTree.contains(3));
        assertTrue(avlTree.contains(2));
        assertTrue(avlTree.contains(4));
        assertTrue(avlTree.contains(1));
        assertTrue(avlTree.contains(5));
        assertFalse(avlTree.contains(10));
        assertFalse(avlTree.contains(-1));
        assertFalse(avlTree.contains(0));
    }

    @Test(timeout = TIMEOUT)
    public void PreOrderTest_empty() {
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals(list, avlTree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void PreOrderTest_root() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        avlTree.add(1);
        assertEquals(list, avlTree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void PreOrderTest_4level() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(12);
        avlTree.add(14);
        avlTree.add(25);
        list.add(10);
        list.add(5);
        list.add(3);
        list.add(7);
        list.add(15);
        list.add(13);
        list.add(12);
        list.add(14);
        list.add(20);
        list.add(25);
        assertEquals(list, avlTree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void PreOrderTest_4level_rotation() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);
        list.add(75);
        list.add(25);
        list.add(10);
        list.add(0);
        list.add(15);
        list.add(50);
        list.add(150);
        list.add(100);
        list.add(200);
        assertEquals(list, avlTree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void PostOrderTest_empty() {
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals(list, avlTree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void PostOrderTest_root() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        avlTree.add(1);
        assertEquals(list, avlTree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void PostOrderTest_4level() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(12);
        avlTree.add(14);
        avlTree.add(25);
        list.add(3);
        list.add(7);
        list.add(5);
        list.add(12);
        list.add(14);
        list.add(13);
        list.add(25);
        list.add(20);
        list.add(15);
        list.add(10);
        assertEquals(list, avlTree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void PostOrderTest_4level_rotation() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);
        list.add(0);
        list.add(15);
        list.add(10);
        list.add(50);
        list.add(25);
        list.add(100);
        list.add(200);
        list.add(150);
        list.add(75);
        assertEquals(list, avlTree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void InOrderTest_empty() {
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals(list, avlTree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void InOrderTest_root() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        avlTree.add(1);
        assertEquals(list, avlTree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void InOrderTest_4level() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(12);
        avlTree.add(14);
        avlTree.add(25);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(10);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(20);
        list.add(25);
        assertEquals(list, avlTree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void InOrderTest_4level_rotation() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);
        list.add(0);
        list.add(10);
        list.add(15);
        list.add(25);
        list.add(50);
        list.add(75);
        list.add(100);
        list.add(150);
        list.add(200);
        assertEquals(list, avlTree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void LevelOrderTest_empty() {
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals(list, avlTree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void LevelOrderTest_root() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        avlTree.add(1);
        assertEquals(list, avlTree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void LevelOrderTest_4level() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(12);
        avlTree.add(14);
        avlTree.add(25);
        list.add(10);
        list.add(5);
        list.add(15);
        list.add(3);
        list.add(7);
        list.add(13);
        list.add(20);
        list.add(12);
        list.add(14);
        list.add(25);
        assertEquals(list, avlTree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void LevelOrderTest_4level_rotation() {
        ArrayList<Integer> list = new ArrayList<>();
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);
        list.add(75);
        list.add(25);
        list.add(150);
        list.add(10);
        list.add(50);
        list.add(100);
        list.add(200);
        list.add(0);
        list.add(15);
        assertEquals(list, avlTree.levelorder());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void DepthTest_null() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.depth(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void DepthTest_notInTree() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        avlTree.depth(4);
    }

    @Test(timeout = TIMEOUT)
    public void DepthTest() {
        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(12);
        avlTree.add(14);
        avlTree.add(25);
        assertEquals(1, avlTree.depth(10));
        assertEquals(2, avlTree.depth(5));
        assertEquals(2, avlTree.depth(15));
        assertEquals(3, avlTree.depth(3));
        assertEquals(3, avlTree.depth(7));
        assertEquals(3, avlTree.depth(13));
        assertEquals(3, avlTree.depth(20));
        assertEquals(4, avlTree.depth(12));
        assertEquals(4, avlTree.depth(14));
        assertEquals(4, avlTree.depth(25));
    }

    @Test(timeout = TIMEOUT)
    public void ClearTest() {
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
        avlTree.clear();
        assertEquals(0, avlTree.size());
        assertEquals(-1, avlTree.height());
    }
}
