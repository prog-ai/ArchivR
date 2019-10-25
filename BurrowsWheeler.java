import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;

public class BurrowsWheeler {

    private static int R = 256;

    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        char[] c = s.toCharArray();
        CircularSuffixArray sufArr = new CircularSuffixArray(s);
        for (int i = 0; i < sufArr.length(); i++) if (sufArr.index(i) == 0) BinaryStdOut.write(i);
        for (int i = 0; i < sufArr.length(); i++)
            BinaryStdOut.write(c[(sufArr.index(i) + sufArr.length() - 1) % sufArr.length()]);
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        char[] c = BinaryStdIn.readString().toCharArray();
        int[] next = new int[c.length];
        char[] sortedIn = c.clone();
        char[] aux = new char[c.length];
        int[] count = new int[R + 1];
        int counts = first;
        Queue<Integer>[] q = (Queue<Integer>[]) new Queue[R];

        for (int i = 0; i < c.length; i++) count[sortedIn[i] + 1]++;
        for (int r = 0; r < R; r++) count[r + 1] += count[r];
        for (int i = 0; i < c.length; i++) aux[count[sortedIn[i]]++] = sortedIn[i];
        for (int i = 0; i < c.length; i++) sortedIn[i] = aux[i];
        for (int i = 0; i < R; i++) q[i] = new Queue<>();
        for (int i = 0; i < c.length; i++) q[c[i]].enqueue(i);
        for (int i = 0; i < c.length; i++) next[i] = q[sortedIn[i]].dequeue();
        for (int i = 0; i < c.length; i++) {
            BinaryStdOut.write(sortedIn[counts]);
            counts = next[counts];
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
    }
}
