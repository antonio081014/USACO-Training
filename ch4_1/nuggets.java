/*
 ID: antonio13
 LANG: JAVA
 PROG: nuggets 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @time Jul 28, 2013, 3:26:58 PM
 */
public class nuggets {

  private static final int SIZE = 100000;
	private boolean[] mark;

	public static void main(String[] args) {
		nuggets main = new nuggets();
		main.run();
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("nuggets.in"));
			PrintWriter out = new PrintWriter(new FileWriter("nuggets.out"));
			int N = Integer.parseInt(in.readLine());
			int[] array = new int[N];
			for (int i = 0; i < N; i++) {
				array[i] = Integer.parseInt(in.readLine());
			}
			in.close();
			out.println(solve(array));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int solve(int[] array) {
		mark = new boolean[SIZE];
		boolean flag = true;
		for (int i = 0; i < array.length; i++)
			for (int j = i + 1; j < array.length; j++)
				if (gcd(array[i], array[j]) == 1)
					flag = false;
		if (flag)
			return 0;

		int max = 0;
		mark[0] = true;
		for (int i = 0; i < SIZE; i++) {
			if (!mark[i])
				max = i;
			else
				for (int j = 0; j < array.length; j++) {
					if (i + array[j] < SIZE)
						mark[i + array[j]] = true;
				}
		}
		return max;
	}

	public int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
}
