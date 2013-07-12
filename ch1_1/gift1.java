/*
 ID: antonio13
 LANG: JAVA
 PROG: gift1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author antonio081014
 * @since Feb 15, 2012, 2:25:18 PM
 */
public class gift1 {

    public List<Person>             list;
    public HashMap<String, Integer> map;

    public static void main(String[] args) throws Exception {
        gift1 main = new gift1();
        main.solve();
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new "gift1.out")));
        list = new ArrayList<Person>();
        map = new HashMap<String, Integer>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            list.add(new Person(name));
            map.put(name, i);
        }
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            int from = map.get(name);
            String[] strs = br.readLine().split("\\s");
            int value = Integer.parseInt(strs[0]);
            int m = Integer.parseInt(strs[1]);
            if (m == 0) {
                list.get(i).value += value;
                continue;
            }
            int give = value / m;
            value %= m;
            list.get(from).value -= give * m;
            for (int j = 0; j < m; j++) {
                int to = map.get(br.readLine());
                list.get(to).value += give;
            }
        }
        for (int i = 0; i < N; i++) {
            out.write(list.get(i).toString() + "\n");
        }
        out.close();
    }
}

class Person {
    public String name;
    public int    value;

    public Person(String n) {
        this.name = n;
        this.value = 0;
    }

    public String toString() {
        return this.name + " " + Integer.toString(value);
    }
}
