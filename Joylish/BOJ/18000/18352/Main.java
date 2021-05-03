import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int numOfCity = Integer.parseInt(st.nextToken());
        int numOfRoad = Integer.parseInt(st.nextToken());
        int minDist = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adj = new ArrayList[numOfCity + 1];
        for (int i = 1; i <= numOfCity; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < numOfRoad; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] isVisited = new int[numOfCity + 1];
        Arrays.fill(isVisited, -1);

        q.add(startCity);
        isVisited[startCity] = 0;
        boolean isThere = false;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : adj[now]) {
                if (isVisited[next] != -1) continue;
                q.add(next);
                isVisited[next] = isVisited[now] + 1;
                if (isVisited[next] == minDist) isThere = true;
            }
        }
        if (!isThere) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numOfCity; i++) {
            if (minDist != isVisited[i]) continue;
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}