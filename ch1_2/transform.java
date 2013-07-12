/*
 ID: antonio13
 LANG: JAVA
 PROG: transform
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @since Feb 16, 2012, 4:11:26 PM
 */
public class transform {

    public static void main(String[] args) throws Exception {
        transform main = new transform();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("transform.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("transform.out"));
        int N = Integer.parseInt(br.readLine());
        String[] src = new String[N];
        String[] dst = new String[N];
        for (int i = 0; i < N; i++)
            src[i] = br.readLine();
        for (int i = 0; i < N; i++)
            dst[i] = br.readLine();
        int ret = 8;
        // print(src);
        // System.out.println();
        // print(reflect(src));
        // print(rotate90(src));
        // print(dst);
        ret = Math.min(ret, Comp(6, src, dst));
        ret = Math.min(ret, Comp(1, rotate90(src), dst));
        ret = Math.min(ret, Comp(2, rotate90(rotate90(src)), dst));
        ret = Math.min(ret, Comp(3, rotate90(rotate90(rotate90(src))), dst));
        ret = Math.min(ret, Comp(4, reflect(src), dst));
        ret = Math.min(ret, Comp(5, rotate90(reflect(src)), dst));
        ret = Math.min(ret, Comp(5, rotate90(rotate90(reflect(src))), dst));
        ret = Math.min(ret,
                Comp(5, rotate90(rotate90(rotate90(reflect(src)))), dst));
        ret = Math.min(ret, 7);
        out.write("" + ret + "\n");
        out.close();
    }

    public int Comp(int code, String[] src, String[] dst) {
        boolean flag = true;
        for (int i = 0; i < src.length && flag; i++)
            if (src[i].compareTo(dst[i]) != 0)
                flag = false;
        if (flag)
            return code;
        return 8;
    }

    public String[] rotate90(String[] src) {
        String[] tmp = src.clone();
        int N = src.length;
        for (int i = 0; i < N; i++) {
            String s = "";
            for (int j = 0; j < N; j++) {
                s += src[N - 1 - j].charAt(i);
            }
            tmp[i] = s;
        }
        return tmp;
    }

    public String[] reflect(String[] src) {
        String[] tmp = src.clone();
        for (int i = 0; i < src.length; i++) {
            tmp[i] = new StringBuffer(src[i]).reverse().toString();
        }
        return tmp;
    }

    public void print(String[] src) {
        for (int i = 0; i < src.length; i++) {
            System.out.println(src[i]);
        }
    }
}
