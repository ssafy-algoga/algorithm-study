import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int[] parent = new int[10001]; // 집합의 부모를 담기 위한 배열
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int v,e;
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		// cost를 오름차순으로 pq에 넣게 됨
		PriorityQueue<p> pq = new PriorityQueue<>((p1,p2)->p1.cost-p2.cost); 
		for(int i=0;i<e;i++) {
			int start,end,cost;
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			pq.add(new p(start,end,cost)); // 간선정보를 pq에 저장
		}
		
		int ans = 0;
		for(int i=0;i<v-1;i++) { // 총 v-1번으로 MST가 완성됨
			while(true) {
				p cur = pq.poll();
				if(merge(cur.s,cur.e)) { // 현재 간선의 start,end부모가 달랐으면 하나의 집합으로 합쳐짐
					ans += cur.cost;
					break;
				}
			}
		}
		System.out.println(ans);
	}
	static class p{
		int s,e,cost;
		public p(int s,int e,int cost) {
			this.s = s;
			this.e = e;
			this.cost = cost;
		}
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