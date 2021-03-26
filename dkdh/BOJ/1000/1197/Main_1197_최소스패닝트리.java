package boj.m3w3_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
	
	static int[] parents;
	
	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int wei;
		public Edge(int v1, int v2, int wei) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.wei = wei;
		}
		@Override
		public int compareTo(Edge o) {
			return this.wei - o.wei;
		}
		
	}
	
	static void makeSet(int v) {
		for (int i = 1; i <= v; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int v) {
		if(parents[v] < 0) return v;
		
		return parents[v] = findSet(parents[v]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[E];
		parents = new int[V+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(v1, v2, w);
		}
		
		Arrays.sort(edges);
		makeSet(V);
		
		int cnt = 0;
		int res = 0;
		for (int i = 0; i < E; i++) {
			
			Edge e = edges[i];
			
			if(union(e.v1, e.v2)) {
				cnt++;
				res += e.wei;
			}
			
			if(cnt == V-1) break;
		}
		
		System.out.print(res);
		
	}

}
