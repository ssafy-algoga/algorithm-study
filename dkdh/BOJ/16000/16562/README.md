# 16562번 친구비
[문제 보러가기](https://www.acmicpc.net/problem/16562)

## 🅰 설계

주어지는 친구 관계에 따라 친구 집합을 나누고, 그 집합에서 가장 친구비가 적은 친구 하나를 사귀면 모든 친구를 사귈 수 있습니다. union-find를 활용합니다.

주어지는 친구 관계에 따라 union으로 친구 집합들을 합치고, 가장 적은 친구비를 요구하는 학생이 집합의 대표자가 되도록 구현합니다.   
친구 집합을 모두 합친 후, 각 친구 집합의 대표자가 요구하는 친구비를 모두 더하면 총 친구비가 나옵니다.

이를 가지고 있는 돈 K와 비교해 총 친구비를 출력하거나 Oh no를 출력합니다.

### 코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16562_친구비 {
	
	static int[] parents;
	static int[] req;
	
	static void makeSet(int n) {
		for (int i = 1; i <= n; i++) {
			parents[i] = -1;
		}
	}
	
	static int find(int v) {
		if(parents[v] < 0) return v;
		
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		if(req[aRoot] <= req[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		req = new int[N+1];
		parents = new int[N+1];
		
		makeSet(N);
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			req[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			union(v, w);
		}
		
		int total = 0;
		for (int i = 1; i <= N; i++) {
			if(parents[i] < 0) total += req[i];
		}
		
		if(total <= K)
			System.out.print(total);
		else
			System.out.print("Oh no");
	}

}

```

## ✅ 후기
