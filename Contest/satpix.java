/*
 ID: antonio13
 TASK: satpix
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Antonio081014
 * @since Jul 22, 2013, 4:22:43 PM
 */

/**
 * <h1>Problem</h1><br />
 * Problem 11: Satellite Photographs [Rob Kolstad, 2005]
 * 
 * Farmer John purchased satellite photos of W x H pixels of his farm (1 <= W <=
 * 80, 1 <= H <= 1000) and wishes to determine the largest 'contiguous'
 * (connected) pasture. Pastures are contiguous when any pair of pixels in a
 * pasture can be connected by traversing adjacent vertical or horizontal pixels
 * that are part of the pasture. (It is easy to create pastures with very
 * strange shapes, even circles that surround other circles.)
 * 
 * Each photo has been digitally enhanced to show pasture area as an asterisk
 * ('*') and non-pasture area as a period ('.'). Here is a 10 x 5 sample
 * satellite photo:
 * 
 * ..*.....** .**..***** .*...*.... ..****.*** ..****.***
 * 
 * This photo shows three contiguous pastures of 4, 16, and 6 pixels. Help FJ
 * find the largest contiguous pasture in each of his satellite photos.
 * 
 * PROBLEM NAME: satpix
 * 
 * INPUT FORMAT:
 * 
 * Line 1: Two space-separated integers: W and H
 * 
 * Lines 2..H+1: Each line contains W "*" or "." characters representing one
 * raster line of a satellite photograph.
 * 
 * SAMPLE INPUT (file satpix.in):
 * 
 * 10 5 ..*.....** .**..***** .*...*.... ..****.*** ..****.***
 * 
 * OUTPUT FORMAT:
 * 
 * Line 1: The size of the largest contiguous field in the satellite photo.
 * 
 * SAMPLE OUTPUT (file satpix.out):
 * 
 * 16
 * 
 ********************************************************************** 
 * */
public class satpix {

  private int[] dx = { 1, 0, -1, 0 };
	private int[] dy = { 0, 1, 0, -1 };

	private int N;
	private int M;
	private String[] board;
	private int[][] mark;

	public static void main(String[] args) {
		satpix main = new satpix();
		main.run();
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("satpix.in"));
			PrintWriter out = new PrintWriter(new FileWriter("satpix.out"));
			String line = in.readLine();
			String[] mn = line.split("\\s");
			M = Integer.parseInt(mn[0]);
			N = Integer.parseInt(mn[1]);
			board = new String[N];
			mark = new int[N][M];
			for (int i = 0; i < N; i++) {
				board[i] = in.readLine();
				for (int j = 0; j < M; j++)
					mark[i][j] = -1;
			}
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i].charAt(j) == '.') {
						// System.out.print(String.format(" %2d", mark[i][j]));
						continue;
					}
					dfs(i, j);
					max = Math.max(max, mark[i][j]);
					// System.out.print(String.format(" %2d", mark[i][j]));
				}
				// System.out.println();
			}
			// System.out.println(max);
			out.write("" + max + "\n");
			in.close();
			out.close();
		} catch (Exception e) {
		}
	}

	private int dfs(int x, int y) {
		if (mark[x][y] != -1 || board[x].charAt(y) != '*')
			return 0;
		mark[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if (xx >= 0 && xx < N && yy >= 0 && yy < M) {
				mark[x][y] += dfs(xx, yy);
			}
		}
		return mark[x][y];
	}
}
