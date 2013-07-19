/*
 ID: antonio13
 TASK: range
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Antonio081014
 * Dynamic Programming.
 */
public class range {
  private int[][] mark;

	public static void main(String[] args) {
		range main = new range();
		main.run();
		System.exit(0);
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("range.in"));
			PrintWriter out = new PrintWriter(new FileWriter("range.out"));
			int N = Integer.parseInt(in.readLine());
			mark = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int c = in.read();
					if (c != '1' && c != '0') {
						j--;
						continue;
					}
					mark[i][j] = c - '0';
					// System.out.print(" " + mark[i][j]);
				}
				// System.out.println();
			}
			in.close();

			for (int i = 1; i < N; i++) {
				for (int j = 1; j < N; j++) {
					if (mark[i][j] == 1) {
						int min = Math.min(mark[i - 1][j],
								Math.min(mark[i - 1][j - 1], mark[i][j - 1]));
						mark[i][j] = min + 1;
					}
				}
			}
			int[] count = new int[N + 1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// System.out.print(" " + mark[i][j]);
					while (mark[i][j] > 0) {
						count[mark[i][j]--]++;
					}
				}
				// System.out.println();
			}
			for (int i = 2; i <= N; i++) {
				if (count[i] > 0) {
					out.write(String.format("%d %d\n", i, count[i]));
				}
			}

			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
