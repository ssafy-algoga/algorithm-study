# 13549번: 숨바꼭질 3

[문제 보러가기](https://www.acmicpc.net/problem/13549)

[코드 보기](./answer.java)

## 🅰 설계

### 주의 사항

순간이동을 할 때는 거리의 값이 1 증가하지 않는 것에 주의 합니다

## 전체 코드

```java
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class P13549 {
    
    static final int MAX = 100000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Queue<Integer> que = new ArrayDeque<>();
    static int N, K;
    static int[] visit = new int[MAX+1];

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(visit, -1);
        que.offer(N);

        visit[N] = 0;
        while (!que.isEmpty()) {
            int x = que.poll();

            if(visit[K]!=-1 && visit[x] >= visit[K]) break;

            int jump = 2*x;
            if (jump <= MAX && visit[jump]==-1) {
                visit[jump] = visit[x];
                que.offer(jump);
            }
            int left = x-1;
            if (0<=left && visit[left]==-1) {
                visit[left] = visit[x]+1;
                que.offer(left);
            }
            int right = x+1;
            if (right<=MAX && visit[right]==-1) {
                visit[right] = visit[x]+1;
                que.offer(right);
            }
        }
        bw.write(visit[K]+"\n");
        bw.flush();
        br.close(); bw.close();
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

파이썬으로 풀 때는 메모리 초과가 안났는데, 자바로 푸니 메모리 초과가 났습니다

### 고생한 점

자바로 풀었을 때 메모리 초과가 났기 때문에 큐 삽입 방식을 바꿔주어야 했습니다

어드바이스를 주신 민규 선생님께 감사드립니다
