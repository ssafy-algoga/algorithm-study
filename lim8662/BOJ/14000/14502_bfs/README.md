# 14502번 연구소
[문제 보러가기](https://www.acmicpc.net/problem/14502)

## 🅰 설계

최대 8*8의 구역에서 공백이 최대 59개가 됩니다.

이중에 3개의 공백을 선택해서 벽으로 만드는 상태에 대해서 조합으로 구하고 

모든 상태에 대해 바이러스 확산 시뮬레이션을 하여서 최대 안전 영역을 구했습니다. 

---
### 1. 선언부
```java
private static int N, M, blankCnt, area;
private static char[][] map, tmap;
private static boolean[][] visited;
private static ArrayList<Integer> empty = new ArrayList<>(); // 빈공간의 좌표들
private static int[] picked = new int[3]; // 선택된 빈공간의 인덱스
private static final int[] dx = {0, 0, 1, -1}; 
private static final int[] dy = {1, -1, 0, 0};
```
ArrayList에 공백의 좌표들을 저장해두고,

공백의 개수(blankCnt) 중에서 3개를 뽑아서 

List상 index를 int배열에 저장했습니다.

---

### 2. 메인 함수
```java
N = Integer.parseInt(st.nextToken());
M = Integer.parseInt(st.nextToken());
map = new char[N][M];

for (int i = 0; i < N; i++) {  
	st = new StringTokenizer(br.readLine());
	for (int j = 0; j < M; j++) {
		char cur = st.nextToken().charAt(0);
		map[i][j] = cur; // 맵 입력
		if(cur == '0') empty.add(i*10 + j); // 공백이면 좌표 기록
	}	
}
blankCnt = empty.size();
combination(0, 0); // blankCnt C 3

System.out.println(area);
```
좌표의 크기가 10을 넘지 않으므로 

공백의 x좌표는 10의 자리, y좌표는 1의 자리로 하여 List에 저장했습니다.

---

### 3. 조합
```java
static void combination(int cnt, int start) { // 벽을 놓을 3개의 빈자리 뽑기
	if (cnt == 3) { // 다뽑으면
		spread(picked); // 바이러스 확산
		return;
	}
	for (int i = start; i < blankCnt; i++) {
		picked[cnt] = i;
		combination(cnt + 1, i + 1);
	}
}
```
3개의 공백을 뽑으면 spread 메소드에 넘겨줍니다.

---

### 4. 확산 시뮬레이션

```java
static void spread(int[] blankIdx) {
	tmap = new char[N][M];
	
	for (int i = 0; i < N; i++)  // 시뮬레이션용 임시맵 복사
	for (int j = 0; j < M; j++) {
		tmap[i][j] = map[i][j];
	}
	
	for (int i = 0; i < 3; i++) { // 임시 맵에 벽 배치
		int x = empty.get(blankIdx[i]) / 10;
		int y = empty.get(blankIdx[i]) % 10;
		tmap[x][y] = '1';
	}
	
	visited = new boolean[N][M];		
	for (int i = 0; i < N; i++)  // 바이러스 확산
	for (int j = 0; j < M; j++) {
		if(!visited[i][j] && tmap[i][j] == '2') { // 방문하지 않은 바이러스 구역이면 확산
			bfs(i, j);
		}
	}

	int cnt = 0; // 안전 영역 크기 구하기
	for (int i = 0; i < N; i++) { 
		for (int j = 0; j < M; j++) {
			if(tmap[i][j] == '0') cnt++;
		}
	}
	area = (cnt > area) ? cnt : area; // 최대 영역 크기 갱신
}
```
먼저 맵을 복사하고 선택된 공백에 벽을 배치하여 시뮬레이션 할 맵을 완성합니다. 

맵을 완탐하면서 바이러스 구역을 찾되 visited로 방문한 구역은 가지치기를 하였습니다.

---
### 5. 너비 우선 탐색

```java
private static void bfs(int x, int y) {
	Queue<int[]> q = new ArrayDeque<int[]>(); // 바이러스 구역을 담음
	q.add(new int[] {x, y});
	int nx = 0, ny = 0;
	
	while(!q.isEmpty()) {
		int[] cur = q.remove();
		x = cur[0]; y = cur[1];
		
		for(int d = 0; d < 4; d++) { // 4방 탐색
			nx = x + dx[d]; ny = y + dy[d];
			// 다음 구역이 지도 내이고, 방문하지 않았다면 
			if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
				
				if (tmap[nx][ny] == '0') { // 빈 공간이면 확산
					tmap[nx][ny] = '2';
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				} else if(tmap[nx][ny] == '2') { // 바이러스 구역이면 이동
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
}
```
바이러스 구역의 좌표를 큐에 담고 4방 탐색을 하며 바이러스를 확산시켜 나갔습니다.

---
## ✅ 후기

최근에 수업시간에 풀었던 문제들(A형, 조합, 시뮬)과 유사해서 좋은 연습이 되었습니다.

설계는 10분이 걸렸지만 구현하는데 1시간 반이 걸렸습니다...

다양한 자료구조와 변수를 사용함에 있어서 초기화를 항상 염두해 두어야 함을 느꼈습니다.

유사한 문제를 더 풀어봐서 조합, bfs 등 반복되는 코드에서 속도를 단축하겠습니다.


