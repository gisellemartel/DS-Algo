
//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================
import java.util.Scanner;
import java.io.*;
import node.SplayNode;

/**
 * <p>
 * This class contains a program which builds a self-adjusting splay binary
 * search tree based on operations indicated in an input .txt file. It contains
 * several attributes, including the root of the Splay Tree inhertied from the
 * abstract Tree class, the size of the tree, and counters for the number of
 * zig-zigs, zig,zags, and compares. There is an add, remove, and find method,
 * which all use the splay method to maintain properties of a self-adjusting
 * splay tree.
 * </p>
 *
 * @author Giselle Martel #26352936
 */
public class SplayTree extends Tree<SplayNode> {
    // attributes
    private static int size = 0;
    private static int zigzag = 0;
    private static int zigzig = 0;
    private static int comparisons = 0;

    /**
     * default constructor
     */
    public SplayTree() {
        super(); // call default constructor of base class
    }

    /**
     *
     * This method adds a node containing the passed value into the splay tree. The
     * method ensures that the properties of a binary search tree are maintained,
     * and allows the tree to self-balance by calling the splay method on the
     * inserted node.
     * 
     * @param value is the value to be inserted into the splay tree
     */
    public void add(int value) {
        // if tree is empty, create root
        if (root == null) {
            root = new SplayNode(null, null, null, value);
            size++; // increment its size by one
            return; // return
        }
        // if element already if found in tree, return (we do not add duplicates)
        if (find(value) != null) {
            return;
        }
        // set two pointers initially to root of the tree
        SplayNode toInsert = root, parent = root;
        // as long as toInsert is not null continue to traverse tree to find location to
        // add new node
        while (toInsert != null) {
            parent = toInsert; // parent points to node to be inserted initially
            if (value > parent.getValue()) // if the value to be added is larger than the value current being pointed to
                toInsert = toInsert.getRight(); // move node to be inserted to left child
            else if (value < parent.getValue()) // if the value to be added is smaller than the value current being
                                                // pointed to
                toInsert = toInsert.getLeft(); // move node to be inserted to right child
        }

        // insert new node containing the value in the tree, parent pointer becomes
        // parent of the new node
        toInsert = new SplayNode(null, null, parent, value);

        // if the value of the inserted node is less than the value of the parent, it
        // should be the left child (BST rule)
        if (parent != null && toInsert.getValue() < parent.getValue()) {
            parent.setLeft(toInsert);
            // if the value of the inserted node is greater than the value of the parent, it
            // should be the right child (BST rule)
        } else {
            if (parent != null) // ensure parent is not null pointer
                parent.setRight(toInsert);
        }
        splay(toInsert);
        // increment size of tree once node is added to tree
        size++;
    }

    /**
     *
     * This method takes a value as a paramater, then determines if a node exists
     * containing this value in the splay tree. If it does exist it removes the
     * node, otherwise the method returns.
     * 
     * @param value is the value to be removed from the splay tree
     */
    public void remove(int value) {

        SplayNode node = find(value); // call the find method to see if there is a node associated with the value
        // if the node is null then return (nothing to remove)
        if (node == null) {
            return;
        } else { // otherwise remove the node

            splay(node); // splay the node toward the root

            SplayNode parent = node.getParent(); // set pointer to parent of node

            // if the node has a parent (pointer is not null)
            if (parent != null) {
                SplayNode grandparent = parent.getParent();
                // if the node does not have a grandparent (it is a right child of the root)
                // rotate the node and root left
                if (grandparent == null && node == root.getRight()) {
                    rotateLeft(node, root);

                    // if the node does not have a grandparent (it is a right child of the root)
                    // rotate the node and root left
                } else if (grandparent == null && node == root.getLeft()) {
                    rotateRight(node, root);
                }
            }

            // if there is a right child
            if (node.getRight() != null) {
                // if there is only a right child, then we can can use the setParent method on
                // this child to delete the node
                if (node.getLeft() == null) {
                    node.getRight().setParent(null); // remove the node
                    root = node.getRight(); // root becomes right child
                    // if there are two children
                } else if (node.getLeft() != null) {
                    SplayNode pointer = node.getLeft(); // set pointer to left child
                    // while pointer has a right child, move the pointer to the right child
                    while (pointer.getRight() != null) {
                        pointer = pointer.getRight();
                    }
                    // once there is no right child to point to, then set the right child of the
                    // pointer to the right child of the node
                    pointer.setRight(node.getRight());
                    // set the node to be the pointer (its right child)
                    node.getRight().setParent(pointer);
                    // remove the node
                    node.getLeft().setParent(null);
                    root = node.getLeft(); // set the root to the left child
                }
            }

            // if there is a left child
            else if (node.getLeft() != null) {
                System.out.println("hello");
                // if there is only a left child, then we can can use the setParent method on
                // this child to delete the node
                if (node.getRight() == null) {
                    node.getLeft().setParent(null); // remove the node
                    root = node.getLeft(); // root becomes left child.
                    // if there are two children
                } else if (node.getRight() != null) {
                    SplayNode pointer = node.getLeft(); // set pointer to left child
                    // while pointer has a right child, move the pointer to the right child
                    while (pointer.getRight() != null) {
                        pointer = pointer.getRight();
                    }
                    // once there is no right child to point to, then set the right child of the
                    // pointer to the right child of the node
                    pointer.setRight(node.getRight());
                    // set the node to be the pointer (its right child)
                    node.getRight().setParent(pointer);
                    // remove the node
                    node.getLeft().setParent(null);
                    root = node.getLeft(); // set the root to the left child
                }

            }
            size--; // decrement size of tree after removal operation
        }
    }

