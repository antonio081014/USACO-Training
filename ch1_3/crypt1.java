/*
 * ID: antonio13
 * LANG: JAVA
 * PROG: crypt1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Method One: solve1(), using the features of the length of numbers;
 * Method Two: solve2(), iterate all of the possible numbers from the existing
 * digit set.
 * */

/**
 * @author antonio081014
 * @since Feb 18, 2012, 4:18:05 PM
 */
public class crypt1 {

    public static void main(String[] args) throws Exception {
        crypt1 main = new crypt1();
        main.solve2();
        System.exit(0);
    }

    public void solve1() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("crypt1.out"));
        int N = Integer.parseInt(br.readLine());
        String strLine = br.readLine();
        strLine = strLine.replaceAll("\\s", "");
        int count = 0;
        for (int i = 100; i < 1000; i++) {
            if (checkString(Integer.toString(i), strLine) == false)
                continue;
            for (int j = 10; j < 100; j++) {
                if (checkString(Integer.toString(j), strLine) == false)
                    continue;
                // int a = j % 10 * i;
                // int b = j / 10 * i;
                String a = Integer.toString(j % 10 * i);
                String b = Integer.toString(j / 10 * i);
                String c = Integer.toString(j % 10 * i + (j / 10 * i) * 10);
                if (a.length() == 3 && b.length() == 3 && c.length() == 4
                        && checkString(a, strLine) && checkString(b, strLine)
                        && checkString(c, strLine))
                    count++;
            }
        }
        out.write("" + count + "\n");
        out.close();
    }

    public boolean checkString(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (b.contains("" + a.charAt(i)) == false)
                return false;
        }
        return true;
    }

    public void solve2() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("crypt1.out"));
        int N = Integer.parseInt(br.readLine());
        String strLine = br.readLine();
        strLine = strLine.replaceAll("\\s", "");
        int count = 0;
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                for (int c = 0; c < N; c++) {
                    int p = Integer.parseInt("" + strLine.charAt(a)
                            + strLine.charAt(b) + strLine.charAt(c));
                    for (int d = 0; d < N; d++) {
                        String tmp1 = Integer.toString(p
                                * (strLine.charAt(d) - '0'));
                        if (tmp1.length() != 3
                                || checkString(tmp1, strLine) == false)
                            continue;
                        for (int e = 0; e < N; e++) {
                            String tmp2 = Integer.toString(p
                                    * (strLine.charAt(e) - '0'));
                            if (tmp1.length() != 3
                                    || checkString(tmp2, strLine) == false)
                                continue;
                            String tmp3 = Integer.toString(p
                                    * (strLine.charAt(d) - '0' + 10 * (strLine
                                            .charAt(e) - '0')));
                            if (tmp3.length() == 4
                                    && checkString(tmp3, strLine))
                                count++;
                        }
                    }
                }
            }
        }
        out.write("" + count + "\n");
        out.close();
    }
}
