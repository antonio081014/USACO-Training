/*
 ID: antonio13
 LANG: JAVA
 PROG: sprime
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-4-18, 16:50:37
 * 
 *        Solution: DFS; Time is not the problem;
 */

public class sprime {
    BufferedWriter out;

    public static void main(String[] args) throws Exception {
	sprime main = new sprime();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
	out = new BufferedWriter(new FileWriter("sprime.out"));
	int N = Integer.parseInt(br.readLine());
	DFS(2, 1, N);
	DFS(3, 1, N);
	DFS(5, 1, N);
	DFS(7, 1, N);
	out.close();
    }

    public void DFS(int number, int level, int N) throws Exception {
	if (level == N) {
	    out.write(Integer.toString(number) + "\n");
	    return;
	}
	for (int i = 0; i < 10; i++) {
	    int tmp = number * 10 + i;
	    if (isPrime(tmp)) {
		DFS(tmp, level + 1, N);
	    }
	}
    }

    public boolean isPrime(int n) {
	if (n % 2 == 0 && n != 2)
	    return false;
	for (int i = 3; i * i <= n; i += 2)
	    if (n % i == 0)
		return false;
	return true;
    }
}
