/*
Fence Rails
Burch, Kolstad, and Schrijvers
Farmer John is trying to erect a fence around part of his field. He has decided on the shape of the fence and has even already installed the posts, but he's having a problem with the rails. The local lumber store has dropped off boards of varying lengths; Farmer John must create as many of the rails he needs from the supplied boards.

Of course, Farmer John can cut the boards, so a 9 foot board can be cut into a 5 foot rail and a 4 foot rail (or three 3 foot rails, etc.). Farmer John has an `ideal saw', so ignore the `kerf' (distance lost during sawing); presume that perfect cuts can be made.

The lengths required for the rails might or might not include duplicates (e.g., a three foot rail and also another three foot rail might both be required). There is no need to manufacture more rails (or more of any kind of rail) than called for the list of required rails.

PROGRAM NAME: fence8

INPUT FORMAT

Line 1:	 N (1 <= N <= 50), the number of boards
Line 2..N+1:	 N lines, each containing a single integer that represents the length of one supplied board
Line N+2:	 R (1 <= R <= 1023), the number of rails
Line N+3..N+R+1:	 R lines, each containing a single integer (1 <= ri <= 128) that represents the length of a single required fence rail
SAMPLE INPUT (file fence8.in)

4
30
40
50
25
10
15
16
17
18
19
20
21
25
24
30
OUTPUT FORMAT

A single integer on a line that is the total number of fence rails that can be cut from the supplied boards. Of course, it might not be possible to cut all the possible rails from the given boards.
SAMPLE OUTPUT (file fence8.out)

7
*/


/*
PS: I still can't pass Test Case 8, which will get TLE at the result.
*/



/*
 ID: antonio13
 LANG: JAVA
 PROG: fence8 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author antonio081014
 * @time Aug 9, 2013, 9:48:54 PM
 */
public class fence8 {

	private int[] board;
	private int[] rails;
	private int[] minStart;
	private int N;
	private int P;
	private int best;

	public static void main(String[] args) {
		fence8 main = new fence8();
		main.run();
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("fence8.in"));

			N = Integer.parseInt(in.readLine());
			board = new int[N];
			int sum = 0;
			for (int i = 0; i < N; i++) {
				board[i] = Integer.parseInt(in.readLine());
				sum += board[i];
			}
			P = Integer.parseInt(in.readLine());
			rails = new int[P];
			for (int i = 0; i < P; i++) {
				rails[i] = Integer.parseInt(in.readLine());
			}
			Arrays.sort(board);
			Arrays.sort(rails);
			int[] sumRails = new int[P];
			int start = 0;
			sumRails[start] = rails[start];
			for (start++; start < P && sumRails[start] <= sum; ++start)
				sumRails[start] = sumRails[start - 1] + rails[start];
			P = start;
			minStart = new int[P];
			for (int i = 0, j = 0; i < P; i++) {
				while (j < N && rails[i] > board[j])
					j++;
				minStart[i] = j;
				if (j == N) {
					P = i;
					break;
				}
			}
			if (P == 0) {
				print(0);
			}
			for (int i = P - 1; i >= 0; i--) {
				best = i;
				DFS(i, minStart[i], 0, sum - sumRails[i]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(int data) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("fence8.out"));
			// out.write(String.format("%d\n", data));
			out.println(data);
			out.close();
			System.exit(0);
		} catch (Exception e) {
		}
	}

	private void DFS(int rID, int minIndex, int waste, int maxWaste) {
		if (rID == 0) {
			// This is the last step;
			for (int i = minIndex; i < N; i++)
				if (board[i] >= rails[rID]) {
					print(best + 1);
					// return;
				}
		}

		for (int i = minIndex; i < board.length; i++) {
			if (board[i] >= rails[rID]) {
				int oldwaste = waste;
				board[i] -= rails[rID];

				// now we check if i_th board being useless, amount of
				// wasted board must be less than 'maxWaste'
				if (board[i] < rails[0] && waste + board[i] > maxWaste) {
					board[i] += rails[rID];
					continue;
				}

				// if i_th board being useless we must update 'waste'
				if (board[i] < rails[0]) {
					waste += board[i];
					// continue;
				}

				// now we check if two rails have equal size, then the
				// usage of board for them must be in non-decreasing order

				if (rails[rID - 1] == rails[rID])
					DFS(rID - 1, i, waste, maxWaste);
				else
					DFS(rID - 1, minStart[rID - 1], waste, maxWaste);
				// at last we set the initial state again
				board[i] += rails[rID];
				waste = oldwaste;
			}
		}
	}
}
