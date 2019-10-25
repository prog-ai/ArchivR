/******************************************************************************
 *  Compilation:  javac CircularSuffixArray.java
 *  Execution:    java CircularSuffixArray filename.txt
 *  Dependencies: In / Arrays
 *
 *  Creates circularSuffixArray Based on the given string s.
 *
 *  % java CircularSuffixArray abra.txt
 *  Length of string is: 12
 *  Indexes are as follows:
 *  11
 *  10
 *  7
 *  0
 *  3
 *  5
 *  8
 *  1
 *  4
 *  6
 *  9
 *  2
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * This class provides methods for printing strings and numbers to standard output.
 *
 * @author Dumitru Hanciu
 */
public class CircularSuffixArray {

    private Integer[] index;


    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("String must not be null.");

        int length = s.length();
        char[] chars = s.toCharArray();
        index = new Integer[length];

        // initialize each array position with its location
        for (int i = 0; i < s.length(); i++)
            index[i] = i;

        // sort the index array using the given comparator
        Arrays.sort(index, (Integer i1, Integer i2) -> {
            int com = chars[i1] - chars[i2];
            for (int count = 0; com == 0 && count < length; count++) {
                int substringAIndex = ++i1 % length;
                int substringBIndex = ++i2 % length;
                char charA = chars[substringAIndex];
                char charB = chars[substringBIndex];
                com = Character.compare(charA, charB);
            }
            return com;
        });
    }

    /**
     * Returns the length of s.
     *
     * @return the length of s
     */
    public int length() {
        return index.length;
    }

    /**
     * Returns index of ith sorted suffix.
     *
     * @param i the ith sorted sufix
     * @return the index of ith sorted suffix
     * @throws IllegalArgumentException if {@code i < 0 || i >= index.length}
     */
    public int index(int i) {
        if (i < 0 || i >= index.length)
            throw new IllegalArgumentException("provided index \"" + i + "\"" +
                                                       " does not satisfy: 0 <= i < length");
        return index[i];
    }


    /**
     * Unit tests the {@code CircularSuffixArray} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        String str = in.readString();
        CircularSuffixArray abra = new CircularSuffixArray(str);
        System.out.println("Length of string is: " + abra.length());
        System.out.println("Indexes are as follows: ");
        for (int i = 0; i < abra.length(); i++) {
            System.out.println(abra.index(i));
        }
    }
}
