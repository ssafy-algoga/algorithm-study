# 2533번 사회망 서비스(SNS)
[문제 보러가기](https://www.acmicpc.net/problem/2533)

## 🅰 설계
"자식 노드가 리프노드이면 그 노드는 early adaptor여야 한다" 라는 아이디어에 착안해서 dfs를 설계하였습니다.
루트노드부터 트리를 탐색하면서 리프노트를 찾고 early adaptor를 갱신합니다.
일단 early adaptor라고 판단한 후에는 early adaptor부터 시작하는 서브트리를
없는 셈 칠 수 있습니다. 주의할 점은 일반적인 트리와 다르게 부모 자식 관계가 없는
루트 없는 트리 형태이기 때문에 간선 정보를 양쪽으로 저장해야 한다는 점과
아무 노드나 루트로 가정해도 아무런 문제가 없다는 점입니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> relation = new ArrayList<>();
    static int[] adapter;
    static boolean[] visited;
    static int cnt;

    static int isAdapter(int node) {
        if(adapter[node] != -1) return adapter[node];
        visited[node] = true;
        List<Integer> friends = relation.get(node);
        boolean flag = false;
        for(Integer friend : friends) {
            if(!visited[friend] && isAdapter(friend) == 0) flag = true;
        }
        if(flag) cnt++;
        return adapter[node] = flag ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfNodes = Integer.parseInt(br.readLine());
        adapter = new int[numOfNodes+1];
        visited = new boolean[numOfNodes+1];
        for(int i=0; i<=numOfNodes; i++) {
            relation.add(new ArrayList<>());
        }
        for(int i=0; i<numOfNodes-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int other = Integer.parseInt(st.nextToken());
            relation.get(one).add(other);
            relation.get(other).add(one);
        }
        Arrays.fill(adapter, -1);
        isAdapter(1);
        System.out.println(cnt);
    }
}
```
## ✅ 후기
풀고나서도 이게 dfs로 푼건지 dp로 푼건지 헷갈립니다. 트리 dp는 유형이 익숙치 않아서
관련문제를 몰아서 풀어봐야 할 것 같습니다.