# 11404번 플로이드
[문제 보러가기](https://www.acmicpc.net/problem/11404)

## 🅰 설계

각 도시를 정점으로 하고 버스 노선을 간선으로하는 그래프에서

모든 정점에서 모든 정점으로 가는 최소 비용을 구하는 플로이드-워셜 알고리즘 문제입니다.

---


### 1. 입력
```java
// 맵 초기화
for (int i = 0; i < N; i++) {
	for (int j = 0; j < N; j++) {
		map[i][j] = INF;
	}
}
// 비용 입력
for (int m = 0; m < M; m++) { 
	StringTokenizer st = new StringTokenizer(br.readLine());
	from = Integer.parseInt(st.nextToken()) - 1; 
	to = Integer.parseInt(st.nextToken()) - 1;
	cost = Integer.parseInt(st.nextToken());
	map[from][to] = Math.min(cost, map[from][to]);
```
모든 노선의 비용을 무한수로 초기화를 시킨 후 각 노선의 비용을 입력 받았습니다.

문제의 조건에 따라 중복된 노선이 입력되면 최솟값으로 최신화를 하였습니다.

---
### 2. 플로이드-워셜
```java
// 모든 도시를 경유 도시로 하여 최소 비용 탐색
for (int via = 0; via < N; via++) { // 경유 도시 
	for (int f = 0; f < N; f++) { // 시작 도시
		for ( int t = 0; t < N; t++) { // 도착 도시
			// 루프거나 없는 노선이면 넘김
			if(f == t || map[f][via] == INF ||  map[via][t] == INF) continue;
			// 경유 비용과 기존 비용중 최소 비용으로 갱신
			map[f][t] = Math.min(map[f][t], map[f][via] + map[via][t]);
		}
	}
}
```
모든 도시에 대해 그 도시를 경유지로 하는 모든 경로를 탐색하고 최소 비용으로 최신화 하였습니다.

단 시작 도시와 도착 도시가 같거나 경유할 노선이 없는 경우는 제외했습니다.

---

### 3. 출력
```java
// 출력
for (int i = 0; i < N; i++) {
	for (int j = 0; j < N; j++) {
		if(map[i][j] == INF) System.out.print("0 "); 
		else System.out.print(map[i][j] + " ");
	}
	System.out.println();
}
```
최신화된 모든 경로의 비용을 출력합니다.

단, 경로가 존재 하지 않는 경우 0으로 출력합니다. 

---

### 4. 전체 코드
```java
class boj_11404_floyd {
	
	final static int INF = Integer.MAX_VALUE; // 없는 노선
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시 수
		int M = Integer.parseInt(br.readLine()); // 버스 수
		int from, to, cost;
		int[][] map = new int[N][N];
		
		// 맵 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = INF;
			}
		}
		// 비용 입력
		for (int m = 0; m < M; m++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken()) - 1; 
			to = Integer.parseInt(st.nextToken()) - 1;
			cost = Integer.parseInt(st.nextToken());
			map[from][to] = Math.min(cost, map[from][to]);
			
		}
		
		// 모든 도시를 경유 도시로 하여 최소 비용 탐색
		for (int via = 0; via < N; via++) { // 경유 도시 
			for (int f = 0; f < N; f++) { // 시작 도시
				for ( int t = 0; t < N; t++) { // 도착 도시
					// 루프거나 없는 노선이면 넘김
					if(f == t || map[f][via] == INF ||  map[via][t] == INF) continue;
					// 경유 비용과 기존 비용중 최소 비용으로 갱신
					map[f][t] = Math.min(map[f][t], map[f][via] + map[via][t]);
				}
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == INF) System.out.print("0 "); 
				else System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
```
## ✅ 후기
비용이 INF인 경우가 시작 도시와 도착 도시가 같은 경우와

노선이 없어 없는 경로인 경우인데 주어진 테스트케이스에는 후자의 경우는 확인이 안되고

전자의 경우에만 고려를 하고 초기화 및 출력을 하여 시행착오를 겪었습니다. 

