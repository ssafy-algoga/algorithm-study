# 14594번 동방 프로젝트 (Small)
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계
처음에 Small버전을 보고 Large버전이 어떤걸 요구할지 예상이 되서 Large버전으로 풀었습니다.  

입력을 보고 떠오른 방법은 두가지였습니다.  

### 1. 스위핑
*빅-종빈빌런*의 행동은 겹칠 수 있습니다.  

예를 들어 `1 6`을 한 후에 `3 5`를 다시 처리하는 것은 의미가 없습니다. 이미 `1 6`에서 `3 5`를 포함하여 벽을 부숴버렸으니까요.  

#### 1-1. 행동을 합칠 수 있을 때
*빅-종빈빌런*의 행동을 [l,r]이라고 하면 l 을 오름차순으로 정렬한 후 이 l이 이전의 r에 포함되면 하나의 행동으로 합쳐버릴 수 있습니다.  

```java
for(int i=0;i<=m;i++) {
	int curl = arr[i].l;
	int curr = arr[i].r;
	
	if(r < curl) { // 방이 하나가 될 수 없음( [...,r] - [...] - [curl,curr] )
		// ...
	}
	else r = Math.max(r, curr); // [...,r]에 [curl,curr]이 포함되면 하나로 합침
}
```

l을 오름차순으로 정렬했기 때문에 r의 범위는 계속해서 늘어날 수 있습니다. 그래서 하나의 행동으로 합쳐질 때 r은 더 큰범위로 확장됩니다.  

#### 1-2. 행동을 합칠 수 없을 때
```java
for(int i=0;i<=m;i++) {
	int curl = arr[i].l;
	int curr = arr[i].r;
	
	if(r < curl) { // 방이 하나가 될 수 없음( [...,r] - [...] - [curl,curr] )
		cnt += curl-r-1; // r 부터 curl 사이의 방의 개수
		cnt++; // 다음으로 하나가 되는 방  ( [curl,r] )
		r = curr; // 다음으로 하나가 되는 방의 끝 범위
	}
	else r = Math.max(r, curr); // [...,r]에 [curl,curr]이 포함되면 하나로 합침
}
```

방을 합칠 수 없다면 현재 curl보다 작고 r보다 큰 방들은 벽이 부숴지지 않으므로 해당 방의 개수를 계산해줍니다.  

그리고 다음으로 하나가 되는 방의 범위 [curl,r]은 하나가 될 것이므로 방의 개수를 하나 추가해줍니다.  

이렇게 끝내면 마지막 [l,r]의 범위가 N을 포함하지 않으면  r ~ N사이의 방은 계산하지 못하므로 [n,n]범위를 가지는 행동을 더미로 넣어줍니다.  

```java
p[] arr = new p[m+1]; // 종빈빌런의 행동
for(int i=0;i<m;i++) {
	st = new StringTokenizer(br.readLine());
	arr[i] = new p(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
}
arr[m] = new p(n,n); // 마지막 [curl,curr]을 처리하기 위해 dummy 넣음
```

#### 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		p[] arr = new p[m+1]; // 종빈빌런의 행동
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new p(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		arr[m] = new p(n,n); // 마지막 [curl,curr]을 처리하기 위해 dummy 넣음
		
		Arrays.sort(arr); // l을 기준으로 오름차순 정렬
		
		int r = 0;
		int cnt = 0;
		for(int i=0;i<=m;i++) {
			int curl = arr[i].l;
			int curr = arr[i].r;
			
			if(r < curl) { // 방이 하나가 될 수 없음( [...,r] - [...] - [curl,curr] )
				cnt += curl-r-1; // r 부터 curl 사이의 방의 개수
				cnt++; // 다음으로 하나가 되는 방  ( [curl,r] )
				r = curr; // 다음으로 하나가 되는 방의 끝 범위
			}
			else r = Math.max(r, curr); // [...,r]에 [curl,curr]이 포함되면 하나로 합침
		}
		System.out.println(cnt);
	}
	
	static class p implements Comparable<p>{
		int l,r;
		public p(int l,int r) {
			this.l = l;
			this.r = r;
		}
		@Override
		public int compareTo(p o) {
			return this.l-o.l;
		}
	}
}
```

### 2. 유니온 파인드
방이 합쳐진다 -> 같은 집합이 된다라고 생각하면 유니온 파인드를 생각할 수 있습니다.  

하지만 구체적인 방식이 떠오르지 않아서 위의 방법으로 푼 후 다른사람의 코드를 참고해서 풀었습니다.  

보통 유니온 파인드는 하나의 집합으로 만드는것에만 집중해서 `union(a,b)`와 `union(b,a)`의 순서가 상관이 없습니다.  

그러나 이 문제에서는 위에서 말씀드렸다시피 반복되는 행동은 할 필요가 없으므로 이걸 처리하는데 `union(a,b)`의 순서가 중요해집니다.  

유니온 파인드를 이용해서 `[l,r]`을 하나의 집합으로 만들 때, `i=parent[l] to r parent[i] = parent[r]`로 만들면 이 `[l,r]` 집합의 `parent`는 가장 오른쪽 범위인 `parent[r]`을 가리키게 됩니다.  

이렇게 모든 `[l,r]`을 합쳐주고 자신이 `parent`인 방의 개수를 세어주면 답이 됩니다.  

#### 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int[] parent = new int[1000001];
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int pa = find(a);
			int pb = find(b);
			for(int j=pa;j<=b;j++) {
				merge(pb,j);
			}
		}
		
		int cnt = 0;
		for(int i=1;i<=n;i++) {
			if(parent[i] == 0) cnt++;
		}
		System.out.println(cnt);
	}
	
	static int find(int x) {
		if(parent[x] == 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void merge(int pb,int j) {
		int pj = find(j);
		if(pb != pj) parent[pj] = pb;
	}
}
```



## ✅ 후기
유니온 파인드 문제라는걸 알아도 사용하기 까다로운 경우가 종종 있는데 이 문제가 살짝 그런종류였던 것 같습니다.  
1번 방법으로 풀 경우 정올 냉장고 문제와 비슷하다는 느낌을 받았습니다.  

