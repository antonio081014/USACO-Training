/*
 ID: antonio13
 LANG: JAVA
 PROG: castle
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-4-25, 13:9:27
 * 
 *        Solution: color for each room by using dfs(flood fill), mark is the
 *        color for each grid, which is also the number of room.
 * 
 *        for breaking the wall, iterate each wall from W to E, from S to N. for
 *        each grid, handle the N-S case first.
 */

public class castle {

    int N;
    int M;
    Grid[][] map;
    int[][] mark;
    int count;
    int[] record;

    public static void main(String[] args) throws Exception {
	castle main = new castle();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("castle.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("castle.out"));
	String[] str = br.readLine().split("\\s");
	M = Integer.parseInt(str[0]);
	N = Integer.parseInt(str[1]);
	map = new Grid[N][M];
	mark = new int[N][M];
	record = new int[N * M + 1];
	count = 0;
	for (int i = 0; i < N; i++) {
	    str = br.readLine().split("\\s");
	    for (int j = 0; j < M; j++)
		map[i][j] = new Grid(Integer.parseInt(str[j]));
	}

	// printMap();

	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < M; j++) {
		if (mark[i][j] == 0) {
		    count++;
		    record[count] = dfs(i, j);
		}
	    }
	}

	// printRecord();
	// printMark();

	out.write(Integer.toString(count) + "\n");

	int max = 0;

	for (int i = 1; i <= count; i++)
	    max = Math.max(max, record[i]);
	out.write(Integer.toString(max) + "\n");

	int x = N - 1;
	int y = 0;
	char c = 'E';

	for (int j = 0; j < M; j++) {
	    for (int i = N - 1; i >= 0; i--) {
		if (map[i][j].n && i - 1 >= 0 && mark[i][j] != mark[i - 1][j]) {
		    if (max < record[mark[i][j]] + record[mark[i - 1][j]]) {
			max = record[mark[i][j]] + record[mark[i - 1][j]];
			x = i;
			y = j;
			c = 'N';
		    }
		}
		if (map[i][j].e && j + 1 < M && mark[i][j] != mark[i][j + 1]) {
		    if (max < record[mark[i][j]] + record[mark[i][j + 1]]) {
			x = i;
			y = j;
			c = 'E';
			max = record[mark[i][j]] + record[mark[i][j + 1]];
		    }
		}

	    }
	}

	out.write(Integer.toString(max) + "\n");
	out.write(Integer.toString(x + 1) + " " + Integer.toString(y + 1) + " "
		+ c + "\n");
	out.close();
    }

    public int dfs(int x, int y) {
	if (mark[x][y] != 0)
	    return 0;
	mark[x][y] = count;
	int cnt = 1;
	if (!map[x][y].e && y + 1 < M)
	    cnt += dfs(x, y + 1);
	if (!map[x][y].w && y > 0)
	    cnt += dfs(x, y - 1);
	if (!map[x][y].s && x + 1 < N)
	    cnt += dfs(x + 1, y);
	if (!map[x][y].n && x > 0)
	    cnt += dfs(x - 1, y);
	return cnt;
    }

    public void printMap() {
	for (int i = 0; i < N; i++)
	    for (int j = 0; j < M; j++) {
		System.out.println("" + i + ", " + j);
		if (map[i][j].n)
		    System.out.println("###");
		else
		    System.out.println("# #");
		if (map[i][j].w)
		    System.out.print("#");
		else
		    System.out.print(' ');
		System.out.print(' ');
		if (map[i][j].e)
		    System.out.println("#");
		else
		    System.out.println(' ');
		if (map[i][j].s)
		    System.out.println("###");
		else
		    System.out.println("# #");
		System.out.println();
	    }
    }

    private void printRecord() {
	for (int i = 0; i <= count; i++)
	    System.out.println(record[i]);
    }

    private void printMark() {
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < M; j++)
		System.out.print("" + mark[i][j]);
	    System.out.println();
	}
    }
}

class Grid {
    public boolean e;
    public boolean n;
    public boolean w;
    public boolean s;

    public Grid(int mask) {
	// North
	if (mask >= 8) {
	    s = true;
	    mask -= 8;
	} else
	    s = false;

	// East;
	if (mask >= 4) {
	    e = true;
	    mask -= 4;
	} else
	    e = false;

	// North;
	if (mask >= 2) {
	    n = true;
	    mask -= 2;
	} else
	    n = false;

	// West;
	if (mask >= 1) {
	    w = true;
	    mask -= 1;
	} else
	    w = false;
    }
}
