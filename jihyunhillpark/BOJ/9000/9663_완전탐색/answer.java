import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	private static int totalCnt = 0;
	private static int N = 0;
	private static int[] dr = {0,-1,-1,-1,0,1,1,1}; //가장 왼 --> 시계방향 8방 offset.
	private static int[] dc = {-1,-1,0,1,1,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		int[][] visited = new int[N][N];
		getNQueen(0, visited);
		System.out.println(totalCnt);
	}
	private static void getNQueen(int level, int[][] visited) {
		if(level == N) {
			totalCnt++;
			return;
		}
		for(int i = 0; i < N ; i++) { //level 행에 열들을 탐색하며..
			if(visited[level][i] == 0) { //방문하지 않은 곳에한해서
				visited[level][i] += 1;
				for(int j = 0; j < 8 ; j++) {
					//공격할수 있는 곳 모두 체크
					int nr = level + dr[j];
					int nc = i + dc[j];
					while(0<= nr && nr < N && 0 <= nc && nc < N) {
						visited[nr][nc] += 1;
						nr += dr[j];
						nc += dc[j];
					}
				}
				getNQueen(level+1, visited);
				for(int j = 0; j < 8 ; j++) {
					//원상복구
					int nr = level + dr[j];
					int nc = i + dc[j];
					while(0<= nr && nr < N && 0 <= nc && nc < N) {
						visited[nr][nc] -= 1;
						nr += dr[j];
						nc += dc[j];
					}
				}
				visited[level][i] -= 1;
			}

		}
	}
}
