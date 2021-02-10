import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
						System.gc();
					}
				}
			}
			System.out.println(cnt);
		}
	}

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

class Point {
	int x;
	int y;
	
	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
