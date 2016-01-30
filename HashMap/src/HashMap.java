import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Your implementation of a HashMap, using external chaining as your collision
 * policy.  Read the PDF for more instructions on external chaining.
 *
 * @author Daniel Kim
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        // Initialize your hashtable here.
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public V add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        } else if (value == null) {
            throw new IllegalArgumentException("Value should not be null.");
        }
        if ((double) (size + 1) / table.length > MAX_LOAD_FACTOR) {
            int arrayLength = table.length;
            MapEntry<K, V>[] copy = table;
            table = (MapEntry<K, V>[]) new MapEntry[arrayLength * 2 + 1];
            size = 0;
            for (int i = 0; i < arrayLength; i++) {
                if (copy[i] != null) {
                    add(copy[i].getKey(), copy[i].getValue());
                    while (copy[i].getNext() != null) {
                        add(copy[i].getNext().getKey()
                                , copy[i].getNext().getValue());
                        copy[i] = copy[i].getNext();

                    }
                }
            }
        }
        MapEntry<K, V> current = table[Math.abs(key.hashCode()) % table.length];
        MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
        while (current != null) {
            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            } else if (current.getNext() == null) {
                current.setNext(newEntry);
                size++;
                return null;
            }
            current = current.getNext();
        }
        table[Math.abs(key.hashCode()) % table.length] = newEntry;
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        MapEntry<K, V> removed = table[Math.abs(key.hashCode()) % table.length];
        int hash = Math.abs(key.hashCode()) % table.length;
        if (removed != null) {
            V oldValue = removed.getValue();
            if (removed.getKey().equals(key)) {
                if (removed.getNext() != null) {
                    table[hash] = removed.getNext();
                } else {
                    table[hash] = null;
                }
                size--;
                return oldValue;
            } else {
                while (removed.getNext() != null) {
                    oldValue = removed.getNext().getValue();
                    if (removed.getNext().getKey().equals(key)) {
                        size--;
                        removed.setNext(removed.getNext().getNext());
                        return oldValue;
                    }
                    removed = removed.getNext();
                }
            }
        }

//        if (removed != null) {
//            if (removed.getKey().equals(key)) {
//                V value = removed.getValue();
//                if (removed.getNext() == null) {
//                    table[Math.abs(key.hashCode()) % table.length] = null;
//                } else {
//                    table[Math.abs(key.hashCode()) % table.length]
//                            = removed.getNext();
//                }
//                size--;
//                return value;
//            } else {
//                while (removed.getNext() != null) {
//                    MapEntry<K, V> removed2 = removed.getNext();
//                    if (removed.getNext().getKey().equals(key)) {
//                        MapEntry<K, V> next = removed.getNext().getNext();
//                        removed.setNext(next);
//                        size--;
//                        return removed2.getValue();
//                    }
//                    removed = removed.getNext();
//                }
//            }
//        }
        throw new NoSuchElementException("Key not found in hashmap");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        int hash = Math.abs(key.hashCode()) % table.length;
        if (table[hash] != null) {
            MapEntry<K, V> next = table[hash].getNext();
            if (table[hash].getKey().equals(key)) {
                return table[hash].getValue();
            } //else {
                while (table[hash].getNext() != null) {
                    if (next.getKey().equals(key)) {
                        return next.getValue();
                    }
                    next = next.getNext();
                }
            //}
        }
        throw new NoSuchElementException("Key not found in hashmap");
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        int hash = Math.abs(key.hashCode()) % table.length;
        if (table[hash] != null) {
            MapEntry<K, V> next = table[hash].getNext();
            if (table[hash].getKey().equals(key)) {
                return true;
            } else {
                while (next != null) {
                    if (next.getKey().equals(key)) {
                        return true;
                    }
                    next = next.getNext();
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        MapEntry<K, V> next;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                set.add(table[i].getKey());
                next = table[i];
                while(next.getNext() != null) {
                    set.add(next.getNext().getKey());
                    next = next.getNext();
                }
//                if (table[i].getNext() != null) {
//                    next = table[i].getNext();
//                    while (next != null) {
//                        set.add(next.getKey());
//                        next = next.getNext();
//                    }
//                }
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                list.add(table[i].getValue());
//                if (table[i].getNext() != null) {
//                    MapEntry<K, V> next = table[i].getNext();
//                    while (next != null) {
//                        list.add(next.getValue());
//                        next = next.getNext();
//                    }
                //}
                MapEntry<K, V> current = table[i];
                while (current.getNext() != null) {
                    list.add(current.getNext().getValue());
                    current = current.getNext();
                }
            }
        }
        return list;
    }

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE.  IT IS FOR TESTING ONLY
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] toArray() {
        return table;
    }

}