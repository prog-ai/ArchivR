import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // do not instantiate
    public MoveToFront() {
    }

    // MoveToFront trie node
    private static class Node {
        private char c;
        private Node n;

        Node(char c) {
            this.c = c;
        }
    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        Node first = new Node('\0');
        Node current = first;
        for (short i = 1; i < R; i++) {
            current.n = new Node((char) i);
            current = current.n;
        }

        // read the input
        char[] input = BinaryStdIn.readString().toCharArray();
        for (int i = 0; i < input.length; i++) {
            current = first;
            char count = 0;
            for (int j = 0; j < R; j++) {
                if (input[i] == current.c) {
                    BinaryStdOut.write(count);
                    break;
                }
                else if (current.n != null && input[i] == current.n.c) {
                    BinaryStdOut.write(++count);
                    Node tmp = current.n;
                    current.n = tmp.n;
                    tmp.n = first;
                    first = tmp;
                    break;
                }
                else {
                    current = current.n;
                    count++;
                }
            }
        }

        // close output stream
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

        char[] input = BinaryStdIn.readString().toCharArray();
        Node first = new Node('\0');
        Node current = first;
        for (short i = 1; i < R; i++) {
            current.n = new Node((char) i);
            current = current.n;
        }
        for (int i = 0; i < input.length; i++) {
            current = first;
            char count = input[i];
            for (short j = 0; j < count - 1; j++) current = current.n;
            if ((int) count == 0) {
                BinaryStdOut.write(current.c);
            }
            else {
                BinaryStdOut.write(current.n.c);
                Node tmp = current.n;
                current.n = tmp.n;
                tmp.n = first;
                first = tmp;
            }
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();

    }
}

