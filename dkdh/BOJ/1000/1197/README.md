# 1197번 최소 스패닝 트리
[문제 보러가기](https://www.acmicpc.net/problem/1197)

## 🅰 설계
크루스칼 알고리즘으로 풀었습니다.
1. 간선 리스트를 오름차순 정렬
2. 가중치가 낮은 간선부터 고려
3. 사이클 발생 확인(union-find)
4. 사이클이 발생하면 선택하지 않고 다음 간선으로 넘어가고 발생하지 않으면 선택
5. V-1 개 간선을 선택했으면 종료

유니온 파인드에서 집합의 크기를 구할 필요는 없지만, 습관을 들이는 느낌으로 코드 넣었습니다.

### 코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
	
	static int[] parents;
	
	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int wei;
		public Edge(int v1, int v2, int wei) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.wei = wei;
		}
		@Override
		public int compareTo(Edge o) {
			return this.wei - o.wei;
		}
		
	}
	
	static void makeSet(int v) {
		for (int i = 1; i <= v; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int v) {
		if(parents[v] < 0) return v;
		
		return parents[v] = findSet(parents[v]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[E];
		parents = new int[V+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(v1, v2, w);
		}
		
		Arrays.sort(edges);
		makeSet(V);
		
		int cnt = 0;
		int res = 0;
		for (int i = 0; i < E; i++) {
			
			Edge e = edges[i];
			
			if(union(e.v1, e.v2)) {
				cnt++;
				res += e.wei;
			}
			
			if(cnt == V-1) break;
		}
		
		System.out.print(res);
		
	}

}

```
