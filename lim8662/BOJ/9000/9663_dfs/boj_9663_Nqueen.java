package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9663_Nqueen {
	static int N, cnt, area;
	static int[] queen; // 각 행에서 퀸의 열 위치를 표시

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queen = new int[N];

		nQueen(0, 0);

		System.out.println(cnt);
	}

	// 재귀로 N번 퀸을 놓는다.
	public static void nQueen(int n, int flag) {
		if (n == N) {// N번 배치했으면 카운트 증가
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) continue; // 열 중첩은 비트마스킹으로 검사
			queen[n] = i; // n번째 행, i번째 열에 퀸을 놓는다.

			if (isPossible(n)) { // 대각선 유효한 배치면 다음 퀸을 놓는다.
				nQueen(n + 1, flag | 1 << i);
			}
		}
	}

	public static boolean isPossible(int n) {
		for (int j = 0; j < n; j++) { // 기존 퀸과의 대각선 검사
			if (Math.abs(queen[n] - queen[j]) == n - j) {
				return false;
			}
		}
		return true;
	}
}
