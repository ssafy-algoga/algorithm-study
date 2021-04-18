# 16562번 친구비
[문제 보러가기](https://www.acmicpc.net/problem/16562)

## 🅰 설계
"친구의 친구는 친구"이기 때문에 친구들을 하나의 집합으로 표현하고 그 중에서 친구비가 가장 싼 친구와 친구가 되면 친구비를 적게 사용할 수 있습니다.  

### 1-1. `find(int x)`
```java
static int find(int x) {
	if(parent[x] <= 0) return x;
	return parent[x] = find(parent[x]);
}
```
친구 집합을 확인하기 위한 `find(int x)`입니다. x라는 학생의 친구집합을 나타냅니다.  

### 1-2. `merge(int a,int b)`
```java
static boolean merge(int a,int b) {
	a = find(a);
	b = find(b);
	if(a == b) return false;
	parent[b] = a;
	return true;
}
```
친구의 친구를 친구로 만들기 위한 `merge(int a,int b)`입니다. a와 b가 다른 친구 집합을 가졌을 경우 b의 친구집합을 a에 포함시키고 true를 리턴하고, a와 b가 같은 친구집합을 가졌을 경우 false를 리턴합니다.  

### 2. 친구의 친구를 친구로 만들기
```java
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
```
a와 b가 친구가 되기 때문에 둘 중 더 적은 cost를 가졌던 친구 집합의 비용이 합쳐진 친구집합의 비용이 되도록 만들면 됩니다.  

### 3. 친구비 확인
```java
int ans = 0;

for(int i=1;i<=n;i++) {
	if(!chk[find(i)]) { // 모든 친구집합의 최소비용을 확인
		ans += cost[find(i)];
		chk[find(i)] = true;
	}
}
if(ans <= k) System.out.println(ans);
else System.out.println("Oh no");
```
각 학생들의 친구집합을 확인하면서 친구가 되지 않은 친구집합의 친구비를 지불하면 답이 됩니다.  


### 전체 코드

```java
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
```

## ✅ 후기
Union-Find 개념이 그럴듯한 현실의 문제에 적용되서 재밌게 풀었던것 같습니다.
