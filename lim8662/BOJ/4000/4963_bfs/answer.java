import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HelloWorld {
	// 섬의 개수 4963
	// 섬과 바다의 지도에서 인접(대각선 포함)한 섬은 하나의 섬으로 취급하여
	// 독립된 섬의 갯수를 리턴
	final static int[] dx = { 0, 0, 1, -1, 1, 1, -1, -1 };
	final static int[] dy = { 1, -1, 0, 0, 1, -1, 1, -1 };
	static int[][] map; // 맵 저장
	static boolean[][] visited; //방문 여부 저장
	static int cnt, row, col;

	public static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int d = 0; d < dx.length; d++) {
			int nx = r + dx[d];
			int ny = c + dy[d];

			if (nx < 0 || nx >= row || ny < 0 || ny >= col || visited[nx][ny])
				continue;

			if (map[nx][ny] == 0) // 바다면 넘어감
				continue;
			else { // 땅이면 재탐색
				dfs(nx, ny);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			cnt = 0;

			// 종료
			if (col == 0 && row == 0)
				break;

			map = new int[row][col];
			visited = new boolean[row][col];
			
			// 맵 입력받기
			for (int r = 0; r < row; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < col; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			// 맵 전체 탐색
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					if (map[r][c] == 1 && !visited[r][c]) {
						dfs(r, c);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}

