package com.boj.숫자정사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 오른쪽 아래쪽 오른-아래대각선

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = parse(st.nextToken());
		int M = parse(st.nextToken());
		
		int[][] map = new int[N][];
		for (int i = 0; i < N; ++i)
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

		int rowLen = N - 1;
		int colLen = M - 1;
		int numLen = (N < M) ? N - 1 : M - 1;
		int max = 1;
		
		for (int X = 0; X < rowLen; X++) {
			for (int Y = 0; Y < colLen; Y++) {
				int now = map[X][Y];
				EXPAND : for (int num = 1; num <= numLen; num++) {
					for(int[] dir: dir) {
						int nextX = X + dir[0] * num;
						int nextY = Y + dir[1] * num;
						boolean isInMap = nextX <= rowLen && nextY <= colLen;
						
						if(!isInMap)
							break EXPAND;
						if(now != map[nextX][nextY])
							continue EXPAND;
					}
					max = Math.max(max, num+1);
				}
			}
		}
		System.out.println(max*max);

	}

	static int parse(String s) {
		return Integer.parseInt(s);
	}
}