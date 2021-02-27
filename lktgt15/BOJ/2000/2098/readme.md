# 2098번 외판원 순회
[문제 보러가기](https://www.acmicpc.net/problem/2098)

## 🅰 설계

완전 탐색으로 풀리는 외판원 순회 문제에서, `비트마스킹 + 메모이제이션`만 추가하면 풀리는 문제였습니다.  

### 1. 입력
```java
for(int i=0;i<n;i++) {
	st = new StringTokenizer(br.readLine());
	Arrays.fill(dp[i], -1); // dp 배열 초기화
	for(int j=0;j<n;j++) {
		dist[i][j] = Integer.parseInt(st.nextToken());
	}
}

System.out.println(f(0,1));
```

`dp[i][j]`는 i도시에서 시작하여 visit 상태가 j일 때, 나머지 방문하지 않은 도시를 방문하고 다시 0번 도시로 돌아가는데 드는 가장 적은 비용이 됩니다.  

모든 도시를 순회하여야 하므로 어떤 도시에서 순회를 시작하여도 상관 없으므로 0번 도시를 시작점으로 정했습니다.

`dp`배열은 업데이트 된적이 없다는 것을 표시하기 위해 -1로 초기화해둡니다.

### 2. 완전 탐색
```java
static int f(int cur,int mask) {
	if(1<<n == mask+1) {
		if(dist[cur][0] != 0) return dist[cur][0];
		else return INF;
	}
	
	int ret = INF;
	for(int i=1;i<n;i++) {
		if((mask&1<<i) == 0 && dist[cur][i] != 0) {
			ret = Math.min(f(i,mask|1<<i)+dist[cur][i],ret);
		}
	}
	return ret;
}
```
`f(cur,mask)`는 **도시 cur에서 시작하여 visit 상태가 mask상태일 때, 나머지 방문하지 않은 도시를 방문하고 0번 도시로 돌아가는 최저 비용**을 리턴합니다.  

문제는 `f(cur,mask)`가 반복 호출된다는 것입니다.  

예를 들어 **0번 도시 -> 1번 도시 -> 2번도시 -> 3번도시**에서 3번도시에서 나머지 도시를 도는 상황은 **0번도시 -> 2번 도시 -> 1번도시 -> 3번도시**의 상황과 같습니다.  

위 `f(cur,mask)`에 적용해 보면 **도시 3번에서 시작하여 visit 상태가 0,1,2,3번 은 체크되었을 때, 나머지 방문하지 않은 도시를 방문하고 다시 0번 도시로 돌아가는 최저 비용**입니다.  

이렇게 반복되는 부분을 `dp[cur][mask]`에 기록해 두고 바로바로 리턴해주면 됩니다.  

### 3. 완전 탐색 -> 메모이제이션

```java
static int f(int cur,int mask) {
	if(~dp[cur][mask] != 0) return dp[cur][mask];
	if(1<<n == mask+1) {
		if(dist[cur][0] != 0) return dist[cur][0];
		else return INF;
	}
	dp[cur][mask] = INF;
	
	for(int i=1;i<n;i++) {
		if((mask&1<<i) == 0 && dist[cur][i] != 0) {
			dp[cur][mask] = Math.min(f(i,mask|1<<i)+dist[cur][i],dp[cur][mask]);
		}
	}
	return dp[cur][mask];
}
```

### 4. 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int[][] dist = new int[20][20];
	static int[][] dp = new int[16][1<<17];
	static int n,sum,INF = (int)1e8;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for(int j=0;j<n;j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(f(0,1));
	}
	
	static int f(int cur,int mask) {
		if(~dp[cur][mask] != 0) return dp[cur][mask];
		if(1<<n == mask+1) {
			if(dist[cur][0] != 0) return dist[cur][0];
			else return INF;
		}
		dp[cur][mask] = INF;
		
		for(int i=1;i<n;i++) {
			if((mask&1<<i) == 0 && dist[cur][i] != 0) {
				dp[cur][mask] = Math.min(f(i,mask|1<<i)+dist[cur][i],dp[cur][mask]);
			}
		}
		return dp[cur][mask];
	}
}
```

## ✅ 후기
정말 유명한 문제인데 유명한만큼 DP Masking을 적용해보기 좋은 문제인것 같습니다.  
