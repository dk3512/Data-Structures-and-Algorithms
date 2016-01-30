import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element
     * in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null.");
        }
        for (T element: data) {
            if (element == null) {
                throw new IllegalArgumentException("Element "
                        + "should not be null.");
            }
            add(element);
        }
    }

    /**
     * BST algorithm that recursively searches
     * for the data or parent node of where the node
     * should be.
     * @param k The data being searched
     * @param v The node being passed through
     * @return The node that has the data k
     * or the parent node of where the node
     * should be.
     */
    private BSTNode<T> treeSearch(T k, BSTNode<T> v) {
        if (v.getLeft() == null && v.getRight() == null) {
            return v;
        }
        if (k.compareTo(v.getData()) < 0) {
            if (v.getLeft() == null) {
                return v;
            }
            return treeSearch(k, v.getLeft());
        } else if (k.compareTo(v.getData()) == 0) {
            return v;
        } else {
            if (v.getRight() == null) {
                return v;
            }
            return treeSearch(k, v.getRight());
        }
    }
    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        root = add(root, data);
    }

    /**
     * Recursive helper add method
     *
     * @param node the head of the subtree to search through
     * @param insertData the data to be inserted
     * @return reference to the head of the subtree
     */
    private BSTNode<T> add(BSTNode<T> node, T insertData) {
        if (node == null) {
            size++;
            //return new BSTNode<T>(insertData);
            node = new BSTNode<T>(insertData);
        }

        T curData =   node.getData();
//        if (insertData.compareTo(curData) == 0) {
//            return node;
//        } else
        if (insertData.compareTo(curData) < 0) {
            node.setLeft(add(node.getLeft(), insertData));
        } else if (insertData.compareTo(curData) > 0){
            node.setRight(add(node.getRight(), insertData));
        }
        return node;
    }
//    @Override
//    public void add(T data) {
//        if (data == null) {
//            throw new IllegalArgumentException("Data should "
//                    + "not be null.");
//        } else if (size == 0) {
//            root = new BSTNode<T>(data);
//            size++;
//        } else {
//            BSTNode<T> current = treeSearch(data, root);
//            BSTNode<T> newest = new BSTNode<T>(data);
//            if (data.compareTo(current.getData()) < 0) {
//                current.setLeft(newest);
//                size++;
//            } else if (data.compareTo(current.getData()) > 0) {
//                current.setRight(newest);
//                size++;
//            }
//        }
//    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        }
        BSTNode<T> dummyNode = new BSTNode<T>(null);
        root = removeHelper(root, data, dummyNode);
        size--;
        return dummyNode.getData();
    }

    /**
     * Helper method for remove
     * @param current Node being observed
     * @param data Data being found
     * @param dummy Holder for node with data
     * @return Node with data
     */
    private BSTNode<T> removeHelper(BSTNode<T> current,
                                    T data, BSTNode<T> dummy) {
        if (current == null) {
            throw new NoSuchElementException("Data is not in the list!");
        }
        if (current.getData().compareTo(data) < 0) {
            current.setRight(removeHelper(current.getRight(), data, dummy));
        } else if (current.getData().compareTo(data) > 0) {
            current.setLeft(removeHelper(current.getLeft(), data, dummy));
        } else {
            dummy.setData(current.getData());
            if (current.getRight() == null && current.getLeft() == null) {
                return null;
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else if (current.getLeft() == null) {
                return current.getRight();
            } else {
                BSTNode<T> predecessor = current.getLeft();
                while (predecessor.getRight() != null) {
                    predecessor = predecessor.getRight();
                }
                current.setData(predecessor.getData());
                current.setLeft(removeHelper(current.getLeft()
                        , predecessor.getData(), new BSTNode<T>(null)));
                return current;
            }
        }
        return current;
    }
    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        return get(root, data).getData();
    }

    /**
     * Recursive helper get method to fetch the node with the search data
     *
     * @param node the head of the subtree to search through
     * @param searchData the data that is being searched for
     * @return the node containing the search data
     */
    private BSTNode<T> get(BSTNode<T> node, T searchData) {
        if (node == null) {
            throw new NoSuchElementException("Element not found");
        }

        T curData = node.getData();
        //BSTNode<T> returnNode;
        if (searchData.equals(curData)) {
            return node;
        } else if (searchData.compareTo(curData) < 0) {
            node = get(node.getLeft(), searchData);
        } else {
            node = get(node.getRight(), searchData);
        }
        return node;
    }
    //@Override
