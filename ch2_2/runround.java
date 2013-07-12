/*
 ID: antonio13
 LANG: JAVA
 PROG: runround
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-1, 15:49:20
 */

public class runround {
    public static void main(String[] args) throws Exception {
	runround main = new runround();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("runround.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("runround.out"));
	Integer M = Integer.parseInt(br.readLine());
	for (M++; !isRunroundNumber(M); M++)
	    ;
	out.write(Integer.toString(M) + "\n");
	out.close();
    }

    private boolean isRunroundNumber(int value) {
	String digit = Integer.toString(value);
	int length = digit.length();

	boolean[] repeated = new boolean[10];
	for (int i = 0; i < length; i++) {
	    if (repeated[digit.charAt(i) - '0']) {
		return false;
	    } else {
		repeated[digit.charAt(i) - '0'] = true;
	    }
	}

	int pointer = 0;
	boolean[] isTouched = new boolean[length];
	for (int i = 0; i < length; i++) {
	    pointer = (pointer + digit.charAt(pointer) - '0') % length;
	    if (isTouched[pointer]) {
		return false;
	    } else {
		isTouched[pointer] = true;
	    }
	}
	return true;
    }

}
