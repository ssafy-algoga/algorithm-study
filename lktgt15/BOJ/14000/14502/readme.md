# 14502번 연구소
[문제 보러가기](https://www.acmicpc.net/problem/14502)

## 🅰 설계
조합을 섞은 전형적인 `Flood fill` 문제였습니다.  

### 1. 입력받기

```java
for(int i=0;i<n;i++) {
	st = new StringTokenizer(br.readLine());
	for(int j=0;j<m;j++) {
		map[i][j] = Integer.parseInt(st.nextToken());
		if(map[i][j] == 0) {
			safe++; // safe한 영역의 개수를 세어 놓음
			colList.add(new p(i,j)); // 기둥을 세울 수 있는 좌표 List
		}
		else if(map[i][j] == 2) {
			virusList.add(new p(i,j)); // 바이러스가 있는 좌표 List
		}
	}
}
```

`colList`에는 기둥을 세울 수 있는 빈 공간들의 좌표가 들어있고, `virusList`에는 바이러스가 위치한 곳의 좌표가 들어있습니다.  

`safe`에 미리 빈 공간을 계산해둬서 맵 전체를 다시 훑어보는 일이 없도록 합니다.  

### 2. 조합

```java
static void f(int idx,int cnt) {
	if(cnt == 3) {
		int cursafe = safe; // 앞서 세어놓은 safe를 가져옴
		for(p virus : virusList) {
			cursafe -= dfs(virus.y,virus.x); // virus 위치에서 dfs
		}
		ans = Math.max(ans, cursafe);
		
		reset();
		
		return;
	}
	
	for(int i=idx;i<colList.size();i++) {
        p col = colList.get(i);
		if(map[col.y][col.x] == 0) { // colList의 i번째 위치에 있는 좌표에 기둥을 세운적이 있는지 체크
			map[col.y][col.x] = 1;
			f(i+1,cnt+1);
			map[col.y][col.x] = 0;
		}
	}
}
```

마침 map의 크기가 8x8 = 64여서 long형으로 mask를 쓰면 딱 맞았습니다.  

mask로 빈 공간에 기둥을 세운적이 있는지 체크하고, 여전히 빈공간이면 기둥을 세워봅니다.  

지금 생각해보니 그냥 `map[col.y][col.x] == 0`으로 확인해도 될것 같네요  

`cnt` 는 기둥을 세운 개수이고 3이 되면 모든 `virus` 위치에서 dfs를 실행합니다.  

### 3. DFS

```java
static int dfs(int y,int x) {
	int ret = 0;
	for(int i=0;i<4;i++) {
		int nxty = y+dy[i];
		int nxtx = x+dx[i];
		if(isValid(nxty,nxtx)) {
			map[nxty][nxtx] = 3; // visit 체크 + 돌려놓기 쉽게 만듦
			ret += dfs(nxty,nxtx)+1;
		}
	}
	return ret;
}
```

`dfs`는 자신을 제외하고 상하좌우를 살펴보면서 갈 수 있으면 `return`값에 +1씩 더해서 리턴합니다.  

이때 바이러스가 퍼진 지역을 3으로 바꿔놓아 원래대로 되돌릴때 편하게 합니다.  

### 4. reset

```java
static void reset() {
	for(int i=0;i<n;i++) for(int j=0;j<m;j++) if(map[i][j] == 3) map[i][j] = 0;
}
```

`dfs`가 모두 끝나면 3으로 바꿔놨던 것들을 다시 빈 공간으로 돌려놓습니다.  

### 5. 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static List<p> colList = new ArrayList<>(), virusList = new ArrayList<>();
	static int[][] map = new int[8][8];
	static int[] dy = {1,-1,0,0}, dx = {0,0,1,-1};
	static int n,m,safe = -3, ans;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					safe++; // safe한 영역의 개수를 세어 놓음
					colList.add(new p(i,j)); // 기둥을 세울 수 있는 좌표 List
				}
				else if(map[i][j] == 2) {
					virusList.add(new p(i,j)); // 바이러스가 있는 좌표 List
				}
			}
		}
		
		f(0,0);
		System.out.println(ans);
	}
	
	static boolean isValid(int y,int x) {
		return y>=0 && y<n && x>=0 && x<m && map[y][x] == 0;
	}

	static void reset() {
		for(int i=0;i<n;i++) for(int j=0;j<m;j++) if(map[i][j] == 3) map[i][j] = 0;
	}
	
	static int dfs(int y,int x) {
		int ret = 0;
		for(int i=0;i<4;i++) {
			int nxty = y+dy[i];
			int nxtx = x+dx[i];
			if(isValid(nxty,nxtx)) {
				map[nxty][nxtx] = 3; // visit 체크 + 돌려놓기 쉽게 만듦
				ret += dfs(nxty,nxtx)+1;
			}
		}
		return ret;
	}
	
	static void f(int idx,int cnt) {
		if(cnt == 3) {
			int cursafe = safe;
			for(p virus : virusList) {
				cursafe -= dfs(virus.y,virus.x);
			}
			ans = Math.max(ans, cursafe);
			
			reset();
			
			return;
		}
		
		for(int i=idx;i<colList.size();i++) {
            p col = colList.get(i);
			if(map[col.y][col.x] == 0) { // colList의 i번째 위치에 있는 좌표에 기둥을 세운적이 있는지 체크
				map[col.y][col.x] = 1;
				f(i+1,cnt+1);
				map[col.y][col.x] = 0;
			}
		}
	}
	
	static class p{
		int y,x;
		public p(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
}
```

## ✅ 후기
처음엔 `BFS`로 시도했었는데, `DFS`와 비교해서 메모리, 시간 차이를 보니 `DFS`가 훨씬 좋은 성능을 보였습니다.  
큐에 넣고 빼는 작업이 오버헤드가 큰것같은데.. 자세히는 잘 모르겠네요  
둘 다 자연스럽게 쓰는것이 중요할 것 같습니다.  
