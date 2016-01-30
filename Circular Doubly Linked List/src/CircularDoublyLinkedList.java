import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularDoublyLinkedList
 *
 * @author Daniel Kim
 * @version 1.0
 */
public class CircularDoublyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Creates an empty circular doubly-linked list.
     */
    public CircularDoublyLinkedList() {

    }

    /**
     * Creates a circular doubly-linked list with
     * {@code data} added to the list in order.
     * @param data The data to be added to the LinkedList.
     * @throws java.lang.IllegalArgumentException if {@code data} is null or any
     * item in {@code data} is null.
     */
    public CircularDoublyLinkedList(T[] data) {
        LinkedListNode<T> current = head;
        if (data == null) {
            throw new IllegalArgumentException("Data should not "
                    + "be null");
        } else if (data != null) {
            for (int counter = 0; counter < data.length; counter++) {
                if (data[counter] == null) {
                    throw new IllegalArgumentException("Item in data "
                            + "should not be null.");
                } else {
                    addToBack(data[counter]);
                    size++;
                }
            }
        }
    }

    @Override
    public void addAtIndex(int index, T data) {
        LinkedListNode<T> next = head;
        LinkedListNode<T> previous = head.getPrevious();
        int counter = 0;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should "
                    + "not be negative.");
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Index should "
                    + "not be greater than size.");
        } else if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        } else if (index == 0) {
            head = new LinkedListNode<T>(data, previous, next);
            previous.setNext(head);
            next.setPrevious(head);
        } else if (index == size) {
            LinkedListNode<T> newest = new LinkedListNode<T>(data,
                    previous, head);
            previous.setNext(newest);
            head.setPrevious(newest);
        } else {
            while (counter < index - 1) {
                previous = previous.getNext();
                next = next.getNext();
                counter++;
            }
            LinkedListNode<T> newest = new LinkedListNode<T>(data,
                    previous.getNext(), next.getNext());
            previous.getNext().setNext(newest);
            next.getNext().setPrevious(newest);
        }
        size++;
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> current = head;
        int counter = 0;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should "
                    + "not be negative.");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index should " +
                    "be less than size.");
        } else if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return head.getPrevious().getData();
        } else {
            while(counter < index - 1) {
                current = current.getNext();
                counter++;
            }
            return current.getNext().getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        int counter = 0;
        LinkedListNode<T> next = head.getNext().getNext();
        LinkedListNode<T> previous = head.getPrevious();
        LinkedListNode<T> current = head;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "positive or zero.");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "less than size.");
        } else if (index == 0) {
            head = head.getNext();
            previous.setNext(head);
            head.setPrevious(previous);
            size--;
            return current.getData();
        } else if (index == size - 1) {
            previous = previous.getPrevious();
            previous.setNext(head);
            head.setPrevious(previous);
            size--;
            return current.getPrevious().getData();
        } else {
            while (counter < index - 1) {
                current = current.getNext();
                counter++;
            }
            LinkedListNode<T> temp = current.getNext().getNext();
            LinkedListNode<T> removed = current.getNext();
            temp.setPrevious(current);
            current.setNext(temp);
            size--;
            return removed.getData();
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        }
        LinkedListNode<T> previous = head.getPrevious();
        head = new LinkedListNode<T>(data, previous, head);
        previous.setNext(head);
        head.setPrevious(previous);
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        }
        LinkedListNode<T> previous = head.getPrevious();
        LinkedListNode<T> newest = new LinkedListNode<T>(data, previous, head);
        previous.setNext(newest);
        head.setPrevious(newest);
        if (size == 0) {
            head = newest;
        }
        size++;
    }

    @Override
    public T removeFromFront() {
        LinkedListNode<T> previous = head.getPrevious();
        LinkedListNode<T> removed = head;
        head = head.getNext();
        previous.setNext(head);
        head.setPrevious(previous);
        size--;
        return removed.getData();
    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> previous = head.getPrevious().getPrevious();
        LinkedListNode<T> removed = previous.getPrevious();
        previous.setNext(head);
        head.setPrevious(previous);
        size--;
        return removed.getData();
    }

    @Override
    public int removeFirstOccurrence(T data) {
        LinkedListNode<T> current = head;
        LinkedListNode<T> next = head.getNext();
        LinkedListNode<T> previous = head.getPrevious();
        int counter = 0;
        if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        }
        //LinkedListNode<T> current = head;
        while (counter < size) {
            if (current.getData().equals(data)) {
                if (counter == 0) {
                    head = head.getNext();
                    previous.setNext(head);
                    head.setPrevious(previous);
                    return counter;
                }
                previous.setNext(next);
                next.setPrevious(previous);
                return counter;
            }
            counter++;
            previous = previous.getNext();
            next = next.getNext();
            current = current.getNext();
        }
        throw new NoSuchElementException("Data was "
                + "not found.");
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        LinkedListNode<T> current = head;
        LinkedListNode<T> next = head.getNext();
        LinkedListNode<T> previous = head.getPrevious();
        int counter = 0;
        boolean b = false;
        if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        }
        while (counter < size) {
            if (current.getData().equals(data)) {
                if (counter == 0) {
                    head = head.getNext();
                    previous.setNext(head);
                    head.setPrevious(previous);
                }
                previous.setNext(next);
                next.setPrevious(previous);
                b = true;
            }
            counter++;
            previous = previous.getNext();
            next = next.getNext();
            current = current.getNext();
        }
        return b;
    }

    @Override
    public Object[] toArray() {
        LinkedListNode<T> current = head;
        Object[] array = new Object[size];
        for (int counter = 0; counter < size; counter++) {
            array[counter] = current.getData();
            current = current.getNext();
        }
        return array;
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
        head = null;
        size = 0;
    }

    /* DO NOT MODIFY THIS METHOD */
    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }
}
