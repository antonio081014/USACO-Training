/*
 ID: antonio13
 LANG: JAVA
 PROG: nocows
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-8, 14:51:8
 * 
 *        Solution: table[i][j] represents the solution of i level with j number
 *        of nodes in that tree;
 */

public class nocows {

    private static final int MOD = 9901;

    public static void main(String[] args) throws Exception {
	nocows main = new nocows();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("nocows.out"));

	String[] str = br.readLine().split("\\s");
	int N = Integer.parseInt(str[0]);
	int K = Integer.parseInt(str[1]);

	int[][] table = new int[K + 1][N + 1];
	int[][] smalltrees = new int[K + 1][N + 1];

	table[1][1] = 1;

	for (int i = 2; i <= K; i++) {
	    for (int j = 1; j <= N; j += 2) {
		for (int k = 1; k <= j - 1 - k; k += 2) {
		    int c = ((k == j - 1 - k) ? 1 : 2);
		    table[i][j] += c
			    * (table[i - 1][j - 1 - k] * smalltrees[i - 2][k]
				    + table[i - 1][k]
				    * smalltrees[i - 2][j - 1 - k] + table[i - 1][k]
				    * table[i - 1][j - 1 - k]);
		    table[i][j] %= MOD;
		}
	    }
	    for (int k = 0; k <= N; k++) {
		smalltrees[i - 1][k] += table[i - 1][k] + smalltrees[i - 2][k];
		smalltrees[i - 1][k] %= MOD;
	    }

	}

	System.out.println(table[K][N]);
	out.write(Integer.toString(table[K][N]) + "\n");

	out.close();
    }
}
