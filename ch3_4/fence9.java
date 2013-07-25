/*
 ID: antonio13
 LANG: JAVA
 PROG: fence9
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @time Jul 24, 2013, 8:10:12 PM <br />
 *       Theorem:Pick's theorem. http://en.wikipedia.org/wiki/Pick%27s_theorem
 */
public class fence9 {

	public static void main(String[] args) {
		fence9 main = new fence9();
		main.run();
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("fence9.in"));
			PrintWriter out = new PrintWriter(new FileWriter("fence9.out"));
			String[] s = in.readLine().split("\\s");
			int n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);
			int p = Integer.parseInt(s[2]);
			int area = (m * p) / 2;
			int b = gcd(n, m);
			b += p;
			if (n != p)
				b += gcd(Math.abs(n - p), m);
			else
				b += m;
			out.write(String.format("%d\n", area + 1 - (b / 2)));
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int gcd(int n, int m) {
		if (m == 0)
			return n;
		return gcd(m, n % m);
	}
}
