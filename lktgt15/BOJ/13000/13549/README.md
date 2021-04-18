# 13549번 숨바꼭질 3
[문제 보러가기](https://www.acmicpc.net/problem/13549)

## 🅰 설계
숨바꼭질 변형 문제가 많은데, 혹시나 기억에 의존해서 풀다가 실수할까봐 문제를 꼼꼼하게 봤습니다

현재 시간을 `curtime`, 현재 위치를 `curpos` 라고 하겠습니다

기본적으로 BFS를 이용하는데 순서를 중요하게 봤습니다

수빈이가 할 수 있는 행동은 2가지가 있는데,
1. `curpos-1` 또는 `curpos+1`로 이동, `curtime = curtime+1`
2. `curpos*2` 로 이동, `curtime = curtime`
입니다

1번 행동을 먼저 처리한다면 `curpos*2`가 `curtime`에 도착할 수 있음에도 `curtime+1`로 체크하게 됩니다
예를 들면 `curpos = 1, curtime = 0`일 때, `curpos+1 = 2` 에 도착하는 시간이 1이 되버립니다
순간이동을 한 다음 `curpos = 2, curtime = 0`일 때도 2번 행동을 먼저 처리해야 시간이 엉키지 않기 때문에 항상 2번 행동을 먼저 처리하는 것이 중요했습니다

다음으로 수빈이의 이동 반경을 확인했습니다
아무리 최악의 경우라도 동생의 위치 범위인 100,000의 2배인 200,000을 넘어서 순간이동을 하진 않을겁니다
0에서 +1만 해서 100,000까지 가는 경우보다 많을테니까요
그래서 `LIMIT`를 200,000으로 넉넉하게 잡아뒀습니다

범위 내에서 수빈이가 동생을 찾지 못하는 경우는 없으므로, 가장 처음 수빈이의 위치가 동생의 위치가 되었을 때 그 위치에서의 시간을 출력해 주면 답이 됩니다

```java
import java.io.*;
import java.util.*;

public class 숨바꼭질3 {
	static BufferedReader br;
	static StringTokenizer st;

	static int LIMIT = (int)2e5+1; // 최대 200,000까지 확인
	static int[] chk = new int[LIMIT]; // 위치 방문 체크 + 위치에 도달한 가장 빠른 시간
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<Integer>(); // BFS를 이용하기 위한 큐 선언
		Arrays.fill(chk, -1); // -1 은 방문하지 않은 위치
		
		q.offer(n);
		chk[n] = 0; // 시작점은 현재 시간 0
		while(!q.isEmpty()) {
			int cur = q.poll(); // 현재 위치
			if(cur == k) { // 현재 위치가 동생의 위치면 현재 시간을 바로 출력
				System.out.println(chk[cur]);
				return;
			}

			if(cur*2 < LIMIT && chk[cur*2] == -1) { // 순간이동
				q.offer(cur*2);
				chk[cur*2] = chk[cur];
			}
			if(cur-1 >= 0 && chk[cur-1] == -1) { // 현재위치 + 1
				q.offer(cur-1);
				chk[cur-1] = chk[cur]+1;
			}
			if(cur+1 < LIMIT && chk[cur+1] == -1) { // 현재위치 - 1
				q.offer(cur+1);
				chk[cur+1] = chk[cur]+1;
			}
		}
	}
}

```


## ✅ 후기

민규님이 알려주신 ArrayDeque를 사용해봤습니다. 은근히 이것저것 신경써줄게 있어서 재밌었습니다

