# 2573 빙산
[문제 보러가기](https://www.acmicpc.net/problem/2573)

## 🅰 설계
1. 빙산의 좌표를 리스트로 관리하여 빠르게 접근했습니다.
2. 빙산의 좌표에서 dfs를 돌며 빙산덩어리의 개수를 셉니다.



함정이 있다면 주변 바다를 카운트 한 뒤에 빙산을 바로 녹이면 안됩니다.

녹인 뒤에 다음 빙산을 바라보면 주변 빙산이 녹은걸로 처리가 되면 바다의 수가 증가하기 때문에 틀린 답을 만날 수가 있습니다.

그러니 바다의 수를 따로 저장해 놓은 뒤 녹이는 작업을 한번에 진행해야 합니다.




## 주요 코드 설명
### initVisited() : map 방문상태를 false로 초기화

```java
private static void initVisited() {
    for (int i = 0; i < visited.length; i++) {
        Arrays.fill(visited[i], false);
    }
}
```

### visitIce() : 빙산덩어리를 세기위해 연결된 빙산을 방문처리

```java
private static void visitIce(int r, int c) {
    for (int d = 0; d < 4; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !visited[nr][nc]) {
            visited[nr][nc] = true;
            visitIce(nr, nc);
        }
    }
}
```

### meltIce() : 빙산과 맞닿은 바다의 면 수 만큼 녹이기

```java
static void meltIce() {
    Point p = null;
    for (int i = 0; i < ice.size(); i++) {
        p = ice.get(i);
        for (int d = 0; d < 4; d++) {
            int nr = p.r + dr[d];
            int nc = p.c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                p.sea++;	//모든 빙산에대해 바다 개수를 다 센뒤에 빼줘야한다
            }
        }

    }
    // 주변 바다 개수만큼 빼주기
    for (int i = 0; i < ice.size(); i++) {
        p = ice.get(i);
        if (p.val - p.sea <= 0) {
            map[p.r][p.c] = 0;
            ice.remove(i);
            i--;
        } else {
            map[p.r][p.c] -= p.sea;
            p.val -= p.sea;
            p.sea=0;
        }
    }
}
```

### 전체 소스

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2573빙산 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> ice = new ArrayList<>();	//빙산의 좌표를 저장해놓을 리스트
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c, val, sea;

		public Point(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 3~300
		M = Integer.parseInt(st.nextToken()); // 열 3~300
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					ice.add(new Point(i, j, map[i][j]));
			}
		}

		int time = 0;
		while (true) {
			initVisited(); // 방문 초기화

			int iceCnt = 0; // 빙산덩어리 개수 담을 변수

			//빙산 덩어리 개수 세기
			for (Point p : ice) {
				if (!visited[p.r][p.c]) { // 방문 안한 빙산이면
					visitIce(p.r, p.c); // 빙산덩어리 방문처리
					iceCnt++; // 빙산덩어리 증가
				}
			}
			//2덩어리 이상으로 나뉘었으면 반환
			if (iceCnt >= 2) {
				System.out.println(time);
				return;
			}

			meltIce(); // 빙산 녹이기
			time++; // 시간 증가

			//모든빙하가 녹으면 실패
			if (ice.size() == 0) {
				System.out.println(0);
				return;
			}
		}
	}

	private static void initVisited() {
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static void visitIce(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				visitIce(nr, nc);
			}
		}
	}

	static void meltIce() {
		Point p = null;
		for (int i = 0; i < ice.size(); i++) {
			p = ice.get(i);
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
					p.sea++;	//모든 빙산에대해 바다 개수를 다 센뒤에 빼줘야한다
				}
			}
			
		}
		// 주변 바다 개수만큼 빼주기
		for (int i = 0; i < ice.size(); i++) {
			p = ice.get(i);
			if (p.val - p.sea <= 0) {
				map[p.r][p.c] = 0;
				ice.remove(i);
				i--;
			} else {
				map[p.r][p.c] -= p.sea;
				p.val -= p.sea;
				p.sea=0;
			}
		}
	}
}

```

## ✅ 후기

### 함정만 잘 간파한다면 쉽게 풀 수 있는 완전탐색 문제였습니다.