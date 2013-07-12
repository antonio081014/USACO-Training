/*
 ID: antonio13
 LANG: JAVA
 PROG: maze1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @time: 2012-5-9, 20:34:54
 */

public class maze1 {

    private static final int[] dx = { 2, 0, -2, 0 };
    private static final int[] dy = { 0, -2, 0, 2 };
    private int                h;
    private int                w;
    private int[]              exitX;
    private int[]              exitY;
    char[][]                   src;

    private int[][]            cost;

    public static void main(String[] args) throws Exception {
        maze1 main = new maze1();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("maze1.out"));

        String[] str = br.readLine().split("\\s");
        w = Integer.parseInt(str[0]);
        h = Integer.parseInt(str[1]);

        exitX = new int[2];
        exitY = new int[2];

        src = new char[2 * h + 1][2 * w + 1];
        for (int i = 0; i < 2 * h + 1; i++) {
            src[i] = br.readLine().toCharArray();
        }
        int cnt = 0;
        cost = new int[h][w];
        for (int i = 1; i < 2 * h + 1; i += 2) {
            for (int j = 1; j < 2 * w + 1; j += 2) {
                cost[i / 2][j / 2] = Integer.MAX_VALUE;
                if (src[i][j] == ' ') {
                    if (i == 1 && src[i - 1][j] == ' ') {
                        exitX[cnt] = i;
                        exitY[cnt] = j;
                        cnt++;
                    }
                    else if (i == 2 * h - 1 && src[i + 1][j] == ' ') {
                        exitX[cnt] = i;
                        exitY[cnt] = j;
                        cnt++;
                    }
                    else if (j == 1 && src[i][j - 1] == ' ') {
                        exitX[cnt] = i;
                        exitY[cnt] = j;
                        cnt++;
                    }
                    else if (j == 2 * w - 1 && src[i][j + 1] == ' ') {
                        exitX[cnt] = i;
                        exitY[cnt] = j;
                        cnt++;
                    }
                }
            }
        }

        cost[exitX[0] / 2][exitY[0] / 2] = 0;
        DFS(exitX[0], exitY[0]);
        if (cnt > 1) {
            cost[exitX[1] / 2][exitY[1] / 2] = 0;
            DFS(exitX[1], exitY[1]);
        }
        int max = 0;
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++) {
                if (cost[i][j] != Integer.MAX_VALUE)
                    max = Math.max(max, cost[i][j]);
                // System.out.print(" " + cost[i][j]);
            }
        // System.out.println(max + 1);
        out.write(Integer.toString(max + 1) + "\n");
        out.close();
    }

    public void DFS(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 1 && xx < 2 * h && yy >= 1 && yy < 2 * w
                    && src[(x + xx) / 2][(y + yy) / 2] == ' ') {
                if (cost[xx / 2][yy / 2] > 1 + cost[x / 2][y / 2]) {
                    cost[xx / 2][yy / 2] = 1 + cost[x / 2][y / 2];
                    DFS(xx, yy);
                }
            }
        }
    }
}
