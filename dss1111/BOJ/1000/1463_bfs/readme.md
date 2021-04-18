# 1463번 1로 만들기
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계
저는 bfs로 풀면 되겠다는 생각을 했습니다.  
해당위치에 도달하는 최소연산횟수를 배열에 저장하고 이를 통해 연산횟수랑 탐색여부를 같이 체크합니다.  
```java
			if(now%3 == 0) {  //3으로 나누어 떨어지는경우
				if(value[now/3]==0) //최초 탐색
					value[now/3]=value[now]+1;
				else
					value[now/3]=Math.min(value[now/3], value[now]+1); //더 작은 값 저장
				q.add(now/3);
			}
```
3으로 나누어 떨어지는경우, 2로 나누어 떨어지는경우, 1을 빼는경우 모두 동일한 로직으로 코드를 작성했습니다. 
1. 탐색위치가 0이면 최초탐색이므로 바로 연산횟수 저장
2. 이미 탐색한 경우 더 작은 값 저장
3. 큐에 넣기
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int [] value;  //해당 위치까지 연산 횟수를 담는 배열
	static Queue<Integer> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		value = new int[num+1];
		q=new ArrayDeque<>();
		getResult(num);
	}
	public static void getResult(int start) {
		q.add(start);
		value[start]=0;
		int now;
		while(!q.isEmpty()) {
			now = q.poll();
			if(now==1) {  //1이면 결과출력
				System.out.println(value[now]);
				return;
			}
			if(now%3 == 0) {  //3으로 나누어 떨어지는경우
				if(value[now/3]==0) //최초 탐색
					value[now/3]=value[now]+1;
				else
					value[now/3]=Math.min(value[now/3], value[now]+1); //더 작은 값 저장
				q.add(now/3);
			}
			if(now%2 == 0) {  //2로 나누어 떨어지는 경우
				if(value[now/2]==0) //최초 탐색
					value[now/2]=value[now]+1;
				else
					value[now/2]=Math.min(value[now/2], value[now]+1); //더 작은 값 저장
				q.add(now/2);
			}
      /*아래는 1을 빼는 경우*/
			if(value[now-1]==0) //최초탐색
				value[now-1]=value[now]+1;
			else
				value[now-1]=Math.min(value[now-1], value[now]+1); //더 작은 값 저장
			q.add(now-1);
		}
	}
}

```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
큐를 linkedList로 만드는것보다 ArrayDeque로 만드는게 낫다고 하셔서 그렇게 해보았습니다.  

### 고생한 점
없습니다.

숨바꼭질 문제들을 풀고나니 비슷한 방법으로 풀게되는 것 같습니다. 어쩌면 생각의 폭이 좁아지는건 아닌지 걱정이 되네요 다른 분들 푼 방법도 구경해봐야겠습니다.  
