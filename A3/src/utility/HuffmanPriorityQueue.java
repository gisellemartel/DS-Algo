package utility;

import node.*;

/**
 * This class is used to implement a priority queue for the Huffman program. It
 * contains three attributes: an integer for the maximum capacity of the queue,
 * an array which will be the data structure for the queue, and an integer value
 * which will track the total number of nodes inside the queue
 * 
 * @author Giselle Martel ID# 26352936
 */
public class HuffmanPriorityQueue {

    private final int CAPACITY = 128; // set max capacity to 128 (only 128 possible characters in ASCII)
    private HuffNode[] queue;
    private int size;

    /**
     * default constructor
     */
    public HuffmanPriorityQueue() {
        queue = new HuffNode[CAPACITY];
        size = 0;
    }

    /**
     * this method insert a node into the queue based on priority (as determined by
     * the compare to method of the HuffNode class)
     * 
     * @param node is the node to be inserted into the queue
     */
    public void insert(HuffNode node) {
        int i;
        // if the queue contains at least one node
        if (this.size() != 0) {
            // increment through the queue
            for (i = size - 1; i >= 0; i--) {
                // if the node has larger frequency (or same frequency but smaller occurrence),
                // then move it one place in the queue
                if (node.compareTo(queue[i]) > 0)
                    queue[i + 1] = queue[i];
                else
                    break;
            }
            // once the correct position is established, insert the node
            queue[i + 1] = node;
            size++; // increment the size of the queue
            // if the queue is empty, add the first node (and increment the size of the
            // queue)
        } else {
            queue[size++] = node;
        }
    }

    /**
     * this method removes node from queue based on priority (last node in queue
     * array)
     * 
     * @return removed node
     */
    public HuffNode removePriorityNode() {
        return queue[--size];
    }

    /**
     * this method returns the node with current highest priority in the queue
     * 
     * @return node at end of queue array
     */
    public HuffNode priorityNode() {
        return queue[size - 1];
    }

    /**
     * isEmpty method
     * 
     * @return true if queue has no nodes
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * accessor method
     * 
     * @return size of queue (number of nodes in queue)
     */
    public int size() {
        return size;
    }

}