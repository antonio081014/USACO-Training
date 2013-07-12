/*
 ID: antonio13
 LANG: JAVA
 PROG: ariprog
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author antonio081014
 * @time: 2012-4-13, 14:20:31
 */

public class ariprog {
    public static void main(String[] args) throws Exception {
	ariprog main = new ariprog();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("ariprog.out"));
	int N = Integer.parseInt(br.readLine());
	int M = Integer.parseInt(br.readLine());
	boolean[] mark = new boolean[M * M * 2 + 1];
	int[] list = new int[M * M * 2 + 1];
	List<Pair> pairs = new ArrayList<Pair>();
	for (int p = 0; p <= M; p++)
	    // if q starts from 0, it will recalculate a lot, but the result
	    // doesn't change.
	    for (int q = p; q <= M; q++)
		mark[p * p + q * q] = true;
	int size = 0;
	for (int i = 0; i < M * M * 2 + 1; i++)
	    if (mark[i])
		list[size++] = i;

	for (int i = 0; i < size; i++) {
	    for (int j = i + 1; j < size; j++) {
		int a = list[i];
		int b = list[j] - list[i];
		boolean flag = true;
		for (int x = 2; x < N; x++) {
		    if (a + b * x > M * M * 2 || mark[a + b * x] == false) {
			flag = false;
			break;
		    }
		}
		if (flag) {
		    pairs.add(new Pair(a, b));
		}
	    }
	}
	Collections.sort(pairs);
	if (pairs.size() == 0) {
	    out.write("NONE\n");
	} else {
	    for (int i = 0; i < pairs.size(); i++) {
		Pair tmp = pairs.get(i);
		out.write("" + tmp.a + " " + tmp.b + "\n");
	    }
	}

	out.close();
    }
}

class Pair implements Comparable<Pair> {
    public int a;
    public int b;

    public Pair(int x, int y) {
	this.a = x;
	this.b = y;
    }

    @Override
    public int compareTo(Pair p) {
	if (this.b == p.b)
	    return this.a - p.a;
	return this.b - p.b;
    }
}
