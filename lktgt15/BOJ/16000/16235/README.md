# 16235번 나무 재테크
[문제 보러가기](https://www.acmicpc.net/problem/16235)

## 🅰 설계
하라는 대로만 하면 되는 시뮬레이션 문제였습니다.

### 1. 입력 및 변수 선언

```java
static PriorityQueue<tree> trees = new PriorityQueue<>((e1,e2) -> e1.age - e2.age); // 현재 계산할 나무들
static PriorityQueue<tree> nxttrees = new PriorityQueue<>((e1,e2) -> e1.age - e2.age); // 다음 계산될 나무들
static List<tree> autumntrees = new ArrayList<>(); // 가을에 번식할 나무들
static List<tree> deadtrees = new ArrayList<>(); // 죽은 나무들
static int[][] map = new int[11][11],addtomap = new int[11][11]; // (r,c)의 양분, winter()에 추가될 양분
static int[] dy= {-1,-1,-1,0,0,1,1,1}, dx= {-1,0,1,-1,1,-1,0,1}; // 8방향 탐색
```

`trees`는 `spring()`에서 사용될 살아있는 나무들이 담깁니다.  

`nxttrees`는 `trees`에서 바로 `trees`에 나무들을 넣어줄 수 없기 때문에 임시로 저장하고 있을 저장소입니다.  

`autumntrees`는 `autumn()`에서 사용될 나이가 5의 배수인 나무들이 담깁니다.  

`deadtrees`는 `spring()`에서 죽은 나무들이 담깁니다.  

`map`은 현재 (r,c)에 존재하는 양분, `addtomap`은 `winter()`에 (r,c)에 추가될 양분입니다.  

```java
for(int i=1;i<=n;i++) {
	st = new StringTokenizer(br.readLine());
	for(int j=1;j<=n;j++) {
		addtomap[i][j] = Integer.parseInt(st.nextToken()); // winter()에 사용될 추가 양분
		map[i][j] = 5; // 맵의 초기 양분
	}
}
```

`addtomap[i][j]`에 `winter()`에 추가될 양분들이 담기고, `map[i][j]`는 초기 양분값인 5로 초기화합니다.  

### 2. 전체 흐름

```java
for(int i=0;i<k;i++) {
	spring();
	summer();
	autumn();
	winter();
}
```

k번 `spring()`,`summer()`,`autumn()`,`winter()`를 호출하게 됩니다.

### 3. spring()

```java
static void spring() {
	while(!trees.isEmpty()) {
		tree cur = trees.poll();
		if(map[cur.y][cur.x] >= cur.age) { // 나무가 있는 map[cur.y][cur.x]에 나무의 나이만큼 양분이 있으면
			map[cur.y][cur.x] -= cur.age;
			cur.age++;
			
			if(cur.age%5 == 0) { // 나무의 나이가 5의 배수이면 autumntrees에도 넣음
				autumntrees.add(cur);
			}
			nxttrees.add(cur); // 공통적으로 nxttrees에 넣음
		}
		else {
			deadtrees.add(cur); // 양분이 충분하지 않다면 deadtrees에 넣음
		}
	}
	while(!nxttrees.isEmpty()) { // 임시 저장소 nxttrees에서 trees로 옮김
		trees.offer(nxttrees.poll());
	}
	
}
```
`trees`에서 나이가 적은 순서대로 나무들을 꺼내서 `map[cur.y][cur.x]`의 양분을 확인하고, 자신의 나이보다 많으면 `map[cur.y][cur.x]`의 값을 그만큼 없애고 나이를 먹습니다.  

1. 나이가 5의 배수일 때 : `nxttrees`에 넣고 `autumntrees`에도 넣어줍니다.  
2. 나이가 5의 배수가 아닐 때 : `nxttrees`에만 넣습니다.  

`map[cur.y][cur.x]`에 자신의 나이만큼 양분이 없다면 `deadtrees`에 넣어줍니다.  

마지막으로 임시로 두었던 `nxttrees`에서 다시 `trees`로 옮겨줍니다.  

### 4. summer()

```java
static void summer() {
	for(tree cur : deadtrees) { // 모든 deadtrees에 있는 나무를 확인하여 map[cur.y][cur.x]에 나이/2만큼 양분을 늘려줌
		map[cur.y][cur.x] += cur.age/2; 
	}
	deadtrees = new ArrayList<>();
}
```

`spring()`에서 넣었던 `deadtree`를 이용하여 죽은 나무를 하나씩 꺼내서 `map[cur.y][cur.x]`에 자신의 나이/2만큼 양분을 늘려줍니다.  

그 다음 빈 리스트로 초기화합니다.  

### 5. autumn()

```java
static void autumn() {
	for(tree cur : autumntrees) { // 모든 autumntrees에 있는 나무를 확인하여 번식시킴
		int cy = cur.y;
		int cx = cur.x;
		for(int i=0;i<8;i++) {
			int ny = cy+dy[i];
			int nx = cx+dx[i];
			if(isValid(ny,nx)) { // 격자 범위를 넘어가지 않으면 trees에 넣음
				trees.offer(new tree(ny,nx,1));
			}
		}
	}
	autumntrees = new ArrayList<>();
}
```

`spring()`에서 넣었던 `autumntrees`를 이용하여 5의 배수인 나무들을 8방향을 탐색하여 격자 제한을 넘어가지 않으면 나이가 1인 나무를 추가합니다.  

### 6. winter()

```java
static void winter() {
	for(int i=1;i<=n;i++) for(int j=1;j<=n;j++) {
		map[i][j] += addtomap[i][j];
	}
}
```

입력으로 받았던 `addtomap[i][j]`만큼 `map[i][j]`에 추가하여 양분을 공급합니다.  

### 전체 코드

```java
package algoga.week6;

import java.io.*;
import java.util.*;

public class 나무재테크 {
	static BufferedReader br;
	static StringTokenizer st;

	static PriorityQueue<tree> trees = new PriorityQueue<>((e1,e2) -> e1.age - e2.age); // 현재 계산할 나무들
	static PriorityQueue<tree> nxttrees = new PriorityQueue<>((e1,e2) -> e1.age - e2.age); // 다음 계산될 나무들
	static List<tree> autumntrees = new ArrayList<>(); // 가을에 번식할 나무들
	static List<tree> deadtrees = new ArrayList<>(); // 죽은 나무들
	static int[][] map = new int[11][11],addtomap = new int[11][11]; // (r,c)의 양분, winter()에 추가될 양분
	static int[] dy= {-1,-1,-1,0,0,1,1,1}, dx= {-1,0,1,-1,1,-1,0,1}; 
	static int n,m,k;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				addtomap[i][j] = Integer.parseInt(st.nextToken()); // winter()에 사용될 추가 양분
				map[i][j] = 5; // 맵의 초기 양분
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x,y,z;
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			trees.offer(new tree(y,x,z));
		}
		
		for(int i=0;i<k;i++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		System.out.println(trees.size());
		
	}
	
	static void spring() {
		while(!trees.isEmpty()) {
			tree cur = trees.poll();
			if(map[cur.y][cur.x] >= cur.age) { // 나무가 있는 map[cur.y][cur.x]에 나무의 나이만큼 양분이 있으면
				map[cur.y][cur.x] -= cur.age;
				cur.age++;
				
				if(cur.age%5 == 0) { // 나무의 나이가 5의 배수이면 autumntrees에도 넣음
					autumntrees.add(cur);
				}
				nxttrees.add(cur); // 공통적으로 nxttrees에 넣음
			}
			else {
				deadtrees.add(cur); // 양분이 충분하지 않다면 deadtrees에 넣음
			}
		}
		while(!nxttrees.isEmpty()) { // 임시 저장소 nxttrees에서 trees로 옮김
			trees.offer(nxttrees.poll());
		}
		
	}
	
	static void summer() {
		for(tree cur : deadtrees) { // 모든 deadtrees에 있는 나무를 확인하여 map[cur.y][cur.x]에 나이/2만큼 양분을 늘려줌
			map[cur.y][cur.x] += cur.age/2; 
		}
		deadtrees = new ArrayList<>();
	}
	
	static void autumn() {
		for(tree cur : autumntrees) { // 모든 autumntrees에 있는 나무를 확인하여 번식시킴
			int cy = cur.y;
			int cx = cur.x;
			for(int i=0;i<8;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(isValid(ny,nx)) { // 격자 범위를 넘어가지 않으면 trees에 넣음
					trees.offer(new tree(ny,nx,1));
				}
			}
		}
		autumntrees = new ArrayList<>();
	}
	
	static void winter() {
		for(int i=1;i<=n;i++) for(int j=1;j<=n;j++) {
			map[i][j] += addtomap[i][j];
		}
	}
	
	static boolean isValid(int y,int x) {
		return y>=1 && y<=n && x>=1 && x<=n;
	}
	
	static class tree{
		int age,y,x;
		public tree(int y,int x,int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}
	}
}
```

## ✅ 후기
오늘 백준님 특강에서 말씀하셨던 (r,c) 확인을 안해서 틀렸던 문제입니다.
대충 생각하고 설계를 들어가서 가을에서 나무를 어떻게 빼야할지 조금 고민했었습니다.
역시 이런문제는 입력이나 제한을 꼼꼼히 읽고 설계를 한 후에 풀어야하는 것 같습니다.
