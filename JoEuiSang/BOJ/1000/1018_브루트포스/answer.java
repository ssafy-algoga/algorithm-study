package 스터디._2월2주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기 {
	static int[][] map;
	static int ansW, ansB, answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = str.charAt(m) == 'W' ? 0 : 1;
			}
		}

		for (int boardR = 0; boardR <= N - 8; boardR++) {
			for (int boardC = 0; boardC <= M - 8; boardC++) {
				int res = check(boardR, boardC);
				answer = Math.min(answer, res);
			}
		}
		System.out.println(answer);
	}

	private static int check(int partR, int partC) {
		int wb = 0;
		ansW = 0;
		ansB = 0;
		for (int r = partR; r < partR + 8; r++) {
			for (int c = partC; c < partC + 8; c++) {
				if (map[r][c] != wb)
					ansW++;
				else {
					ansB++;
				}
				wb = wb ^ 1;
			}
			wb = wb ^ 1;
		}
		return Math.min(ansW, ansB);
	}

}
