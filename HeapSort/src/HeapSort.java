//==============================================================================================================================================
//  COMP 352 Summer 2018 Assignment 2 Question 2
//  Written By: Giselle Martel ID# 26352936
//  Due Date: May 28, 2018
//==============================================================================================================================================

/**
 * This class contains the two versions of an array-based ascending order sort method using a bottom-up Heap Sort algorithm
 * Several helper methods are included to first build a max-heap from an unsorted array, and then to sort this heap
 * using the bottom-up heap sort algorithm. The array can be sorted without tracing or with tracing depending on the given
 * arguments in console.
 *
 * @author Giselle Martel ID# 26352936
 */
public class HeapSort {


    /**
     * This method will sort and array using the bottom-up heap sort algorithm. It first builds
     * a max-heap from the passed array. and then performs the sorting.
     *
     * @param a is the array to be sorted
     */
    public static void sort(int[] a) {
        int length = a.length;
        heapify(a, length, length / 2 - 1); //build max-heap from array

        for (int i = length-1; i > 0; i--) {
            //find the leaf of the "prime path" (path through nodes who are the bigger than their sibling until leaf is found)
            int prime = primePath(a, i);
            //swap the root with current node (since it is a max-heap, highest val are at the root and thus need
            // to be swapped with leaves one by one)
            swap(a, 0, i);
             //while the prime node is less than the root of the heap, move up the prime path to parent of prime leaf node
            while (a[prime] < a[0]) {
                prime = (prime-1)/2;
            }
            int leaf = a[prime];  //store the node of the prime path
            a[prime] = a[0];  //swap the node of the prime path with the root of heap
            //while the index of prime node is larger than the root of the heap
            while(prime > 0) {
                int temp = leaf;        //temporarily store the node of the prime path
                prime = (prime-1)/2;    //move up one level in the prime path
                leaf = a[prime];        //swap the prev node of the with its prime node 
                a[prime] = temp;        
            }

        }
    }

    /**
     * This overloaded method will sort an array using the bottom-up heap sort algorithm.
     * It first builds a max-heap from the passed array. and then performs the sorting.This
     * version of the sort method is also capable of displaying the current step in the
     * sorting process to allow for tracing.
     *
     * @param a         is the array to be sorted
     * @param traceStep is the step that is to be traced in the algorithm. If step is 0, then heapified array will be
     *                  displayed. If the traceStep is 1, then the resulting array after the first iteration
     *                  of the outer loop will be displayed, etc.
     */
    public static void sort(int[] a, int traceStep) {
        int length = a.length;
        heapify(a, length, length / 2 - 1); //build max-heap from array
        int ctr = 0; //set a counter to track which step we've reached in algorithm
        if (traceStep == 0) { //if the traceStep is 0, display the heapified array
            System.out.print("trace at step 0: ");
            displayA(a);
        }
        //increment from end of array (last leaf) to left-child of root
        for (int i = length - 1; i > 0; i--) {
             //find the leaf of the "prime path" (path through nodes who are the bigger than their sibling until leaf is found)
            int prime = primePath(a, i);
            //swap the root with current node (since it is a max-heap, highest val are at the root and thus need
            // to be swapped with leaves one by one)
            swap(a, 0, i);
             //while the prime node is less than the root of the heap, move up the prime path to parent of prime leaf node
            while (a[prime] < a[0]) {
                prime = (prime-1)/2;
            }
            int leaf = a[prime];  //store the node of the prime path
            a[prime] = a[0];  //swap the node of the prime path with the root of heap
            //while the index of prime node is larger than the root of the heap
            while(prime > 0) {
                int temp = leaf;        //temporarily store the node of the prime path
                prime = (prime-1)/2;    //move up one level in the prime path
                leaf = a[prime];        //swap the prev node of the with its prime node 
                a[prime] = temp;        
            }
            ctr++; //increment counter after each iteration of heap sort
            if (ctr == traceStep) { //once the counter is equal to the traceStep, display the contents of array
                System.out.print("trace at step " + traceStep + ": ");
                displayA(a);
            }
        }
    }

    /**
     * This method will traverse the heap to find the prime leaf, which is the node whose ancestors
     * are always the larger their sibling (if they have a sibling).
     *
     * @param a      is the array-based heap to be inspected
     * @param length is the length of the array
     * @return the index of the prime leaf node
     */
    public static int primePath(int[] a, int length) {
        int prime = 0; //starting from the left child of root
        //if node is not a leaf, continue searching for the prime path
        while (prime < length / 2) {
            int leftChild = 2 * prime + 1;
            //check to see if a right child exists and if is is larger than left child
            if (leftChild + 1 < length && a[leftChild + 1] > a[leftChild]) {
                prime = leftChild + 1; //right child is largest
            } else {
                prime = leftChild; //left child is largest
            }
        }
        return prime; //return prime leaf
    }



    /**
     * this method will take an array of integers and will convert into
     * a partially ordered max-Heap by calling the siftDown method.
     *
     * @param a      int an array of integers to be made into a heap
     * @param length is the length of the given array
     */
    public static void heapify(int[] a, int length, int node) {
        while (node >= 0) {  //as long as we haven't reached past the root
            siftDown(a, node, length); //siftdown the heap
            node--; //move to next node
        }
    }

    /**
     * @param a      the array which is being sifting downward (converting it to a heap)
     * @param node   is the current node we are inspecting
     * @param length is the length of the array
     */
    public static void siftDown(int[] a, int node, int length) {
        if (node < 0 || node >= length) {
            return;
        }
        //as long as the index of the node is less than the position of left-most inner node
        while ((node < length / 2)) {
            int child = node * 2 + 1; //find the left child of current node
            //if the right child is larger than parent
            if (child + 1 < length && a[child] < a[child + 1]) {
                child++;
            }
            //if the largest value is not the parent node, then it must be swapped with larger child
            if (a[node] >= a[child]) {
                return;
            } else {
                swap(a, node, child); //swap larger child with parent
                node = child;    //sift downward position of new parent node after swap
            }
        }
    }

    /**
     * this method swaps to given values from an array
     *
     * @param a is the array to be modified
     * @param x is the index of the first element to be swapped
     * @param y is the index of the second element to be swapped
     */
    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    /**
     * this method displays the contents of array
     *
     * @param a is the array to be printed to console
     */

    private static void displayA(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
