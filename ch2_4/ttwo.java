/*
 ID: antonio13
 LANG: JAVA
 PROG: ttwo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-9, 14:34:40
 * 
 *        Simulation; Using visited array to store the visited state; total
 *        state: 10*10*4*10*10*4 = 160,000;
 */

public class ttwo {

    int N;
    private static final int[] deltax = { -1, 0, 1, 0 };
    private static final int[] deltay = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
	ttwo main = new ttwo();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("ttwo.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("ttwo.out"));

	N = 10;
	boolean[][][][][][] visited = new boolean[10][10][4][10][10][4];
	char[][] map = new char[10][10];

	int x1 = -1;
	int y1 = -1;
	int d1 = 0;

	int x2 = -1;
	int y2 = -1;
	int d2 = 0;

	for (int i = 0; i < 10; i++) {
	    map[i] = br.readLine().toCharArray();
	    // System.out.println(map[i]);
	    for (int j = 0; j < 10; j++)
		if (map[i][j] == 'C') {
		    x2 = i;
		    y2 = j;
		    map[i][j] = '.';
		} else if (map[i][j] == 'F') {
		    x1 = i;
		    y1 = j;
		    map[i][j] = '.';
		}
	}
	int count = 0;
	for (;;) {
	    if (x1 == x2 && y1 == y2) {
		out.write(Integer.toString(count) + "\n");
		// System.out.println(count);
		out.close();
		return;
	    }
	    if (visited[x1][y1][d1][x2][y2][d2]) {
		out.write("0\n");
		// System.out.println(0);
		out.close();
		return;
	    }
	    count++;
	    visited[x1][y1][d1][x2][y2][d2] = true;
	    if (checkBoundary(x1 + deltax[d1], y1 + deltay[d1])
		    && map[x1 + deltax[d1]][y1 + deltay[d1]] != '*') {
		x1 += deltax[d1];
		y1 += deltay[d1];
	    } else
		d1 = (d1 + 1) % 4;

	    if (checkBoundary(x2 + deltax[d2], y2 + deltay[d2])
		    && map[x2 + deltax[d2]][y2 + deltay[d2]] != '*') {
		x2 += deltax[d2];
		y2 += deltay[d2];
	    } else
		d2 = (d2 + 1) % 4;

	    // System.out.println("F: " + x1 + " " + y1);
	    // System.out.println("C: " + x2 + " " + y2);
	}
    }

    public boolean checkBoundary(int x, int y) {
	return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
}
