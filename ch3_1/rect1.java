/*
 ID: antonio13
 LANG: JAVA
 PROG: rect1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-6-13, 10:21:53
 * 
 *        Solution: take a look at
 *        http://www.nocow.cn/index.php/USACO/rect1#.E6.BC.82.E6.B5.AE.E6.B3.95.
 *        It's pretty complicated, but it's like the explanation did, every
 *        rectangle need to compare with the higher ones, if overlap happened
 *        there, it will be broke into pieces; Then, these pieces will compare
 *        with its higher layer of rectangles;
 * 
 */

public class rect1 {

    private final static int nmax = 2501;

    int[] x1;
    int[] y1;
    int[] x2;
    int[] y2;
    int[] color;
    int N;
    int A;
    int B;
    int[] solution;

    public static void main(String[] args) throws Exception {
	rect1 main = new rect1();
	main.init();
	main.run2();
	System.exit(0);
    }

    public void init() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("rect1.in"));
	String[] str = br.readLine().split("\\s");
	A = Integer.parseInt(str[0]);
	B = Integer.parseInt(str[1]);
	N = Integer.parseInt(str[2]);
	solution = new int[nmax];
	x1 = new int[N + 1];
	x2 = new int[N + 1];
	y1 = new int[N + 1];
	y2 = new int[N + 1];
	color = new int[N + 1];

	for (int i = 1; i <= N; i++) {
	    str = br.readLine().split("\\s");
	    x1[i] = Integer.parseInt(str[0]);
	    y1[i] = Integer.parseInt(str[1]);
	    x2[i] = Integer.parseInt(str[2]);
	    y2[i] = Integer.parseInt(str[3]);
	    color[i] = Integer.parseInt(str[4]);
	}
	x1[0] = 0;
	x2[0] = A;
	y1[0] = 0;
	y2[0] = B;
	color[0] = 1;
    }

    /**
     * Method two;
     * http://www.nocow.cn/index.php/USACO/rect1#.E7.81.8C.E6.B0.B4.E6.B3.95
     * */
    public void run2() throws Exception {
	BufferedWriter out = new BufferedWriter(new FileWriter("rect1.out"));
	for (int x = 0; x <= A; x++)
	    for (int y = 0; y <= B; y++) {
		for (int k = N; k >= 0; k--) {
		    if (x > x1[k] && x <= x2[k] && y > y1[k] && y <= y2[k]) {
			solution[color[k]]++;
			break;
		    }
		}
	    }

	for (int i = 1; i < nmax; i++) {
	    if (solution[i] > 0) {
		out.write(Integer.toString(i) + " "
			+ Integer.toString(solution[i]) + "\n");
	    }
	}
	out.close();
    }

    /****************************************************************************
     * Method one;
     * http://www.nocow.cn/index.php/USACO/rect1#.E6.BC.82.E6.B5.AE.E6.B3.95
     * 
     * */
    public void run() throws Exception {
	BufferedWriter out = new BufferedWriter(new FileWriter("rect1.out"));
	int tmp = A * B;
	for (int i = N; i > 0; i--) {
	    cover(x1[i], x2[i], y1[i], y2[i], i + 1, color[i]);

	}
	for (int i = 2; i < nmax; i++)
	    tmp -= solution[i];
	solution[1] = tmp;

	for (int i = 1; i < nmax; i++)
	    if (solution[i] > 0) {
		out.write(Integer.toString(i) + " "
			+ Integer.toString(solution[i]) + "\n");
	    }

	out.close();
    }

    void cover(int left, int right, int top, int bottom, int k, int color) {
	while ((k <= N)
		&& (left > x2[k] || right < x1[k] || top > y2[k] || bottom < y1[k])) {
	    k++;
	}

	if (k > N) {
	    solution[color] += (right - left) * (bottom - top);
	    return;
	}
	if (left <= x1[k]) {
	    cover(left, x1[k], top, bottom, k + 1, color);
	    left = x1[k];
	}
	if (right >= x2[k]) {
	    cover(x2[k], right, top, bottom, k + 1, color);
	    right = x2[k];
	}
	if (top <= y1[k]) {
	    cover(left, right, top, y1[k], k + 1, color);
	    top = y1[k];
	}
	if (bottom >= y2[k]) {
	    cover(left, right, y2[k], bottom, k + 1, color);
	    bottom = y2[k];
	}
    }
}