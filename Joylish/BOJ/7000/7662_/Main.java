import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Integer> minq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    static PriorityQueue<Integer> maxq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int numOfOper = Integer.parseInt(br.readLine());
            minq.clear();
            maxq.clear();
            map.clear();
            while (numOfOper-- > 0) {
                String[] op = br.readLine().split(" ");
                int val = Integer.parseInt(op[1]);
                if (op[0].equals("I")) {
                    maxq.add(val);
                    minq.add(val);
                    map.put(val, map.getOrDefault(val, 0) + 1);
                } else if (op[0].equals("D") && val == 1) {
                    if (maxq.isEmpty()) continue;
                    map.replace(maxq.peek(), map.get(maxq.peek()) - 1);
                } else if (op[0].equals("D") && val == -1) {
                    if (minq.isEmpty()) continue;
                    map.replace(minq.peek(), map.get(minq.peek()) - 1);
                }
                while (!maxq.isEmpty() && map.get(maxq.peek()) == 0) maxq.poll();
                while (!minq.isEmpty() && map.get(minq.peek()) == 0) minq.poll();
            }
            if (maxq.isEmpty()) sb.append("EMPTY").append("\n");
            else sb.append(maxq.poll()).append(" ").append(minq.poll()).append("\n");
        }
        System.out.println(sb);
    }
}