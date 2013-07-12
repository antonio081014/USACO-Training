/*
 ID: antonio13
 LANG: JAVA
 PROG: preface
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-1, 15:6:16
 */

public class preface {

    int[][] code = { { 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 }, { 3, 0, 0 },
	    { 1, 1, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 2, 1, 0 }, { 3, 1, 0 },
	    { 1, 0, 1 } }, s = { { 0, 0, 0 }, { 1, 0, 0 }, { 3, 0, 0 },
	    { 6, 0, 0 }, { 7, 1, 0 }, { 7, 2, 0 }, { 8, 3, 0 }, { 10, 4, 0 },
	    { 13, 5, 0 }, { 14, 5, 1 } };

    int[] res;

    public static void main(String[] args) throws Exception {
	preface main = new preface();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("preface.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("preface.out"));

	int n = Integer.parseInt(br.readLine());
	res = new int[10];
	for (int pri = 0, k = 1; k <= n; k *= 10, pri += 2) {
	    for (int i = 0; i < 3; ++i) {
		res[i + pri] += n / (k * 10) * s[9][i] * k;
		if (n / k % 10 != 0)
		    res[i + pri] += s[n / k % 10 - 1][i] * k;
		res[i + pri] += (n % k + 1) * code[n / k % 10][i];
	    }
	}
	for (int i = 0; i < 7; ++i)
	    if (res[i] != 0)
		out.write(String.format("%c %d\n", "IVXLCDM".charAt(i), res[i]));
	out.close();
    }
}
