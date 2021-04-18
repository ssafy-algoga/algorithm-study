# 14725번 개미굴
[문제 보러가기](https://www.acmicpc.net/problem/14725)

## 🅰 설계
정보를 입력받아 트리를 구성하고   
구성한 트리를 정해진 출력 양식으로 출력하는 문제입니다.

개미가 주는 정보는 루트부터 리프노드까지의 경로이므로 쭉 따라가며 없는 노드는 만들어주는 식으로 구현했습니다.

트리를 구성하는 노드는 아래와 같습니다.
```java
static class Node {
  String value;
  TreeMap<String, Node> children = new TreeMap<String, Node>();

  public Node(String value) {
    super();
    this.value = value;
  }

}
```
나중에 사전식으로 출력해줘야 하기 때문에 순서를 유지할 수 있는 TreeMap을 사용했습니다.

개미가 준 경로를 따라가며 없는 노드는 만들어주는 로직입니다.
```java
Node temp = root;
for (int j = 0; j < K; j++) {
  String info = st.nextToken();

  if(!temp.children.containsKey(info)) {
    temp.children.put(info, new Node(info));
  }
  temp = temp.children.get(info);
}
```

트리를 모두 구성한 후, dfs로 트리를 탐색하면서 출력합니다.
```java
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
```
개미굴 입구인 root는 출력할 값이 없기 때문에 출력하지 않도록 합니다. 깊이에 따라 "--" 를 붙여야 하기 때문에 cnt 변수를 활용했습니다.

### 코드
```java
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

```

## ✅ 후기
