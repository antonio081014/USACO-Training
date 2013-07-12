/*
 ID: antonio13
 TASK: fact4
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class fact4 {

	public static void main(String[] args) throws Exception {
		fact4 main = new fact4();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"fact4.out")));

		int N = Integer.parseInt(br.readLine());
		br.close();
		int cnt2 = 0;
		int cnt5 = 0;
		int prod = 1;
		for (int i = 1; i <= N; i++) {
			int tmp = i;
			while (tmp % 2 == 0) {
				tmp /= 2;
				cnt2++;
			}
			while (tmp % 5 == 0) {
				tmp /= 5;
				cnt5++;
			}
			prod = (prod * tmp) % 10;
		}

		if (cnt2 == cnt5)
			out.write(Integer.toString(prod));
		else if (cnt2 > cnt5) {
			for (int i = 0; i < cnt2 - cnt5; i++)
				prod = (prod * 2) % 10;
			out.write(Integer.toString(prod));
		} else {
			for (int i = 0; i < cnt5 - cnt2; i++)
				prod = (prod * 5) % 10;
			out.write(Integer.toString(prod));
		}
		out.write("\n");

		out.close();
	}
}
