# 14502번 연구소

[문제 보러가기](https://www.acmicpc.net/problem/14502)

## 🅰 설계

벽을 먼저 3개 세워놓고, 바이러스 퍼져나가는 것을 확인한다.
바이러스가 최소로 퍼진 때가 최대 안전영역을 가지므로 해답으로 한다.
1. 입력받을 때 바이러스 위치는 큐에 넣는다.
2. 벽 3개를 세울 위치를 정하기 위해 조합을 이용한다.
3. 벽이 세워진 map을 BFS 탐색해서 최소로 바이러스가 퍼질 때를 구한다. (= 최대 안전영역)

## 코드
``` java
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

``` 
## ✅ 후기


문제를 풀기 전에 생각하는 시간이 굉장히 오래걸렸다.   
벽 3개를 구하는 조합을 구현할 때 특히나 오래걸렸다.   
좌표를 1차원적으로 생각하기 위해 for문 한 개를 이용하고, N*M 크기에서 나누기, 나머지 연산을 이용해 풀 수 있었다.  

열심히 해야겠다.
