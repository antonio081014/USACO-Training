/*
 ID: antonio13
 LANG: JAVA
 PROG: checker
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author antonio081014
 * @time: 2012-4-18, 17:12:6
 * 
 * http://www.matrix67.com/blog/archives/266
 */

public class checker {

    private int            N;
    private int            count;
    private int            aim;
    private BufferedWriter out;
    private int[]          column;

    public static void main(String[] args) throws Exception {
        checker main = new checker();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("checker.in"));
        out = new BufferedWriter(new FileWriter("checker.out"));
        N = Integer.parseInt(br.readLine());

        aim = (1 << N) - 1;

        column = new int[N];

        count = 0;
        placequeen(0, 0, 0, 0);

        out.write(Integer.toString(count));
        out.write('\n');

        out.close();
    }

    public void placequeen(int row, int ld, int rd, int col) throws IOException {
        if (col == aim) {
            if (count < 3) {
                out.write(Integer.toString(column[0]));
                for (int i = 1; i < N; i++)
                    out.write(" " + Integer.toString(column[i]));
                out.write('\n');
            }
            count++;
            return;
        }
        int pos = aim & (~(col | ld | rd));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p;

            if (count < 3) {
                int k = p;
                int l = 1;
                while (k != 1) {
                    k >>= 1;
                    l++;
                }
                column[row] = l;
            }

            placequeen(row + 1, (ld | p) << 1, (rd | p) >> 1, col | p);
        }

    }
}