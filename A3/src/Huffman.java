//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 3 
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: June 16, 2018
//=========================================================================


import java.util.Scanner;
import java.io.*;
import node.*;
import utility.HuffMap;

/**
 * <p>
 * This class implements a Huffman tree using a custom build hash map and
 * prioity queue. First, the program reads an input file to encode characters
 * and the build a frequency map from the results. Using the frequency and
 * occurrence of each character, and Huffman tree is constructed. The program at
 * the end will prompt the user to enter a String, which will then be encoded
 * using the huffman tree.
 * </p>
 * 
 * @author Giselle Martel ID# 26352936
 */
public class Huffman extends Tree<HuffNode> {
    private static Scanner input = new Scanner(System.in); // static Scanner object to be used by various methods for
                                                           // user input
    private static final int CAPACITY = 128; // set max capacity to 128 (there exists 128 possible characters in ASCII)
    private static int occurrence = 0; // initialize the occurrence counter to 0
    private static HuffMap codeMap = new HuffMap();
    private int size;
    private HuffNode[] queue;

    /**
     * default constructor
     */
    public Huffman() {
        super();
        queue = new HuffNode[CAPACITY];
        size = 0;
    }

    /**
     * this method insert a node into the Huffman tree by implementing priority
     * queue. Order is determined by the compare to method of the HuffNode class.
     * 
     * @param node is the node to be inserted into the queue
     */
    public void add(HuffNode node) {
        int i;
        // if the queue contains at least one node
        if (size != 0) {
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
     * this static method takes an array of chars and then inserts them into a map.
     * 
     * @param characters
     */
    private static HuffNode[] charMap(char[] characters) {

        HuffNode[] map = new HuffNode[CAPACITY];
        // iterate each through each char stored in the characters array to construct a
        // char map
        for (char letter : characters) {
            /*
             * if no node exists for the character, instantiate new HuffNode with initial
             * frequency of 1, and the occurrence in the array
             */
            if (map[letter] == null) {
                map[letter] = new HuffNode(null, null, letter, 1, occurrence);
                occurrence++; // increment occurrence counter
            } else {
                // if duplicate character is found, increment the frequency of the HuffNode
                int freq = map[letter].getFrequency();
                map[letter].setFrequency(++freq);
            }
        }
        return map;
    }

    /**
     * this method takes an array of characters and builds a frequency table, first
     * by calling the charMap method to build a map, and then by removing any null
     * values from the map
     * 
     * @param characters
     * @return table of Huffman nodes
     */
    public static HuffNode[] buildFrequencyTable(char[] characters) {
        HuffNode[] map = charMap(characters); // create map of all characters found in source text.
        HuffNode[] table = new HuffNode[occurrence]; // initialize new array of HuffNodes to create frequency table
        int ctr = 0; // initialize counter to 0 for the index of the frequency map
        // iterate through array to copy non-null values to array
        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                table[ctr] = map[i]; // assign any non-null values from map into table
                ctr++; // increment to next index in table
            }
        }
        return table; // return the frequency table
    }

    /**
     * this method builds a huffman tree by using a custom priority queue
     * 
     * @param map is a the map of Huffman nodes
     */
    public void buildTree(HuffNode[] map) {
        for (int i = 0; i < map.length; i++) {
            add(new HuffNode(null, null, map[i].getCharacter(), map[i].getFrequency(), map[i].getOccurrence()));
        }

        if (size == 1) {
            add(new HuffNode(null, null, '\0', 1, occurrence++));
        }

        while (size > 1) {
            HuffNode left = removePriorityNode();
            HuffNode right = removePriorityNode();
            add(new HuffNode(left, right, '\0', left.getFrequency() + right.getFrequency(), occurrence++));
        }
        root = removePriorityNode();
    }

    /**
     * this method assigns binary encondings to each character by traversing the
     * huffman tree in a top down fashion from root to leaves
     * 
     * @param node is the root of the tree to be traversed
     * @param s    is a String that will be concatenated with the resulting encoding
     */
    public static void assignCode(HuffNode node, String s) {
        if (!node.isLeaf()) {
            assignCode(node.getLeft(), s += '0');
            s = s.substring(0, s.length() - 1);
            assignCode(node.getRight(), s += '1');
            s = s.substring(0, s.length() - 1);
        } else {
            codeMap.put(node.getCharacter(), s);
        }
    }

    /**
     * this method assigns binary encondings to each character by traversing the
     * huffman tree in a top down fashion from root to leaves, and then displays the
     * result to console
     * 
     * @param node is the root of the tree to be traversed
     * @param s    is a String that will be concatenated with the resulting encoding
     */
    public static void displayCodeTable(HuffNode node, String s) {

        if (!node.isLeaf()) {
            displayCodeTable(node.getLeft(), s += '0');
            s = s.substring(0, s.length() - 1);
            displayCodeTable(node.getRight(), s += '1');
            s = s.substring(0, s.length() - 1);
        } else {
            System.out.println(node + "\thuffman encoding: " + s);
        }
    }

    /**
     * This method takes a String from console, and then encodes it using the
     * Huffman tree that was previously built.
     * 
     * @param input
     * @param str
     */
    public static void displayEncodedString(char[] input, String str) {
        String result = "";
        for (int i = 0; i < input.length; i++) {
            if (codeMap.get(input[i]) != null)
                result += codeMap.get(input[i]);
        }
        System.out.println("\nHuffman encoding of '" + str + "' is:\n\n" + result);
    }

    /**
     * main method
     * 
     * @param args console arguments
     */
    public static void main(String[] args) {
        String text = null, source;
        try {
            // get name of source file from console
            source = args[0];
            // read source file into array of chars
            text = readFile(source);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No filename specified in console for source text. Please try again");
        }

        // Build Frequency table
        HuffNode[] table = buildFrequencyTable(toArray(text));

        // instantiate a new tree object
        Huffman tree = new Huffman();

        // Build Huffman Tree
        tree.buildTree(table);

        // encode each character in Huffman tree starting from the root
        assignCode(tree.root, "");

        // display result
        System.out
                .println("\n\nRESULT\n=============================================================================\n");
        displayCodeTable(tree.root, "");

        // prompt user to enter string to encode and display result
        String response = "";
        do {
            encode();
            System.out.println("\nEnter 'y' to encode another string and any other key to exit");
            response = input.nextLine();
        } while (response.equals("y"));
        input.close(); // close Scanner
        System.out.println("Goodbye.");
    }

    /**
     * This method reads input source .txt file line by line and then copy a result
     * into a String
     * 
     * @param source name of source .txt file
     * @return text which is a String containing contents of .txt file
     */
    private static String readFile(String source) {
        Scanner in = null;
        try {
            in = new Scanner(new FileInputStream(source));
            String text = "";
            while (in.hasNextLine()) {
                text += in.nextLine();
            }
            in.close();
            return text;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file. Please ensure you have the correct file name");
            return null;
        }
    }

    /**
     * this method prompts the user to enter a String, and then calls
     * displayEncodedString to encode ans display it to console
     */
    private static void encode() {
        // encode user input
        System.out.println("\nPlease enter a String to encode");
        String str = input.nextLine();
        char[] chars = toArray(str);
        displayEncodedString(chars, str);
    }

    /**
     * method that converts String into array of characters
     * 
     * @param s
     * @return arr of chars
     */
    private static char[] toArray(String s) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        return arr;
    }
}