    /**
     * this method take a integer value as a parameter and searches the splay tree
     * to see if a node containing that value exists.
     *
     * @param value is the value to be searched for in the tree
     * @return the node containing the associated value if it exists and null
     *         otherwise
     */
    public SplayNode find(int value) {

        SplayNode pointer = root; // set a pointer to the root

        // traverse the tree searching for a node containing value until we've reached
        // the end of the tree (or if the
        // root is null then the loop won't execute).
        while (pointer != null) {
            // if we find a match then perform splay operation on the node and return it
            if (value == pointer.getValue()) {
                splay(pointer);
                return pointer;
                // otherwise if the value is smaller than the current pointer, then traverse
                // down the left path of the
                // splay BST
            } else if (value < pointer.getValue()) {
                pointer = pointer.getLeft();
                // otherwise if the value is larger than the current pointer, then traverse down
                // the right path of the
                // splay BST
            } else if (value > pointer.getValue()) {
                pointer = pointer.getRight();
            }
        }
        return null; // return null if a node containing the value wasn't found.
    }

    /**
     * This method recursively prints the nodes of the splay tree in post order
     * fashion.
     *
     * @param node is the root of the tree to be traversed in a postorder fashion
     */
    public void postOrder(SplayNode node) {
        if (node == null) { // stopping case
            return;
        } else {
            postOrder(node.getLeft()); // recursive case
            postOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    /**
     * This method takes a SplayNode node and performs either the zig-zig or zig-zag
     * rotation on the tree based on its position and the position of its ancestors
     * in the tree
     *
     * @param node to be splayed
     */
    private void splay(SplayNode node) {

        // we perform splay operations on the node until it becomes the root (root has
        // no parent)
        while (node.getParent() != null) {
            SplayNode parent = node.getParent(); // parent of node
            SplayNode grandParent = parent.getParent(); // grandparent of node

            /*
             * as long as the grandparent node is not null, then we may perform the splay
             * (zig-zig or zig-zag operations) to move the node up the BST.
             */
            if (grandParent != null) {
                /*
                 * if the node is the right child, and its parent is the left child, perform and
                 * zig-zag operation between the node and its parent. If the node is a right
                 * child, and its parent is also a right child, then first perform a left
                 * rotation on the grandparent and parent, and then another left rotation on the
                 * node and parent (zig-zig). If the node is a left child, and its parent is
                 * also a left child, then perform right rotation on the parent and grandparent,
                 * and then another right rotation on the node and parent (zig-zag). The final
                 * case is where the node is the left child and the parent is the right child.
                 * Here we perform and a zig-zag operation.
                 */
                if (node == parent.getRight() && parent == grandParent.getLeft()) {
                    rotateLeft(node, parent); // rotate tree right
                    parent = node.getParent(); // reset the parent pointer
                    rotateRight(node, parent); // rotate tree left
                    zigzag++; // increment zigzag counter
                    comparisons += 2; // increment number of comparisons by 2 (since we are comparing two sets of
                                      // nodes
                } else if (node == parent.getRight() && parent == grandParent.getRight()) {
                    rotateLeft(parent, grandParent);
                    rotateLeft(node, parent);
                    zigzig++;
                    comparisons += 2;
                } else if (node == parent.getLeft() && parent == grandParent.getLeft()) {
                    rotateRight(parent, grandParent);
                    rotateRight(node, parent);
                    zigzig++;
                    comparisons += 2;
                } else {
                    rotateRight(node, parent);
                    parent = node.getParent(); // reset the parent pointer
                    rotateLeft(node, parent);
                    zigzag++;
                    comparisons += 2;
                }
            } else {
                // STOP RULE HAPPENS HERE
                // if there is no grandparent node, then the root of the splay tree is the
                // parent of the node being splayed
                root = parent;
                return;
            }
        }
        root = node; // after splaying the node to the top of the tree, set the root to the node.
    }

    /**
     * This method performs a single right rotation of the splay tree to move the
     * child node closer to the root
     *
     * @param node   is the node being splayed to the root
     * @param parent is the parent of the node
     */
    private void rotateRight(SplayNode node, SplayNode parent) {
        SplayNode grandparent = parent.getParent();

        // if there is a grandparent to the node, then check to see if the parent is a
        // left or right child
        if (grandparent != null) {
            // if the parent is a left child, then set the left child to be the node
            if (parent == grandparent.getLeft()) {
                grandparent.setLeft(node);
                comparisons++;
                // otherwise if the parent is a right child then set the right child to be the
                // node
            } else {
                grandparent.setRight(node);
                comparisons++;
            }
            grandparent = parent.getParent(); // reset grandparent pointer to current parent
        }

        // if the node has a right child, then set itself to the parent node
        if (node.getRight() != null) {
            node.getRight().setParent(parent);
        } // switch the parent of the node with the grandparent
        node.setParent(grandparent);
        // set the parent of the node the node
        parent.setParent(node);
        // set the left child of the parent to the right child of the node
        parent.setLeft(node.getRight());
        // set the right child of the node to the parent
        node.setRight(parent);

    }

    /**
     * This method performs a single left rotation of the splay tree to move the
     * child node closer to the root
     *
     * @param node   is the node being splayed to the root
     * @param parent is the parent of the node
     */
    private void rotateLeft(SplayNode node, SplayNode parent) {
        SplayNode grandparent = parent.getParent();

        // if there is a grandparent to the node, then check to see if the parent is a
        // left or right child
        if (grandparent != null) {
            // if the parent is a left child, then set the left child to be the node
            if (parent == grandparent.getLeft()) {
                grandparent.setLeft(node);
                comparisons++; // increment the comparisons counter (1 compare between two nodes)
                // otherwise if the parent is a right child then set the right child to be the
                // node
            } else {
                grandparent.setRight(node);
                comparisons++;
            }
            grandparent = parent.getParent(); // reset grandparent pointer to current parent
        }
        // if the node has a left child, then set itself to the parent node
        if (node.getLeft() != null) {
            node.getLeft().setParent(parent);
        }
        // switch the parent of the node with the grandparent
        node.setParent(grandparent);
        // set the parent of the node the node
        parent.setParent(node);
        // set the right child of the parent to the left child of the node
        parent.setRight(node.getLeft());
        // set the left child of the node to the parent
        node.setLeft(parent);
    }

    /**
     * main method
     *
     * @param args takes arguments from console to determine which input file will
     *             be read and which traversal step will be printed
     */
    public static void main(String[] args) {
        System.out.println("Welcome to SplayTree.java\n");

        String source; // declare a variable for the name of the source file given in console
        int step = -1; // initially set the trace step to -1

        try {
            // get name of source file from console
            source = args[0];

            // if second argument is given (traversal step)
            if (args.length == 2)
                try {
                    step = Integer.parseInt(args[1]); // retrieve desired traversal step from console
                } catch (NumberFormatException e2) {
                    System.out.println(
                            "Invalid argument given for traversal step. Please enter positive integer value only");
                }

            readFile(source, step); // read input file

            // print final result to console
            System.out.println("\n" + comparisons + " compares\nZig-Zigs: " + zigzig + "\nZig-Zags: " + zigzag);

        } catch (IndexOutOfBoundsException e3) {
            System.out.println("No filename specified in console for source text. Please try again");
        }
    }

    /**
     * @param source is the name of the source file to be read
     * @param trace  is the trace step indicated in console
     */
    private static void readFile(String source, int trace) {
        Scanner in; // Scanner object for reading input file
        SplayTree tree = new SplayTree(); // instantiate new SplayTree object

        try {
            in = new Scanner(new FileInputStream(source)); // read input file
            char operation; // declare variable for operation to be executed (a,r,f)
            int value; // value to be used in operation
            String line; // String to contain line read from input file
            boolean print; // boolean print will determine if trace step is to be printed to console
            int ctr = 0; // set counter to track which traversal step we've reached

            // read input file until end
            while (in.hasNextLine()) {
                ctr++; // increment counter after each operation is read from file then executed
                line = in.nextLine(); // read line
                if (!line.equals("")) { // skip empty lines
                    operation = line.charAt(0); // first char of String is the operation
                    value = Integer.parseInt(line.substring(1)); // remaining String is the value
                    // once the ctr is equal to the trace step, print becomes true and doOperation
                    // will show the
                    // post order traversal of the tree
                    if (ctr == trace) {
                        print = true;
                    } else {
                        print = false;
                    }
                    doOperation(operation, value, trace, print, tree); // call method to perform operation on splay tree
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Sorry, failed to read input file '" + source + "'. Please try again");
        }
    }

    /**
     * @param operation is char which refers to operation to be performed ('a' to
     *                  add, 'r' to remove, etc)
     * @param value     is the value to be used in the operation
     * @param step      is the trace step
     * @param print     is a boolean value which determines is the trace step
     *                  traversal will be displayed or not
     * @param tree      is the Splay Binary Search Tree
     */
    private static void doOperation(char operation, int value, int step, boolean print, SplayTree tree) {

        // perform add, remove, or find operation on splay tree
        switch (operation) {
        case 'a':
            tree.add(value);
            break;
        case 'r':
            tree.remove(value);
            break;
        case 'f':
            tree.find(value);
            break;
        }
        // print traversal step
        if (print) {
            System.out.print("Traversal at " + step + ": ");
            tree.postOrder(tree.root);
        }
    }
}
