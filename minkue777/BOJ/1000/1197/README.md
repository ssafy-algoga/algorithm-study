# 1197번 최소 스패닝 트리
[문제 보러가기](https://www.acmicpc.net/problem/1197)

## 🅰 설계
우선순위 큐를 통해 distance가 적은 간선부터 차례대로 뽑아낸 후 유니온-파인드 자료구조를 이용하여
싸이클이 생기지 않는지 유무를 계속 체크합니다. 사실 union 메소드는 리턴형이 꼭 boolean일 필요는 없지만
MST에서는 boolean을 리턴해야 구현이 편해집니다.

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static int find(int u) {
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        parent[u] = v;
        return true;
    }

    static class Edge {
        int from;
        int to;
        int weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        parent = new int[numOfNodes+1];
        for(int idx = 1; idx <= numOfNodes; idx++) {
            parent[idx] = idx;
        }
        int numOfEdges = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for(int idx = 0; idx < numOfEdges; idx++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        int cnt = 0;
        int sum = 0;
        while(cnt != numOfNodes-1) {
            Edge edge = pq.poll();
            if(union(edge.from, edge.to)) {
                sum += edge.weight;
                cnt++;
            }
        }
        System.out.println(sum);
    }
}
```

## ✅ 후기
특별히 어려운 점이 없는 기본유형입니다.