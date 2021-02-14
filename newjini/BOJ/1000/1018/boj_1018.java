package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1018 { // 체스판 다시 칠하기

	static char[][] white = { 
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' } };

	static char[][] black = { 
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M]; // 입력 배열
		char[][] eight = new char[8][8]; // 8x8 배열
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		int cnt = 2500; // 최종 최소 cnt
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int fy = i; // first y
				int fx = j; // first x 
				int endy = i + 7;
				int endx = j + 7; 
				int bcnt = 0, wcnt = 0; // 왼쪽맨끝 : 흑 , 백 일 때 바꿔지는 흑백갯수세기
				eight = new char[8][8];
				if (endy < N && endx < M) {
					for (int a = fy, c=0; a <= endy; a++,c++) { // 8X8 배열로 자르기
						for (int b = fx, d=0; b <= endx; b++,d++) {
							eight[c][d] = arr[a][b];
						}
					}
					for (int a = 0; a < 8; a++) { // white 배열과 검사
						for (int b = 0; b < 8; b++) {
							if (eight[a][b] != white[a][b]) {
								wcnt++; 	
							}
						}
					}
					for (int a = 0; a < 8; a++) { // black 배열과 검사
						for (int b = 0; b < 8; b++) {
							if (eight[a][b] != black[a][b]) {
								bcnt++;
							}
						}
					}
					cnt = Math.min(Math.min(wcnt, bcnt), cnt);
				}
			}
		}
		System.out.println(cnt);
	}

}
