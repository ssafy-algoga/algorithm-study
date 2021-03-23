import java.io.*;
import java.util.*;

public class Main {
    static int numOfNodes;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] parent;
    static int[] depth;

    static void dfs(int node, int dep) {
        depth[node] = dep;
        for(int child : graph.get(node)) {
            if(depth[child] != -1) continue;
            parent[child] = node;
            dfs(child, dep + 1);
        }
    }

    static int lca(int u, int v) {
        while (depth[u] != depth[v]) {
            if(depth[u] > depth[v]) {
                u = parent[u];
            } else {
                v = parent[v];
            }
        }

        while(u != v) {
            u = parent[u];
            v = parent[v];
        }
        return u;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        numOfNodes = Integer.parseInt(br.readLine());
        parent = new int[numOfNodes+1];
        depth = new int[numOfNodes+1];
        Arrays.fill(depth, -1);
        for(int idx = 0; idx <= numOfNodes; idx++) {
            graph.add(new ArrayList<>());
        }

        for(int idx = 0; idx < numOfNodes-1; idx++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        dfs(1, 0);
        int numOfQueries = Integer.parseInt(br.readLine());
        for(int idx = 0; idx < numOfQueries; idx++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append("\n");
        }
        System.out.println(sb);
    }
}