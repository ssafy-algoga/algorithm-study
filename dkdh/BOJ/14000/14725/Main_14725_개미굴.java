package boj.m3w3_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_14725_개미굴 {
	
	static class Node {
		String value;
		TreeMap<String, Node> children = new TreeMap<String, Node>();
		
		public Node(String value) {
			super();
			this.value = value;
		}
		
	}
	
	static StringBuilder sb = new StringBuilder();
	static Node root;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(in.readLine());
		
		root = new Node("");
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			
			Node temp = root;			
			for (int j = 0; j < K; j++) {
				String info = st.nextToken();
				
				if(!temp.children.containsKey(info)) {
					temp.children.put(info, new Node(info));
				}
				temp = temp.children.get(info);
			}
		}
		
		dfs(root, -1);
		
		System.out.print(sb.toString());
	}

	private static void dfs(Node cur, int cnt) {
		if(cnt >= 0) {
			for (int i = 0; i < cnt; i++) {
				sb.append("--");
			}
			sb.append(cur.value).append("\n");
		}
		
		for (Node node : cur.children.values()) {
			dfs(node, cnt+1);
		}
	}

}
