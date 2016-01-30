import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Daniel Kim
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null.");
        } else {
            for (T element: data) {
                if (element == null) {
                    throw new IllegalArgumentException("Data "
                            + "should not be null.");
                }
                add(element);
            }
        }
    }

    /**
     * Rotate left child
     * @param c Parent node of rotation
     * @return The new parent node
     */
    private AVLNode<T> rotateLeftChild(AVLNode<T> c) {
        AVLNode<T> b = c.getLeft();
        c.setLeft(b.getRight());
        b.setRight(c);
        c.setHeight(Math.max(height(c.getLeft()), height(c.getRight())) + 1);
        b.setHeight(Math.max(height(b.getLeft()), c.getHeight()) + 1);
        c.setBalanceFactor(height(c.getLeft()) - height(c.getRight()));
        b.setBalanceFactor(height(b.getLeft()) - c.getHeight());
        return b;
    }

    /**
     * Rotate right child
     * @param a Parent node of rotation
     * @return The new parent node
     */
    private AVLNode<T> rotateRightChild(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        a.setHeight(Math.max(height(a.getLeft()), height(a.getRight())) + 1);
        b.setHeight(Math.max(height(b.getRight()), a.getHeight()) + 1);
        a.setBalanceFactor(height(a.getLeft()) - height(a.getRight()));
        b.setBalanceFactor(a.getHeight() - height(b.getRight()));
        return b;
    }

    /**
     * Double rotation of left child
     * @param c Parent node of rotation
     * @return Rotation of left child
     */
    private AVLNode<T> doubleRotateLeftChild(AVLNode<T> c) {
        c.setLeft(rotateRightChild(c.getLeft()));
        return rotateLeftChild(c);
    }

    /**
     * Double rotation of right child
     * @param a Parent node of the rotation
     * @return Rotation of right child
     */
    private AVLNode<T> doubleRotateRightChild(AVLNode<T> a) {
        a.setRight(rotateLeftChild(a.getRight()));
        return rotateRightChild(a);
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null.");
        }
        root = add(root, data);
    }

    /**
     * Helper method for add
     * @param node node being traversed
     * @param data Data being added
     * @return node being added
     */
    private AVLNode<T> add(AVLNode<T> node, T data) {
        if (node == null) {
            node = new AVLNode<T>(data);
            size++;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(node.getRight(), data));
        }
        node.setHeight(Math.max(height(node.getLeft())
                , height(node.getRight())) + 1);
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
        if (node.getBalanceFactor() > 1) {
            if (data.compareTo(node.getLeft().getData()) < 0) {
                node = rotateLeftChild(node);
            } else {
                node = doubleRotateLeftChild(node);
            }
        } else if (node.getBalanceFactor() < -1) {
            if (data.compareTo(node.getRight().getData()) > 0) {
                node = rotateRightChild(node);
            } else {
                node = doubleRotateRightChild(node);
            }
        }

        return node;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should "
                    + "not be null.");
        }
        AVLNode<T> dummyNode = new AVLNode<T>(null);
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
    private AVLNode<T> removeHelper(AVLNode<T> current,
                                    T data, AVLNode<T> dummy) {
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
                AVLNode<T> predecessor = current.getLeft();
                while (predecessor.getRight() != null) {
                    predecessor = predecessor.getRight();
                }
                current.setData(predecessor.getData());
                current.setLeft(removeHelper(current.getLeft()
                        , predecessor.getData(), new AVLNode<T>(null)));
            }
        }
        current.setHeight(Math.max(height(current.getLeft())
                , height(current.getRight())) + 1);
        current.setBalanceFactor(height(current.getLeft())
                - height(current.getRight()));
        if (current.getBalanceFactor() > 1) {
            if (height(current.getLeft().getLeft())
                    >= height(current.getLeft().getRight())) {
                current = rotateLeftChild(current);
            } else {
                current = doubleRotateLeftChild(current);
            }
        } else if (current.getBalanceFactor() < -1) {
            if (height(current.getRight().getRight())
                    >= height(current.getRight().getLeft())) {
                current = rotateRightChild(current);
            } else {
                current = doubleRotateRightChild(current);
            }
        }
        return current;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null.");
        }
        AVLNode<T> node = treeSearch(data, root);
        if (size == 0) {
            throw new NoSuchElementException("Data not found");
        } else if (node.getData().equals(data)) {
            return node.getData();
        }
        throw new NoSuchElementException("Data not found");
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
    private AVLNode<T> treeSearch(T k, AVLNode<T> v) {
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
    private List<T> preorderList(List<T> preOrderList, AVLNode<T> current) {
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
    private List<T> postorderList(List<T> postOrderList, AVLNode<T> current) {
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
    private List<T> inorderList(List<T> inOrderList, AVLNode<T> current) {
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
        Queue<AVLNode<T>> q = new LinkedList<AVLNode<T>>();
        List<T> loList = new ArrayList<T>();
        if (root != null) {
            q.add(root);
        }
        while (q.size() != 0) {
            AVLNode<T> current = q.peek();
            q.remove();
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
     * Helper method to find height
     * @param node Node for height
     * @return The height of the BST
     */
    private int height(AVLNode<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null");
        }
        return 1 + depth(data, root);
    }

    /**
     * Finds the depth of the node
     * @param data Data being found for depth
     * @param node Node being traversed
     * @return Increment of depth
     */
    private int depth(T data, AVLNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Data not found.");
        }
        if (data.compareTo(node.getData()) < 0) {
            return 1 + depth(data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            return (1 + depth(data, node.getRight()));
        } else {
            return 0;
        }
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }
}
