/*
 ID: antonio13
 LANG: JAVA
 PROG: numtri
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-4-18, 9:5:42
 */

public class numtri {

    private int[][] cost;

    public static void main(String[] args) throws Exception {
	numtri main = new numtri();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("numtri.out"));

	int R = Integer.parseInt(br.readLine());
	cost = new int[2][R];
	String[] str;
	int row = 0;
	int max = 0;
	for (int i = 0; i < R; i++) {
	    str = br.readLine().split("\\s");
	    for (int j = 0; j <= i; j++) {
		int a = Integer.parseInt(str[j]);
		cost[row][j] = a + cost[1 - row][j];
		if (j != 0) {
		    cost[row][j] = Math.max(cost[row][j], a
			    + cost[1 - row][j - 1]);
		}
		max = Math.max(max, cost[row][j]);
	    }

	    row = 1 - row;
	}
	out.write(Integer.toString(max) + "\n");

	out.close();
    }
}
