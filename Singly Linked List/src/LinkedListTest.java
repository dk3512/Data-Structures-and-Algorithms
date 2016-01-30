import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LinkedListTest {

    private LinkedListInterface<String> stringList;
    private LinkedListInterface<Integer> intList;

    @Rule
    public Timeout timeout = Timeout.millis(200);

    @Before
    public void setUp() throws Exception {
        stringList = new SinglyLinkedList<String>();
        intList = new SinglyLinkedList<Integer>();
    }

    @Test
    public void testAddAtIndex() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        stringList.addAtIndex(0, "String0");
        assertEquals("String0", stringList.get(0));

        stringList.addAtIndex(1, "String1");
        assertEquals("String1", stringList.get(1));

        stringList.addAtIndex(2, "String2");
        assertEquals("String2", stringList.get(2));

        stringList.addAtIndex(1, "String15");
        assertEquals("String15", stringList.get(1));

        int size = stringList.size();
        stringList.addAtIndex(size - 1, "String" + (size - 1));
        assertEquals("String" + (size - 1), stringList.get(size - 1));
    }

    @Test
    public void testGet() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        stringList.addToBack("String1");
        assertEquals("String1", stringList.get(0));

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
            intList.addToBack(i);
        }

        for (int i = 0; i < 1000; i+=4) {
            assertEquals("String" + i, stringList.get(i + 1));
            assertEquals((Integer) i, intList.get(i));
        }

        assertEquals("String999", stringList.get(999 + 1));
        assertEquals((Integer) 999, intList.get(999));
    }

    @Test
    public void testRemoveAtIndex() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        stringList.addToBack("String1");
        assertEquals("String1", stringList.removeAtIndex(0));

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
            intList.addToBack(i);
        }

        assertEquals("String499", stringList.removeAtIndex(499));
        assertEquals("String399", stringList.removeAtIndex(399));
        assertEquals("String599", stringList.removeAtIndex(597));
        assertEquals((Integer) 499, intList.removeAtIndex(499));
        assertEquals((Integer) 0, intList.removeAtIndex(0));
        assertEquals((Integer) 999, intList.removeAtIndex(997));
    }

    @Test
    public void testAddToFront() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.addToFront("String" + i);
            assertEquals(i + 1, stringList.size());
            assertEquals("String" + i, stringList.get(0));

            intList.addToFront(i);
            assertEquals(i + 1, intList.size());
            assertEquals((Integer) i, intList.get(0));
        }
    }

    @Test
    public void testAddToBack() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
            assertEquals(i + 1, stringList.size());
            assertEquals("String" + i, stringList.get(i));

            intList.addToBack(i);
            assertEquals(i + 1, intList.size());
            assertEquals((Integer) i, intList.get(i));
        }
    }

    @Test
    public void testRemoveFromFront() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertNull(stringList.removeFromFront());

        stringList.addToBack("String1");
        assertEquals("String1", stringList.removeFromFront());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
        }

        intList.addToBack(1);

        assertEquals("String0", stringList.removeFromFront());
        assertEquals((Integer) 1, intList.removeFromFront());
    }

    @Test
    public void testRemoveFromBack() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertNull(stringList.removeFromBack());

        stringList.addToBack("String1");
        assertEquals("String1", stringList.removeFromBack());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
        }

        intList.addToBack(1);

        assertEquals("String999", stringList.removeFromBack());
        assertEquals((Integer) 1, intList.removeFromBack());
    }

    @Test
    public void testRemoveFirstOccurrence() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        stringList.addToBack("Hello");
        assertEquals(0, stringList.removeFirstOccurrence("Hello"));

        stringList.addToBack("Hello");
        stringList.addToBack("Hello2");
        stringList.addToBack("Hello1");
        stringList.addToBack("Hello");
        stringList.addToBack("Hello3");
        stringList.addToBack("Hello1");

        intList.addToBack(0);
        intList.addToBack(2);
        intList.addToBack(3);
        intList.addToBack(6);
        intList.addToBack(3);
        intList.addToBack(4);

        assertEquals(0, stringList.removeFirstOccurrence("Hello"));
        assertEquals(1, stringList.removeFirstOccurrence("Hello1"));

        assertEquals(2, intList.removeFirstOccurrence(3));
        assertEquals(4, intList.removeFirstOccurrence(4));

    }

    @Test
    public void testToArray() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        String[] stringItems = new String[10];
        Integer[] intItems = new Integer[0];

        for (int x = 0; x < stringItems.length; x++) {
            stringItems[x] = "a" + x;
            stringList.addToBack(stringItems[x]);
        }

        assertArrayEquals(stringItems, stringList.toArray());
        assertArrayEquals(intItems, intList.toArray());
    }

    @Test
    public void testIsEmpty() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertTrue(stringList.isEmpty());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + 1);
        }

        intList.addToBack(1);

        assertFalse(intList.isEmpty());
        assertFalse(stringList.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
            intList.addToBack(i);
        }

        for (int i = 0; i < 100; i++) {
            intList.removeFromBack();
        }

        assertEquals(1000, stringList.size());
        assertEquals(900, intList.size());

    }

    @Test
    public void testClear() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
        }

        stringList.clear();
        intList.clear();

        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
    }

    @Test
    public void testGetHead() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertNull(stringList.getHead());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
            if (i == 0) {
                assertEquals("String0", stringList.getHead().getData());
            }
        }

        intList.addToBack(1);

        assertEquals((Integer) 1, intList.getHead().getData());
        assertEquals("String0", stringList.getHead().getData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void RemoveIllegalArgumentTest() {
        stringList.removeFirstOccurrence(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddIllegalArgumentTest() {
        stringList.addAtIndex(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void FrontIllegalArgumentTest() {
        stringList.addToFront(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void BackIllegalArgumentTest() {
        stringList.addToBack(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.addAtIndex(1, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addLowerIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.addAtIndex(-1, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLowerIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.removeAtIndex(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfLowerBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.removeAtIndex(-1);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeEmptyNoSuchElementTest() {
        assertEquals(0, stringList.size());
        stringList.removeFirstOccurrence("Hello");
    }

    @Test(expected = NoSuchElementException.class)
    public void removeNoSuchElementTest() {
        assertEquals(0, stringList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.addToBack("String" + i);
        }

        stringList.removeFirstOccurrence("Hello");
    }
}
