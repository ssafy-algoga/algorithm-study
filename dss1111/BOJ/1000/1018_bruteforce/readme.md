# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계
저는 W로 시작하는 체스판을 answer1, B로시작하는 체스판을 answer2라는 2차원 char배열로 만들었습니다.  
c로 코딩할때 이번처럼 미리 값을 넣어놓고 비교했던게 편했던 기억이나서 똑같은 방식으로 해보았습니다.     
```java
	static char [][] answer1 = {{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'}}; 
	static char [][] answer2 = {{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'}}; 
```
비교할 보드크기인 8을 뺀 만큼의 길이로 모든 체스판을 비교합니다. getResult에 넣는 것은 시작좌표입니다.
```java
		for(int i=0;i<=N-8;i++) {
			for(int j=0;j<=M-8;j++) {
				getResult(i,j);
			}
		}
```
getResult에서는 answer1과 answer2를 보드판과 비교해서 더 적게 칠하는 경우를 결과로 얻습니다.  
### 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char [][] board;
	static char [][] answer1 = {{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'}}; 
	static char [][] answer2 = {{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'}}; 
	static int N,M;
	static int result=987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board= new char[N][M];
		for(int i=0;i<N;i++) {
			board[i]=br.readLine().toCharArray();
		}
		for(int i=0;i<=N-8;i++) {
			for(int j=0;j<=M-8;j++) {
				getResult(i,j);
			}
		}
		System.out.println(result);
	}
	public static void getResult(int y,int x) {
		int count1=0; //1번 체스판과 비교한 결과
		int count2=0; //2번 체스판과 비교한 결과
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[y+i][x+j]!=answer1[i][j])count1++; 
				if(board[y+i][x+j]!=answer2[i][j])count2++;
			}
		}
		result = Math.min(result, Math.min(count1, count2));
	}
}
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
없습니다.
### 고생한 점
없습니다.
