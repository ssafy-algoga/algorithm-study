import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	// 집합을 확인하기 위한 parent,친구가 되기 위한 비용을 담은 cost
	static int[] parent = new int[10001], cost = new int[10001];

	// 이미 친구인 집합인지 확인하는 chk 
	static boolean[] chk = new boolean[10001];
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int n,m,k;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<m;i++) {
			int a,b,mincost;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			mincost = Math.min(cost[find(a)], cost[find(b)]); // a집합과 b집합이 친구가 되기위한 최소 비용
			
			merge(a,b);
			cost[find(a)] = mincost; 
			// 새로운 친구 집합의 친구가 되기 위한 비용은 두 집합의 최소비용 중 더 작은 비용이 됨
		}
		
		int ans = 0;
		
		for(int i=1;i<=n;i++) {
			if(!chk[find(i)]) { // 모든 친구집합의 최소비용을 확인
				ans += cost[find(i)];
				chk[find(i)] = true;
			}
		}
		if(ans <= k) System.out.println(ans);
		else System.out.println("Oh no");
	}
	
	static int find(int x) {
		if(parent[x] <= 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean merge(int a,int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		parent[b] = a;
		return true;
	}
}