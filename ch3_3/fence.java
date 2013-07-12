/*
 ID: antonio13
 PROB: fence
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author antonio081014
 * @time Mar 7, 2013, 11:53:50 PM
 */
public class fence {

	private int[] degree;
	private int[][] graph;
	private int N;
	private ArrayList<Integer> list;

	public static void main(String[] args) throws Exception {
		fence main = new fence();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		init();
		find_euler_path();
		print();
	}

	private void init() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		N = Integer.parseInt(in.readLine());
		graph = new int[500][500];
		degree = new int[500];
		for (int i = 0; i < N; i++) {
			String[] strs = in.readLine().split("\\s");
			int from = Integer.parseInt(strs[0]) - 1;
			int to = Integer.parseInt(strs[1]) - 1;
			graph[from][to]++;
			graph[to][from]++;
			degree[from]++;
			degree[to]++;
		}
		in.close();
	}

	private void print() throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter("fence.out"));
		for (int i = list.size() - 1; i >= 0; i--)
			out.write(String.format("%d\n", list.get(i) + 1));
		out.close();
	}

	private void find_euler_path() {
		list = new ArrayList<Integer>();
		int found = -1;
		for (int i = 0; i < 500; i++)
			if (degree[i] % 2 == 1) {
				found = i;
				break;
			}
		if (found == -1) {
			for (int i = 0; i < 500; i++)
				if (degree[i] > 0) {
					found = i;
					break;
				}
		}
		find_circuit(found);
	}

	private void find_circuit(int current) {
		for (int i = 0; i < 500; i++) {
			if (graph[current][i] > 0) {
				graph[current][i]--;
				graph[i][current]--;
				find_circuit(i);
			}
		}
		list.add(current);
	}

}
