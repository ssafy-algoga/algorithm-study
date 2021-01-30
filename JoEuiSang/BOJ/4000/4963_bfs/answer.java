package _1월_5주차;

import java.io.FileInputStream;
import java.util.Scanner;


public class 섬의개수 {
	//방향 순회용 배열
	static int[] dr = { 0, 0, -1, -1, -1, 1, 1, 1 };
	static int[] dc = { 1, -1, -1, 0, 1, -1, 0, 1 };
	static int[][] map; //맵
	static int[][] visited; //방문체크 맵
	static int w; //너비
	static int h; //높이

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("land.txt"));
		Scanner sc = new Scanner(System.in);

		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();
			
			// 종료 조건
			if (w == 0 && h == 0)
				break;

			int landCnt=0;
			map = new int[h][w];
			visited = new int[h][w];

			// 맵 셋팅
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			//맵 순회 
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					// 현재좌표가 땅이고, 방문 안했으면
					if (map[r][c] == 1 && visited[r][c] == 0) {
						// dfs 시작
						dfs(r, c);
						landCnt++; //섬의 개수 증가
					}
				}
			}
			System.out.println(landCnt);
		}
	}

	static void dfs(int r, int c) {
		//8방향 모두 검사
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			// 맵 밖이면 패스
			if (nr < 0 || nr >= h || nc < 0 || nc >= w)
				continue;

			// 현재위치가 땅이면서 방문을 안했으면
			if (map[nr][nc] == 1 && visited[nr][nc] == 0) {
				visited[nr][nc] = 1; //현재위치 방문표시 후
				dfs(nr, nc); //다음좌표로 dfs
			}
		}
	}
}
