package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_11559_PuyoPuyo {

	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};	
	
	static int cnt;
	
	static Deque<int[]> q;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[12][6];
		
		for (int i = 0; i < 12; i++) {
			String input = br.readLine();
			for (int j = 0; j < 6; j++) {
				switch (input.charAt(j)) {
				case '.':
					map[i][j] = 0;
					break;
				case 'R':
					map[i][j] = 1;
					break;
				case 'G':
					map[i][j] = 2;
					break;
				case 'B':
					map[i][j] = 3;
					break;
				case 'P':
					map[i][j] = 4;
					break;
				case 'Y':
					map[i][j] = 5;
					break;
				}
			}
		}
		// 입력 끝
		
		int combo = 0;
		boolean is;
		cnt = 0;
		q = new ArrayDeque<int[]>();
		
		do {
			is = false;
			visited = new boolean[12][6];
			
			for (int i = 11; i >= 0; --i) {
				int temp = 0;
				for (int j = 0; j < 6; ++j) {
					int puyo = map[i][j];
					temp += puyo;
					
					// 빈 공간은 탐색 안 하도록
					if(puyo == 0) visited[i][j] = true;
					
					// 뿌요가 있는 공간 중 방문하지 않은 곳 탐색하기
					else if(!visited[i][j]){
						find(i, j, puyo);
						
						if(cnt >= 4) is = true;
						else {
							for (int k = 0; k < cnt; k++) {
								q.removeLast();
							}
						}
						
						cnt = 0;
					}
				}
				
				// 한 줄이 다 뿌요 없으면 그 위는 탐색 필요x
				if(temp==0) break;
			}
			
			// 터질 뿌요가 있으면
			if(is) {
				delete();
				gravity();
				combo++;
			}
		}while(is);
		
		System.out.print(combo);
	}

	private static void gravity() {

		for (int c = 0; c < 6; ++c) {
			for (int r = 10; r >= 0; --r) {
				if(map[r][c] != 0) goDown(r, c);
			}
		}
	}

	private static void goDown(int r, int c) {
		int nr = r+1;
		
		while(nr < 12) {
			if(map[nr][c]==0) {
				map[nr][c] = map[r][c];
				map[r][c] = 0;
				
				r = nr;
				nr = r+1;
			}
			else break;
		}
	}

	private static void delete() {
		
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			
			map[loc[0]][loc[1]] = 0;
		}
	}

	private static void find(int r, int c, int color) {
		
		cnt++;
		visited[r][c] = true;
		q.add(new int[] {r, c});
		
		int nr, nc;
		
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			
			if(nr<12 && nr>=0 && nc>=0 && nc<6 &&
					!visited[nr][nc] && map[nr][nc] == color) {
				find(nr, nc, color);
			}
		}

	}

}
