/*
 * ID: antonio13
 * LANG: JAVA
 * PROG: clocks
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author antonio081014
 * @time: 2012-4-13, 11:40:53
 */

public class clocks {
    // private static StringBuffer sb;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        int[][] moves = { { 1, 1, 0, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 1, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 0, 0, 1, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 1, 1, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 0, 1, 1 } };
        boolean[] seen = new boolean[1 << 20];

        BufferedReader br = new BufferedReader(new FileReader("clocks.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("clocks.out"));
        int st = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer stok = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                st <<= 2;
                st += (parseInt(stok.nextToken()) / 3) & 3;
            }
        }
        seen[st] = true;
        Queue<Node> q = new PriorityQueue<Node>(1 << 20);
        q.add(new Node(st, null, -1));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.code == 0) {
                list = new ArrayList<Integer>();
                print(cur);
                Collections.sort(list);
                int cnt = 0;
                int lst = 0;
                String ret = "";
                for (int i = 0; i < list.size(); i++) {
                    int tmp = list.get(i);
                    if (lst != tmp) {
                        if (cnt > 0) {
                            for (int j = 0; j < cnt; j++)
                                ret += Integer.toString(lst) + " ";
                        }
                        cnt = 1;
                        lst = tmp;
                    }
                    else {
                        cnt++;
                        if (cnt >= 4)
                            cnt -= 4;
                    }
                }
                cnt %= 4;
                for (int i = 0; i < cnt; i++)
                    ret += Integer.toString(lst) + " ";
                pw.println(ret.trim());
                break;
            }
            for (int i = 0; i < moves.length; i++) {
                int nc = 0;
                int t = cur.code;
                for (int j = 8; j >= 0; j--) {
                    int d = t & 3;
                    t >>= 2;
                    d += moves[i][j];
                    d = d & 3;
                    nc += d << ((8 - j) << 1);
                }
                if (!seen[nc]) {
                    seen[nc] = true;
                    q.add(new Node(nc, cur, i));
                }
            }
        }
        pw.flush();
        pw.close();
        System.exit(0);
    }

    private static int parseInt(String s) {
        int result = 0;
        int sign = (s.charAt(0) == '-') ? -1 : 1;
        if (sign == -1)
            s = s.substring(1);
        if (s.charAt(0) == '+')
            s = s.substring(1);
        int i = 0, max = s.length();
        if (max > 0) {
            while (i < max) {
                result *= 10;
                result += s.charAt(i++) - 48;
            }
        }
        return sign * result;
    }

    private static void print(Node cur) {
        if (cur.lastMove == -1)
            return;
        print(cur.prev);
        list.add(cur.lastMove + 1);
    }
}

class Node implements Comparable<Node> {
    int  code, lastMove;
    Node prev;

    public Node(int code, Node prev, int lastMove) {
        this.code = code;
        this.prev = prev;
        this.lastMove = lastMove;
    }

    @Override
    public int compareTo(Node n) {
        return this.code - n.code;
    }
}