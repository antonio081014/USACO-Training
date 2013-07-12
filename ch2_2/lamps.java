/*
 ID: antonio13
 LANG: JAVA
 PROG: lamps
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author antonio081014
 * @time: 2012-5-4, 16:26:24
 * 
 *        for this problem, I used the way to go through every possibility,
 *        since there are only 4 buttons, and any one of button pressed even
 *        times will be counted as nothing changed.
 * 
 *        So, there are only 2^4 states there.
 */

public class lamps {
    public static void main(String[] args) throws Exception {
	lamps main = new lamps();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("lamps.out"));
	int N = Integer.parseInt(br.readLine());
	int C = Integer.parseInt(br.readLine());
	String[] ones = br.readLine().split("\\s");
	String[] zeros = br.readLine().split("\\s");
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < N; i++)
	    sb.append('*');
	for (int i = 0; i < ones.length - 1; i++) {
	    int idx = Integer.parseInt(ones[i]) - 1;
	    sb.setCharAt(idx, '1');
	}
	for (int i = 0; i < zeros.length - 1; i++) {
	    int idx = Integer.parseInt(zeros[i]) - 1;
	    sb.setCharAt(idx, '0');
	}

	while (C > 4)
	    C -= 2;

	String result = sb.toString();
	sb = new StringBuilder();

	for (int i = 0; i < N; i++)
	    sb.append('1');
	String init = sb.toString();

	// System.out.println(result);
	// System.out.println(init);

	TreeSet<String> set = new TreeSet<String>();

	for (int mask = 0; mask < (1 << 4); mask++) {
	    String tmp = init;
	    int count = 0;
	    for (int i = 0; i < 4; i++) {
		if ((mask >> i & 1) != 0) {
		    count++;
		    if (i == 0)
			tmp = button1(tmp);
		    else if (i == 2)
			tmp = button3(tmp);
		    else if (i == 1)
			tmp = button2(tmp);
		    else if (i == 3)
			tmp = button4(tmp);
		}
	    }

	    // System.out.println(tmp);
	    boolean flag = true;
	    for (int i = 0; i < N && flag; i++) {
		if (result.charAt(i) != '*'
			&& result.charAt(i) != tmp.charAt(i))
		    flag = false;
	    }
	    if (flag && C >= count && (C - count) % 2 == 0) {
		// System.out.println(tmp);
		set.add(tmp);
	    }
	}

	if (set.size() == 0) {
	    out.write("IMPOSSIBLE\n");
	} else {
	    Iterator<String> it = set.iterator();
	    while (it.hasNext()) {
		out.write(it.next() + "\n");
	    }
	}

	out.close();
    }

    public String button1(String a) {
	String ret = "";
	for (int i = 0; i < a.length(); i++)
	    ret += '1' - a.charAt(i);
	return ret;
    }

    public String button2(String a) {
	String ret = "";
	for (int i = 0; i < a.length(); i++)
	    if (i % 2 == 0)
		ret += '1' - a.charAt(i);
	    else
		ret += a.charAt(i);
	return ret;
    }

    public String button3(String a) {
	String ret = "";
	for (int i = 0; i < a.length(); i++)
	    if (i % 2 == 1)
		ret += '1' - a.charAt(i);
	    else
		ret += a.charAt(i);
	return ret;
    }

    public String button4(String a) {
	String ret = "";
	for (int i = 0; i < a.length(); i++)
	    if (i % 3 == 0)
		ret += '1' - a.charAt(i);
	    else
		ret += a.charAt(i);
	return ret;
    }
}
