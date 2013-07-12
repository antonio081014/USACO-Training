/*
 ID: antonio13
 TASK: kimbits
 LANG: JAVA 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @time Feb 21, 2013, 7:37:09 PM
 */
public class kimbits {

	private int N;
	private int L;
	private long I;
	private long[][] mark;
	private PrintWriter out;

	public static void main(String[] args) throws Exception {
		kimbits main = new kimbits();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
		out = new PrintWriter(new FileWriter("kimbits.out"));

		String[] strs = br.readLine().split("\\s");
		N = Integer.parseInt(strs[0]);
		L = Integer.parseInt(strs[1]);
		I = Long.parseLong(strs[2]);
		br.close();

		mark = new long[N + 1][L + 1];
		for (int i = 0; i <= N; i++)
			mark[i][0] = 1L;
		for (int i = 0; i <= L; i++) {
			mark[0][i] = 1L;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= L; j++)
				if (i >= j)
					mark[i][j] = mark[i - 1][j - 1] + mark[i - 1][j];
				else
					mark[i][j] = mark[i][i];
		}
		// print(N, L, I);
		printbit(N, L, I);
		out.write('\n');
		out.close();
	}

	private void print(int n, int l, int i) {
		if (l > n)
			l = n;
		if (mark[n][l] == i) {
			for (int j = 0; j < i; j++)
				out.write('1');
			for (int j = i; j < n; j++)
				out.write('0');
		} else {
			if (i > mark[n - 1][l]) {
				out.write('1');
				print(n - 1, l - 1, (int) ((long) i - mark[n - 1][l]));
			} else {
				out.write('0');
			}
		}
	}

	void printbit(int i, int j, long K) {
		if (j > i)
			j = i;

		if (K == mark[i][j]) {
			for (int t = 1; t <= j; t++)
				out.write('1');
			for (int t = 1; t <= i - j; t++)
				out.write('0');
		} else {

			if (K <= mark[i - 1][j]) {
				out.write('0');
				printbit(i - 1, j, K);
			} else {
				out.write('1');
				printbit(i - 1, j - 1, K - mark[i - 1][j]);
			}
		}
	}
}
