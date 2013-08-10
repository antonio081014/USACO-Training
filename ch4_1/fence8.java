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
