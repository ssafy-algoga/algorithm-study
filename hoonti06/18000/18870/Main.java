import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input, sorted;
    static HashMap<Integer, Integer> cntMap;

    static int upperBound(int key) {
        int left = 0;
        int right = N-1;
        int res = N; // default 값 중요
        while (left <= right) {
            int mid = (left + right) / 2;
            if (key < sorted[mid]) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    static void solution() {
        Arrays.sort(sorted);

        int lower = 0;
        int cnt = 0;
        while (lower < N) {
            int key = sorted[lower];
            int upper = upperBound(key);

            cntMap.put(key, cnt);
            lower = upper;
            cnt++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new int[N];
        sorted = new int[N];
        cntMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++)
            sorted[i] = input[i] = Integer.parseInt(st.nextToken());

        solution();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(cntMap.get(input[i])).append(" ");
        System.out.println(sb);
    }
}

