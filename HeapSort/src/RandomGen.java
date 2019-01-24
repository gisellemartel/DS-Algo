//==============================================================================================================================================
//  COMP 352 Summer 2018 Assignment 2 Question 2
//  Written By: Giselle Martel ID# 26352936
//  Due Date: May 28, 2018
//==============================================================================================================================================

import java.util.Random;


/**
 * <p>
 * this class will create an array of randomized integers, using methods of the Random class
 * There are two overloaded methods, one which takes the length of the array as an parameter, and
 * another which takes the length of the array and a seed as parameters. The seed will be used as the parameter
 * when instantiating an object of the Random class. This allows the same sequence of Random numbers to be
 * repeatedly created as long as the same seed is given.
 * </p>
 *
 * @author Giselle Martel ID# 26352936
 */

public class RandomGen {
    private static final int RANGE = 10000; //create fixed range of number for random integers


    /**
     * This function will create a randomized array of integers, with
     * @param length is the length of the array to be created
     * @return array containing randomized integers in the range from 0 to 10000
     */
    public static int[] generateArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(RANGE);
        }
        return array;
    }


    /**
     *
     * @param length is the length of the array to be created
     * @param seed is a long to be passed into constructor of Random when instantiating random object
     *             A unique seed will always generate the same sequence of randomized integers
     * @return array containing randomized integers in the range from 0 to 10000
     */
    public static int[] generateArray(int length, long seed) {
        Random random = new Random(seed);
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(RANGE);
        }
        return array;
    }

}
