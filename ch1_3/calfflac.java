/*
 * ID: antonio13
 * LANG: JAVA
 * PROG: calfflac
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 1st, String.substring will take much time.
 * 2nd, String.toLower will take much time. Don't translate to lower case
 * every time, just translate it at the very first beginning.
 * It takes me more than 36 hrs.
 * 
 * Time Complexity: O(N*M)
 * N is the length of the total string.
 * M is the length of the longest palindrome string.
 * 
 * 12th submission passed the test.
 * */

/**
 * @author antonio081014
 * @since Feb 17, 2012, 10:44:40 AM
 */
public class calfflac {

    public String store;
    public int    max;
    int           From;
    int           To;

    public static void main(String[] args) throws Exception {
        calfflac main = new calfflac();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("calfflac.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("calfflac.out"));
        String strLine = "";
        String str;
        while ((str = br.readLine()) != null) {
            strLine += str + "\n";
        }
        max = 0;
        From = 0;
        String text = strLine.toLowerCase();
        int N = strLine.length();
        for (int i = 0; i < N; i++) {
            palindrom1(i, text, N);
            palindrom2(i, text, N);
        }
        out.write("" + max + "\n");
        for (int i = From; i <= To; i++)
            out.write(strLine.charAt(i));
        out.write("\n");
        // out.write(strLine.substring(From, To + 1) + "\n");
        out.close();
    }

    //Check for BBABB;
    public void palindrom1(int index, String s, int N) {
        int start = index;
        int end = index;
        int count = isAlpha(s.charAt(index)) ? 1 : 0;
        for (int i = index - 1, j = index + 1; i >= 0 && j < N;) {
            while (i >= 0 && isAlpha(s.charAt(i)) == false)
                i--;
            while (j < s.length() && isAlpha(s.charAt(j)) == false)
                j++;
            if (i < 0 || j >= N)
                break;
            if (s.charAt(i) != s.charAt(j)) {
                if (count > max) {
                    From = start;
                    To = end;
                    max = count;
                }
                return;
            }
            else {
                count += 2;
                start = i;
                end = j;
                i--;
                j++;
            }
        }
        if (count > max) {
            From = start;
            To = end;
            max = count;
        }
        // return count;
        // return Math.min(index, s.length() - index - 1) * 2 + 1;
    }
    
    //Check for BBAABB;
    public void palindrom2(int index, String s, int N) {
        int start = index;
        int end = index;
        int count = 0;
        for (int i = index, j = index + 1; i >= 0 && j < N;) {
            while (i >= 0 && isAlpha(s.charAt(i)) == false)
                i--;
            while (j < s.length() && isAlpha(s.charAt(j)) == false)
                j++;
            if (i < 0 || j >= N)
                break;
            if (s.charAt(i) != s.charAt(j)) {
                if (count > max) {
                    From = start;
                    To = end;
                    max = count;
                }
                return;
                // return 2 * (j - index - 1);
            }
            else {
                count += 2;
                start = i;
                end = j;
                i--;
                j++;
            }
        }
        if (max < count) {
            From = start;
            To = end;
            max = count;
        }
        // return count;
        // return Math.min(index + 1, s.length() - index - 1) * 2;
    }

    public boolean isAlpha(char a) {
        if (a >= 'a' && a <= 'z')
            return true;
        if (a >= 'A' && a <= 'Z')
            return true;
        return false;
    }

}
