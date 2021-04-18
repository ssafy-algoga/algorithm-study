# 14594번 동방 프로젝트 (Small)
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계
빅-종빈빌런

N개의 방이 있으면 벽은 N+1개가 있다.   
여기서 0번 벽과 N번 벽은 부숴지지 않고, 방의 개수는 벽의 개수-1 과 같다.

벽이 부숴졌는지를 저장할 boolean 배열을 선언하고,   
규칙에 따라 부숴진 벽을 표시한 다음,   
벽의 개수를 세고 -1 을 하면 방의 개수를 구할 수 있다.

실제 코드에서는 마지막 벽을 세지 않는 방식으로 구현하였다.

### 코드
```jsx
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14594_동방프로젝트Small {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[] walls = new boolean[N];
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			while(x < y) {
				walls[x] = true;
				x++;
			}
		}
		
		int rooms = 0;
		for (int i = 0; i < N; i++) {
			if(!walls[i]) rooms++;
		}
		
		System.out.print(rooms);
	}

}
```

## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점
// 고생한 점
