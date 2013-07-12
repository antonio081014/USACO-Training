/*
 * ID: antonio13
 * LANG: JAVA
 * PROG: holstein
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-4-25, 19:18:40
 * 
 *        Solution: enumerate all of the combinations;
 */

public class holstein {

    public int[]   amount;
    public int[][] cost;

    public static void main(String[] args) throws Exception {
        holstein main = new holstein();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("holstein.out"));
        int V = Integer.parseInt(br.readLine());
        amount = new int[V];
        String[] str = br.readLine().split("\\s");
        for (int i = 0; i < V; i++)
            amount[i] = Integer.parseInt(str[i]);
        int G = Integer.parseInt(br.readLine());
        cost = new int[G][V];
        for (int i = 0; i < G; i++) {
            str = br.readLine().split("\\s");
            for (int j = 0; j < V; j++) {
                cost[i][j] = Integer.parseInt(str[j]);
            }
        }

        int min = Integer.MAX_VALUE;
        int mak = 0;

        for (int mask = 0; mask < (1 << G); mask++) {
            int[] sum = new int[V];
            int tmp = 0;
            for (int i = 0; i < G; i++) {
                if ((mask & 1 << i) != 0) {
                    tmp++;
                    for (int j = 0; j < V; j++) {
                        sum[j] += cost[i][j];
                    }
                }
            }

            boolean mark = true;
            for (int i = 0; i < V && mark; i++) {
                if (sum[i] < amount[i])
                    mark = false;
            }

            if (mark && tmp <= min) {
                if (tmp == min) {
                    mak = Math.min(mak, mask);
                }
                else if (tmp < min) {
                    min = tmp;
                    mak = mask;
                }
                // System.out.println(Integer.toBinaryString(mak));
            }
        }

        out.write(Integer.toString(min));
        // System.out.print(min);

        for (int i = 0; i < G; i++) {
            if ((mak & 1 << i) != 0) {
                out.write(" " + Integer.toString(i + 1));
                // System.out.print(" " + (i + 1));
            }
        }
        out.write("\n");
        out.close();
    }
}
