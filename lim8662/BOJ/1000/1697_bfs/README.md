# 1697번 숨바꼭질
[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계

각 위치를 노드로 보고 N에서 K까지의 최단 경로를 찾는 문제입니다.

노드 마다 Nx2, N-1, N+1 세 노드로 분기되고 각 노드를 너비 우선 탐색하였습니다.

---

### 1. 선언부

```java
static int N, K, step;
static boolean[] visited = new boolean[1000001]; // 해당 위치의 방문 여부 
```

boolean 배열로 위치의 방문 여부를 저장해서 중복 방문를 방지하였고

step은 현재 탐색중인 깊이를 기록합니다.

---

### 2. 너비 우선 탐색

```java
private static void bfs() {
	Queue<Integer> q = new LinkedList<>();
	q.add(N);
	visited[N] = true;
	
	while (!q.isEmpty()) {
		int size = q.size();
		
		for (int i = 0; i < size; i++) { // 현 단계의 모든 위치를 탐색
			int cur = q.remove();
			if(cur == K) { // 찾으면 해당 단계 출력
				System.out.println(step);
				return;
			}
			// 다음 단계의 탐색할 위치 추가
			int jump = cur * 2;
			if(jump <= 100000 && !visited[jump]) {
				q.add(jump); visited[jump] = true;
			}
			if(cur < 100000 && !visited[cur+1]) {
				q.add(cur+1); visited[cur+1] = true;
			}
			if(cur > 0 && !visited[cur-1]) {
				q.add(cur-1); visited[cur-1] = true;
			}
		}
    	step++;	// 다음 단계로 넘어감 
	}
}
```

queue에 탐색할 위치를 순서대로 넣고 같은 단계의 모든 위치를 꺼내 찾았는지 확인합니다.

아니면 다음 분기 위치를 큐에 저장하는데 경계 및 방문 여부를 체크합니다. 

---


## ✅ 후기
풀어보니 제가 추가한 DP 문제가 아니였습니다... (1로 만들기-DP) 

평소에 BFS보다는 DFS를 애용해서 2차원 배열 탐색이 아닌 BFS가 생소하게 느껴졌습니다.

좋은 경험 및 문제점을 파악하게 해준 문제였고 추천해주신 분께 감사드립니다.


