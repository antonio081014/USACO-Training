/*
 ID: antonio13
 LANG: JAVA
 PROG: hamming
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-4-25, 22:38:27
 */

public class hamming {
    public static void main(String[] args) throws Exception {
        hamming main = new hamming();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("hamming.out"));

        String[] str = br.readLine().split("\\s");
        int N = Integer.parseInt(str[0]);
        int B = Integer.parseInt(str[1]);
        int D = Integer.parseInt(str[2]);

        int[] num = new int[64];
        num[0] = 0;
        out.write("0");
        int length = 1;
        for (int i = 1; i < (1 << B); i++) {
            boolean flag = true;
            for (int j = 0; j < length && flag; j++) {
                if (distance(i, num[j]) < D) {
                    flag = false;
                }
            }
            if (flag) {
                num[length++] = i;
                if (length % 10 != 1)
                    out.write(" " + Integer.toString(i));
                else
                    out.write(Integer.toString(i));
                if (length % 10 == 0)
                    out.write("\n");
            }
            if (length >= N)
                break;
        }
        if (length % 10 != 0)
            out.write("\n");

        out.close();
    }

    public int distance(int x, int y) {
        int cnt = 0, tmp = x ^ y;
        for (; tmp > 0;) {
            cnt++;
            // tmp >>= 1;
            tmp = tmp - (tmp & (-tmp));
        }
        return cnt;
    }
}
