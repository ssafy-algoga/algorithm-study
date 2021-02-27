# 2098 외판원 순회 (미완)
[문제 보러가기](https://www.acmicpc.net/problem/2098)

## 🅰 설계
비트마스킹 문제라는 걸 알고 시작하였고, 구현해 보았지만 답을 맞추진 못하였습니다.

테케는 맞았지만 추가적인 다른 풀이법을 적용해야 한다는 생각을 하였고, 거리의 최단거리니, 각 노드까지의 최단거리를 기억해놓은 메모이제이션 기법을 적용할 수 있을거라 생각하였지만 코드로 옮겨내지는 못하였습니다. ㅠ

좀 더 배우고 공부해야겠습니다.




## 전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		traverse(0, 0, 0, 0);
		System.out.println(ans);
	}

	private static void traverse(int mask, int cnt, int curNode, int dist) {
        if (dist >= ans)
			return;
		if (cnt == N) {
			ans = Math.min(ans, dist);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (i == curNode || (mask & 1 << i) != 0)
				continue;
			traverse(mask | 1 << i, cnt + 1, i, dist + graph[curNode][i]);
		}
	}
}
```

### 

## ✅ 후기
### 아직 DP를 푸는데 핵심인 메모이제이션을 활용하는데 미숙한 모습이 있다. 관련 문제를 풀어보며 실력을 키워나가야겠다.