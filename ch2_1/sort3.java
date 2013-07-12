/*
 ID: antonio13
 LANG: JAVA
 PROG: sort3
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * @author antonio081014
 * @time: 2012-4-25, 16:29:5
 */

public class sort3 {

    public int N;
    public int[] num;
    public int[] sorted;
    public int[][] count;

    public static void main(String[] args) throws Exception {
	sort3 main = new sort3();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("sort3.out"));
	N = Integer.parseInt(br.readLine());
	num = new int[N];
	sorted = new int[N];
	count = new int[4][4];

	for (int i = 0; i < N; i++) {
	    num[i] = Integer.parseInt(br.readLine());
	    sorted[i] = num[i];
	}
	Arrays.sort(sorted);

	out.write(Integer.toString(solve1()) + "\n");

	out.close();
    }

    public int solve1() {
	int ret = 0;
	for (int i = 0; i < N; i++) {
	    count[num[i]][sorted[i]]++;
	}
	ret += count[1][2]
		+ count[1][3]
		+ count[3][2]
		+ (count[3][1] - count[1][3] <= 0 ? 0 : count[3][1]
			- count[1][3]);
	return ret;
    }
}
