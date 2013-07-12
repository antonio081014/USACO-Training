/*
 ID: antonio13
 LANG: JAVA
 PROG: pprime
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author antonio081014
 * @time: 2012-4-18, 9:40:42
 */

public class pprime {

    Set<Integer> set;
    BufferedReader br;
    BufferedWriter out;

    private boolean isPrime(int n) {
	if (n % 2 == 0 && n != 2)
	    return false;
	for (int i = 3; i * i <= n; i += 2)
	    if (n % i == 0)
		return false;
	return true;
    }

    public static void main(String[] args) throws Exception {
	pprime main = new pprime();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	br = new BufferedReader(new FileReader("pprime.in"));
	out = new BufferedWriter(new FileWriter("pprime.out"));
	String[] str = br.readLine().split("\\s");
	set = new TreeSet<Integer>();
	int a = Integer.parseInt(str[0]);
	int b = Integer.parseInt(str[1]);
	int lenA = str[0].length();
	int lenB = str[1].length();

	if (lenA <= 1 && lenB >= 1)
	    generate1digits(a, b);
	if (lenA <= 2 && lenB >= 2)
	    generate2digits(a, b);
	if (lenA <= 3 && lenB >= 3)
	    generate3digits(a, b);
	if (lenA <= 5 && lenB >= 5)
	    generate5digits(a, b);
	if (lenA <= 7 && lenB >= 7)
	    generate7digits(a, b);

	out.close();
    }

    public void generate7digits(int A, int B) throws Exception {
	/* generate five digit palindrome: */
	for (int d1 = 1; d1 <= 9; d1 += 2) { /* only odd; evens aren't so prime */
	    for (int d2 = 0; d2 <= 9; d2++) {
		for (int d3 = 0; d3 <= 9; d3++) {
		    for (int d4 = 0; d4 <= 9; d4++) {
			String s = "";
			s += d1;
			s += d2;
			s += d3;
			s += d4;
			s += d3;
			s += d2;
			s += d1;
			int num = Integer.parseInt(s);
			if (num >= A && num <= B && isPrime(num)) {
			    out.write(Integer.toString(num) + "\n");
			    // set.add(num);
			}
		    }
		}
	    }
	}
    }

    public void generate5digits(int A, int B) throws Exception {
	/* generate five digit palindrome: */
	for (int d1 = 1; d1 <= 9; d1 += 2) { /* only odd; evens aren't so prime */
	    for (int d2 = 0; d2 <= 9; d2++) {
		for (int d3 = 0; d3 <= 9; d3++) {
		    String s = "";
		    s += d1;
		    s += d2;
		    s += d3;
		    s += d2;
		    s += d1;
		    int num = Integer.parseInt(s);
		    if (num >= A && num <= B && isPrime(num)) {
			out.write(Integer.toString(num) + "\n");
			// set.add(num);
		    }
		}
	    }
	}
    }

    public void generate3digits(int A, int B) throws Exception {
	for (int d1 = 1; d1 <= 9; d1 += 2) {
	    for (int d2 = 0; d2 <= 9; d2++) {
		String s = "";
		s += d1;
		s += d2;
		s += d1;
		int num = Integer.parseInt(s);
		if (num >= A && num <= B && isPrime(num)) {
		    out.write(Integer.toString(num) + "\n");
		    // set.add(num);
		}
	    }
	}

    }

    public void generate2digits(int A, int B) throws Exception {
	if (A <= 11 && B >= 11)
	    out.write("11\n");
	// set.add(11);
    }

    public void generate1digits(int A, int B) throws Exception {
	for (int i = 2; i <= 7; i++) {
	    if (i >= A && i <= B && isPrime(i))
		out.write(Integer.toString(i) + "\n");
	    // set.add(i);
	}
    }
}
