//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================


package utility;

/**
 * <p>s
 * This class implements a Hash map which is used in the Huffman class. it
 * contains an inner class called Element which stores the key and value of the
 * map contents It contains three attributes: an array of Element nodes, the
 * total capacity of the map, and the number of nodes inside the map.
 * </p>
 * 
 * @author Giselle Martel ID# 26352936
 */
public class HuffMap {
    private Element[] map;
    private int capacity = 100;
    private int size;

    /**
     * default constructor
     */
    public HuffMap() {
        map = new Element[capacity];
        size = 0;
    }

    /**
     * put method to insert new values in map
     * 
     * @param k is the key and v is the value
     */
    public void put(char k, String v) {
        // if the key contains no value return
        if (k == '\0')
            return;
        // pass the key into hash method to determine its hash code
        int hash = hash(k);
        // instantiate new element to add to the hash map (next pointer initially null)
        Element e = new Element(k, v, null);

        // if the map is empty, add first value
        if (map[hash] == null) {
            map[hash] = e;

        } else {
            Element prev = null; // set pointers
            Element current = map[hash];
            // as long as the current pointer is not null
            while (current != null) {
                // if the current pointer's key is the same as the passed key
                if (current.key == k) {
                    // is the previous pointer is null
                    if (prev == null) {
                        // the next element is equal to element after pointer
                        e.next = current.next;
                        map[hash] = e; // insert new element in the map
                        return;
                    } else { // if the previous pointer is not null
                        // the next element is equal to element after pointer
                        e.next = current.next;
                        prev.next = e; // add the element to the current location
                        return;
                    }
                }
                prev = current; // move the pointers after each iteration
                current = current.next;
            }
            prev.next = e; // if reached end of map, insert element at end
        }
        size++; // increment the size of the map
    }

    /**
     * this method takes a key as a paramter, and then determines if there exists in
     * the map an associated value.
     * 
     * @param k is the key
     * @return value associated with the key if it exists, and null otherwise
     */
    public String get(char k) {
        // get the hash of the key
        int hash = hash(k);
        // if the map is empty return null
        if (map[hash] == null) {
            return null;
        } else {
            Element temp = map[hash]; // set a temporary pointer
            // iterate through the map
            while (temp != null) {
                // if the key associated with pointer is equal to the passed key, then a match
                // is found. return the associated value
                if (temp.key == k)
                    return temp.val;
                temp = temp.next; // move pointer to next position in map
            }
            return null; // return null if no match found
        }
    }

    /**
     * accessor method for attribute size
     * 
     * @return size of map
     */
    public int size() {
        return size;
    }

    /**
     * this method takes a key and calculates a unique hash code using the method
     * hashCode()
     * 
     * @param k is the key
     * @return the value of the hashing
     */
    private int hash(Character k) {
        int hash = k.hashCode();
        if (hash < 0)
            return -1 * hash % capacity; // return postive values only
        else
            return hash % capacity;
    }

    /**
     * inner class to contains elements of map. Three attributes: key, val, and
     * pointer to next node
     * 
     * @author Giselle Martel ID# 26352936
     */
    static class Element {
        private char key;
        private String val;
        private Element next;

        /**
         * parametrized constructor
         * 
         * @param key
         * @param val
         * @param next
         */

        public Element(char key, String val, Element next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
