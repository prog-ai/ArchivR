/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class test {

    public static void tests(int a, int b) {
        StdOut.println(a + " " + b);
        while (a < 2) StdOut.println(--a);
    }

    public static void main(String[] args) {
        for (int i = 7; i < -3; i--) {
            tests(i, i);
        }

    }
}
