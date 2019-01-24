//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================


package node;

/**
 * <p>
 * SplayNode class for SplayTree program. Contains the attributes of the
 * SplayNode including pointer to parent, integer value, and inherited pointers
 * to left and right children from abstract node class
 * </p>
 * 
 * @author Giselle Martel ID# 26352936
 */
public class SplayNode extends Node<SplayNode> {
    private SplayNode parent;
    private int value;

    /**
     * parametrized constructor method
     * 
     * @param left   is the pointer to left child
     * @param right  is the pointer to right child
     * @param parent is the pointer to the parent
     * @param value  is the value the node contains
     */
    public SplayNode(SplayNode left, SplayNode right, SplayNode parent, int value) {
        super(left, right); // call parametrized constructor of abstract base class
        this.parent = parent;
        this.value = value;
    }

    /**
     * accessor method
     * 
     * @return parent attribute
     */
    public SplayNode getParent() {
        return parent;
    }

    /**
     * mutator method
     * 
     * @param parent will be used to set the value of the parent attribute
     */
    public void setParent(SplayNode parent) {
        this.parent = parent;
    }

    /**
     * accessor method
     * 
     * @return value attribute
     */
    public int getValue() {
        return value;
    }

    /**
     * mutator method
     * 
     * @param value will be used to set the value of the value attribute
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * toString method
     * 
     * @return String containing attribute value
     */
    public String toString() {
        return "value: " + value;
    }
}