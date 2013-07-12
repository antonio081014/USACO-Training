/*
 ID: antonio13
 PROB: msquare
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class msquare {

	public static void main(String[] args) throws Exception {
		msquare main = new msquare();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"msquare.out")));
		String[] strs = in.readLine().split("\\s");
		in.close();
		int[] src = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] dst = new int[8];
		for (int i = 0; i < 8; i++)
			dst[i] = Integer.parseInt(strs[i]);

		Queue<State> queue = new LinkedList<State>();
		queue.add(new State(src, ""));
		while (queue.isEmpty() == false) {
			State state = queue.poll();
			if (state.isEqual(dst)) {
				out.write(String.format("%d\n%s", state.ops.length(), state.ops));
				break;
			}
			queue.add(state.transformA());
			queue.add(state.transformB());
			queue.add(state.transformC());
		}
		out.close();
	}

}

class State implements Comparable<State> {
	public int[] array;
	public String ops;

	public State(int[] a, String prev) {
		this.array = a.clone();
		this.ops = prev;
	}

	public boolean isEqual(int[] a) {
		for (int i = 0; i < array.length; i++)
			if (array[i] != a[i])
				return false;
		return true;
	}

	public State transformA() {
		int[] a = this.array.clone();
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
		String prev = this.ops + "A";
		State A = new State(a, prev);
		return A;
	}

	public State transformB() {
		int[] b = this.array.clone();
		int tmp = b[3];
		b[1] = b[0];
		b[2] = b[1];
		b[3] = b[2];
		b[0] = tmp;
		tmp = b[4];
		b[4] = b[5];
		b[5] = b[6];
		b[6] = b[7];
		b[7] = tmp;
		String prev = this.ops + "B";
		State B = new State(b, prev);
		return B;
	}

	public State transformC() {
		int[] c = this.array.clone();
		int tmp = c[1];
		c[1] = c[6];
		c[6] = c[5];
		c[5] = c[2];
		c[2] = tmp;
		String prev = this.ops + "C";
		State B = new State(c, prev);
		return B;
	}

	@Override
	public int compareTo(State o) {
		for (int i = 0; i < array.length; i++)
			if (this.array[i] != o.array[i])
				return this.array[i] - o.array[i];
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		return true;
	}
	
	
}
