/*
 ID: antonio13
 LANG: JAVA
 PROG: concom
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-9, 9:16:7
 */

public class concom {

    // List<Integer> comp;
    int[][] matrix;
    int N;

    public static void main(String[] args) throws Exception {
	concom main = new concom();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("concom.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("concom.out"));

	N = Integer.parseInt(br.readLine());

	matrix = new int[101][101];

	for (int i = 0; i < N; i++) {
	    String[] str = br.readLine().split("\\s");
	    int a = Integer.parseInt(str[0]);
	    int b = Integer.parseInt(str[1]);
	    int p = Integer.parseInt(str[2]);
	    matrix[a][b] = p;
	}

	for (int i = 1; i < 101; i++) {
	    int[] sum = new int[101];
	    DFS(i, new boolean[101], sum);
	    for (int j = 1; j < 101; j++) {
		if (i != j && sum[j] > 50) {
		    System.out.println("" + (i) + " " + j);
		    out.write(Integer.toString(i) + " " + Integer.toString(j)
			    + "\n");
		}
	    }
	}

	out.close();
    }

    public void DFS(int x, boolean[] flag, int[] cnt) {
	if (flag[x])
	    return;
	flag[x] = true;
	for (int i = 100; i > 0; --i) {
	    cnt[i] += matrix[x][i];
	    if (cnt[i] > 50)
		DFS(i, flag, cnt);
	}
    }

}
