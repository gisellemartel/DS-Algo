//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================


package node;

/**
 * <p>
 * abstract generic base class which will be extended to build HuffNode class
 * and SplayNode class. Contains two generic attributes: left node pointer and
 * right node pointer.
 * </p>
 * 
 * @author Giselle Martel ID# 26352936
 */
abstract public class Node<T> {

    private T left;
    private T right;

    /**
     * parametrized constructor
     * 
     * @param left  is a generic object which sets the left child pointer to its
     *              value
     * @param right is a generic object which sets the right child pointer to its
     *              value
     */
    public Node(T left, T right) {
        this.left = left;
        this.right = right;
    }

    /**
     * accessor method
     * 
     * @return left attribute
     */
    public T getLeft() {
        return left;
    }

    /**
     * accessor method
     * 
     * @return right attribute
     */
    public T getRight() {
        return right;
    }

    /**
     * mutator method
     * 
     * @param left is a generic object which sets the left child pointer to its
     *             value
     */
    public void setLeft(T left) {
        this.left = left;
    }

    /**
     * mutator method
     * 
     * @param right is a generic object which sets the right child pointer to its
     *              value
     */
    public void setRight(T right) {
        this.right = right;
    }

}