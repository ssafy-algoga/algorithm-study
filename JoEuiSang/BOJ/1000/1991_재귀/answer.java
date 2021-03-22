package _3월_1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int root, left, right;

	public Node(int root, int left, int right) {
		super();
		this.root = root;
		this.left = left;
		this.right = right;
	}

}

public class Boj1991트리순회 {
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		Node[] tree = new Node[32];

		tree[1] = new Node(1, 2, 3);
		for (int tc = 1; tc <= N; tc++) {
			st = new StringTokenizer(br.readLine());
			int idx = st.nextToken().charAt(0) - 'A' + 1; // . : -18 A~Z : 1~26
			int left = st.nextToken().charAt(0) - 'A' + 1;
			int right = st.nextToken().charAt(0) - 'A' + 1;

			tree[idx] = new Node(idx, left, right);

		}
		preOrder(tree, 1);
		System.out.println(sb);
		sb.setLength(0);
		inOrder(tree, 1);
		System.out.println(sb);
		sb.setLength(0);
		postOrder(tree, 1);
		System.out.println(sb);
	}

	// 전위 : 루트가 먼저
	public static void preOrder(Node[] tree, int idx) {
		int left = tree[idx].left;
		int right = tree[idx].right;

		if (idx < 32) {
			char a = (char) (tree[idx].root + 'A' - 1);
			sb.append(a);

			if (left > 0) {// 왼쪽자식이 있으면
				preOrder(tree, left);
			}

			if (right > 0) {// 오른쪽 자식이 있으면
				preOrder(tree, right);
			}
		} else {
			return;
		}

	}

	// 후위
	public static void postOrder(Node[] tree, int idx) {
		int left = tree[idx].left;
		int right = tree[idx].right;


		if (right < 32) { // 트리범위 안에있으면
			if (left > 0) {// 왼쪽자식이 있으면
				postOrder(tree, left);
			}

			if (right > 0) {// 오른쪽 자식이 있으면
				postOrder(tree, right);
			}

			sb.append((char) (tree[idx].root + 'A' - 1));
		} else {
			return;
		}
	}

	// 중위 : root가 중간
	public static void inOrder(Node[] tree, int idx) {
		int left = tree[idx].left;
		int right = tree[idx].right;


		if (right < 32) { // 트리범위 안에있으면
			if (left > 0) {// 왼쪽자식이 있으면
				inOrder(tree, left);
			}

			sb.append((char) (tree[idx].root + 'A' - 1));

			if (right > 0) {// 오른쪽 자식이 있으면
				inOrder(tree, right);
			}
		} else {
			return;
		}
	}

}
