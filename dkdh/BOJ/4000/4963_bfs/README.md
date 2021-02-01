# 4963번 문제제목
[문제 보러가기](https://www.acmicpc.net/problem/4963)

## 🅰 설계
지도를 차례로 탐색하면서 땅을 발견하면 그 땅 한 지역에서 시작해 그 땅과 이어진 모든 땅을 탐색하면서 방문 마킹을 한다. 이렇게 이어진 모든 땅이 하나의 섬이 된다.   
그 이후 지도를 계속 탐색하다가 방문 마킹이 되어있지 않은 땅을 발견한다면 이는 첫 번째 섬에 연결된 땅이 아니므로 두 번째 섬의 부분이 된다. 또 이 땅에서 시작해 이어진 모든 땅에 방문 마킹을 하면 이는 두 번째 섬을 이루게 된다.

위와 같은 아이디어에서 착안해 코드를 짰습니다.   
탐색 방법으로는 dfs를 사용했고, 방문 마킹은 boolean 2차원 배열을 이용했습니다.

### 구현
입력은 BufferedReader를 이용했습니다.
```jsx
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

방문 여부를 저장하기 위해 2차원 boolean 배열을 이용했습니다.
```jsx
boolean[][] visited = new boolean[h][w];
```

지도를 저장하기 위해 2차원 int 배열을 선언했습니다. 이어진 지역을 탐색할 때 팔방탐색을 하게 되는데, 이 때 경계 문제를 해결하기 위해 패딩을 추가해줬습니다.
```jsx
int[][] map = new int[h+2][w+2];
```


이어진 모든 땅을 탐색하기 위해 dfs를 사용했습니다.   
주어진 땅 지역에 대해
1. 방문 여부 검사
2. 방문 마킹
3. 팔방탐색 후 방문하지 않은 땅 지역이 있다면 해당 땅 지역에 대해 dfs 호출   

과 같은 프로세스입니다.

### 코드
```jsx
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
					
					// 방문한 적 있거나 바다 지역이면 탐색x
					if(visited[i][j]==true || map[i+1][j+1]==0)
						continue;
					
					// 방문한 적 없는 각 '땅' 지역에 대해	
					dfs(map, visited, i+1, j+1);
					count++;
				}
			}
			System.out.println(count);
		}
	}
	
	public static void dfs(int[][] map, boolean[][] visited, int r, int c) {
		// 이미 방문한 지역이라면 탐색x
		if(visited[r-1][c-1]==true)
			return;
		
		// 방문 마킹
		visited[r-1][c-1] = true;
		
		// 주변 갈 수 있는 곳 탐색(3x3). 패딩을 해둬서 경계 확인 필요x
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[r-1+i][c-1+j]==1) {
					dfs(map, visited, r-1+i, c-1+j);
				}
			}
		}

	}

}
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
일단은 자료구조를 사용하지 않고 배열로만 하는 느낌으로 하고 있습니닷

처음에는 bfs로 해야지 했는데 dfs가 되어 있었습니다..   
bfs는 큐 없으면 힘들 것 
### 고생한 점
visited랑 map이랑 같은 지역에 대해 다른 좌표를 쓰다보니   
처음에 이런 사항을 생각하고도 그냥 주의해서 하자 하고 그냥 갔는데   
결국 중간에 한 번 틀리긴 했네요

앞으로는 그냥 속 편한게 visited도 크게 잡아서 통일시켜버릴까 합니다.
