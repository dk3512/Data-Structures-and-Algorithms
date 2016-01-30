import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class BriansLitAFTests {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testLeftRotationReplace() {
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);

        assertEquals((Integer) 150, avlTree.getRoot().getData());

        avlTree.add(175);
        avlTree.add(180);
        avlTree.add(176);

        assertEquals(2, avlTree.height());
        assertEquals((Integer) 175, avlTree.getRoot().getData());
        assertEquals((Integer) 180, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 100, avlTree.getRoot().getLeft().getLeft().getData());
    }

    @Test(timeout = TIMEOUT)
    public void removeRoot() {
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        assertEquals((Integer) 150, avlTree.remove(150));

        assertEquals(2, avlTree.size());
        assertEquals((Integer) 100, avlTree.getRoot().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        avlTree.add(1);
        avlTree.clear();

        assertEquals(0, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void collectionConstructor1() {
        ArrayList<Integer> list = new ArrayList<Integer>(10);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        AVL<Integer> tree = new AVL<Integer>(list);

        assertEquals(5, tree.size());
        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void collectionConstructor2() {
        ArrayList<Integer> list = new ArrayList<Integer>(10);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(null);

        AVL<Integer> tree = new AVL<Integer>(list);
    }

    @Test(timeout = TIMEOUT)
    public void preorderTraversal() {
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);

        List<Integer> list = new ArrayList<Integer>(9);
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
    public void postorderTraversal() {
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);

        List<Integer> list = new ArrayList<Integer>(9);
        list.add(0);
        list.add(15);
        list.add(10);
        list.add(50);
        list.add(25);
        list.add(100);
        list.add(200);
        list.add(150);
        list.add(75);

        assertEquals(avlTree.postorder(), list);
    }

    @Test(timeout = TIMEOUT)
    public void inorderTraversal() {
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);

        List<Integer> list = new ArrayList<Integer>(9);
        list.add(0);
        list.add(10);
        list.add(15);
        list.add(25);
        list.add(50);
        list.add(75);
        list.add(100);
        list.add(150);
        list.add(200);

        assertEquals(avlTree.inorder(), list);
    }

    @Test(timeout = TIMEOUT)
    public void levelorderTraversal() {
        avlTree.add(100);
        avlTree.add(150);
        avlTree.add(200);
        avlTree.add(50);
        avlTree.add(75);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(10);
        avlTree.add(0);

        List<Integer> list = new ArrayList<Integer>(9);
        list.add(75);
        list.add(25);
        list.add(150);
        list.add(10);
        list.add(50);
        list.add(100);
        list.add(200);
        list.add(0);
        list.add(15);

        assertEquals(avlTree.levelorder(), list);
    }

    @Test(timeout = TIMEOUT)
    public void removalEdge1() {
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.add(3);
        avlTree.add(5);
        avlTree.remove(1);

        assertEquals((Integer) 4, avlTree.getRoot().getData());
        assertEquals((Integer) 5, avlTree.getRoot().getRight().getData());
        assertEquals(2, avlTree.height());
        assertEquals(false, avlTree.contains(1));
    }

    @Test(timeout = TIMEOUT)
    public void heightOfEmptyTree() {
        assertEquals(-1, avlTree.height());
    }
}