# 17070번 파이프 옮기기 1
[문제 보러가기](https://www.acmicpc.net/problem/17070)

## 🅰 설계
파이프가 놓여진 방향에 따라서 다음에 놓을 수 있는 위치가 정해지고, 이를 모두 놓아보는 방법을 생각해 볼 수 있습니다.

### 1. type에 따라 이용할 배열 선언

```java
static int[][] map = new int[32][32]; // 벽을 체크해줄 맵
static int[] dy= {0,1,1}, dx= {1,1,0}; // 타입에 따른 다음 파이프를 놓을 지점
static int n; // 전체 격자판 규격
static boolean[][] validtype = {{true,true,false},{true,true,true},{false,true,true}}; // 타입에 따른 다음으로 놓을 파이프 타입이 유효한지 체크
```
저는 0 = 가로, 1 = 대각선, 2 = 세로로 정하였습니다.  

0에서는 0과 1로, 1에서는 0과 1과 2로, 2에서는 1,2로 갈 수 있기 때문에 각각 이에 맞는 valid 배열과 dy,dx배열을 선언하였습니다.  

### 2. 전체 경우의 수 탐색

```java
static long f(int cy,int cx,int type) {
	if(cy == n-1 && cx == n-1) return 1;
	long ret = 0;
	
	for(int i=0;i<3;i++) {
		int ny = cy+dy[i];
		int nx = cx+dx[i];
		if(isValid(ny,nx,i) && validtype[type][i]) { // 다음 파이프가 놓일 지점이 유효한지 검사
			ret += f(ny,nx,i); // 다음 파이프를 놓고 경우의 수 계산
		}
	}
	
	return ret;
}

static boolean isValid(int cy,int cx,int type) {
	return cy < n && cx < n && map[cy][cx] == 0 && (type == 1 ? map[cy-1][cx] == 0 && map[cy][cx-1] == 0 : true);
}

```
`f(cy,cx,type)`은 **(cy,cx)에서 파이프의 현재 형태가 type일 때, (n-1,n-1)로 이동시키는 방법의 수**가 됩니다.  

`isValid(cy,cx,type)`에서는 다음 (y,x)가 맵의 범위를 넘는지와 타입에 따라 벽이 존재하여 갈 수 있는지 없는지 체크합니다.  

보통 저는 `isValid(cy,cx)`로 맵의 범위를 넘는지만 체크하는데 여기서는 type을 넣어주는게 더 깔끔해서 type까지 넣어서 체크하였습니다.  

실제로 경우의 수를 따라가보면 `f(cy,cx,type)`이 반복호출 된다는 것을 알 수 있습니다.  

메모이제이션을 적용해주면 시간을 단축시킬 수 있습니다.  

### 3. 전체 경우의 수 탐색 + 메모이제이션

```java
static long f(int cy,int cx,int type) {
	long ret = dp[cy][cx][type]; // 현재 좌표,type을 memo
	if(ret != -1) return ret;
	if(cy == n-1 && cx == n-1) return dp[cy][cx][type] = 1;
	ret = 0;
	
	for(int i=0;i<3;i++) {
		int ny = cy+dy[i];
		int nx = cx+dx[i];
		if(isValid(ny,nx,i) && validtype[type][i]) { // 다음 파이프가 놓일 지점이 유효한지 검사
			ret += f(ny,nx,i); // 다음 파이프를 놓고 경우의 수 계산
		}
	}
	
	return dp[cy][cx][type] = ret;
}
```

### 전체 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static long[][][] dp = new long[32][32][3]; // 메모용 dp 배열
	static int[][] map = new int[32][32]; // 벽을 체크해줄 맵
	static int[] dy= {0,1,1}, dx= {1,1,0}; // 타입에 따른 다음 파이프를 놓을 지점
	static int n; // 전체 격자판 규격
	static boolean[][] validtype = {{true,true,false},{true,true,true},{false,true,true}}; // 타입에 따른 다음으로 놓을 파이프 타입이 유효한지 체크
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				Arrays.fill(dp[i][j], -1);
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(f(0,1,0));
	}
	
	static long f(int cy,int cx,int type) {
		long ret = dp[cy][cx][type]; // 현재 좌표,type을 memo
		if(ret != -1) return ret;
		if(cy == n-1 && cx == n-1) return dp[cy][cx][type] = 1;
		ret = 0;
		
		for(int i=0;i<3;i++) {
			int ny = cy+dy[i];
			int nx = cx+dx[i];
			if(isValid(ny,nx,i) && validtype[type][i]) { // 다음 파이프가 놓일 지점이 유효한지 검사
				ret += f(ny,nx,i); // 다음 파이프를 놓고 경우의 수 계산
			}
		}
		
		return dp[cy][cx][type] = ret;
	}
	
	static boolean isValid(int cy,int cx,int type) {
		return cy < n && cx < n && map[cy][cx] == 0 && (type == 1 ? map[cy-1][cx] == 0 && map[cy][cx-1] == 0 : true);
	}
}
```


## ✅ 후기
브루트포스 + 메모이제이션으로 해결 가능하기 때문에 연습하기 좋은 문제였던것 같습니다.
