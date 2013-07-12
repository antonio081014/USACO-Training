/*
 ID: antonio13
 LANG: JAVA
 PROG: beads
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @since Feb 15, 2012, 5:11:30 PM
 */
public class beads {

    public static void main(String[] args) throws Exception {
        beads main = new beads();
        main.solve();
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                "beads.out")));
        int N = Integer.parseInt(br.readLine());
        String strLine = br.readLine();
        strLine += strLine;
        int max = 0;
        for (int i = 0; i < 2 * N; i++) {
            int m1 = 0;
            char a = 'w';
            boolean first = true;
            for (int k = i; k < 2 * N; k++) {
                if (a == strLine.charAt(k) || strLine.charAt(k) == 'w') {
                    m1++;
                    if (strLine.charAt(k) != 'w') {
                        first = false;
                        a = strLine.charAt(k);
                    }
                }
                else if (strLine.charAt(k) != 'w') {
                    if (first) {
                        first = false;
                        m1++;
                        a = strLine.charAt(k);
                    }
                    else
                        break;
                }
            }
            a = 'w';
            first = true;
            int m2 = 0;
            for (int k = i - 1; k >= 0; k--) {
                if (a == strLine.charAt(k) || strLine.charAt(k) == 'w') {
                    m2++;
                    if (strLine.charAt(k) != 'w') {
                        first = false;
                        a = strLine.charAt(k);
                    }
                }
                else if (strLine.charAt(k) != 'w') {
                    if (first) {
                        first = false;
                        m2++;
                        a = strLine.charAt(k);
                    }
                    else
                        break;
                }
            }
            if (m1 + m2 > max) {
                max = m1 + m2;
                // System.out.println(i + ": " + strLine.charAt(i) + ", " +
                // max);
                // System.out.println(m1 + ", " + m2);
            }
            if (m1 + m2 >= N) {
                max = N;
                break;
            }
        }
        // System.out.println(max);
        out.write("" + max + "\n");
        out.close();
    }
}
