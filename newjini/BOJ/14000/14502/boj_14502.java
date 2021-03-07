package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14502 { // 연구소

	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] map; // 지도
	static int[][] safeMap; // 벽을 더한 지도
	static Queue<P> q = new LinkedList<P>();   // 원본 q 
	static Queue<P> tmp = new LinkedList<P>(); // 매번 계산되는 q

	static boolean[][] isSelected; // 벽 세우는 것에 대한 방문 여부
	static boolean[][] visited; // BFS 방문 여부

	static P[] wall; // 벽 3개의 위치 저장하는 배열
	static int leng; // N*M
	static int res = -1; // 최대 안전영역 결과 값

	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		leng = N * M; // 나중에 벽을 설치하기 위해 쓰이는 값
		
		map = new int[N][M];
		safeMap = new int[N][M];
		wall = new P[3];
		isSelected = new boolean[N][M];
		
		for (int i = 0; i < N; i++) { // map 배열 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) { // 지도에서  0인 곳(빈칸)만 추가
					isSelected[i][j] = true;
				}
				if (map[i][j] == 2) { // 바이러스 위치는 q에 추가
					q.add(new P(i, j));
				}

			}
		}
		combi(0, 0); 
		System.out.println(res);
		

	}

	private static void combi(int cnt, int start) {
		// 기저 조건 -> 벽 3개를 세울 좌표 찾았다면 BFS 
		if (cnt == 3) {
			visited = new boolean[N][M];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					safeMap[i][j] = map[i][j]; // 기존 벽 복사
				}
			}
			for (int i = 0; i < 3; i++) {
				int a = wall[i].y;
				int b = wall[i].x;
				safeMap[a][b] = 1; // 벽 세우기
			}
			tmp.addAll(q); // 기존 큐 복사
			int countSafe = 0; // 안전한 곳 세기
			res = Math.max(res, BFS(countSafe)); 
			return;
		}
		// 유도 파트
		for (int i = start; i < leng; i++) { // 1차원으로 생각하기 위해 N*M 에서 계산
			int a = i / M;  // 행
			int b = i % M;  // 열
			if (!isSelected[a][b]) { // 0이 아닌 좌표는 건너 뛰기
				continue;
			}

			wall[cnt] = new P(a, b); // 세울 수 있는 벽의 좌표 추가
			combi(cnt + 1, i + 1);
		}
	}

	private static int BFS(int cnt) {
		
		while (!tmp.isEmpty()) {
			P now = tmp.poll();
			int y = now.y;
			int x = now.x;
			
			visited[y][x] = true;
			
			for (int i = 0; i < 4; i++) {
				int ny = dy[i] + y;
				int nx = dx[i] + x;
				if (ny >= 0 && ny < N && nx >= 0 && nx < M && safeMap[ny][nx] == 0 && !visited[ny][nx]) {
					safeMap[ny][nx] = 2;
					visited[ny][nx] = true;
					tmp.add(new P(ny,nx));
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(safeMap[i][j] == 0) // 안전한 곳 세기
					cnt++;
			}
		}
		return cnt;
	}

}

class P {
	int x;
	int y;

	P(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
