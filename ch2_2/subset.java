/*
 ID: antonio13
 LANG: JAVA
 PROG: subset
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-1, 15:20:17
 * 
 *        Solution: cost[i][j]: represents ways of the first i elements to
 *        compose sum j.
 */

public class subset {

    long[][] cost;

    public static void main(String[] args) throws Exception {
	subset main = new subset();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("subset.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("subset.out"));
	int N = Integer.parseInt(br.readLine());
	int sum = N * (N + 1) / 2;
	if (sum % 2 != 0) {
	    out.write("0\n");
	    out.close();
	    return;
	}
	cost = new long[N][sum + 1];

	for (int i = 0; i < N; i++) {
	    cost[i][i] = 1;
	    for (int j = 0; j <= sum; j++) {
		if (i > 0) {
		    cost[i][j] = cost[i - 1][j];
		    if (j >= i)
			cost[i][j] += cost[i - 1][j - i];
		}
	    }
	}
	out.write(Long.toString(cost[N - 1][sum / 2]) + "\n");

	out.close();
    }
}
