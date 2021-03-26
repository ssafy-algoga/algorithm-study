# 11437번 LCA
[문제 보러가기](https://www.acmicpc.net/problem/11437)

## 🅰 설계
각각의 노드별로 depth와 parent를 저장해둡니다.
1. 두 노드의 depth를 일치시켜 줍니다.
2. 두 노드가 같아질 때 까지 parent 노드로 이동합니다.

이를 위해 depth를 계산하기 위한 dfs 메소드와 lca를 계산하기 위한 메소드 두가지가 필요합니다.

```java
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
```
## ✅ 후기
사실 세그먼트 트리나 DP를 활용한 풀이가 좀 더 일반적이긴 하지만 코테에 나오기엔 너무 과한 느낌입니다.