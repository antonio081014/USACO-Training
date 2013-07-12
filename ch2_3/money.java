/*
 ID: antonio13
 LANG: JAVA
 PROG: money
 */
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author antonio081014
 * @time: 2012-5-8, 17:53:47
 */

public class money {
    public static void main(String[] args) throws Exception {
        money main = new money();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        Scanner sc = new Scanner(new FileReader("money.in"));
        // BufferedReader br = new BufferedReader(new FileReader("money.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("money.out"));

        // String[] str = br.readLine().split("\\s");
        int V = sc.nextInt();
        int N = sc.nextInt();
        long[] cost = new long[N + 1];

        // str = br.readLine().split("\\s");

        cost[0] = 1L;

        for (int i = 0; i < V; i++) {
            int m = sc.nextInt();
            for (int j = m; j <= N; j++) {
                cost[j] += cost[j - m];
            }
        }

        // System.out.println(cost[N]);
        out.write(Long.toString(cost[N]) + "\n");

        out.close();
    }
}
