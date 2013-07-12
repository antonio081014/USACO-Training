/*
 ID: antonio13
 LANG: JAVA
 PROG: dualpal
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author antonio081014
 * @since Feb 16, 2012, 8:06:31 PM
 */
public class dualpal {

    public static void main(String[] args) throws Exception {
        dualpal main = new dualpal();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("dualpal.out"));
        String[] str = br.readLine().split("\\s");
        int N = Integer.parseInt(str[0]);
        int S = Integer.parseInt(str[1]);
        int count = 0;
        for (S++; S <= Integer.MAX_VALUE; S++) {
            if (isValid(S)) {
                out.write("" + S + "\n");
                count++;
            }
            if (count >= N)
                break;
        }
        out.close();
    }

    public boolean isValid(int a) {
        int count = 0;
        for (int base = 2; base <= 10; base++) {
            if (isPalindrome(Integer.toString(a, base)))
                count++;
        }
        return count >= 2 ? true : false;
    }

    public boolean isPalindrome(String a) {
        for (int i = 0; i < a.length() / 2; i++)
            if (a.charAt(i) != a.charAt(a.length() - i - 1))
                return false;
        return true;
    }
}
