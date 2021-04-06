# 17071번 숨바꼭질 5
[문제 보러가기](https://www.acmicpc.net/problem/17071)

## 🅰 설계
목표물이 움직이기 때문에 이전에 방문한 곳도 다시 방문할 수 있어야 합니다.
하지만 방문 처리를 하지 않는다면 BFS는 시간과 공간 제약을 아득히 초과해버리는
알고리즘이기 때문에 아예 새로운 접근이 필요했습니다.

결국 방문처리가 어쩔 수 없다고 생각한다면 큐에 넣지 않고도 언제 다시 발견될 수
있는지를 유추할 수 있어야 하는데 그 아이디어는 X+1과 X-1이 계속 반복된다는 것에
있습니다. 즉 t초에 처음으로 x 노드를 방문했다면 x + 2n 초에도 반드시 x를
발견합니다. 따라서 짝수초 후에 발견한 노드와 홀수초 후에 발견한 노드를 개별적으로
방문처리를 해주면서 BFS를 수행하면 됩니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VAL = 500000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[][] dist = new int[2][MAX_VAL + 1];
        for(int[] row : dist) Arrays.fill(row, -1);
        Queue<Integer> q = new ArrayDeque<>();
        dist[0][start] = 0;
        q.offer(start);
        int accel = 0;
        while(!q.isEmpty()) {
            target += accel;
            if(target > MAX_VAL) break;
            if(dist[accel % 2][target] != -1) {
                System.out.println(accel);
                return;
            }

            int size = q.size();
            for(int i = 0; i < size; i++) {
                int curNode = q.poll();
                int[] nextNode = {curNode - 1, curNode + 1, 2 * curNode};
                for(int next : nextNode) {
                    if(next >= 0 && next <= MAX_VAL && dist[(accel+1) % 2][next] == -1) {
                        dist[(accel+1) % 2][next] = accel+1;
                        q.offer(next);
                    }
                }
            }
            accel++;
        }
        System.out.println(-1);
    }
}
```
## ✅ 후기
코딩테스트에 나올 가능성은 제로에 가까운 특수한 문제라고 생각합니다.
플래티넘 5여도 충분할 것 같아요. 아이디어를 떠올리기 너무 힘든 문제입니다.