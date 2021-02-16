# 13549번 숨바꼭질3
[문제 보러가기](https://www.acmicpc.net/problem/13549)

## 🅰 설계
```java
static int [] time=new int[SIZE]; // time[5] -> 위치 5로 갈때 걸리는 최소시간
```
저번 숨바꼭질문제에서는 visited 배열을 만들어서 체크하는 방식을 사용했었고 이번에는 배열에 시간을 담는 방식으로 설계해보았습니다.    
```java
if(now*2<=SIZE-1 && time[now*2]>t) {
  time[now*2]=t;
  q.addFirst(now*2);
}
if(now<SIZE-1 && time[now+1]>t){
  time[now+1]=t+1;
  q.addLast(now+1);
}
if(now>0 && time[now-1]>t) {
  time[now-1]=t+1;
  q.addLast(now-1);
}
```
순간이동할때 *2 위치로 이동하고 0초가 걸리고  
걸어서 +1, -1로 이동하는데 1초가 걸린다고 주어져있습니다  

순간이동하는 경우 시간이 0초가 걸리기 때문에 더 먼저 처리하기위해 덱의 앞에 넣어주고 걸어가는 경우 덱의 뒤에 넣어 이후에 처리하게 해줍니다.   

음.. 원래 설계는 큐로했었는데 계속 정답처리가 안되어서 덱으로 코드를 바꾸었습니다. 알고보니 이와는 상관없이 위에 입력받는 부분에서 오류가 있더라구요  
제 코드르 보시면 항상 해당 위치의 최소시간을 배열에 저장하게 되어있기 때문에 순간이동, 걷기 우선순위 상관없이 항상 최소값이 저장됩니다.  
따라서 덱,큐 상관없이 결과를 얻을 수 있습니다. 덱으로 통과하고 나서 큐로 변경하여 제출해보니 똑같이 통과되는 것을 확인했습니다.   
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int SIZE = 100001;
	static int N,K;
	static int [] time=new int[SIZE];
	public static void main(String[] args) throws IOException {
		Arrays.fill(time, 654321);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		getResult();
	}
	public static void getResult() {
		time[N]=0;
		Queue <Integer> q = new ArrayDeque<>(); //위치
		q.add(N);
		while(!q.isEmpty()) {
			int now = q.poll();
			int t = time[now];
			if(now==K) {
				System.out.println(t);
				return;
			}
			if(now*2<=SIZE-1 && time[now*2]>t) {
				time[now*2]=t;
				q.add(now*2);
			}
			if(now<SIZE-1 && time[now+1]>t){
				time[now+1]=t+1;
				q.add(now+1);
			}
			if(now>0 && time[now-1]>t) {
				time[now-1]=t+1;
				q.add(now-1);
			}
		}
	}
}
```
큐를 사용한 코드 버리긴 아까워서 업로드해두겠습니다. 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static final int SIZE = 100001;
	static int N,K;
	static int [] time=new int[SIZE]; //해당 위치의 시간을 기록하기 위한 배열
	public static void main(String[] args) throws IOException {
		Arrays.fill(time, 654321); //매우 큰 값으로 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		getResult();
	}
	public static void getResult() {
		time[N]=0;
		Deque <Integer> q = new ArrayDeque<>(); //위치
		q.add(N);
		while(!q.isEmpty()) {
			int now = q.poll();
			int t = time[now];
			if(now==K) {
				System.out.println(t);
				return;
			}
			if(now*2<=SIZE-1 && time[now*2]>t) { //x*2 순간이동
				time[now*2]=t;
				q.addFirst(now*2);
			}
			if(now<SIZE-1 && time[now+1]>t){ //+1
				time[now+1]=t+1;
				q.addLast(now+1);
			}
			if(now>0 && time[now-1]>t) {  //-1
				time[now-1]=t+1;
				q.addLast(now-1);
			}
		}
	}
}
```
덱을 사용한 코드입니다.  
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
이전 숨바꼭질 문제를 풀었기 때문에 어떻게 푸는지 알 수 있었습니다. 역시 문제를 풀어보는게 중요한 것 같습니다.    

### 고생한 점
없습니다.   
