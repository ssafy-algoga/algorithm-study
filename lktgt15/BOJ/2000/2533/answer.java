import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int n,LIMIT = (int)1e6;
	static List<Integer>[] path;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1][2];
		path = new List[n+1];
		for(int i=1;i<=n;i++) { // dp 배열을 -1로 초기화, path 인접 리스트를 생성
			dp[i][0] = dp[i][1] = -1;
			path[i] = new ArrayList<>();
		}
		for(int i=0;i<n-1;i++) { // 무향 그래프를 만듦
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			path[a].add(b);
			path[b].add(a);
		}
		
		System.out.println(Math.min(f(1,0,1), f(1,0,0))); // cur = 시작 노드를 n으로 바꿔도 동일하게 답을 얻을 수 있음
	}
	
	static int f(int cur,int prev,int early) { // cur 노드에서 early일 때 남은 노드들을 최소로 얼리어답터로 만드는 경우
		if(dp[cur][early] != -1) return dp[cur][early];
		int ret = early; // 자신이 early ( 1 == 얼리어답터, 0 == 얼리어답터가 아님 )
		
		for(int nxt : path[cur]) { // 인접 리스트 확인
			if(nxt == prev) continue; // 이전 노드면 pass
			
			if(early == 1) { // 자신이 얼리어답터면 다음 노드는 얼리어답터일 수도 있고 아닐수도 있음
				ret += Math.min(f(nxt,cur,0),f(nxt,cur,1));
			}
			else { // 자신이 얼리어답터가 아니면 다음 노드는 무조건 얼리어답터여야 함
				ret += f(nxt,cur,1);
			}
		}
		
		return dp[cur][early] = ret;
	}
}