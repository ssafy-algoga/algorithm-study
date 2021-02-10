# 4963번 섬의개수

[문제 보러가기](https://www.acmicpc.net/problem/4963)

## 🅰 설계

1. 조건 : arr이 1(땅)이면, BFS 탐색
2. < BFS >
   - 방문 한 곳이라면, `arr[i][j] = 0` 으로 바꾸기 (방문 체크를 이렇게 했습니다.)
   - Queue에 넣고, 이후 큐가 빌 때까지 8방 탐색하면서 조건 맞는 곳은 큐에 넣고 빼고 반복

![17413](https://media.discordapp.net/attachments/802048763232780321/805616074686988338/image.png)

## 코드
```
public class boj_4963 {

	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };	
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[][] arr = new int[50][50];
	static int a;
	static int b;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			
			a = sc.nextInt(); // 3
			b = sc.nextInt(); // 2
			if (a == 0 && b == 0) {
				sc.close();
				break;
			}
			for (int i = 0; i < b; i++) {
				for (int j = 0; j < a; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int cnt = 0;
			for (int i = 0; i < b; i++) {
				for (int j = 0; j < a; j++) {
					if (arr[i][j] == 1) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
    // BFS 코드 부분
	public static void bfs(int yy, int xx) {
		Queue<Point> q = new LinkedList<Point>();
		arr[yy][xx] = 0;
		q.add(new Point(yy, xx));
		
		while (!q.isEmpty()) { 

			int y = q.peek().y;
			int x = q.peek().x;
			q.remove();
			for (int k = 0; k < 8; k++) {
				int nexty = y + dy[k];
				int nextx = x + dx[k];
				if (nexty >= 0 && nexty < b && nextx >= 0 && nextx < a) {
					if (arr[nexty][nextx] == 1) {
						arr[nexty][nextx] = 0;
						q.add(new Point(nexty, nextx));
					}
				}
			}
		}

	}

}
// Point 클래스 queue에 (x,y) 로 저장하기 위해
class Point {
	int x;
	int y;
	
	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
```

## ✅ 후기

Queue를 이용해서 BFS를 구현할 때, Queue 2개를 만드는 것이 아닌, Point 클래스를 이용해서 Queue에 (x,y)를 넣어 구현하고 싶었다.

처음에는 가로 세로를 잘못봐서 에러가 났고, 이후에는 메모리초과가 계속해서 났었다.
다시 보니까 nexty, nextx를 찾은 후에 `arr[nexty][nextx] = 0;` 를 안해서 큐에 가능한 모든 애들을 계속해서 넣었었다.

문제를 제대로 차근차근 읽어야 할 것 같다.