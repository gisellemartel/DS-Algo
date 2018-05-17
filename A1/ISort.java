//=========================================================================
//  COMP 352 Section Summer 2018 
//  Assignment 1 Question 1
//  Written By: Giselle Martel ID#: 26352936
//  Due Date: Tuesday May 15, 2018

//  This program implements an array insertion sort algorithm using nested loops.
//  The algorithm is implemented in one method with debug statements to display 
//  sorting on console, and also in another method which will sort array only. 
//=========================================================================

public class ISort{

    public static void main(String[] args) {
        System.out.println("\n============================================\nWelcome to Giselle's insertion sort program\n============================================\n");

        

        /*use try-catch to catch IndexOutOfBoundsException if user does not 
        input any information into console*/
        try {
        	//if the user does not indicate debug, then parse integers into array
            if (args[0].equals("debug")) {
      			//create array to copy values from command line into
            	int[] a = new int[args.length-1];
                //fill array with input from command line
                for (int i = 1; i < args.length; i++) {
                    a[i-1] = Integer.parseInt(args[i]);
                }
                long start = System.nanoTime(); //track time before start of method
                insertionSortDebug(a); //perform insertion sort
                long end = System.nanoTime();   //track time after method completion
                long totalTime = end - start; //calculate total algorithm execution time
                System.out.println("insertion sort completed in " + totalTime + " nano seconds.");
            //otherwise call insertionSort method 
            } else {
            	//create array to copy values from command line into
            	int[] a = new int[args.length];
                //fill array with input from command line
                for (int i = 0; i < args.length; i++) {
                    a[i] = Integer.parseInt(args[i]);
                }
                                long start = System.nanoTime(); //track time before start of method
                insertionSort(a);
                long end = System.nanoTime();   //track time after method completion
                long totalTime = end - start; //calculate total algorithm execution time
                System.out.println("insertion sort completed in " + totalTime + " nano seconds.");
            }

        }catch (IndexOutOfBoundsException e) {
            System.out.println("Array Out of Bounds. Program Terminating");
            System.exit(0);
        }
        
        //exit program when finished
   		System.out.println("\n=========================================\nThank you for using the program! Goodbye!\n=========================================\n");
   		System.exit(0);
    }
 

    /*insertion sort method without printing*/
    static void insertionSort(int a[]) {
        int current, temp;  //declare int to hold current value and key of value to be checked

        for (int i = 1; i < a.length; i++) {

            current = a[i]; //set current value to second element of array initially
            temp = i - 1; //store index of previous element in array in temp
            /*as long as we haven't reached past the starting bound of array, and the previous element
                is greater than the current element */
            while (temp > -1 && current < a[temp]) {
                a[temp + 1] = a[temp]; //set value after temp to value at temp (since it is larger)
                temp = temp - 1;   //move temp index to previous element.
            }
            //set next value in array to current (value to be sorted)
            a[temp + 1] = current;
        }
    }

    /* Insertion sort method which will be used when debug is requested on the command line
    	this version of the algorithm will call the debug method in order to display the sorting
    	of the array to console. 
    */
    static void insertionSortDebug(int a[]) {
        int current, temp;  //declare int to hold current value and key of value to be checked

        //print first line of debug 
        System.out.print("[" + a[0] + "] ");
        for(int i =1; i<a.length; i++){
        	System.out.print(a[i] + " ");
        }
        System.out.println("|");

        for (int i = 1; i < a.length; i++) {
            current = a[i]; //set current value to second element of array initially
            temp = i - 1; //store index of previous element in array in temp
            
            /*Check current value to be sorted with values in sorted portion of array to determine debug print statements.
            if the index of element to left of current value in array becomes -1,
            we have reached the left bound of array and thus will print | at end of debug statement. 
            If the element to left of current value being  sorted in array is equal,
            then print equal at end of debug statement. If the current value being sorted is greater, then print > at
            end of debug statement. In any other case, the current value being sorted is smaller
            so print < at end of debug statement. */

            if(temp<0){
                debug(a,current,temp,i,"end");
            }else if (current == a[temp]) {
                debug(a, current, temp, i, "equal");
            } else if (current > a[temp]) {
                debug(a, current, temp, i, "greater");
            } else {
                debug(a, current, temp, i, "less");
            }

            /*as long as we haven't reached past the starting bound of array, and the previous element
                is greater than the current element */
            while (temp > -1 && current < a[temp]) {
                a[temp + 1] = a[temp]; //set value after temp to value at temp (since it is larger)
                temp = temp - 1;   //move temp index to previous element.

                //check values being compared again to determine what will be printed at end of debug statement
                if(temp<0){
                    debug(a,current,temp,i,"end");
                }else if (current == a[temp]) {
                    debug(a, current, temp, i, "equal");
                } else if (current > a[temp]) {
                    debug(a, current, temp, i, "greater");
                } else {
                    debug(a, current, temp, i, "less");
                }
            }
            //set next value in array to current (value to be sorted)
            a[temp + 1] = current; 
            }
            for(int i =0; i<a.length; i++){
            	System.out.print(a[i] + " ");
            }
            System.out.println();
    }

    /* Debug method which will be used to print contens of array, current value
    being sorted, and value being compared.*/
    static void debug(int[] a, int current, int temp, int index, String code) {

    	//iterate over entire array to print for debugging purposes
        for (int i = 0; i < a.length; i++) {
        	//if weve reach index in array where value being checked, print in between i's
            if (i == temp) { 
                System.out.print("i" + a[temp] + "i ");
            }
            //if we are at the current value in array, print in between []
            else if (i == index) {
                System.out.print("[" + current + "] ");  
            }
            //if the value is being "shifted", we do want to print its duplicate, 
            //but the value that was temporarily overwritten
            else if(i==index-1){
            	System.out.print(a[index] + " ");
            }
            //if the value is being "shifted", we do want to print its duplicate, 
            //but the value that was temporarily overwritten
           	else if(( i>0 && i<a.length-1) && (a[i]==a[i-1]) ){
            	System.out.print(a[i+1] + " ");
            }
           	//any other case just print the value at i
           	else{
            		System.out.print(a[i] + " ");
            }	
          
        }

        /*print last part of statement depending on whether current value 
        is less, greater, equal to previous values in array, or if we have 
        reached left end of array*/
        switch (code) {
            case "end":
                System.out.print("|");
                break;
            case "less":
                System.out.print("<");
                break;
            case "greater":
                System.out.print(">");
                break;
            case "equal":
                System.out.print("=");
                break;
        }
        System.out.println();

    }

}