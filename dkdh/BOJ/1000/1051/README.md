# 1051번 숫자 정사각형
[문제 보러가기](https://www.acmicpc.net/problem/1051)

## 🅰 설계
N x M 사이즈 직사각형에서 꼭짓점의 값이 같은 가장 큰 정사각형의 넓이를 구하는 문제입니다.

반복문 돌면서 꼭짓점의 값이 같은지 확인하는 식으로 구현했습니다.

N x M 사이즈의 직사각형에서 정사각형의 최대 변의 길이는 N과 M 중 작은 쪽이 됩니다.   
이 값을 max로 두고,
```jsx
int max = N < M ? N : M;
```

max를 1씩 줄이면서 반복문을 돌리며 정사각형의 꼭짓점의 값이 같은지 확인하다가   
확인되면 반복문에서 빠져나갑니다.
```jsx
LOOP: while(max>0) {
			for (int i = 0; i <= N-max; i++) {
				for (int j = 0; j <= M-max; j++) {
					if(map[i][j]==map[i+max-1][j] 
							&& map[i][j+max-1]==map[i+max-1][j+max-1]
									&& map[i][j]==map[i][j+max-1])
						break LOOP;
				}
			}
			max--;
		}
```

그리고 max 값의 제곱을 출력합니다!

#### 코드
```jsx
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051_숫자정사각형 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		// 입력 받기
		for (int i = 0; i < N; i++) {
			// 한 줄 씩 입력 받기
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				// 각 문자를 숫자로 변환해서 배열에 저장
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		int max = N < M ? N : M;
LOOP: while(max>0) {
			for (int i = 0; i <= N-max; i++) {
				for (int j = 0; j <= M-max; j++) {
					if(map[i][j]==map[i+max-1][j] 
							&& map[i][j+max-1]==map[i+max-1][j+max-1]
									&& map[i][j]==map[i][j+max-1])
						break LOOP;
				}
			}
			max--;
		}
		
		System.out.println(max*max);
	}

}

```

## ✅ 후기
