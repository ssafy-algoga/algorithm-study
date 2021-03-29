package BOJ;

import java.io.*;
import java.util.*;

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
