# 2573번 빙산
[문제 보러가기](https://www.acmicpc.net/problem/2573)

## 🅰 설계
일단 처음에 문제를 쪼개는 것 부터 생각했습니다.
```java
 		while(ice!=0) {//빙산이 있으면 루프
			year++;//1년증가
			melt();//녹이기
			if(countIce()>=2) {//빙산그룹이 2개이상이면
				System.out.println(year);
				return;
			}
		}
		System.out.println(0);//끝까지 둘로 안나뉘면 0출력
```
주석 그대로입니다. 얼음을 녹이고 그룹이 몇개 나오는지 체크하는 식으로 구성했습니다.  
```java
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
				if(map[n][m]!=0)	//빙산이면
					ice++;	//카운트
			}
		}
```
루프문을 조건을 어떻게 넣는 것이 좋을까 생각하다가 처음 상태를 입력받을 때 빙산수를 카운트해서 빙산이 있을때만 돌도록 했습니다.  
```java
	static void melt() {
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]!=0) {
					int count=0;
					if(isSea(n-1,m)) count++;
					if(isSea(n+1,m)) count++;
					if(isSea(n,m-1)) count++;
					if(isSea(n,m+1)) count++;
					map[n][m]-=count;
					if(map[n][m]<=0)
						map[n][m]=-1; //녹을 빙산을 -1로처리 바로 0으로 처리하면 다른 빙산에 영향을줌
				}
			}
		}
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]==-1) {
					map[n][m]=0; //녹이기
					ice--;
				}
			}
		}
	}
```
빙산을 녹이는 부분입니다. 치즈랑 다르게 가운데 공간같은거 생각안해서 편했습니다. 
melt 이해를 위해 isSea를 먼저 봐야합니다.  
```java
	static boolean isSea(int n,int m) {
		if(map[n][m]==0)
			return true;
		else 
			return false;
	}
```
0 = 바다면 true 그외엔 false를 반환합니다.  

이를 통해 melt에서 녹을 빙산을 0으로 바로처리하면 다른 빙산들 탐색할때 바다로 처리되기 때문에 바다가 아닌것으로 처리되는 -1로 두었어요. 
이번에 녹을 빙산 -1로 체크한 뒤 루프를 다돌면 그제서야 빙산을 녹이고 남은 빙산수를 업데이트 합니다.  

```java
	static int countIce() {
		visit = new boolean[N][M];
		int count = 0;
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]!=0 && !visit[n][m]) { //방문하지 않은 빙산을만나면
					count++; // 그룹수 1추가
					group(n,m); // 그룹모두 체크
				}
			}
		}
		return count;
	}
	static void group(int n,int m) {
		visit[n][m]=true;
		//사방을 보면서 빙산이고 미방문이면 체크
		if(!isSea(n-1,m)&&!visit[n-1][m])group(n-1,m);
		if(!isSea(n+1,m)&&!visit[n+1][m])group(n+1,m);
		if(!isSea(n,m-1)&&!visit[n][m-1])group(n,m-1);
		if(!isSea(n,m+1)&&!visit[n][m+1])group(n,m+1);
	}
```
순차적으로 배열을 돌면서 빙산이면 bfs를 통해 해당그룹을 체크합니다. 이를 통해 countIce는 그룹의 수를 반환하게 됩니다.

## 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M,N;
	static int [][] map;
	static boolean [][] visit;
	static int ice; //남은 빙산수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
				if(map[n][m]!=0)	//빙산이면
					ice++;	//카운트
			}
		}
		int year=0;
		while(ice!=0) {
			year++;
			melt();
			if(countIce()>=2) {
				System.out.println(year);
				return;
			}
		}
		System.out.println(0);
	}
	static void melt() {
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]!=0) {
					int count=0;
					if(isSea(n-1,m)) count++;
					if(isSea(n+1,m)) count++;
					if(isSea(n,m-1)) count++;
					if(isSea(n,m+1)) count++;
					map[n][m]-=count;
					if(map[n][m]<=0)
						map[n][m]=-1; //녹을 빙산을 -1로처리 바로 0으로 처리하면 다른 빙산에 영향을줌
				}
			}
		}
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]==-1) {
					map[n][m]=0; //녹이기
					ice--;
				}
			}
		}
	}
	static int countIce() {
		visit = new boolean[N][M];
		int count = 0;
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]!=0 && !visit[n][m]) { //방문하지 않은 빙산을만나면
					count++; // 그룹수 1추가
					group(n,m); // 그룹모두 체크
				}
			}
		}
		return count;
	}
	static void group(int n,int m) {
		visit[n][m]=true;
		//사방을 보면서 빙산이고 미방문이면 체크
		if(!isSea(n-1,m)&&!visit[n-1][m])group(n-1,m);
		if(!isSea(n+1,m)&&!visit[n+1][m])group(n+1,m);
		if(!isSea(n,m-1)&&!visit[n][m-1])group(n,m-1);
		if(!isSea(n,m+1)&&!visit[n][m+1])group(n,m+1);
	}
	static boolean isSea(int n,int m) {
		if(map[n][m]==0)
			return true;
		else 
			return false;
	}
}
```

## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
수업시간에 풀었던 치즈문제랑 비슷하다고 생각했습니다.  

### 고생한 점
난이도 자체는 평이한 것 같은데 구현에 시간이 걸릴수 밖에 없었습니다.  


