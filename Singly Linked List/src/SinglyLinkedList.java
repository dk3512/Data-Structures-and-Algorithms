/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Daniel Kim
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        LinkedListNode<T> previous = head;
        int counter = 0;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "positive or zero.");
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "less than or equal to size.");
        } else if (data == null) {
            throw new IllegalArgumentException("Data should not be null");
        } else if (index == 0) {
            head = new LinkedListNode<T>(data, head);
        } else {
            while (counter < index - 1) {
                previous = previous.getNext();
                counter++;
            }
            LinkedListNode<T> newest = new LinkedListNode<T>(data,
                    previous.getNext());
            previous.setNext(newest);
        }
        size++;
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> current = head;
        int counter = 0;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should "
                    + "be positive or zero");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "less than size.");
        } else if (index == 0) {
            return head.getData();
        } else {
            while (counter < index - 1) { //could do current!=nul
                current = current.getNext();
                counter++;
            }
            return current.getNext().getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        LinkedListNode<T> current = head;
        int counter = 0;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "positive or zero.");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index should be "
                    + "less than size.");
        } else if (index == 0) {
            head = head.getNext();
        } else {
            while (counter < index - 1) {
                current = current.getNext();
                counter++;
            }
            LinkedListNode<T> temp = current.getNext().getNext();
            LinkedListNode<T> removed = current.getNext();
            current.setNext(temp);
            size--;
            return removed.getData();
        }
        size--;
        return current.getData();
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data "
                + "should not be null");
        }
        head = new LinkedListNode<T>(data, head);
        size++;
    }

    @Override
    public void addToBack(T data) {
        LinkedListNode<T> current = head;
        LinkedListNode<T> newest = new LinkedListNode<T>(data, null);
        if (data == null) {
            throw new IllegalArgumentException("Data "
                    + "should not be null");
        }
        if (head == null) {
            head = newest;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newest);
        }
        size++;
    }

    @Override
    public T removeFromFront() {
        LinkedListNode<T> current = head;
        if (size == 0) {
            return null;
        }
        head = head.getNext();
        size--;
        return current.getData();
    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> current = head;
        LinkedListNode<T> previous = head;
        int counter = 1;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            size--;
            head = null;
            return current.getData();
        } else {
            while (previous.getNext().getNext() != null) {
                previous = previous.getNext();
            }
            LinkedListNode<T> removed = previous.getNext();
            previous.setNext(null);
            size--;
            return removed.getData();
        }
    }

    @Override
    public int removeFirstOccurrence(T data) {
        LinkedListNode<T> current = head;
        LinkedListNode<T> previous = head;
        int counter = 1;
        if (data == null) {
            throw new IllegalArgumentException("Data "
                    + "should not be null.");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("The "
                    + "list is empty.");
        }
        if (size == 1) {
            if (current.getData() == data) {
                head = null;
                size--;
                return 0;
            } else {
                throw new NoSuchElementException("Data is "
                        + "not found.");
            }
        }
        if (current.getData() == data) {
            head = head.getNext();
            size--;
            return 0;
        }
        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData() == data) {
                previous.setNext(current.getNext());
                size--;
                return counter;
            }
            counter++;
            previous = previous.getNext();
        }
        throw new NoSuchElementException("Data is "
                + "not found.");
    }

    @Override
    public Object[] toArray() {
        int counter = 0;
        LinkedListNode<T> current = head;
        Object[] array = new Object[size];
        while (counter < size) {
            array[counter] = current.getData();
            counter++;
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

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
