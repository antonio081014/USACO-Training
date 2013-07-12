/*
 ID: antonio13
 LANG: JAVA
 PROG: friday
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @since Feb 15, 2012, 4:39:53 PM
 */
public class friday {

    public int[] counts;

    public static void main(String[] args) throws Exception {
        friday main = new friday();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                "friday.out")));
        counts = new int[7];
        int N = Integer.parseInt(br.readLine());
        int days = 0;
        for (int y = 1900; y < 1900 + N; y++) {
            for (int m = 1; m <= 12; m++) {
                counts[(days + 13) % 7]++;
                days += getDays(y, m);
                days %= 7;
            }
        }
        String ret = "";
        for (int i = 0; i < 7; i++)
            ret += " " + counts[(i + 6) % 7];
        out.write(ret.substring(1) + "\n");
        out.close();
    }

    public int getDays(int Y, int M) {
        if (M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10
                || M == 12) {
            return 31;
        }
        else if (M == 4 || M == 6 || M == 9 || M == 11) {
            return 30;
        }
        else {
            if ((Y % 100 == 0 && Y % 400 == 0) || (Y % 100 != 0 && Y % 4 == 0)) {
                return 29;
            }
            else {
                return 28;
            }
        }

    }
}
