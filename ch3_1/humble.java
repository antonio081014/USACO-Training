/*
 ID: antonio13
 LANG: JAVA
 PROG: humble
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * @author antonio081014
 * @time: 2012-5-25, 14:37:58
 * 
 *        Solution: follow the idea from Russ Cox;
 *        http://www.nocow.cn/index.php/USACO/humble
 * 
 *        The worst case would be O(KN^2), but it could never be that bad, since
 *        pindex save a lot of time for iterating the humble numbers;
 */

public class humble {

    long[] num;
    int[] pindex;
    long[] humbles;

    public static void main(String[] args) throws Exception {
	humble main = new humble();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("humble.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("humble.out"));

	String[] str = br.readLine().split("\\s");
	int K = Integer.parseInt(str[0]);
	int N = Integer.parseInt(str[1]);
	str = br.readLine().split("\\s");
	num = new long[K];
	pindex = new int[K];
	humbles = new long[N + 1];
	for (int i = 0; i < K; num[i] = Integer.parseInt(str[i]), i++)
	    ;
	Arrays.sort(num);
	humbles[0] = 1;

	for (int i = 1; i <= N; i++) {
	    long last = humbles[i - 1];
	    long min = Long.MAX_VALUE;
	    for (int k = 0; k < K; k++) {
		for (int j = pindex[k]; j < i; j++) {
		    if (num[k] * humbles[j] > last) {
			min = Math.min(min, num[k] * humbles[j]);
			break;
		    } else {
			pindex[k] = j;
		    }
		}
	    }
	    humbles[i] = min;
	}

	// for (int i = 0; i <= N; i++)
	// System.out.println(humbles[i]);
	out.write(Long.toString(humbles[N]) + "\n");

	out.close();
    }
}
