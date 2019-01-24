//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================


package node;

/**
 * <p>
 * HuffNode class contains attributes character, frequency, and occurrence for
 * characters found the input source file. It extends the node class, and the
 * attributes of left and right pointer. This class also defines the compareTo
 * method from the Comparable interface.
 * </p>
 * 
 * @author Giselle Martel ID# 26352936
 */
public class HuffNode extends Node<HuffNode> implements Comparable<HuffNode> {

    private char character; // private attributes
    private int frequency;
    private int occurrence;

    /**
     * parametrized constructor for HuffNode class
     * 
     * @param left       is the pointer to the left child
     * @param right      is the pointer to the righ child
     * @param character  is the char
     * @param frequency  is the number of times the char is found
     * @param occurrence is the index of where the char first appears
     */
    public HuffNode(HuffNode left, HuffNode right, char character, int frequency, int occurrence) {
        super(left, right); // call the paramtrized constructor of the base class
        this.character = character;
        this.frequency = frequency;
        this.occurrence = occurrence;
    }

    /**
     * accessor method
     * 
     * @return character
     */
    public char getCharacter() {
        return character;
    }

    /**
     * mutator method
     * 
     * @param occurrence will set the class attribute occurrence to this value
     */
    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    /**
     * accessor method
     * 
     * @return occurrence
     */
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * mutator method
     * 
     * @param frequency will set the class attribute frequency to this value
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * accessor method
     * 
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * this method determines if the node is a leaf node of the Huffman tree
     * 
     * @return true if there is no left or right child
     */
    public boolean isLeaf() {
        return (this.getLeft() == null && this.getRight() == null);
    }

    /**
     * compareTo method first compares frequencies of two nodes. If the difference
     * is greater or smaller, it will return a non-zero integer value. If the two
     * nodes have the same frequency, then the method with compare the occurrences
     * of the two nodes.
     * 
     * @param node is the node to be compared to this
     * @return negative integer is frequency is smaller, and positive integer if
     *         frequency is greater
     * @return if frequencies are the same, and occurrence is smaller, returns a
     *         postive number
     * @return if frequencies are the same, and occurrence is greater, return a
     *         negative number
     */
    public int compareTo(HuffNode node) {
        int compare = Integer.compare(this.getFrequency(), node.getFrequency());

        if (compare == 0) {
            // return result *-1 since we want occurrence is descending order (frequency is
            // ascending order)
            return -1 * Integer.compare(this.getOccurrence(), node.getOccurrence());
        }
        return Integer.compare(this.getFrequency(), node.getFrequency());
    }

    /**
     * toString method
     * 
     * @return values of attributes character, frequency, and occurrence
     */
    public String toString() {
        return "character: '" + character + "'\tfrequency: " + frequency + "\toccurrence: " + occurrence;
    }

    /**
     * this method will display the children of the current node (debugging
     * purposes)
     * 
     */
    public void displayChildren() {
        System.out.println("Left child of " + this.getCharacter() + " is " + this.getLeft() + " and right child is "
                + this.getRight());
    }
}
