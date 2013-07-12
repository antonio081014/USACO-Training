/*
 ID: antonio13
 LANG: JAVA
 PROG: inflate
 */
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author antonio081014
 * @time: 2012-5-25, 13:55:5
 * 
 *        Solution: totally Dynamic programming;
 */

public class inflate {

    int[] cost;

    public static void main(String[] args) throws Exception {
	inflate main = new inflate();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	Scanner br = new Scanner(new FileReader("inflate.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("inflate.out"));

	int M = br.nextInt();
	int N = br.nextInt();
	cost = new int[M + 1];
	for (int i = 0; i < N; i++) {
	    int a = br.nextInt();
	    int b = br.nextInt();
	    for (int j = b; j <= M; j++) {
		cost[j] = Math.max(cost[j], cost[j - b] + a);
	    }
	}

	out.write(Integer.toString(cost[M]) + "\n");

	out.close();
    }
}
