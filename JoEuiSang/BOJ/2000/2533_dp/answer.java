import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N;	
	static boolean[] visited;				//방문 체크 배열
	static LinkedList<Integer>[] graph;		//각 노드들의 연결정보		
	static int[][] dp;						//얼리어답터일때와, 아닐때의 최소값을 저장할 DP배열
	static final int EARLY = 1, LATE = 0;	//얼리와, 레이트의 상수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		graph = new LinkedList[N + 1];
		dp = new int[N + 1][2];

		for (int i = 0; i < N + 1; i++) {
			graph[i] = new LinkedList<>();		//그래프 초기화
		}

		//그래프 관계 생성
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		//시작
		dfs(1);

		System.out.println(Math.min(dp[1][EARLY], dp[1][LATE]));
	}

	private static void dfs(int value) {
		visited[value] = true;		//방문처리
		dp[value][EARLY] = EARLY;	//얼리어답터 1처리
		dp[value][LATE] = LATE;		//레이트어답터 0처리

		//현재 노드의 연결링크들 모두 확인
		for (int link : graph[value]) {
			//이미 방문한곳이면 패스
			if (visited[link])
				continue;
			dfs(link);	//재귀
			dp[value][EARLY] += Math.min(dp[link][EARLY], dp[link][LATE]);	//내가 얼리어답터면 다음 친구는 얼리어답터 또는 레이트어답터가 될 수 있다.
			dp[value][LATE] += dp[link][EARLY];								//내가 레이트어답터면 다음친구는 무조건 얼리어답터여야한다.
		}
	}
}
