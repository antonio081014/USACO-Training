/*
 ID: antonio13
 TASK: stamps
 LANG: JAVA
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class stamps {

	public static void main(String[] args) throws Exception {
		stamps main = new stamps();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"stamps.out")));

		String[] strs = br.readLine().split("\\s");
		int K = Integer.parseInt(strs[0]);
		int N = Integer.parseInt(strs[1]);
		int top = 0;
		int[] elements = new int[N];
		String strLine = null;
		while ((strLine = br.readLine()) != null) {
			strs = strLine.split("\\s");
			for (int i = 0; i < strs.length; i++)
				elements[top++] = Integer.parseInt(strs[i]);
		}
		br.close();
		Arrays.sort(elements, 0, N);
		int MAXN = K * elements[N - 1] + 1;
		int[] mark = new int[MAXN];
		Arrays.fill(mark, Integer.MAX_VALUE);
		for (int i = 0; i < N; i++)
			mark[elements[i]] = 1;
		boolean flag = true;
		for (int i = 1; i < MAXN; i++) {
			if (mark[i] > K || mark[i] == Integer.MAX_VALUE) {
				// System.out.println("Hello");
				out.write(Integer.toString(i - 1));
				flag = false;
				break;
			}
			for (int j = 0; j < N; j++) {
				if (elements[j] + i < MAXN)
					mark[elements[j] + i] = Math.min(mark[elements[j] + i],
							mark[i] + 1);
			}
			// System.out.println(String.format("%d, %d", i, mark[i]));
		}
		// System.out.println(mark[14]);
		if (flag) {
			out.write(Integer.toString(K * elements[N - 1]));
		}
		out.write("\n");
		out.close();
	}

}
