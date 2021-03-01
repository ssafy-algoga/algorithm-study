# 11559번 Puyo Puyo
[문제 보러가기](https://www.acmicpc.net/problem/11559)

## 🅰 설계

주어진 룰대로 구현만 하면 되는 문제입니다.



---
### 1. 선언부
```java
static int chain;
static char[][] map = new char[12][6];
static boolean[][] visited;
static boolean isPang;
static final int[] dx = { 0, 0, 1, -1 };
static final int[] dy = { 1, -1, 0, 0 };

```
한 콤보에서 터뜨릴 뿌요를 찾을 때 `visited`로 방문 여부를 체크합니다.

그리고 `isPang`으로 터진 뿌요가 있는지를 확인해서 콤보를 이어갈지 끝낼지를 결정합니다. 

---





### 2. combo()
```java
private static void combo() {
	visited = new boolean[12][6];
	isPang = false;
	
	for (int col = 0; col < 6; col++) { // 가능한 모든 뿌요 터뜨림
		for (int row = 11; row >= 0; row--) {
			if (map[row][col] == '.') break;
			if(!visited[row][col]) 
				pang(row,col);
		}
	}
	if(isPang) { // 터진 뿌요가 있다면 
		chain++; // 연쇄 1증가
	
		// 뿌요 정렬
		char[][] tmap = new char[12][6]; // 정렬된 맵
		for(char[] init : tmap) { // 초기화
			Arrays.fill(init, '.');
		}
		
		for (int col = 0; col < 6; col++) {
			int nr = 11;
			for (int row =11; row >= 0; row--) {
				if(map[row][col] == '.') break;
				if(map[row][col] == 'x') continue;
				tmap[nr--][col] = map[row][col];
			}
		}
		map = tmap;
		
		combo();
	}		
}
```
더이상 터진 뿌요가 없을 때 까지 콤보를 이어가는 메소드입니다.

먼저 맵의 모든 뿌요를 완탐하면서 뿌요들을 터뜨립니다. 

만약 터진 뿌요가 있다면 연쇄를 증가시키고

따로 초기화된 맵을 통해서 남은 뿌요를 아래서 부터 정렬시킨 후 다음 콤보를 진행합니다.  


---

### 3. pang()

```java
private static void pang(int row, int col) {
	Queue<int[]> q  = new ArrayDeque<int[]>();
	char cur = map[row][col]; // 탐색할 뿌요의 색
	q.add(new int[] { row, col });
	visited[row][col] = true; // 뿌요 방문 여부 검사
	List<int[]> target = new ArrayList<>(); // 터뜨릴 뿌요 리스트
	target.add(new int[] { row, col });
	
	while (!q.isEmpty()) {
		int[] tmp = q.remove();
		int r = tmp[0];
		int c = tmp[1];
		for (int d = 0; d < 4; d++) { // 4방탐색
			int nr = r + dx[d];
			int nc = c + dy[d];
			if(nr < 0 || nr >= 12 || nc < 0 || nc >= 6) continue;
			if(!visited[nr][nc] && map[nr][nc] == cur) { // 색이 같고 방문하지 않은 뿌요라면
				visited[nr][nc] = true;
				target.add(new int[] {nr, nc});
				q.add(new int[] {nr, nc});
			}
		}
	}
	if(target.size() >= 4) { // 같은 뿌요가 4개 이상이면 pang 
		isPang = true;
		for(int[] t : target) map[t[0]][t[1]] = 'x';
	}
}
```
주어진 좌표의 뿌요에서 BFS로 같은 색의 뿌요를 탐색하고 target 리스트에 그 위치를 저장합니다.

타겟의 수가 4이상이면 터뜨리고 map에 표기합니다.

---

## ✅ 후기
풀고 싶었던 문제여서 그런지 재미있게 풀었습니다.

