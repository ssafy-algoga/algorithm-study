import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		traverse(0, 0, 0, 0);
		System.out.println(ans);
	}

	private static void traverse(int mask, int cnt, int curNode, int dist) {
        if (dist >= ans)
			return;
		if (cnt == N) {
			ans = Math.min(ans, dist);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (i == curNode || (mask & 1 << i) != 0)
				continue;
			traverse(mask | 1 << i, cnt + 1, i, dist + graph[curNode][i]);
		}
	}
}