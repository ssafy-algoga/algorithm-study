package boj.m3w5_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 좌표에 대해 ㅡ \ | 파이프의 끝이 오는 가짓수
		int[][][] dp = new int[N+1][N+1][3];
		
		dp[1][2][0] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if(map[i][j] != 0) continue;
				
				// ㅡ
				if(j-1>=0)
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				
				// \
				if(i-1>=0 && j-1>=0
						&& map[i-1][j] == 0 && map[i][j-1] == 0)
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			
				// |
				if(i-1>=0)
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}

}
