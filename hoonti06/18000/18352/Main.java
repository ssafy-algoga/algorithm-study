import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static List<Integer>[] edge;
    static int[] visited;

    static String bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(X);
        visited[X] = 0;

        int cnt = 1;
        while (!q.isEmpty() && cnt <= K) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                int cur = q.poll();

                for (int i = 0; i < edge[cur].size(); i++) {
                    int next = edge[cur].get(i);
                    if (visited[next] != -1) continue;
                    visited[next] = cnt;
                    q.offer(next);
                }
            }
            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++)
            if (visited[i] == K)
                sb.append(i).append('\n');
        return sb.length() == 0 ? "-1" : sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new int[N+1];
        edge = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            visited[i] = -1;
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st =  new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edge[from].add(to);
        }
        System.out.print(bfs());
    }
}

