package 스터디._2월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj11559PuyoPuyo {
	static char[][] map = new char[12][6];
	static boolean[][] visit = new boolean[12][6];
	static int count, ans;
	static ArrayList<int[]> link = new ArrayList<int[]>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int r = 0; r < 12; r++) {
			String str = br.readLine();
			for (int c = 0; c < 6; c++) {
				map[r][c] = str.charAt(c);
			}
		}

		while (pop()) {
			down();
			ans++;
		}

		System.out.println(ans);
	}

	public static void init() {
		count = 0;
		link.clear();
		for (int r = 0; r < 12; r++) {
			for (int c = 0; c < 6; c++) {
				visit[r][c] = false;
			}
		}
	}

	private static boolean pop() {
		boolean flag = false;
		for (int r = 11; r >= 0; r--) {
			for (int c = 0; c < 6; c++) {
				init();
				if (map[r][c] != '.' && !visit[r][c]) {
					if (dfs(r, c, map[r][c]) >= 4) { // 4개 이상 모였으면 터뜨리기
						flag = true;
						for (int[] l : link) {
							map[l[0]][l[1]] = '.';
						}
					}
				}
			}
		}
		return flag;
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static int dfs(int r, int c, char color) {
		visit[r][c] = true;
		link.add(new int[] { r, c }); // 현재 컬러와 연결된 좌표들을 리스트에 추가
		count++; // 들어오면 현재 컬러와 연결된 갯수를 증가시킨다.

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == color && !visit[nr][nc]) {
				dfs(nr, nc, color);
			}
		}

		return count;
	}

	private static void down() {
		for (int c = 0; c < 6; c++) {
			int floorR = 11; // 중력에 의해 떨어지는 부분이 안착할 바닥
			int dropR = 10; // 중력에 의해 떨어지는 부분
			while (floorR > 0 && dropR > 0) {
				
				// 바닥이 빈칸이 아니면 바닥 올려주기
				while (floorR > 0 && map[floorR][c] != '.') {
					floorR--;
					dropR--;
				}

				// 낙하물이 있으면
				while (dropR >= 0) {
					if (dropR >= 0 && map[dropR][c] != '.') {
						map[floorR][c] = map[dropR][c];
						map[dropR][c] = '.';
						floorR--;
						dropR--;
					} else {// 낙하물없으면
						while (dropR >= 0 && map[dropR][c] == '.') {
							dropR--;
						}
					}
				}
			}
		}
	}
}
