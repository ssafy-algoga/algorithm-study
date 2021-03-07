# 14594번 동방프로젝트(small)
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계
아주 간단한 문제였던 것 같습니다.
시작지점부터 끝지점이 주어지고 그사이의 벽을 허물기 때문에 저는
1. 방갯수 입력받기
2. 방갯수 만큼 벽만들기
3. 시작지점 ~ 끝지점 사이의 벽허물기
4. 다돌고나서 벽세기
이렇게 문제를 푸는 것을 생각해보았습니다.  

처음에는 비트로 풀 수 있지않을까 했는데 방이 100개니까 int로는 불가능하고. 방갯수가 더 작았다면 비트마스킹 연습을 할 수 있었을텐데 아쉽습니다.    

```java
		for(int i=start-1;i<end-1;i++) {
			wall[i] = false;
		}
```
방번호가 1번부터인데 배열은 0번 부터라 인덱스에 -1을 해주었습니다.   
### 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean [] wall;
	static int room;
	static int start,end;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		room = Integer.parseInt(br.readLine());
		wall = new boolean[room];
		init();
		int act = Integer.parseInt(br.readLine());
		for(int a=0;a<act;a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			crush();
		}
		System.out.println(count());
	}
	static void init() { //벽 초기화
		for(int i=0;i<room;i++) {
			wall[i]=true;
		}
	}
	static void crush() {//start부터 end까지 벽을 false로 바꿈
		for(int i=start-1;i<end-1;i++) {
			wall[i] = false;
		}
	}
	static int count() { //벽 갯수 카운트
		int count=0;
		for(int i=0;i<room;i++) {
			if(wall[i]==true)
				count++;
		}
		return count;
	}
}
```

## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
없습니다.  
### 고생한 점
없습니다.
