# 16562번 친구비
[문제 보러가기](https://www.acmicpc.net/problem/16562)

## 🅰 설계
유니온-파인드 자료구조를 이용해서 전체 집합을 친구들의 집합으로 나눕니다.
그리고 집합마다 최소비용을 가진 친구를 찾아 더해준 후 가진돈과 비교하면 됩니다.
집합의 크기가 필요하지 않은 문제기 때문에 굳이 음수를 사용해서 저장하지 않았고
나중에 최소값을 찾기 위해 한번 더 불필요하게 순회하지 않기 위해
`union()` 수행 시 비용이 더 작은 노드가 부모노드가 되게 끔 처리합니다.

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] costs;

    static int find(int u) {
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return;
        if(costs[u] <= costs[v]) {
            parent[v] = u;
        } else {
            parent[u] = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> minCost = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfStudents = Integer.parseInt(st.nextToken());
        parent = new int[numOfStudents+1];
        for(int idx = 1; idx <= numOfStudents; idx++) {
            parent[idx] = idx;
        }
        int numOfUnions = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        costs = new int[numOfStudents+1];
        st = new StringTokenizer(br.readLine());
        for(int idx = 1; idx <= numOfStudents; idx++) {
            costs[idx] = Integer.parseInt(st.nextToken());
        }

        for(int idx = 0; idx < numOfUnions; idx++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int sum = 0;
        for(int idx = 1; idx <= numOfStudents; idx++) {
            if(parent[idx] == idx) {
                sum += costs[idx];
            }
        }
        
        System.out.println(sum <= money ? sum : "Oh no");
    }
}
```

## ✅ 후기
풀이가 다양하게 나올법한 문제는 아니라서 특별히 나누고 싶은 사항은 없습니다.