/*
 ID: antonio13
 TASK: game1
 LANG: JAVA
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Antonio081014
 * 
 */
public class game1 {

  private int[][] mark;
	private int[] score;

	public static void main(String[] args) throws Exception {
		game1 main = new game1();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		// try {
		// BufferedReader in = new BufferedReader(new
		// FileReader("game1.in"));
		Scanner in = new Scanner(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new FileWriter("game1.out"));
		int sum = 0;
		int N = in.nextInt();
		mark = new int[N][N];
		score = new int[N];
		for (int i = 0; i < N; i++) {
			score[i] = in.nextInt();
			sum += score[i];
			// System.out.println(score[i]);
			for (int j = 0; j < N; j++)
				mark[i][j] = -1;
		}
		int score1 = maxie(0, N - 1);
		// System.out.println(String.format("%d %d", score1, sum - score1));
		out.write(String.format("%d %d\n", score1, sum - score1));
		in.close();
		out.close();
		// } catch (Exception e) {
		// System.out.println("Error:" + e.getLocalizedMessage());
		// }
	}

	private int maxie(int start, int end) {
		if (start > end)
			return 0;
		if (start == end)
			return mark[start][end] = score[start];
		if (mark[start][end] != -1)
			return mark[start][end];
		int min1 = Math.min(maxie(start + 2, end), maxie(start + 1, end - 1))
				+ score[start];
		int min2 = Math.min(maxie(start + 1, end - 1), maxie(start, end - 2))
				+ score[end];
		return mark[start][end] = Math.max(min1, min2);
	}
}
