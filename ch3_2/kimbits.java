/*
 ID: antonio13
 TASK: kimbits
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class kimbits {

	private int L;
	private int N;
	private int I;
	private String[] list;
	private PrintWriter out;
	private boolean flag;
	private int top;

	public static void main(String[] args) throws Exception {
		kimbits main = new kimbits();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		String[] strs = br.readLine().split("\\s");
		br.close();
		flag = true;
		N = Integer.parseInt(strs[0]);
		L = Integer.parseInt(strs[1]);
		I = Integer.parseInt(strs[2]);
		// System.out.println(String.format("N %d, L %d, I %d", N, L, I));
		list = new String[I];
		top = 0;
		rec("", 0);
		out.close();
	}

	private void rec(String s, int ones) {
		if (s.length() == N) {
			if (ones <= L && flag)
				list[top++] = s;
			if (top == I && flag) {
				out.write(s + "\n");
				flag = false;
			}
			return;
		}
		if (flag == false)
			return;
		rec(s + "0", ones);
		rec(s + "1", ones + 1);
	}
}
