/*
 ID: antonio13
 LANG: JAVA
 PROG: namenum
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author antonio081014
 * @since Feb 16, 2012, 6:59:21 PM
 */
public class namenum {

    public Set<String> set;

    public static void main(String[] args) throws Exception {
        namenum main = new namenum();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("dict.txt"));
        BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("namenum.out"));
        set = new TreeSet<String>();
        String number = in.readLine();
        in.close();
        String strLine;
        while ((strLine = br.readLine()) != null) {
            if (number.compareTo(score(strLine)) == 0)
                set.add(strLine);
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            out.write(it.next() + "\n");
        }
        if (set.size() == 0)
            out.write("NONE\n");
        out.close();
    }

    public String score(String a) {
        String sum = "";
        for (int i = 0; i < a.length(); i++)
            sum += getNumber(a.charAt(i));
        return sum;
    }

    public int getNumber(char c) {
        switch (c) {
        case 'A':
            return 2;
        case 'B':
            return 2;
        case 'C':
            return 2;
        case 'D':
            return 3;
        case 'E':
            return 3;
        case 'F':
            return 3;
        case 'G':
            return 4;
        case 'H':
            return 4;
        case 'I':
            return 4;
        case 'J':
            return 5;
        case 'K':
            return 5;
        case 'L':
            return 5;
        case 'M':
            return 6;
        case 'N':
            return 6;
        case 'O':
            return 6;
        case 'P':
            return 7;
        case 'R':
            return 7;
        case 'S':
            return 7;
        case 'T':
            return 8;
        case 'U':
            return 8;
        case 'V':
            return 8;
        case 'W':
            return 9;
        case 'X':
            return 9;
        case 'Y':
            return 9;
        default:
            return 0;
        }
    }
}
