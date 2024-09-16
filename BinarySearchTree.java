package DataStructures;

import ADTs.BinarySearchTreeADT;
import Exceptions.EmptyTreeException;
import Exceptions.NodeNotFoundException;

/**
 * A binary search tree implementation that supports typical operations such as insertion, 
 * deletion, and search. The tree allows for elements that are comparable.
 * 
 * @param <E> the type of elements in the tree, which must be comparable
 */
public class BinarySearchTree<E extends Comparable<E>> implements BinarySearchTreeADT<E> {
    /**
     * Default constructor for BinarySearchTree.
     * Initializes an empty binary search tree.
     */
    public BinarySearchTree() {
    }

    /**
     * The root node of the binary search tree.
     */
    private TreeNode<E> root = null;
    /**
     * The number of elements in the binary search tree.
     */
    private int size = 0;

    /**
     * Checks if the tree is empty.
     * 
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Gets the number of elements in the tree.
     * 
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Inserts an element into the tree.
     * 
     * @param element the element to insert
     */
    @Override
    public void insert(E element) {
        root = insert(root, element);
        size++;
    }
    
    /**
     * Recursively inserts an element into the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @param element the element to insert
     * @return the new root of the subtree
     */
    private TreeNode<E> insert(TreeNode<E> node, E element) {
        if (node == null) {
            return new TreeNode<>(element);
        }
        int comparison = element.compareTo(node.getElement());
        if (comparison < 0) {
            node.setLeft(insert(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(insert(node.getRight(), element));
        } else {
            node.incrementCount(); // Handle duplicates
        }
        return node;
    }

    /**
     * Checks if the tree contains a specific element.
     * 
     * @param element the element to check for
     * @return true if the element is in the tree, false otherwise
     * @throws EmptyTreeException if the tree is empty
     */
    @Override
    public boolean contains(E element) throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return contains(root, element);
    }

    /**
     * Recursively checks if the subtree rooted at the given node contains a specific element.
     * 
     * @param node the root of the subtree
     * @param element the element to check for
     * @return true if the element is in the subtree, false otherwise
     */
    private boolean contains(TreeNode<E> node, E element) {
        if (node == null) {
            return false;
        }
        int comparison = element.compareTo(node.getElement());
        if (comparison < 0) {
            return contains(node.getLeft(), element);
        } else if (comparison > 0) {
            return contains(node.getRight(), element);
        } else {
            return true;
        }
    }

    /**
     * Searches for an element in the tree.
     * 
     * @param element the element to search for
     * @return the found element
     * @throws NodeNotFoundException if the element is not found
     * @throws EmptyTreeException if the tree is empty
     */
    @Override
    public E search(E element) throws NodeNotFoundException, EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return search(root, element);
    }

    /**
     * Recursively searches for an element in the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @param element the element to search for
     * @return the found element
     * @throws NodeNotFoundException if the element is not found
     */
    private E search(TreeNode<E> node, E element) throws NodeNotFoundException {
        if (node == null) {
            throw new NodeNotFoundException();
        }
        int comparison = element.compareTo(node.getElement());
        if (comparison < 0) {
            return search(node.getLeft(), element);
        } else if (comparison > 0) {
            return search(node.getRight(), element);
        } else {
            return node.getElement();
        }
    }

    /**
     * Deletes an element from the tree.
     * 
     * @param element the element to delete
     * @throws EmptyTreeException if the tree is empty
     */
    @Override
    public void delete(E element) throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root = delete(root, element);
        if (root != null) {
            size--;
        }
    }

    /**
     * Recursively deletes an element from the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @param element the element to delete
     * @return the new root of the subtree
     */
    private TreeNode<E> delete(TreeNode<E> node, E element) {
        if (node == null) {
            return null;
        }
        int comparison = element.compareTo(node.getElement());
        if (comparison < 0) {
            node.setLeft(delete(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(delete(node.getRight(), element));
        } else {
            if (node.getCount() > 1) {
                node.decrementCount();
            } else {
                if (node.getLeft() == null && node.getRight() == null) {
                    return null;
                } else if (node.getLeft() == null) {
                    return node.getRight();
                } else if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    TreeNode<E> successor = findMin(node.getRight());
                    node.setElement(successor.getElement());
                    node.setRight(delete(node.getRight(), successor.getElement()));
                }
            }
        }
        return node;
    }

    /**
     * Finds the minimum element in the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @return the node with the minimum element
     */
    private TreeNode<E> findMin(TreeNode<E> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findMin(node.getLeft());
    }

    /**
     * Returns a string representation of the elements in the tree in in-order traversal.
     * 
     * @return a string representation of the tree in in-order traversal
     */
    @Override
    public String inOrder() {
        StringBuilder result = new StringBuilder();
        inOrder(root, result);
        if (result.length() > 0) {
            result.setLength(result.length() - 2); // Remove the trailing comma and space
        }
        return "[" + result.toString() + "]";
    }

    /**
     * Recursively performs an in-order traversal of the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @param result the StringBuilder to accumulate the result
     */
    private void inOrder(TreeNode<E> node, StringBuilder result) {
        if (node != null) {
            inOrder(node.getLeft(), result);
            result.append(node.getElement()).append(", ");
            inOrder(node.getRight(), result);
        }
    }

    /**
     * Returns a string representation of the elements in the tree in pre-order traversal.
     * 
     * @return a string representation of the tree in pre-order traversal
     */
    @Override
    public String preOrder() {
        StringBuilder result = new StringBuilder();
        preOrder(root, result);
        if (result.length() > 0) {
            result.setLength(result.length() - 2); // Remove the trailing comma and space
        }
        return "[" + result.toString() + "]";
    }

    /**
     * Recursively performs a pre-order traversal of the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @param result the StringBuilder to accumulate the result
     */
    private void preOrder(TreeNode<E> node, StringBuilder result) {
        if (node != null) {
            result.append(node.getElement()).append(", ");
            preOrder(node.getLeft(), result);
            preOrder(node.getRight(), result);
        }
    }

    /**
     * Returns a string representation of the elements in the tree in post-order traversal.
     * 
     * @return a string representation of the tree in post-order traversal
     */
    @Override
    public String postOrder() {
        StringBuilder result = new StringBuilder();
        postOrder(root, result);
        if (result.length() > 0) {
            result.setLength(result.length() - 2); // Remove the trailing comma and space
        }
        return "[" + result.toString() + "]";
    }

    /**
     * Recursively performs a post-order traversal of the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @param result the StringBuilder to accumulate the result
     */
    private void postOrder(TreeNode<E> node, StringBuilder result) {
        if (node != null) {
            postOrder(node.getLeft(), result);
            postOrder(node.getRight(), result);
            result.append(node.getElement()).append(", ");
        }
    }
}
