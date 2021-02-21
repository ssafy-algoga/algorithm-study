import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class boj_11404_floyd {
	
	final static int INF = Integer.MAX_VALUE; // 없는 노선
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시 수
		int M = Integer.parseInt(br.readLine()); // 버스 수
		int from, to, cost;
		int[][] map = new int[N][N];
		
		// 맵 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = INF;
			}
		}
		// 비용 입력
		for (int m = 0; m < M; m++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken()) - 1; 
			to = Integer.parseInt(st.nextToken()) - 1;
			cost = Integer.parseInt(st.nextToken());
			map[from][to] = Math.min(cost, map[from][to]);
			
		}
		
		// 모든 도시를 경유 도시로 하여 최소 비용 탐색
		for (int via = 0; via < N; via++) { // 경유 도시 
			for (int f = 0; f < N; f++) { // 시작 도시
				for ( int t = 0; t < N; t++) { // 도착 도시
					// 루프거나 없는 노선이면 넘김
					if(f == t || map[f][via] == INF ||  map[via][t] == INF) continue;
					// 경유 비용과 기존 비용중 최소 비용으로 갱신
					map[f][t] = Math.min(map[f][t], map[f][via] + map[via][t]);
				}
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == INF) System.out.print("0 "); 
				else System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

