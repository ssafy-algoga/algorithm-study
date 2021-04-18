# 1197 최소 스패닝 트리
[문제 보러가기](https://www.acmicpc.net/problem/1197)

## 🅰 설계
1. 간선의 개수가 최대 10만개이기 때문에 간선을 이용하여 그래프를 생성하였습니다.
2. 작은 간선을 우선적으로 선택해야하기때문에 간선리스트를 오름차순 정렬하였습니다.
3. 간선을 선택하는 과정에서 싸이클을 발생시키지 않아야하기 때문에 선택 로직에 union-find를 이용하였습니다.
4. (최소신장트리 알고리즘 중 하나인 크루스칼 알고리즘)




## 주요 코드 설명
### Edge : 간선의 정보를 나타는 class
```java
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
        return this.weight - o.weight;	//오름차순 정렬, 내림차순은 o-this
    }
}
```




### make() : 각 노드의 단위집합 생성

```java
static void make() { // 크기가 1인 단위집합을 만든다
    for (int i = 0; i < V; i++) {
        parents[i] = i;
    }
}
```



### findSet() : 노드가 속한 그룹의 대표자를 반환

```java
static int findSet(int a) {
    if (parents[a] == a)
        return a;
    return parents[a] = findSet(parents[a]); // path compression 후
}
```



### union() : 그룹이 다른 서로다른 노드를 합친다

```java
static boolean union(int a, int b) {
    int aRoot = findSet(a);
    int bRoot = findSet(b);

    if (aRoot == bRoot)
        return false;

    parents[bRoot] = aRoot;
    return true;
}
```

### 전체 소스

```java
public class MST1_KruskalTest {
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
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int V, E, parents[];
	static Edge[] edgeList;

	static void make() { // 크기가 1인 단위집합을 만든다
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]); // path compression 후
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parents = new int[V];
		edgeList = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}

		// 간선리스트 가중치 기준 오름차순 정렬
		Arrays.sort(edgeList);

		make();

		int result = 0;
		int count = 0;// 선택한 간선수

		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {// 싸이클이 발생하지 않았다면
				result += edge.weight;
				if (++count == V - 1)
					break;
			}
		}
		System.out.println(result);
	}
}
```

## ✅ 후기
### 수업시간에 배운 직후에 풀었던 문제라 쉽게 풀 수 있었지만 푼 시점과 리뷰를 작성하는 시점이 차이가 좀 있다보니 가물가물했던 내용을 다시 한 번 복습하며 이해할 수 있었습니다.