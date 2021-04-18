package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1018_체스판다시칠하기 {

	public static int N;
	public static int M;
	
	public static int[][] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j)=='B' ? 1 : 0;
			}
		}
		// 입력 완료
		
		int min_cnt = Integer.MAX_VALUE;
		
		for (int i = 0; i <= N-8; i++) {
			for (int j = 0; j <= M-8; j++) {
				min_cnt = Math.min(min_cnt, getBoxCnt(i, j));
			}
		}
		
		System.out.print(min_cnt);
		
	}

	public static int getBoxCnt(int rStart, int cStart) {
		int wCnt = 0;
		int bCnt = 0;
		
		int bRight = 1;
		
		int cur;
		
		for (int i = rStart; i < rStart + 8; i++) {
			for (int j = cStart; j < cStart + 8; j++) {
				cur = board[i][j];
				
				if(cur != bRight) {
					bCnt++;
				}
				else {
					wCnt++;
				}
				
				bRight ^= 1;
			}
			bRight ^= 1;
		}
		
		return Math.min(wCnt, bCnt);
	}
}
