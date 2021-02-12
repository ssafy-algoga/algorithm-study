import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1018_bruteForce {
	// 체스판 다시 칠하기
	static int N, M, min = Integer.MAX_VALUE;
	static char[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		board = new char[N][];

		// 보드 입력받기
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		// 모든 경우에 대해 체스판 비교해보기
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				compare(i, j);
			}
		}
		System.out.println(min);
	}

	public static void compare(int x, int y) {
		int cnt = 0; // 칠해야 할 정사각형 개수

		// W시작 체스판과 비교
		for (int row = x; row < x + 8; row++) {
			for (int col = y; col < y + 8; col++) {
				if (row % 2 == 0) { // W시작 줄
					if (col % 2 == 0) { // W
						if (board[row][col] != 'W') cnt++;
					} else { // B
						if (board[row][col] != 'B') cnt++;
					}
				} else { // B시작 줄
					if (col % 2 == 0) { // B
						if (board[row][col] != 'B') cnt++;
					} else { // W
						if (board[row][col] != 'W') cnt++;
					}
				}
			}
		}
		min = (min > cnt) ? cnt : min;
		cnt = 0;
		
		// B시작 체스판과 비교
		for (int row = x; row < x + 8; row++) {
			for (int col = y; col < y + 8; col++) {
				if (row % 2 == 0) { // B시작 줄
					if (col % 2 == 0) { // B
						if (board[row][col] != 'B') cnt++;
					} else { // W
						if (board[row][col] != 'W') cnt++;
					}
				} else { // W시작 줄
					if (col % 2 == 0) { // W
						if (board[row][col] != 'W') cnt++;
					} else { // B
						if (board[row][col] != 'B') cnt++;
					}
				}
			}
		}
		min = (min > cnt) ? cnt : min;
	}

}
