//==============================================================================================================================================
//  COMP 352 Summer 2018 Assignment 2 Question 2
//  Written By: Giselle Martel ID# 26352936
//  Due Date: May 28, 2018
//==============================================================================================================================================

/**
 * <p>
 * Driver class of program which implements a bottom-up heap sort. This driver accepts input from console, which will be
 * used to determine what kind of array will be generated (which will be sorted using heap sort). The user may specify
 * in console if they wish to generate an array of random integers or an array of integers in descending order by entering
 * either 'RandomGen' for random  or 'FixedGen' for descending as first argument in the console. The length of the array
 * should be indicated as the second argument in the console. The user may also specify if they wish to have a certain step of
 * the sorting process traced and displayed. They can enter as the third argument which step they wish to view (1,2,3 etc)
 * or enter 0 if they wish to only view the heapified array before sorting. If -1 is entered, then this step will be skipped.
 * If the user indicates they wish to sort a random array, they have the option of indicating a seed they would like to
 * use in order to repeat the random generated numbers for testing purposes.
 * </p>
 *
 * @author Giselle Martel ID# 26352936
 */
public class HSDriver {
    public static void main(String[] args) {
        
        //if 3 arguments are given in console (no argument given for seed)
        if (args.length == 3) {
            //if user indicates RandomGen
            if (args[0].equalsIgnoreCase("randomgen")) {
                int length = verifyInt(args[1]); //verify argument given for length is an integer
                //if the length is positive, then create new randomized array by instantiating obj of RandomGen class
                if (length > 0) {
                    int[] randArray = RandomGen.generateArray(length);
                    randomGenSort(randArray, args);
                    //otherwise inform user positive value must be given for length and request to re-run program
                } else {
                    System.out.println("Invalid argument given for length. Please re-run and enter positive integer value only into console for 2nd arg.");
                }
                //if user indicated FixedGen
            } else if (args[0].equalsIgnoreCase("fixedgen")) {
                int length = verifyInt(args[1]); //verify argument given for length is an integer
                //if the length is positive, then create new reverse-order array by calling helper method fixedGenSort
                if (length > 0) {
                    fixedGenSort(length, args);
                } else {
                    System.out.println("Invalid argument given for length. Please re-run and enter positive integer value only into console.");
                }
            } else {
                //if user enters invalid first arg request to re-run program with valid args.
                System.out.println("First argument invalid. Please re-run program and enter RandomGen or FixedGen as first console argument");
            }

            //if 4 arguments are given in console (including arg for seed)
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("randomgen")) {
                int length = verifyInt(args[1]);
                if (length > 0) {
                    try {
                        long seed = Long.parseLong(args[3]); //parse seed into long
                        int[] randArray = RandomGen.generateArray(length, seed);
                        randomGenSort(randArray, args);
                    } catch (NumberFormatException e) {
                        //if arg cannot be parsed into string, then prompt user to re-run the program with valid arg.
                        System.out.println("Invalid argument given for seed. Please re-run and enter a long as fourth arg into console.");
                    }
                }
            } else if (!args[0].equalsIgnoreCase("fixedgen")) {
                //if user enters invalid first arg request to re-run program with valid args.
                System.out.println("First argument invalid. Please re-run program and enter RandomGen or FixedGen as first console argument");
            } else {
                int length = verifyInt(args[1]); //verify argument given for length is an integer
                //if the length is positive, then create new reverse-order array by calling helper method fixedGenSort
                if (length > 0) {
                    fixedGenSort(length, args);
                } else {
                    System.out.println("Invalid argument given for length. Please re-run and enter positive integer value only into console.");
                }
            }
        } else {
            System.out.println("Necessary arguments not given in console. Please re-run program"); //must be a minimum of 3 arguments given in console
        }
    }

    /**
     * this method takes an array of randomized integers and calls the appropriate sort method to perform a heap sort
     * on the array attribute of the RandomGen object (randomized array). If an invalid argument is given in console, then
     * sorting will not take place and the user will be notified on console.The result of the sorting will be displayed
     * to console if it does occur.
     * @param randArray is an array of randomized integers
     * @param args is an array containing arguments from console
     */
    private static void randomGenSort(int[] randArray, String[] args) {
        int traceStep = verifyInt(args[2]);  //verify the arg given and then parse into int.
        long start, end, time; //variables to track time taken to run heap sort algorithm

        if (traceStep > -1) {
            System.out.print("sorting: ");
            display(randArray);
            start = System.nanoTime();
            HeapSort.sort(randArray, traceStep);
            end = System.nanoTime();
            time = end-start;
            System.out.print("result: ");
            display(randArray);
            System.out.println("completed in: " + time + "ns");
        } else if (traceStep == -1) {
            System.out.print("sorting: ");
            long avg = 0;
            display(randArray);
            for(int i =0; i<2000; i++){
                start = System.nanoTime();
                HeapSort.sort(randArray);
                end = System.nanoTime();
                avg += end-start;
            }
            avg /= 2000;
            System.out.print("result: ");
            display(randArray);
            System.out.println("\n\naverage time: " + avg + "ns\n\n");
        } else {
            System.out.println("Invalid argument for trace-step given in console. Please enter 0 to show" +
                    "heapified array or postive integer representing step in sorting process. Enter -1 to skip this step");
        }
    }

    /**
     * this method calls the static method of the FixedGen class to create an array in descending order.
     * It will then call the appropriate sort method to perform a heap sort on the array. If an invalid argument is given in console, then
     * sorting will not take place and the user will be notified on console. The result of the sorting will be displayed
     * to console if it does occur.
     * @param length is the length of the array
     * @param args is an array containing arguments from console
     */
    private static void fixedGenSort(int length, String[] args) {
        int[] fixArray = FixedGen.generateArray(length);
        long start, end, time;
        int traceStep = verifyInt(args[2]);

        if (traceStep > -1) {
            System.out.print("sorting: ");
            display(fixArray);
            start = System.nanoTime();
            HeapSort.sort(fixArray, traceStep);
            end = System.nanoTime();
            time = end-start;
            System.out.print("result: ");
            display(fixArray);
            System.out.println("completed in: " + time + "ns");
        } else if (traceStep == -1) {
            System.out.print("sorting: ");
            display(fixArray);
            start = System.nanoTime();
            HeapSort.sort(fixArray, traceStep);
            end = System.nanoTime();
            time = end-start;
            System.out.print("result: ");
            display(fixArray);
            System.out.println("completed in: " + time + "ns");
        } else {
            System.out.println("Invalid argument for trace-step given in console. Please enter 0 to show" +
                    "heapified array or postive integer representing step in sorting process. Enter -1 to skip this step");
        }
    }

    /**
     * helper method which verifies if arguments from console can be parsed into integer, and then parses the argument
     * into an int.
     * @param s is a String which will be parsed into an int if possible
     * @return result which is an integer parsed from String.
     * @return -2 if String cannot be parsed into an int
     */

    private static int verifyInt(String s) {
        int result;
        try {
            result = Integer.parseInt(s);
            return result;
        } catch (NumberFormatException e) {
            return -2;
        }

    }

    /**
     * helper method used to display content of array
     * @param a is an array of integers
     */
    private static void display(int[] a){
        for(int i =0; i<a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}