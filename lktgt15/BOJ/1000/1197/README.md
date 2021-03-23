# 1197번 최소 스패닝 트리
[문제 보러가기](https://www.acmicpc.net/problem/1197)

## 🅰 설계
문제 설명대로 그래프에서 모든 정점들을 연결하는 가중치의 합이 최소인 트리를 만들면 됩니다.  

이는 `Union-Find`로 간단하게 구할 수 있습니다.  

1. find, merge

```java
static int find(int x) {
		if(parent[x] <= 0) return x;
		return parent[x] = find(parent[x]);
	}
```
- `find(int x)`는 x의 부모, 같은 집합에 속한 정점 중 가장 위에있는 정점을 리턴합니다.  

```java
static boolean merge(int a,int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		parent[b] = a;
		return true;
	}
```
- `merge(int a,int b)`는 a와 b의 부모가 같지 않으면 = 같은 집합에 속해있지 않으면 b의 부모를 a로 만들고 true를 리턴하고, 같은 집합에 속해있으면 false를 리턴하는 함수입니다.  

간선 (start,end,cost) = (시작점,도착점,비용) 에 대해서 `merge(start,end)`가 true라면 두 정점이 하나의 집합이 되고 모든 정점을 하나의 집합을 만들기 위해서  
1. 비용이 적은 간선 순으로 `merge(start,end)`를 호출하여  
2. *v-1*번만 `merge(start,end)`가 true이면 v개의 정점이 하나의 집합이 됩니다.  

```java
PriorityQueue<p> pq = new PriorityQueue<>((p1,p2)->p1.cost-p2.cost);
for(int i=0;i<e;i++) {
	int start,end,cost;
	st = new StringTokenizer(br.readLine());
	start = Integer.parseInt(st.nextToken());
	end = Integer.parseInt(st.nextToken());
	cost = Integer.parseInt(st.nextToken());
	pq.add(new p(start,end,cost));
}
```
비용이 적은 간선을 순서대로 뽑기위해 PriorityQueue를 이용합니다.

```java
int ans = 0;
for(int i=0;i<v-1;i++) {
	while(true) {
		p cur = pq.poll();
		if(merge(cur.s,cur.e)) {
			ans += cur.cost;
			break;
		}
	}
}
System.out.println(ans);
```
PriorityQueue에서 간선을 하나씩 뽑으면서 *v-1번* `merge(start,end)`가 true를 반환하면 그 가중치를 합한 값이 최소 스패닝 트리가 됩니다.  

### 전체코드

```java
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
```

## ✅ 후기
Union-Find가 어렵지 않기 때문에 MST를 만드는것도 사실 그렇게 어렵진 않았던 것 같습니다.
