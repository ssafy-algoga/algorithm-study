# 1697번: 숨바꼭질

[문제 보러가기](https://www.acmicpc.net/problem/1697)

[코드 보기](./P1697.java)

## 🅰 설계

가장 빠른 시간을 구하는 점에서 최단 경로를 착안하여, bfs를 이용하여 풀었습니다

수빈이가 매 초마다 다음과 같은 3가지 경우의 수를 각각 진행하는 상황을 큐에 삽입합니다

- 1초 뒤, x-1로 가는 경우
- 1초 뒤, x+1로 가는 경우
- 1초 뒤, 2*x로 가는 경우

## P class

큐에 삽입될 데이터 타입으로, 경과 시간과 거리 정보를 저장하고 있는 객체입니다

```jsx
class P {
    int x, second;
    public P (int x, int second) {
        this.x = x;
        this.second = second;
    }
}
```

## bfs

큐에서 데이터를 하나씩 꺼내어 확인하면서, 동생을 잡을 수 있는 최소 시간을 구합니다

```jsx
int[] dx = {-1, 1, 0}; //x-1, x+1, x*2

int bfs() {

	Queue<P> que = new LinkedList<>();

	que.offer(new P(n,0));
		visit[n] = true;

		while (!que.isEmpty()) {

			P p = que.poll();

			if (p.x == k)
				return p.second;

			dx[2] = p.x;
			for (int i = 0; i < 3; i++) {
				int nx = p.x + dx[i];

				if (nx<0 || MAX<nx)
				continue;
				if (visit[nx])
				continue;
                
				visit[nx] = true;
				que.add(new P(nx,p.second+1));
			}
		}

		return -1;
}
```

## 전체 코드

```jsx
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class P {
    int x, second;
    public P (int x, int second) {
        this.x = x;
        this.second = second;
    }
}

public class P1697 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static boolean[] visit;

    static int[] dx = {-1, 1, 0};
    static final int MAX=100000;

    static int bfs() {

        Queue<P> que = new LinkedList<>();

        que.offer(new P(n,0));
        visit[n] = true;

        while (!que.isEmpty()) {

            P p = que.poll();

            if (p.x == k)
                return p.second;

            dx[2] = p.x;
            for (int i = 0; i < 3; i++) {
                int nx = p.x + dx[i];

                if (nx<0 || MAX<nx)
                    continue;
                if (visit[nx])
                    continue;
                
                visit[nx] = true;
                que.add(new P(nx,p.second+1));
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");

        n = Integer.parseInt(tokens[0]);
        k = Integer.parseInt(tokens[1]);

        visit = new boolean[MAX+1];
        
        System.out.println(bfs());
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

제가 제일 좋아하는 bfs 유형의 문제입니다

### 고생한 점

없습니다
