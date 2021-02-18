# 1463번 1로 만들기
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계
bfs로 풀었습니다.

처음 N에서 1로 만들기까지 3으로 나누기, 2로 나누기, -1하기 세 가지 분기점이 있는데   
최종적으로 구하는 것은 가장 적은 연산횟수이고   
각 분기점에 대해 1의 가중치가 있는 상태에서 노드 N에서 노드 1에 대한 최단경로를 구하는 것과 같아서 bfs를 선택했습니다.

문제 조건에서 3이나 2로 나누어떨어질 때와 -1했을 때 세 가지 경우를 두는데   
3이나 2로 나누어떨어진다는 것은 인덱스 경계를 벗어나지 않는 것도 보장하고,   
-1이 데해서는 1이 되면 바로 종료하기 때문에 인덱스 경계를 확인하지 않아도 되서 편했습니다.

큐 자료구조는 ArrayDeque 사용했습니다

### 코드
```jsx
package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// bfs
public class Main_1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		sc.close();
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		int now;
		
		int[] cnts = new int[N+1];
		
		cnts[N] = 1;
		q.add(N);
		while(!q.isEmpty()) {
			now = q.poll();
			
			if(now==1) {
				System.out.print(cnts[now]-1);
				break;
			}
			
			if(now%3==0 && cnts[now/3]==0) {
				q.add(now/3);
				cnts[now/3] = cnts[now]+1;
			}
			if(now%2==0 && cnts[now/2]==0) {
				q.add(now/2);
				cnts[now/2] = cnts[now]+1;
			}
			if(cnts[now-1]==0) {
				q.add(now-1);
				cnts[now-1] = cnts[now]+1;
			}
			
		}
	}

}

```

## ✅ 후기
