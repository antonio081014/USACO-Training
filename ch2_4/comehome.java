/*
 ID: antonio13
 LANG: JAVA
 PROG: comehome
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-11, 13:25:39
 */

public class comehome {

    public int N;
    public int[][] cost;

    public static void main(String[] args) throws Exception {
	comehome main = new comehome();
	// main.Floyd();
	main.Dijkstra();
	System.exit(0);
    }

    public void Floyd() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("comehome.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("comehome.out"));

	N = Integer.parseInt(br.readLine());
	cost = new int[52][52];
	for (int i = 0; i < 52; i++) {
	    for (int j = 0; j < 52; j++)
		cost[i][j] = Integer.MAX_VALUE / 2;
	    cost[i][i] = 0;
	}

	for (int i = 0; i < N; i++) {
	    String[] str = br.readLine().split(" ");
	    int a = getIndex(str[0].charAt(0));
	    int b = getIndex(str[1].charAt(0));
	    int c = Integer.parseInt(str[2]);
	    cost[a][b] = Math.min(cost[a][b], c);
	    cost[b][a] = Math.min(cost[b][a], c);
	}

	// for (int i = 0; i < 52; System.out.println(), i++)
	// for (int j = 0; j < 52; j++)
	// System.out.print(" " + cost[i][j]);

	for (int k = 0; k < 52; k++) {
	    for (int i = 0; i < 52; i++)
		for (int j = 0; j < 52; j++)
		    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
	}

	// for (int i = 26; i < 52; i++)
	// System.out.println(cost[i][51]);

	int min = Integer.MAX_VALUE;
	int idx = -1;
	for (int i = 26; i < 51; i++) {
	    if (cost[i][51] < min) {
		min = cost[i][51];
		idx = i;
	    }
	}

	// System.out.println(idx - 26);
	// System.out.println(min);
	out.write((char) ('A' + idx - 26) + " " + Integer.toString(min) + "\n");
	out.close();
    }

    public int getIndex(char c) {
	if (c >= 'a')
	    return c - 'a';
	else
	    return 26 + c - 'A';
    }

    public void Dijkstra() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("comehome.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("comehome.out"));

	N = Integer.parseInt(br.readLine());
	cost = new int[52][52];
	for (int i = 0; i < 52; i++) {
	    for (int j = 0; j < 52; j++)
		cost[i][j] = Integer.MAX_VALUE / 2;
	    cost[i][i] = 0;
	}

	for (int i = 0; i < N; i++) {
	    String[] str = br.readLine().split(" ");
	    int a = getIndex(str[0].charAt(0));
	    int b = getIndex(str[1].charAt(0));
	    int c = Integer.parseInt(str[2]);
	    cost[a][b] = Math.min(cost[a][b], c);
	    cost[b][a] = Math.min(cost[b][a], c);
	}

	boolean[] visited = new boolean[52];
	// using 'Z' as the source vertice;
	int[] dist = cost[51];
	for (;;) {
	    // find the next closest node;
	    int min = Integer.MAX_VALUE;
	    int idx = -1;
	    for (int i = 0; i < 51; i++) {
		if (!visited[i] && dist[i] < min) {
		    min = dist[i];
		    idx = i;
		}
	    }
	    if (idx >= 26) {
		out.write((char) ('A' + idx - 26) + " " + Integer.toString(min)
			+ "\n");
		out.close();
		return;
	    }
	    visited[idx] = true;
	    for (int i = 0; i < 51; i++) {
		if (!visited[i]) {
		    dist[i] = Math.min(dist[i], dist[idx] + cost[idx][i]);
		}
	    }
	}

    }
}
