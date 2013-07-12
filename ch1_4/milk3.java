/*
 ID: antonio13
 LANG: JAVA
 PROB: milk3
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author antonio081014
 * @time: 2012-4-15, 13:36:39
 */

public class milk3 {

    public Set<Integer>           set;
    public TreeMap<Pair, Boolean> map;
    public int                    capA;
    public int                    capB;
    public int                    capC;

    public static void main(String[] args) throws Exception {
        milk3 main = new milk3();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("milk3.out"));
        set = new TreeSet<Integer>();
        map = new TreeMap<Pair, Boolean>();
        String[] strs = br.readLine().split("\\s");
        capA = Integer.parseInt(strs[0]);
        capB = Integer.parseInt(strs[1]);
        capC = Integer.parseInt(strs[2]);
        solve(0, 0, capC);
        List<Integer> list = new ArrayList<Integer>(set);
        out.write(Integer.toString(list.get(0)));
        for (int i = 1; i < list.size(); i++) {
            out.write(" " + Integer.toString(list.get(i)));
        }
        out.write("\n");
        out.close();
    }

    public void solve(int A, int B, int C) {
        Pair pair = new Pair(A, B, C);
        if (map.get(pair) != null)
            return;
        map.put(pair, true);
        if (A == 0)
            set.add(C);
        if (A > 0) {
            solve(A - Math.min(A, capB - B), B + Math.min(A, capB - B), C);
            solve(A - Math.min(A, capC - C), B, C + Math.min(A, capC - C));
        }
        if (B > 0) {
            solve(A + Math.min(B, capA - A), B - Math.min(B, capA - A), C);
            solve(A, B - Math.min(B, capC - C), C + Math.min(B, capC - C));
        }
        if (C > 0) {
            solve(A + Math.min(C, capA - A), B, C - Math.min(C, capA - A));
            solve(A, B + Math.min(C, capB - B), C - Math.min(C, capB - B));
        }
    }
}

class Pair implements Comparable<Pair> {
    public int A;
    public int B;
    public int C;

    public Pair(int a, int b, int c) {
        this.A = a;
        this.B = b;
        this.C = c;
    }

    public int compareTo(Pair a) {
        if (this.A != a.A)
            return this.A - a.A;
        if (this.B != a.B)
            return this.B - a.B;
        return this.C - a.C;
    }
}
