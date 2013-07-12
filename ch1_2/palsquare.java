/*
 ID: antonio13
 LANG: JAVA
 PROG: palsquare
 */

/**
 * @author antonio081014
 * @since Feb 16, 2012, 7:46:12 PM
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class palsquare {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("palsquare.in"));
        PrintWriter pw = new PrintWriter(new File("palsquare.out"));

        int base = sc.nextInt();

        for (int i = 1; i <= 300; i++) {
            String square = Integer.toString(i * i, base).toUpperCase();
            // String square = toBase(i * i, base, rep);
            if (isPalindrome(square)) {
                pw.println(Integer.toString(i, base).toUpperCase() + " "
                        + square);
            }
        }

        pw.close();
    }

    private static boolean isPalindrome(String input) {
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - i - 1))
                return false;
        }
        return true;
    }

    private static String toBase(int i, int base, char[] rep) {
        StringBuilder sb = new StringBuilder();
        int val = i;
        while (val >= base) {
            sb.insert(0, rep[val % base]);
            val = val / base;
        }
        return sb.insert(0, rep[val]).toString();
    }
}