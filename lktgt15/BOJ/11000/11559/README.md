# 11559번 Puyo Puyo
[문제 보러가기](https://www.acmicpc.net/problem/11559)

## 🅰 설계
문제에서 시키는 대로 시뮬레이션을 돌리면 되는 문제였습니다.  


### 1. 싸이클
```java
while(play()) {
	ans++;
	move();
	reset();
};
System.out.println(ans);
```
`play()`는 블럭이 연쇄될 수 있는지 체크하고, 터지는 블럭이 있으면 true, 없으면 false를 리턴하는 함수입니다. 동시에 터질 수 있는 블럭은 터뜨립니다.  

`move()`는 블럭이 터진 후 남은 블럭을 아래쪽으로 정렬할 때 사용됩니다.
`reset()`은 탐색할 때 사용했던 boolean배열을 초기화하는데 사용합니다.  

### 2. play()

```java
static boolean play() { // 터지는 블럭이 있는지 체크하고 터뜨림
	boolean ret = false;
	
	for(int i=0;i<12;i++) {
		for(int j=0;j<6;j++) {
			if(map[i][j] != '.' && chk1[i][j] == false) { // i,j위치의 블럭을 체크
				chk1[i][j] = true;
				if(chain(i,j) < 4) continue; // i,j위치의 블럭과 같은 블럭이 4개 이상 인접한 블럭이 있는지 체크
				ret = true;
				chk2[i][j] = true;
				boom(i,j); // 4개 이상이면 터뜨림
			}
		}
	}
	
	return ret;
}
```
맵을 확인하고 터지는 블럭이 있으면 터뜨립니다.  


### 3. chain(int y,int x)

```java
static int chain(int y,int x) { // 같은 블럭이 상,하,좌,우로 몇 개 있는지 체크
	int ret = 1;
	for(int i=0;i<4;i++) {
		int nxty = y+dy[i];
		int nxtx = x+dx[i];
		if (isValid(nxty,nxtx) && !chk1[nxty][nxtx] && map[nxty][nxtx] == map[y][x]) {
			chk1[nxty][nxtx] = true;
			ret += chain(nxty,nxtx);
		}
	}
	return ret;
}
```
상,하,좌,우로 연결된 블럭이 몇개인지 리턴합니다.  

### 4. boom(int y,int x)

```java
static void boom(int y,int x) { // 블럭을 터뜨림
	for(int i=0;i<4;i++) {
		int nxty = y+dy[i];
		int nxtx = x+dx[i];
		if(isValid(nxty,nxtx) && !chk2[nxty][nxtx] && map[nxty][nxtx] == map[y][x]) {
			chk2[nxty][nxtx] = true;
			boom(nxty,nxtx);
		}
	}
	map[y][x] = '.';
}
```
`chain(int y,int x)`에서 4개이상 연결된 블럭이 확인되면 그 블럭들을 터뜨립니다.  

### 5. reset()

```java
static void reset() { // chk1, chk2배열을 초기화
	for(int i=0;i<12;i++) for(int j=0;j<6;j++) {
		chk1[i][j] = false;
		chk2[i][j] = false;
	}
}
```
`chain(int y,int x)`와 `boom(int y,int x)`에서 사용한 boolean배열을 초기화합니다.  

### 전체코드
```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static char[][] map = new char[12][]; // 맵
	// chk1 은 블럭이 터지는지 체크할 때, chk2는 블럭을 터뜨려 없앨때 사용
	static boolean[][] chk1 = new boolean[12][6],chk2 = new boolean[12][6];
	static int[] dy = {1,-1,0,0}, dx = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<12;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int ans = 0;
		while(play()) {
			ans++;
			move();
			reset();
		};
		System.out.println(ans);
	}
	
	static void move() { // 맵에서 블럭을 터뜨린 후 블럭을 내릴 때 사용
		for(int j=0;j<6;j++) {
			int idx = 11;
			for(int i=11;i>=0;i--) {
				if(map[i][j] != '.') {
					char tmp = map[i][j];
					map[i][j] = '.';
					map[idx--][j] = tmp;
				}
			}
		}
	}
	
	static void reset() { // chk1, chk2배열을 초기화
		for(int i=0;i<12;i++) for(int j=0;j<6;j++) {
			chk1[i][j] = false;
			chk2[i][j] = false;
		}
	}
	
	static boolean play() { // 터지는 블럭이 있는지 체크하고 터뜨림
		boolean ret = false;
		
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(map[i][j] != '.' && chk1[i][j] == false) { // i,j위치의 블럭을 체크
					chk1[i][j] = true;
					if(chain(i,j) < 4) continue; // i,j위치의 블럭과 같은 블럭이 4개 이상 인접한 블럭이 있는지 체크
					ret = true;
					chk2[i][j] = true;
					boom(i,j); // 4개 이상이면 터뜨림
				}
			}
		}
		
		return ret;
	}
	
	static void boom(int y,int x) { // 블럭을 터뜨림
		for(int i=0;i<4;i++) {
			int nxty = y+dy[i];
			int nxtx = x+dx[i];
			if(isValid(nxty,nxtx) && !chk2[nxty][nxtx] && map[nxty][nxtx] == map[y][x]) {
				chk2[nxty][nxtx] = true;
				boom(nxty,nxtx);
			}
		}
		map[y][x] = '.';
	}
	
	static boolean isValid(int y,int x) { // 범위 체크
		return y>=0 && y<12 && x>=0 && x<6;
	}
	
	static int chain(int y,int x) { // 같은 블럭이 상,하,좌,우로 몇 개 있는지 체크
		int ret = 1;
		for(int i=0;i<4;i++) {
			int nxty = y+dy[i];
			int nxtx = x+dx[i];
			if (isValid(nxty,nxtx) && !chk1[nxty][nxtx] && map[nxty][nxtx] == map[y][x]) {
				chk1[nxty][nxtx] = true;
				ret += chain(nxty,nxtx);
			}
		}
		return ret;
	}
}
```


## ✅ 후기
시뮬레이션을 연습하기 좋은 문제였습니다.