//    public T get(T data) {
//        if (data == null) {
//            throw new IllegalArgumentException("Data should not be null");
//        } else if (size == 0) {
//            throw new NoSuchElementException("Data not found");
//        } else if (treeSearch(data, root).getData().equals(data)) {
//            return treeSearch(data, root).getData(); //vs. just data???
//        }
//        throw new NoSuchElementException("Data not found");
//    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null");
        } else if (size == 0) {
            return false;
        } else if (treeSearch(data, root).getData().equals(data)) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> preList = new ArrayList<T>();
        preList = preorderList(preList, root);
        return preList;
    }

    /**
     * Algorithm to recursively make a list of datas found in preorder
     * @param preOrderList List being added to for
     *                     preorder traversal.
     * @param current The current node of where the traversal currently is
     * @return List of nodes being traversed in preorder
     */
    private List<T> preorderList(List<T> preOrderList, BSTNode<T> current) {
        if (current == null) {
            return preOrderList;
        }
        preOrderList.add(current.getData());
        if (current.getLeft() != null) {
            preorderList(preOrderList, current.getLeft());
        }
        if (current.getRight() != null) {
            preorderList(preOrderList, current.getRight());
        }
        return preOrderList;
    }

    @Override
    public List<T> postorder() {
        List<T> postList = new ArrayList<T>();
        postList = postorderList(postList, root);
        return postList;
    }
    /**
     * Algorithm to recursively make a list of datas found in postorder
     * @param postOrderList List being added to for
     *                     postorder traversal.
     * @param current The current node of where the traversal currently is
     * @return List of nodes being traversed in postorder
     */
    private List<T> postorderList(List<T> postOrderList, BSTNode<T> current) {
        if (current == null) {
            return postOrderList;
        }
        if (current.getLeft() != null) {
            postorderList(postOrderList, current.getLeft());
        }
        if (current.getRight() != null) {
            postorderList(postOrderList, current.getRight());
        }
        postOrderList.add(current.getData());
        return postOrderList;
    }

    @Override
    public List<T> inorder() {
        List<T> inList = new ArrayList<T>();
        inList = inorderList(inList, root);
        return inList;
    }

    /**
     * Algorithm to recursively make a list of datas found in inorder
     * @param inOrderList List being added to for
     *                     in traversal.
     * @param current The current node of where the traversal currently is
     * @return List of nodes being traversed in inorder
     */
    private List<T> inorderList(List<T> inOrderList, BSTNode<T> current) {
        if (current == null) {
            return inOrderList;
        }
        if (current.getLeft() != null) {
            inorderList(inOrderList, current.getLeft());
        }
        inOrderList.add(current.getData());
        if (current.getRight() != null) {
            inorderList(inOrderList, current.getRight());
        }
        return inOrderList;
    }

    @Override
    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new LinkedList<BSTNode<T>>();
        List<T> loList = new ArrayList<T>();
        if (root != null) {
            q.add(root);
        }
        while (q.size() != 0) {
            BSTNode<T> current = q.poll();
            loList.add(current.getData());
            if (current.getLeft() != null) {
                q.add(current.getLeft());
            }
            if (current.getRight() != null) {
                q.add(current.getRight());
            }
        }
        return loList;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Recursively finds the height of the BST
     * @param current Current node being accessed for height
     * @return The height of the BST
     */
    private int height(BSTNode<T> current) {
        if (current == null) {
            return -1;
        } else {
            return 1 + Math.max(height(current
                    .getLeft()), height(current.getRight()));
        }
    }

    public int count(BSTNode node, int i) {
        if (node == null) {
            i = 0;
        }
        if (node.getLeft() != null) {
            i = 1 + count(node.getLeft(), i);
        }
        if (node.getRight() != null) {
            i = 1 + count(node.getRight(), i);
        }
        return i;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}
