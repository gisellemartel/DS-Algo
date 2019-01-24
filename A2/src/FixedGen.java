//==============================================================================================================================================
//  COMP 352 Summer 2018 Assignment 2 Question 2
//  Written By: Giselle Martel ID# 26352936
//  Due Date: May 28, 2018
//==============================================================================================================================================

/**
 * <p>
 * this class will create an array of integers in reverse order. There is one method, which takes the length of
 * the array to be created as a parameter.
 * </p>
 *
 * @author Giselle Martel ID# 26352936
 */

public class FixedGen {

    /**
     * This method creates an array of integers in reverse order.
     * @param length is the length of array to be created
     * @return array which was created
     */
    public static int[] generateArray(int length) {
        int[] array = new int[length]; //generate array with specified length in console
        int index = length - 1;
        //create array in reverse order
        for (int i = 0; i < array.length; i++) {
            array[index] = i;
            index--;
        }
        return array;
    }

}
