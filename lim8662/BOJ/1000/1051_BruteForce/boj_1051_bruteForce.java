import java.io.*;
import java.util.*;

class boj_1051_bruteForce {
	// N * M 직사각형에서 꼭짓점 수가 같은
	// 가장 큰 정사각형의 넓이 리턴
	// N, M : 1~50

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int l = 0; // 정사각형 한변의 길이

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = br.read() - '0';
			}
			br.readLine();
		}
		// 정사각형 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int r = 0; r < M - j; r++) {
					int edge = map[i][j];
					if (edge == map[i][j + r]) { // 윗변을 찾으면
						if (i + r < N && edge == map[i + r][j] && edge == map[i + r][j + r]) { // 아랫변도 찾으면
							l = Math.max(l, r + 1);
						}
					}
				}
			}
		}
		System.out.println(l * l);
	}
}
