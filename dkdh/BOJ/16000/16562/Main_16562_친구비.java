package boj.m3w3_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16562_친구비 {
	
	static int[] parents;
	static int[] req;
	
	static void makeSet(int n) {
		for (int i = 1; i <= n; i++) {
			parents[i] = -1;
		}
	}
	
	static int find(int v) {
		if(parents[v] < 0) return v;
		
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		if(req[aRoot] <= req[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		req = new int[N+1];
		parents = new int[N+1];
		
		makeSet(N);
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			req[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			union(v, w);
		}
		
		int total = 0;
		for (int i = 1; i <= N; i++) {
			if(parents[i] < 0) total += req[i];
		}
		
		if(total <= K)
			System.out.print(total);
		else
			System.out.print("Oh no");
	}

}
