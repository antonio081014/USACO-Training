/*
 ID: antonio13
 LANG: JAVA
 TASK: barn1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Gap implements Comparable<Gap> {
    int start, end;

    Gap(int s, int e) {
        start = s;
        end = e;
    }

    public int compareTo(Gap o) {
        return o.getSize() - getSize();
    }

    public int getSize() {
        return end - start - 1;
    }

} // Gap class

public class barn1 {

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                "barn1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int boards, stallsCovered;

        int[] stalls = new int[c];
        List<Gap> gaps = new ArrayList<Gap>();

        for (int i = 0; i < c; i++)
            stalls[i] = Integer.parseInt(f.readLine());

        Arrays.sort(stalls);

        for (int i = 1; i < c; i++) {
            if (stalls[i] - stalls[i - 1] > 1) {
                gaps.add(new Gap(stalls[i - 1], stalls[i]));
            }
        }
        Collections.sort(gaps);

        stallsCovered = stalls[c - 1] - stalls[0] + 1;
        for (int i = 0; i < gaps.size() && i < m - 1; i++) {
            stallsCovered -= gaps.get(i).getSize();
        }

        out.println(stallsCovered);

        out.close();
        System.exit(0);
    }
}