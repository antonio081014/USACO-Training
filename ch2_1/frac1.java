/*
 ID: antonio13
 LANG: JAVA
 PROG: frac1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author antonio081014
 * @time: 2012-4-25, 16:4:21
 * 
 *        Solution: super fast solution from Russ We notice that we can start
 *        with 0/1 and 1/1 as our ``endpoints'' and recursively generate the
 *        middle points by adding numerators and denominators.
 * 
 *        Each fraction is created from the one up to its right and the one up
 *        to its left. This idea lends itself easily to a recursion that we cut
 *        off when we go too deep.
 */

public class frac1 {
    TreeSet<Pair> set;
    int N;

    public static void main(String[] args) throws Exception {
	frac1 main = new frac1();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("frac1.out"));
	N = Integer.parseInt(br.readLine());
	set = new TreeSet<Pair>();
	Pair a = new Pair(0, 1);
	Pair b = new Pair(1, 1);
	set.add(a);
	set.add(b);
	generate(a, b);

	Iterator<Pair> it = set.iterator();
	while (it.hasNext()) {
	    Pair tmp = it.next();
	    out.write(tmp.toString() + "\n");
	}

	out.close();
    }

    public void generate(Pair a, Pair b) {
	int x = a.numerator + b.numerator;
	int y = a.denominator + b.denominator;
	if (y <= N) {
	    Pair tmp = new Pair(x, y);
	    set.add(tmp);

	    generate(a, tmp);
	    generate(tmp, b);
	}
    }
}

class Pair implements Comparable<Pair> {
    public int numerator;
    public int denominator;

    public Pair(int n, int d) {
	this.numerator = n;
	this.denominator = d;
    }

    public int compareTo(Pair a) {
	return this.numerator * a.denominator - this.denominator * a.numerator;
    }

    public String toString() {
	return Integer.toString(numerator) + "/"
		+ Integer.toString(denominator);
    }
}