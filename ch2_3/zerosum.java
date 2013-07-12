/*
 ID: antonio13
 LANG: JAVA
 PROG: zerosum
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-8, 17:24:49
 */

public class zerosum {

    char[] seq;
    int N;
    BufferedWriter out;

    public static void main(String[] args) throws Exception {
	zerosum main = new zerosum();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
	out = new BufferedWriter(new FileWriter("zerosum.out"));

	N = Integer.parseInt(br.readLine());
	seq = new char[2 * N];
	for (int i = 0; i < N; i++) {
	    seq[2 * i] = (char) ('1' + i);
	}

	DFS(1, 0, 1);

	out.close();
    }

    public void DFS(int k, int sum, int last) throws Exception {
	if (k == N) {
	    if (sum + last == 0)
		print();
	    return;
	}
	seq[k * 2 - 1] = ' ';
	DFS(k + 1, sum, last > 0 ? last * 10 + k + 1 : last * 10 - k - 1);
	seq[k * 2 - 1] = '+';
	DFS(k + 1, sum + last, k + 1);
	seq[k * 2 - 1] = '-';
	DFS(k + 1, sum + last, -k - 1);
    }

    public void print() throws Exception {
	for (int i = 0; i < 2 * N - 1; i++)
	    out.write(seq[i]);
	out.write("\n");
    }
}
