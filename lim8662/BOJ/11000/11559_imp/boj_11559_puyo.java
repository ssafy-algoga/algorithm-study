package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class boj_11559_puyo {
	static int chain;
	static char[][] map = new char[12][6];
	static boolean[][] visited;
	static boolean isPang;
	static final int[] dx = { 0, 0, 1, -1 };
	static final int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		combo();

		System.out.println(chain);
	}

	private static void combo() {
		visited = new boolean[12][6];
		isPang = false;
		
		for (int col = 0; col < 6; col++) { // 가능한 모든 뿌요 터뜨림
			for (int row = 11; row >= 0; row--) {
				if (map[row][col] == '.') break;
				if(!visited[row][col]) 
					pang(row,col);
			}
		}
		if(isPang) { // 터진 뿌요가 있다면 
			chain++; // 연쇄 1증가
		
			// 뿌요 정렬
			char[][] tmap = new char[12][6]; // 정렬된 맵
			for(char[] init : tmap) { // 초기화
				Arrays.fill(init, '.');
			}
			
			for (int col = 0; col < 6; col++) {
				int nr = 11;
				for (int row =11; row >= 0; row--) {
					if(map[row][col] == '.') break;
					if(map[row][col] == 'x') continue;
					tmap[nr--][col] = map[row][col];
				}
			}
			map = tmap;
			
			combo();
		}		
	}
	
	// BFS로 같은 색의 뿌요 탐색
	private static void pang(int row, int col) {
		Queue<int[]> q  = new ArrayDeque<int[]>();
		char cur = map[row][col]; // 탐색할 뿌요의 색
		q.add(new int[] { row, col });
		visited[row][col] = true; // 뿌요 방문 여부 검사
		List<int[]> target = new ArrayList<>(); // 터뜨릴 뿌요 리스트
		target.add(new int[] { row, col });
		
		while (!q.isEmpty()) {
			int[] tmp = q.remove();
			int r = tmp[0];
			int c = tmp[1];
			for (int d = 0; d < 4; d++) { // 4방탐색
				int nr = r + dx[d];
				int nc = c + dy[d];
				if(nr < 0 || nr >= 12 || nc < 0 || nc >= 6) continue;
				if(!visited[nr][nc] && map[nr][nc] == cur) { // 색이 같고 방문하지 않은 뿌요라면
					visited[nr][nc] = true;
					target.add(new int[] {nr, nc});
					q.add(new int[] {nr, nc});
				}
			}
		}
		if(target.size() >= 4) { // 같은 뿌요가 4개 이상이면 pang 
			isPang = true;
			for(int[] t : target) map[t[0]][t[1]] = 'x';
		}
	}
}
