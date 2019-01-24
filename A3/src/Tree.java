//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================



/**
 * abstract generic tree base class which will be extended to build Huffman and
 * SplayTree classes. Contains two attributes: a generic root pointer attribute,
 * and size of tree attribute
 * 
 * @author Giselle Martel ID#26352936
 */

public abstract class Tree<T> {
    protected T root;
    protected int size;

    /**
     * default constructor
     */
    public Tree() {
        root = null;
    }
}