/*
 ID: antonio13
 LANG: JAVA
 PROG: agrinet
 */
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author antonio081014
 * @time: 2012-5-20, 13:11:32
 * 
 *        Solution: just minimum spanning tree;
 */

public class agrinet {

	private int[][] connection;
	private int[] distance;
	private boolean[] intree;

	public static void main(String[] args) throws Exception {
		agrinet main = new agrinet();
		main.run();
		System.exit(0);
	}

	public void run() throws Exception {
		Scanner br = new Scanner(new FileReader("agrinet.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("agrinet.out"));

		int N = br.nextInt();
		connection = new int[N][N];
		distance = new int[N];
		intree = new boolean[N];

		for (int i = 0; i < N; i++) {
			distance[i] = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				connection[i][j] = br.nextInt();
			}
		}

		int treesize = 1;
		int treecost = 0;
		intree[0] = true;
		for (int i = 1; i < N; i++) {
			distance[i] = Math.min(distance[i], connection[0][i]);
		}

		while (treesize < N) {
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (intree[i] == false && distance[i] < distance[idx]) {
					idx = i;
				}
			}
			// check if max == MAX_VALUE, not connected;
			treesize++;
			treecost += distance[idx];
			intree[idx] = true;
			for (int i = 0; i < N; i++) {
				if (!intree[i] && distance[i] > connection[idx][i])
					distance[i] = connection[idx][i];
			}
		}

		out.write(Integer.toString(treecost) + "\n");

		out.close();
	}
}
