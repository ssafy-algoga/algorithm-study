import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class P15663 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static int N, M;
    static int[] arr;
    static boolean[] visit;

    static void dfs(int count, String stack) {
        if (count==M) {
            set.add(stack+"\n");
            return;
        }

        for (int i=0; i<N; i++) {
            if (visit[i])
            continue;

            visit[i] = true;
            dfs(count+1,stack+" "+arr[i]);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        arr = new int[N];
        visit = new boolean[N];

        tokens = br.readLine().split(" ");
        for (int i=0; i<N; i++)
        arr[i] = Integer.parseInt(tokens[i]);

        Arrays.sort(arr);

        for (int i=0; i<N; i++) {
            visit[i] = true;
            dfs(1,Integer.toString(arr[i]));
            visit[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        for (String s: set) sb.append(s);
        System.out.print(sb);
    }
}
