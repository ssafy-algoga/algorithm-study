import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Set<Integer> set = new HashSet<>();
    static List<Integer> list;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            set.add(num);
        }

        list = new ArrayList<>(set);
        Collections.sort(list);
        int len = list.size();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.putIfAbsent(list.get(i), i);
        }

        for (int i = 1; i < len; i++) {
            int now = list.get(i);
            int past = list.get(i - 1);
            map.replace(now, map.get(past) + 1);
        }

        for(int num : arr){
            sb.append(map.get(num)).append(" ");
        }

        System.out.println(sb);
    }
}