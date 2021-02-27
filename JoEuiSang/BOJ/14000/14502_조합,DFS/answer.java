package 스터디._2월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Virus {
	int r, c;

	public Virus(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Boj14502연구소 {
	static int N, M, ans;
	static int[][] map, tmpMap;
	static ArrayList<Virus> virus;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// 원본 맵
		tmpMap = new int[N][M];	// dfs 돌릴 맵
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Virus(i, j));
				}
			}
		}

		setWall(0, 0);	

		System.out.println(ans);
	}

	//벽세우기 조합
	static void setWall(int start, int cnt) {
		if (cnt == 3) {
			copyMap();

			for (Virus v : virus) {
				spreadVirus(v.r, v.c);
			}

			ans = Math.max(ans, SafeArea());
			return;
		}

		for (int i = start; i < N * M; i++) {
			int x = i / M;	//행 계산
			int y = i % M;	//열 계산

			if (map[x][y] == 0) {
				map[x][y] = 1;
				setWall(i + 1, cnt + 1);
				map[x][y] = 0;
			}
		}
	}

	//
	private static int SafeArea() {
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 0)
					safe++;
			}
		}
		return safe;
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static void spreadVirus(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (0 <= nr && N > nr && 0 <= nc && M > nc && tmpMap[nr][nc] == 0) {
				tmpMap[nr][nc] = 2;
				spreadVirus(nr, nc);
			}
		}
	}

	static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}

}
