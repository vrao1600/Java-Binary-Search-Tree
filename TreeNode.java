package DataStructures;

/**
 * A class for a node in a binary search tree
 * 
 * @param <E> the type of element stored in this node
 */
public class TreeNode<E extends Comparable<E>> {

    private E element;
    private TreeNode<E> left;
    private TreeNode<E> right;
    private int count; // To keep track of duplicates

    public TreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
        this.count = 1; // Initialize count to 1
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    public void decrementCount() {
        this.count--;
    }
}
