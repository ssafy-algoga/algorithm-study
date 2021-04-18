# 11559번 Puyo Puyo
[문제 보러가기](https://www.acmicpc.net/problem/11559)

## 🅰 설계

이런 시뮬레이션 문제는 메소드 나눠서 하는 게 정리도 잘 되고 편한 것 같습니다.

전체적으로   
1) 같은 색 뿌요 4개 이상 붙어있는 게 있는지 확인 -> 있으면 연쇄+1, 없으면 종료
2) 뿌요 삭제
3) 중력 발동
4) 1~3 반복

의 시나리오를 따랐습니다.

같은 색 뿌요가 4개 이상 붙어있는 것은 맵에서 뿌요가 있는 모든 칸에 대해 검사해봐야 합니다.   
dfs 방식으로 같은 색 뿌요를 탐색하고, visited 배열에 표시를 해서 다시 탐색하는 일이 없도록 했습니다.   
뿌요가 없는 칸에 대해서도 visited 배열에 true로 표시해서, 뿌요가 없는 칸을 계속 뻗어가며 탐색하는 일이 없도록 했습니다.

맵의 가장 아래 행부터 탐색하는데, 한 행이 모두 비어있다면 그 위는 탐색할 필요가 없으므로 맵에 대한 탐색을 그만둡니다.

같은 색의 뿌요를 탐색해나가면서 큐에 그 위치들을 집어넣고, 더 이상 탐색이 불가능할 때 개수를 검사해 4개 이상이면 큐에 그대로 두고, 4개 미만이면 큐에서 그 개수만큼 다시 꺼내줍니다. 이 때 뒤에 넣은 것을 다시 뒤에서 빼야 하므로 deque를 사용했습니다.

맵에 대한 탐색이 끝나면, 큐에 저장된 위치를 꺼내 하나하나 삭제해줍니다.

그리고 각 열에 대해 중력을 작동시켜 줍니다.

```jsx
int combo = 0;
boolean is;
cnt = 0;
q = new ArrayDeque<int[]>();

do {
  is = false;
  visited = new boolean[12][6];

  for (int i = 11; i >= 0; --i) {
    int temp = 0;
    for (int j = 0; j < 6; ++j) {
      int puyo = map[i][j];
      temp += puyo;

      // 빈 공간은 탐색 안 하도록
      if(puyo == 0) visited[i][j] = true;

      // 뿌요가 있는 공간 중 방문하지 않은 곳 탐색하기
      else if(!visited[i][j]){
        find(i, j, puyo);

        if(cnt >= 4) is = true;
        else {
          for (int k = 0; k < cnt; k++) {
            q.removeLast();
          }
        }

        cnt = 0;
      }
    }

    // 한 줄이 다 뿌요 없으면 그 위는 탐색 필요x
    if(temp==0) break;
  }

  // 터질 뿌요가 있으면
  if(is) {
    delete();
    gravity();
    combo++;
  }
}while(is);
```

### 코드
```jsx
package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_11559_PuyoPuyo {

	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};	
	
	static int cnt;
	
	static Deque<int[]> q;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[12][6];
		
		for (int i = 0; i < 12; i++) {
			String input = br.readLine();
			for (int j = 0; j < 6; j++) {
				switch (input.charAt(j)) {
				case '.':
					map[i][j] = 0;
					break;
				case 'R':
					map[i][j] = 1;
					break;
				case 'G':
					map[i][j] = 2;
					break;
				case 'B':
					map[i][j] = 3;
					break;
				case 'P':
					map[i][j] = 4;
					break;
				case 'Y':
					map[i][j] = 5;
					break;
				}
			}
		}
		// 입력 끝
		
		int combo = 0;
		boolean is;
		cnt = 0;
		q = new ArrayDeque<int[]>();
		
		do {
			is = false;
			visited = new boolean[12][6];
			
			for (int i = 11; i >= 0; --i) {
				int temp = 0;
				for (int j = 0; j < 6; ++j) {
					int puyo = map[i][j];
					temp += puyo;
					
					// 빈 공간은 탐색 안 하도록
					if(puyo == 0) visited[i][j] = true;
					
					// 뿌요가 있는 공간 중 방문하지 않은 곳 탐색하기
					else if(!visited[i][j]){
						find(i, j, puyo);
						
						if(cnt >= 4) is = true;
						else {
							for (int k = 0; k < cnt; k++) {
								q.removeLast();
							}
						}
						
						cnt = 0;
					}
				}
				
				// 한 줄이 다 뿌요 없으면 그 위는 탐색 필요x
				if(temp==0) break;
			}
			
			// 터질 뿌요가 있으면
			if(is) {
				delete();
				gravity();
				combo++;
			}
		}while(is);
		
		System.out.print(combo);
	}

	private static void gravity() {

		for (int c = 0; c < 6; ++c) {
			for (int r = 10; r >= 0; --r) {
				if(map[r][c] != 0) goDown(r, c);
			}
		}
	}

	private static void goDown(int r, int c) {
		int nr = r+1;
		
		while(nr < 12) {
			if(map[nr][c]==0) {
				map[nr][c] = map[r][c];
				map[r][c] = 0;
				
				r = nr;
				nr = r+1;
			}
			else break;
		}
	}

	private static void delete() {
		
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			
			map[loc[0]][loc[1]] = 0;
		}
	}

	private static void find(int r, int c, int color) {
		
		cnt++;
		visited[r][c] = true;
		q.add(new int[] {r, c});
		
		int nr, nc;
		
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			
			if(nr<12 && nr>=0 && nc>=0 && nc<6 &&
					!visited[nr][nc] && map[nr][nc] == color) {
				find(nr, nc, color);
			}
		}

	}

}
```

## ✅ 후기
이런 시뮬레이션 문제에 약한 편인데, 메소드 다 분리하고 잘게잘게 나눠 생각하니까 좀 나은 것 같습니다!   
너무 고민하지 말고 바로바로 떠오르는 걸 해보는 것도 좋은 것 같아요
