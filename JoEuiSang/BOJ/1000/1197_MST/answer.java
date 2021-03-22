package 스터디._3월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1197최소스패닝트리_크루스칼 {
	static int V, E, answer;
	static Edge[] edgeList;
	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		parents = new int[V+1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}

		Arrays.sort(edgeList);

		make();
		
		int count = 0;

		for (Edge e : edgeList) {
			if (union(e.from, e.to)) {
				answer += e.weight;
				if (count++ == V - 1)
					break;
			}
		}

		System.out.println(answer);

	}

	static void make() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	static int findRoot(int edge) {
		if (parents[edge] == edge)
			return edge;

		return parents[edge] = findRoot(parents[edge]);
	}

	static boolean union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		
		if(aRoot == bRoot) 
			return false;
			
		parents[bRoot] = aRoot;
		return true;
	}

}
