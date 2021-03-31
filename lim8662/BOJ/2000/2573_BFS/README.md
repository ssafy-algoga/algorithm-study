# 2573번 빙산
[문제 보러가기](https://www.acmicpc.net/problem/2573)

## 🅰 설계

주어진 조건대로 구현하는 문제입니다.

빙산이 분리될 때까지 녹이고 확인하는 과정을 반복합니다.

먼저 녹이는 단계는 맵을 완탐하며 모든 빙산에 대해 

`getCoastCnt()`만큼 감소시킨 높이를 별도의 `tmap`에 기록합니다.

다음에 `tmap`에 대해 완탐과 BFS를 통해 `iceBergCnt`를 구하고 

덩어리 수가 1이면 시간 증가후 다시 반복하고 

0이나 2이상이면 문제 조건에 따라 출력합니다.

---

### 1. 전체 코드

```java
class boj_2573_iceBerg {
	
	static final int[] dx = { -1, 1, 0, 0};
	static final int[] dy = { 0, 0, 1, -1};
	static int R, C, ans = 0;
	static final int SEA = 0;
	static int[][] map, tmap; // 빙산의 높이 저장
	static boolean[][] visited;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(melt());
	}

	private static int melt() { // 빙산 녹이기
		int cnt = 0, time = 0;
		while(true) {
			
			tmap = new int[R][C]; // 녹아서 변한 맵 저장
			for (int r = 0; r < R; r++) // 완탐하여 모든 빙산을 녹임
			for (int c = 0; c < C; c++) {
				cnt = getCoastCnt(r,c); // 해안의 수 = 녹는 빙산의 높이
				if(map[r][c] - cnt > 0) { // 녹아도 빙산이 남는다면
					tmap[r][c] = map[r][c] - cnt;
				} 	
			}
			
			int iceBergCnt = 0; // 빙산 덩어리 수 체크
			visited = new boolean[R][C];
			for (int r = 0; r < R; r++) 
			for (int c = 0; c < C; c++) {
				if(tmap[r][c] > 0 && !visited[r][c]) { // 미방문 빙산 발견
					iceBergCnt++;
					bfs(r,c); // 덩어리 방문 처리
				}
			}
			
			if(iceBergCnt == 0) { // 모두 녹았다면
				return 0;
			}
			else if(iceBergCnt == 1) { // 분리안됨
				++time;
				map = tmap;
			}
			else { // 분리되면 종료
				return ++time;
			}	
		}
	}

	private static int getCoastCnt(int r, int c) { // 해당 좌표에서 해안 수를 반환
		int nr = 0, nc = 0, cnt = 0;
		
		for (int d = 0; d < 4; d++) { // 4방탐색
			nr = r + dx[d];
			nc = c + dy[d];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if( map[nr][nc] == SEA ) cnt++; // 바다면 해안수 증가
		}
		return cnt;
	}
	
	private static void bfs(int x, int y) {
		
		Queue<int[]> q = new ArrayDeque<int[]>(); // 맵의 좌표를 담음
		visited[x][y] = true;	
		q.add(new int[] {x, y});
		
		int nx = 0, ny = 0;
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			x = cur[0]; y = cur[1];
			
			for(int d = 0; d < 4; d++) { // 4방 탐색
				nx = x + dx[d]; 
				ny = y + dy[d];
				if(nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny]) {		
					if (tmap[nx][ny] > 0) { // 인접 미등록 빙산 방문
						visited[nx][ny] = true;	
						q.add(new int[] {nx, ny});
					}		
				}
			}
		}
	}
	
}
```

## ✅ 후기

최근에 풀었던 다리만들기2의 Easy 버전 문제라 쉽게 풀었습니다.


