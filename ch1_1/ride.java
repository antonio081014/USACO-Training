/*
 ID: antonio13
 LANG: JAVA
 PROG: ride
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @since Feb 14, 2012, 7:53:41 PM
 */
public class ride {

    public static void main(String[] args) throws Exception {
        ride main = new ride();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                "ride.out")));
        String a = f.readLine();
        String b = f.readLine();

        if (score(a) == score(b))
            out.write("GO\n");
        else
            out.write("STAY\n");
        out.close();
    }

    public int score(String a) {
        int sum = 1;
        for (int i = 0; i < a.length(); i++) {
            sum *= a.charAt(i) - 'A' + 1;
        }
        return sum % 47;
    }
}