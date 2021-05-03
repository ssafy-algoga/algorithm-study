import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static TreeMap<Integer, Integer> q = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int numOfOper = Integer.parseInt(br.readLine());
            q.clear();
            while (numOfOper-- > 0) {
                String[] op = br.readLine().split(" ");
                int val = Integer.parseInt(op[1]);
                if (op[0].equals("I")) {
                    q.put(val, q.getOrDefault(val, 0) + 1);
                } else {
                    if (q.isEmpty()) continue;
                    int key = val == -1 ? q.firstKey() : q.lastKey();
                    // 현재 q에 있는 key 인지 확인
                    // 1이면 아예 사라지기 때문에 remove
                    // 1이 아니면 1 줄이기
                    if (q.get(key) == 1) q.remove(key);
                    else q.replace(key, q.get(key) - 1);
                }
            }
            if (q.isEmpty()) sb.append("EMPTY").append("\n");
            else sb.append(q.lastKey()).append(" ").append(q.firstKey()).append("\n");
        }
        System.out.println(sb);
    }
}
