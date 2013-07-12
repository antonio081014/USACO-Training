/*
 ID: antonio13
 PROB: butter
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * For every vertex, using Dijkstra to get the shortest path cost between single
 * source and every other vertex.<br/>
 * 
 * PS: using Priority Queue to improve performance, since heap uses O(nlgn) to
 * insert, and O(1) to retrieve. Also, for the constrains provided from the
 * problem statement (C<=1450), use one two dimension array to keep the
 * connected edges for each vertex will greatly reduce the number to adjacent
 * vertex (800*800=640000) to check.
 * 
 * */
public class butter {

	private int N;
	private int P;
	private int[][] graph;
	private int[] cows;
	private ArrayList<ArrayList<Integer>> edges;

	// private ArrayList<ArrayList<Integer>> weights;

	public static void main(String[] args) throws Exception {
		butter main = new butter();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		init();

		int min = solve();

		PrintWriter out = new PrintWriter(new FileWriter("butter.out"));
		out.write(String.format("%d\n", min));
		out.close();
	}

	private void init() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));

		String[] strs = in.readLine().split("\\s");
		N = Integer.parseInt(strs[0]);
		P = Integer.parseInt(strs[1]);
		int C = Integer.parseInt(strs[2]);

		cows = new int[N];
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(in.readLine()) - 1;
		}

		edges = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < P; i++) {
			edges.add(new ArrayList<Integer>());
		}
		graph = new int[P][P];
		for (int i = 0; i < P; i++) {
			for (int j = 0; j < P; j++)
				graph[i][j] = Integer.MAX_VALUE;
			graph[i][i] = 0;
		}

		for (int i = 0; i < C; i++) {
			strs = in.readLine().split("\\s");
			int from = Integer.parseInt(strs[0]);
			int to = Integer.parseInt(strs[1]);
			int weight = Integer.parseInt(strs[2]);
			graph[from - 1][to - 1] = weight;
			graph[to - 1][from - 1] = weight;

			edges.get(from - 1).add(to - 1);
			edges.get(to - 1).add(from - 1);
		}
		in.close();
	}

	private int solve() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < P; i++) {
			int cost = Dijkstra(i);
			min = Math.min(min, cost);
		}
		return min;
	}

	private int Dijkstra(int current) {
		Node[] list = new Node[P];
		for (int i = 0; i < P; i++) {
			list[i] = new Node(i);
		}

		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		list[current].dist = 0;
		queue.add(list[current]);
		while (queue.isEmpty() == false) {
			Node mNode = queue.poll();
			int id = mNode.ID;
			// System.out.println("ID: " + id);
			if (list[id].visited == false) {
				list[id].visited = true;
				int sz = edges.get(id).size();
				for (int i = 0; i < sz; i++) {
					int dst = edges.get(id).get(i);
					if (graph[id][dst] == Integer.MAX_VALUE)
						continue;
					int cost = graph[id][dst];
					if (list[dst].visited == false
							|| list[dst].dist - cost > list[id].dist) {
						list[dst].dist = Math.min(list[dst].dist, list[id].dist
								+ cost);
						queue.add(list[dst]);
					}
				}
			}
		}

		int total = 0;
		for (int i = 0; i < N; i++)
			total += list[cows[i]].dist;
		return total;
	}

}

class Node implements Comparable<Node> {
	public boolean visited;
	public int ID;
	public int dist;

	public Node(int id) {
		this.ID = id;
		this.dist = Integer.MAX_VALUE;
		this.visited = false;
	}

	public int compareTo(Node a) {
		if (this.dist == a.dist)
			return this.ID - a.ID;
		else
			return this.dist - a.dist;
	}
}