package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1051 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for(int size = 0; size < Math.min(N-i, M-j); size++) {
					if(arr[i][j] == arr[i+size][j] && arr[i][j] == arr[i][j+size] && arr[i][j] == arr[i+size][j+size]) {
						res = (int) Math.max(Math.pow(size+1, 2), res);
					}
				}
			}
		}
		System.out.println(res);

	}

}
