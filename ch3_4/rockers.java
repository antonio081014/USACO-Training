/*
 ID: antonio13
 TASK: rockers
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Antonio081014
 * @since Jul 27, 2013, 4:04:33 PM
 */
public class rockers {

  public static void main(String[] args) {
		rockers main = new rockers();
		main.run();
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("rockers.in"));
			PrintWriter out = new PrintWriter(new FileWriter("rockers.out"));
			String[] s = in.readLine().split("\\s");
			int N = Integer.parseInt(s[0]);
			int T = Integer.parseInt(s[1]);
			int M = Integer.parseInt(s[2]);
			s = in.readLine().split("\\s");
			int[] a = new int[N];
			for (int i = 0; i < N; i++)
				a[i] = Integer.parseInt(s[i]);
			// System.out.println(solve(N, T, M, a));
			out.write("" + solve(N, T, M, a) + "\n");
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int solve(int N, int T, int M, int[] a) {
		int[][][] dp = new int[N + 1][M + 1][T + 1];
		for (int i = 0; i < N; i++)
			for (int j = 0; j <= M; j++)
				for (int k = 0; k <= T; k++)
					dp[i][j][k] = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				for (int k = 1; k <= T; k++) {
					// Case1, use this disk;
					// Case2, do not use it;
					dp[i][j][k] = Math.max(i - 1 >= 0 ? dp[i - 1][j][k] : 0,
							j - 1 >= 0 ? dp[i][j - 1][T] : 0);
					// Case3, Space on Disk > Song's Space.
					if (a[i] < k)
						dp[i][j][k] = Math.max(dp[i][j][k],
								i - 1 >= 0 ? dp[i - 1][j][k - a[i]] + 1 : 1);
					// Case4, Space on Disk == Song's Space.
					if (a[i] == k)
						dp[i][j][k] = Math.max(dp[i][j][k], i - 1 >= 0
								&& j - 1 >= 0 ? dp[i - 1][j - 1][T] + 1 : 1);
				}
		return dp[N - 1][M - 1][T];
	}
}
