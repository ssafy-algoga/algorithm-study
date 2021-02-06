# 11404 플로이드
[문제 보러가기](https://www.acmicpc.net/submit/11404/26017749)

## 🅰 설계
알고리즘이 정해져있어서 다른 분들과 별다를 것은 없습니다.  
인접행렬과 인접리스트중 구현이 덜 복잡한 인접행렬로 표현했습니다.  
  
  
``
	static final int MAX = Integer.MAX_VALUE/2; //매우 큰 값, 오버플로 안나도록 2를 나누어 줌
``  
초기값으로 매우 큰 값을 주기위해서 Integer.MAX_VALUE를 사용했는데 오버플로우가 나서 2를 나누어 주었습니다.  
```
		for(int i=1;i<=city;i++) {
			for(int j=1;j<=city;j++) {
				if(i==j) {
					edge[i][j] = 0;	//a도시에서 출발해서 a도시에 도착하는 경우 0처리
					continue;
				}
				edge[i][j] = MAX;	//그렇지 않으면 매우 큰 값으로 초기화
			}
		}
```  
저는 이중for문 돌면서 0과 MAX를 초기화 해주었습니다.  

### 전체코드
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [][] edge;
	static final int MAX = Integer.MAX_VALUE/2; //매우 큰 값, 오버플로 안나도록 2를 나누어 줌
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int city = Integer.parseInt(br.readLine());
		int bus = Integer.parseInt(br.readLine());
		edge = new int[city+1][city+1];
		for(int i=1;i<=city;i++) {
			for(int j=1;j<=city;j++) {
				if(i==j) {
					edge[i][j] = 0;	//a도시에서 출발해서 a도시에 도착하는 경우 0처리
					continue;
				}
				edge[i][j] = MAX;	//그렇지 않으면 매우 큰 값으로 초기화
			}
		}
		StringTokenizer st;
		for(int i=0;i<bus;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());	//시작도시
			int end=Integer.parseInt(st.nextToken());	//도착도시
			int cost = Integer.parseInt(st.nextToken());//비용
			edge[start][end] = Math.min(edge[start][end], cost); //더 적은비용을 넣어줌
		}
		//플로이드
		for(int m=1;m<=city;m++) {
			for(int i=1;i<=city;i++) {
				for(int j=1;j<=city;j++) {
					edge[i][j] = Math.min(edge[i][j],edge[i][m]+edge[m][j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder(); //결과출력용
		for(int i=1;i<=city;i++) {
			for(int j=1;j<=city;j++) {
				if(edge[i][j] == MAX) {	// 길없음
					sb.append("0");
					sb.append(" ");
				}
				else {
					sb.append(edge[i][j]); 
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
```

## ✅ 후기 
### 새롭게 알게되거나 공유해서 알게된 점
플로이드 와샬 알고리즘을 알게되었습니다.  

### 고생한 점
플로이드 쓸때 인덱스를 i,j,k순서로 썼다가 헷갈려서 m,i,j순서로 변경했습니다.
