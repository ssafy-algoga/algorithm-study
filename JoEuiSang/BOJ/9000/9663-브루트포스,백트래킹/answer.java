package 스터디._2월2주;

import java.util.Scanner;

public class N_Queen {
	//크기, 정답, 행을 채웠는지 안채웠는지 확인할 변수
	static int N, ans, fillRow;
	static boolean[][] map;		//체스판
	static boolean[] row, col;	//퀸을 놓은 행과 열을 기억할 배열
	static int offset, repeatR, repeatC, repeat;	//위치, 

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[N][N];
		row = new boolean[N];
		col = new boolean[N];

		chess(0);
		System.out.println(ans);
	}

	private static void chess(int R) {
		if (R == N) {	//기저조건 : 모든 행에 퀸이 놓여졌다
			ans++;
			return;
		}

		//R부터 행 탐색, 윗부분은 이미 처리했음
		for (int r = R; r < N; r++) {
			for (int c = 0; c < N; c++) {	//현재행의 어느 열에 놓을지 처리
				if (fillRow < r)			//채워진 행 수가 현재 행보다 작으면, 끝내기 (위쪽 행을 처리하지 않았다는 뜻)
					return;
				if (col[c] == true || !diagonal(r, c)) 		//다른 행, 현재열에 퀸이 있다면 패스 || 대각에 퀸이 있다면 패스
					continue;				

				map[r][c] = true;		//퀸 놓기
				col[c] = true;			//열에 퀸있다고 표시
				fillRow++;				//채운 행 수 ++
				chess(r + 1);			//재귀
				map[r][c] = false;		//돌려놓기
				col[c] = false;			//돌려놓기
				fillRow--;				//돌려놓기
			}
		}
	}

	//r,c 기준 대각선을 탐색하는 함수
	private static boolean diagonal(int r, int c) {
		offset = 0;		//기준점으로부터 거리
		repeatR = Math.max(N - r - 1, r);	//행 반복할 횟수 중 큰값
		repeatC = Math.max(N - c - 1, c);	//열 반복할 횟수 중 큰값
		repeat = Math.min(repeatR, repeatC);	//그 둘 중 작은값(대각으로 진행하기 때문)
		while (repeat-- > 0) {
			offset++;
			// 좌상, 우상, 우하, 좌하
			int[] dr = { r - offset, r - offset, r + offset, r + offset };
			int[] dc = { c - offset, c + offset, c + offset, c - offset };

			// 좌상단 
			if (dr[0] >= 0 && dc[0] >= 0) {
				if (map[dr[0]][dc[0]] == true) // 대각방향에 다른 퀸이 있으면
					return false;
			}

			// 우상단
			if (dr[1] >= 0 && dc[1] < N) {
				if (map[dr[1]][dc[1]] == true)
					return false;
			}

			// 우하단
			if (dr[2] < N && dc[2] < N) {
				if (map[dr[2]][dc[2]] == true)
					return false; 
			}

			// 좌하단
			if (dr[3] < N && dc[3] >= 0) {
				if (map[dr[3]][dc[3]] == true)
					return false; 
			}
		}
		return true;
	}
}
