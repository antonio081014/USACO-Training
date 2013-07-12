/*
 ID: antonio13
 PROB: contact
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class contact {

	public static void main(String[] args) throws Exception {
		contact main = new contact();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"contact.out")));
		String[] strs = br.readLine().split("\\s");
		int a = Integer.parseInt(strs[0]);
		int b = Integer.parseInt(strs[1]);
		int n = Integer.parseInt(strs[2]);

		int A_min = 1 << a;
		int B_max = (1 << (b + 1)) - 1;

		int[] counter = new int[20000];

		long start = System.currentTimeMillis();
		String strLine = "0";
		String input = null;
		while ((input = br.readLine()) != null)
			strLine += input;
		int L = strLine.length();
		br.close();
		long at = System.currentTimeMillis();

		for (int i = 0; i < L; i++) {
			for (int j = 1, mask = 1; j <= b && i + j < L; j++) {
				mask = mask * 2 + (strLine.charAt(i + j) - '0');
				counter[mask] += j >= a ? 1 : 0;
			}
		}
		long bt = System.currentTimeMillis();

		// ArrayList<Pattern> list = new ArrayList<Pattern>();
		Pattern[] list = new Pattern[20001];
		int top = 0;
		for (int i = A_min; i <= B_max; i++) {
			if (counter[i] > 0)
				list[top++] = new Pattern(i, counter[i]);
		}

		Arrays.sort(list, 0, top);
		long ct = System.currentTimeMillis();
		// for (int i = 0; i < 10; i++)
		// System.out.println(String.format("%d, %d", list[i].data,
		// list[i].count));
		// Collections.sort(list);
		out.write(String.format("%d\n", list[0].count));
		print(list[0].data, out);
		for (int i = 1, c = 0, idx = 0; i < top; i++) {
			if (list[i].count != list[i - 1].count) {
				c++;
				idx = 0;
				if (c >= n)
					break;
				out.write(String.format("\n%d\n", list[i].count));
			} else {
				out.write(idx == 5 ? '\n' : ' ');
				idx = (idx + 1) % 6;
			}
			print(list[i].data, out);
		}
		out.write("\n");
		out.close();
		long dt = System.currentTimeMillis();

		System.out.println("Input: " + (at - start));
		System.out.println("Main: " + (bt - at));
		System.out.println("Filter: " + (ct - bt));
		System.out.println("Output: " + (dt - ct));
	}

	void print(int num, PrintWriter out) {
		if (num <= 1)
			return;
		print(num / 2, out);
		out.write('0' + (num % 2));

	}
}

class Pattern implements Comparable<Pattern> {
	public int data;
	public int count;

	public Pattern() {
		this.data = 0;
		this.count = 0;
	}

	public Pattern(int d, int c) {
		this.data = d;
		this.count = c;
	}

	@Override
	public int compareTo(Pattern a) {
		if (this.count == a.count)
			return this.data - a.data;
		return a.count - this.count;
	}
}