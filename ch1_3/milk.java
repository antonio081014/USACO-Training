/*
 ID: antonio13
 LANG: JAVA
 PROG: milk
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author antonio081014
 * @since Feb 16, 2012, 10:56:46 PM
 */
public class milk {

    public List<Farmer> list;

    public static void main(String[] args) throws Exception {
        milk main = new milk();
        main.solve();
        System.exit(0);
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("milk.out"));
        String[] str = br.readLine().split("\\s");
        int amount = Integer.parseInt(str[0]);
        int N = Integer.parseInt(str[1]);
        list = new ArrayList<Farmer>();
        for (int i = 0; i < N; i++) {
            str = br.readLine().split("\\s");
            int p = Integer.parseInt(str[0]);
            int a = Integer.parseInt(str[1]);
            list.add(new Farmer(p, a));
        }
        Collections.sort(list);
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            if (amount >= list.get(i).amount) {
                sum += ((long) list.get(i).price) * list.get(i).amount;
                amount -= list.get(i).amount;
            }
            else {
                sum += ((long) amount) * list.get(i).price;
                break;
            }
        }
        out.write("" + sum + "\n");
        out.close();
    }

}

class Farmer implements Comparable<Farmer> {
    public int price;
    public int amount;

    public Farmer(int p, int a) {
        this.price = p;
        this.amount = a;
    }

    @Override
    public int compareTo(Farmer o) {
        return this.price - o.price;
    }
}
