package 스터디._3월4주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2573빙산 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> ice = new ArrayList<>();	//빙산의 좌표를 저장해놓을 리스트
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c, val, sea;

		public Point(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 3~300
		M = Integer.parseInt(st.nextToken()); // 열 3~300
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					ice.add(new Point(i, j, map[i][j]));
			}
		}

		int time = 0;
		while (true) {
			initVisited(); // 방문 초기화

			int iceCnt = 0; // 빙산덩어리 개수 담을 변수

			for (Point p : ice) {
				if (!visited[p.r][p.c]) { // 방문 안한 빙산이면
					visitIce(p.r, p.c); // 빙산덩어리 방문처리
					iceCnt++; // 빙산덩어리 증가
				}
			}

			if (iceCnt >= 2) {
				System.out.println(time);
				return;
			}

			meltIce(); // 빙산 녹이기
			time++; // 시간 증가

			if (ice.size() == 0) {
				System.out.println(0);
				return;
			}

		}
	}

	private static void initVisited() {
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static void visitIce(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				visitIce(nr, nc);
			}
		}
	}

	static void meltIce() {
		Point p = null;
		for (int i = 0; i < ice.size(); i++) {
			p = ice.get(i);
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
					p.sea++;	//모든 빙산에대해 바다 개수를 다 센뒤에 빼줘야한다
				}
			}
			
		}
		// 주변 바다 개수만큼 빼주기
		for (int i = 0; i < ice.size(); i++) {
			p = ice.get(i);
			if (p.val - p.sea <= 0) {
				map[p.r][p.c] = 0;
				ice.remove(i);
				i--;
			} else {
				map[p.r][p.c] -= p.sea;
				p.val -= p.sea;
				p.sea=0;
			}
		}
	}
}
