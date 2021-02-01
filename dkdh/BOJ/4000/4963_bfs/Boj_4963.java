package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_4963 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = 0;
		
		int w = 0;
		int h = 0;
		
		// 메인 반복문: 주어진 지도마다 반복
		while(true) {
			// w와 h 입력받기 (지도의 너비와 높이)
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			// 너비와 높이가 둘 다 0이면 종료
			if(w==0 && h==0) break;
			
			// 섬의 개수를 새기 위한 변수
			count = 0;
						
			// 지도를 저장할 배열. 패딩 추가(+2)
			int[][] map = new int[h+2][w+2];
			
			// 방문 여부를 저장할 boolean 배열
			boolean[][] visited = new boolean[h][w];
			
			// 지도 입력 받기
			for (int i = 0; i < h; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					if(st1.hasMoreTokens())
						map[i+1][j+1] = Integer.parseInt(st1.nextToken());
				}
			}
			
			// 주어진 지도에서 각 지역에 대해 반복
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					
					if(visited[i][j]==true || map[i+1][j+1]==0)
						continue;
					
					// 방문한 적 없는 각 '땅' 지역에 대해	
					visited = bfs(map, visited, i+1, j+1);
					count++;
				}
			}
			System.out.println(count);
		}
	}
	
	public static boolean[][] bfs(int[][] map, boolean[][] visited, int r, int c) {
		// 이미 방문한 지역이라면 탐색x
		if(visited[r-1][c-1]==true)
			return visited;
		
		// 방문 마킹
		visited[r-1][c-1] = true;
		
		// 주변 갈 수 있는 곳 탐색(3x3). 패딩을 해둬서 경계 확인 필요x
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[r-1+i][c-1+j]==1) {
					visited = bfs(map, visited, r-1+i, c-1+j);
				}
			}
		}
		
		return visited;
	}

}
