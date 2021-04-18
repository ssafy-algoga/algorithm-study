# 1697번 숨바꼭질
[문제 보러가기](https://www.acmicpc.net/problem/1697)

## 🅰 설계
![image](https://user-images.githubusercontent.com/37682970/107230169-0ea58580-6a62-11eb-9e5b-b534c2272145.png)   
큐를 돌면서 -,+,*를 조건을 체크해서 큐에 넣어주는 BFS방식을 사용했습니다.   
```
static boolean [] visited = new boolean[100001];
static int [] point = new int [100001]; 
```
단순하게 탐색했는지 체크하는 visited와 탐색도 체크할 수 있고 돌면서 도달시간을 저장하는 point랑 어떤것을 쓸까 고민했습니다.   
point를 사용하면 값끼리 비교하는 작업이 필요해질 것 같아서 그냥 visited 체크하는 방법을 사용했습니다.  

### 전체코드
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static boolean [] visited = new boolean[100001];
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		getResult();
		
	}
	public static void getResult() {
		//맨처음 위치 정보 추가
		q.add(N);
		visited[N] = true;
		int t = 0; //탐색시간
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int now = q.poll(); //큐에서 하나빼온다
				if(now == K) { // 결과 찾음
					System.out.println(t);
					return;
				}
				//수행할 수 있는 다음 탐색 3가지
				if(now>0 && !visited[now-1]) {
					visited[now-1]=true;
					q.add(now-1);
				}
				if(now<100000 && !visited[now+1]) {
					visited[now+1]=true;
					q.add(now+1);
				}
				if(now*2<=100000 && !visited[now*2]) { // *는 100000도 가능
					visited[now*2]=true;
					q.add(now*2);
				}
			}
			t++; // 탐색시간++
		}
	}
}
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
없습니다  
### 고생한 점
1. -,+,* 동작중에 *는 100000도 포함인데 이거 찾느라고 고생했습니다.  
2. 다음케이스를 찾을때 큐를 for문 돌면서 가져오는 방법을 사용했는데 이거 생각하는게 힘들었습니다.  
