# 11404번 플로이드
[문제 보러가기](https://www.acmicpc.net/problem/11404)

## 🅰 설계
모든 출발/도착 도시 (i, j) 쌍에 대해 최단 거리를 구하는 문제입니다.

플로이드 와샬 알고리즘의 대표적인 문제 상황으로, 
플로이드 와샬 알고리즘은 이처럼 모든 정점의 다른 정점에 대한 최단 거리를 구할 때 사용할 수 있습니다.   
중간 노드를 거쳐 가는 경로를 고려하면서 점점 최단 경로를 찾아가는 느낌으로 이해하고 있습니다.

i에서 j로 가는 최단 경로는 어떤 정점 k를 포함하거나 포함하지 않을 수 있는데,   
간단하게 i와 j, k 세 개의 정점만 있다고 생각해보면   
포함하지 않는 경우는 i와 j 사이에 경로가 있(고 충분히 비용이 적)다면 그것을 선택하는 것으로 생각할 수 있고,   
k를 포함하는 경우 그 경로는 i->k 경로와 k->j 경로를 합친 것과 같을 것이기 때문에   
이를 기반으로 확장시켜 이해했습니다.

### 구현
모든 정점에 대한 다른 모든 정점에 대한 최단 거리를 구하기 위해서 그 거리를 저장한 2차원 배열을 선언해줬습니다.   
노드가 1~N이어서 편하게 쓰려고 +1 했습니다.
```jsx
int[][] shortest = new int[N+1][N+1];
```

INF로 초기화해주고,
```jsx
for (int i = 1; i <= N; i++) {
	for (int j = 1; j <= N; j++) {
		if(i==j) continue;
		shortest[i][j] = INF;
	}
}
```

버스 노선을 입력 받아 표를 채워줍니다.   
이 때 같은 노선이 있는 경우가 있어 그 경우 더 비용이 작은 쪽으로 저장했습니다. (최단 경로니까)

이후 각 중간 노드를 거쳐감에 있어   
각 정점->정점 거리 비용 표를 갱신해줍니다.
```jsx
if(shortest[i][j]>shortest[i][node]+shortest[node][j])
	shortest[i][j] = shortest[i][node]+shortest[node][j];
```
다음에는 배열 이름을 그냥 짧게 쓰려구요

그리고 출력해줍니다.   
INF는 0으로 바꿔서 출력합니다.

### 코드
```jsx
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11404_플로이드 {
	
	private static int INF = 10000001;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] shortest = new int[N+1][N+1];
		
		// 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				shortest[i][j] = INF;
			}
		}
		
		// 입력으로 초기값 결정
		// 같은 노선의 버스는 무조건 비용이 낮은 것 선택
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int r = shortest[a][b];
			if(r!=0) {
				shortest[a][b] = r < c? r : c;
			}else {
				shortest[a][b] = c;
			}

		}
		
		// 각 노드를 거쳐 갈 때
		for (int node = 1; node <= N; node++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j || node==i || node==j) continue;
					if(shortest[i][j]>shortest[i][node]+shortest[node][j])
						shortest[i][j] = shortest[i][node]+shortest[node][j];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(shortest[i][j]==INF)
					sb.append(0).append(" ");
				else
					sb.append(shortest[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}

```

## ✅ 후기
INF 값을 정하는데 있어서 처음엔 신경을 별로 안 썼는데,   
98% 에서 틀려서 다시 생각했습니다..   
도시 개수가 최대 100개이고, 버스의 비용이 최대 100,000이니까   
적당히 100* 100,000 정도는 되야 합니다.
