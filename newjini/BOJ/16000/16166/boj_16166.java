package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_16166 { // 서울의 지하철
	static int N;
	static ArrayList<int[]> subway; // 각 호선 역 정보 ex) subway[0] : { 0, 2, 3} , ...
	static ArrayList<Integer> startLine; // 출발 역의 호선 정보가 여러 개일 수도 있어서 배열
	static ArrayList<Integer> endLine; // 도착 역의 호선 정보가 여러 개일 수도 있어서 배열
	static boolean[][] trans; // 환승 정보
	static boolean[] visited; // 역 방문 정보
	static int res = 999;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 지하철 호선 개수
		endLine = new ArrayList<Integer>(); // 초기화
		startLine = new ArrayList<Integer>(); // 초기화
		subway = new ArrayList<int[]>();
		int idx = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int sub = Integer.parseInt(st.nextToken()); // 호선 번호
			int[] arr = new int[sub]; // 넣을 호선
			for (int j = 0; j < sub; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				if (arr[j] == 0) {
					startLine.add(i); // start : 출발역의 호선
				}
			}
			subway.add(arr);
		}
		int endStation = Integer.parseInt(br.readLine()); // 도착 역
		
		for (int i = 0; i < N; i++) { // 도착역의 호선 찾기
			int[] arr = subway.get(i);
			int leng = arr.length;
			for (int j = 0; j < leng; j++) {
				if (arr[j] == endStation) {
					endLine.add(i);
				}
			}
		}

		trans = new boolean[N][N]; // A호선 -> B호선으로 환승할 수 있는 정보
		visited = new boolean[N]; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) // 같은 호선으로 환승하진 않으니까 false 
					trans[i][j] = false;
				else {
					int[] station1 = subway.get(i); // i호선의 역정보
					int[] station2 = subway.get(j); // j호선의 역정보
					int leng1 = station1.length;
					int leng2 = station2.length;
					for (int a = 0; a < leng1; a++) {
						for (int b = 0; b < leng2; b++) {
							if(station1[a] == station2[b]) { // i, j 호선 모두 같은 역을 갖고있다면
								trans[i][j] = true; // 환승 가능
							}
						}
					}
				}
			}
		}
		

		int cnt = 0;
		for(int i=0;i<startLine.size(); i++) { // 출발 호선 정보
			for(int j=0;j<endLine.size(); j++) { // 도착 호선 정보
				visited[startLine.get(i)] = true;
				DFS(startLine.get(i), endLine.get(j), cnt);
				visited[startLine.get(i)] = false;
			}
		}

		if(res == 999) res = -1;
		System.out.println(res);
		

	}

	private static void DFS(int nowLine, int endLine, int cnt) {
		
		// 기저조건
		if(nowLine == endLine) { // 도착호선에 도착한다면
			res = Math.min(cnt, res);	// 환승횟수의 최솟값 저장
			return;
		}
		
		// 유도파트
		for(int i=0;i<N;i++) {
			
			if(trans[nowLine][i] && !visited[i]) { // nowLine -> i호선 환승 가능하고, 방문하지 않았다면
				visited[i] = true; 
				DFS(i, endLine, cnt+1); // 환승 +1 
				visited[i] = false;
			}
		}
	}

}
