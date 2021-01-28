# 4963번 섬의 개수
[문제 보러가기](https://www.acmicpc.net/problem/4963)

## 🅰 설계

### 1. 선언부
int[][] map : 맵의 땅, 바다 여부를 저장할 배열 

boolean[][] visited : 맵의 방문 여부를 저장할 배열

두 2차원 배열을 이용해서 재귀적으로 DFS(깊이 우선 탐색)으로 구현하였습니다. 

```jsx
public class Main {
	// 섬의 개수 4963
	// 섬과 바다의 지도에서 인접(대각선 포함)한 섬은 하나의 섬으로 취급하여
	// 독립된 섬의 갯수를 리턴
	final static int[] dx = { 0, 0, 1, -1, 1, 1, -1, -1 };
	final static int[] dy = { 1, -1, 0, 0, 1, -1, 1, -1 };
	static int[][] map; // 맵 저장
	static boolean[][] visited; //방문 여부 저장
	static int cnt, row, col;
```


### 2. 메인 함수
map을 입력받고 완전탐색을 합니다. 

방문하지 않은 땅인 경우에만 DFS를 시행하고 섬의 개수를 증가시킵니다.
```
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
```

### 3. dfs()
먼저 방문 체크를 해주고 8방 탐색을 합니다.

인접 구획의 경계 및 방문 여부를 체크하고,

방문하지 않은 땅이라면 다시 DFS를 시행합니다.
```
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
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
![image](https://user-images.githubusercontent.com/43156636/106171976-90c9ba80-61d5-11eb-9dec-9fa9b90e5498.png)
이전에 BFS로 푼 적이 있어서 이번엔 DFS로 풀었습니다.

이 문제에서는 DFS가 성능면에서 살짝 우수하다는 것을 알았고 구현도 더 편했습니다.
### 고생한 점
풀어 본 문제지만 풀이 시간이 크게 단축되진 않았습니다.

시간을 줄일 방법을 고려해봐야겠습니다.
