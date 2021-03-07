package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14502_lab {
	private static int N, M, blankCnt, area;
	private static char[][] map, tmap;
	private static boolean[][] visited;
	private static ArrayList<Integer> empty = new ArrayList<>();
	private static int[] picked = new int[3]; // 선택된 빈공간 좌표들
	private static final int[] dx = {0, 0, 1, -1}; 
	private static final int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
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
	}
	
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
}
