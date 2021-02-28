# 1202번 보석 도둑
[문제 보러가기](https://www.acmicpc.net/problem/1202)

## 🅰 설계
각자 무게M과 가격V를 가진 보석 N개를 최대 C의 무게를 담을 수 있는 K개의 가방(가방 하나에 보석 하나만 담을 수 있다)에 적당히 담았을 때 보석 가격의 합의 최댓값을 구하는 문제입니다.

담을 수 있는 최대 무게가 적은 가방부터 고려해서   
해당 가방에 담을 수 있는 모든 보석들 중 가격이 가장 높은 보석을 가방에 담으면 됩니다.

첫 가방에서 담지 못한 보석들이 반드시 다음 가방에 담을 후보가 되기 때문에(다음 가방이 이전 가방보다 담을 수 있는 무게가 같거나 크므로)   
계속해서 가격이 가장 높은 보석을 선택하면 최종적으로 가격의 합이 가장 크게 됩니다.

### 구현
담을 수 있는 최대 무게가 적은 가방부터 고려해야 하기 때문에 배열에 가방의 최대 무게를 담고, 오름차순으로 정렬해주었습니다.   
보석 또한 무게가 적은 것부터 정렬해 차례대로 가방이 담을 수 있는 무게와 비교해 후보군에 넣을 수 있도록 했습니다.
```jsx
Arrays.sort(bag);
Arrays.sort(jewelry, (int[] o1, int[] o2) -> o1[0] - o2[0]);
```

계속해서 가격이 가장 높은 보석을 고를 수 있도록 우선순위 큐를 사용했습니다.   
이 때 큐에 저장된 것은 가방 안에 들어갈 수 있는 보석의 후보군들로 무게가 이미 고려되어 저장한 것이기 때문에 가격만 저장해주었습니다.
```jsx
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

### 코드
```jsx
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1202_보석도둑 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] jewelry = new int[N][2];
		int[] bag = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			jewelry[i][0] = Integer.parseInt(st.nextToken());
			jewelry[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		// 입력 끝
		
		Arrays.sort(bag);
		Arrays.sort(jewelry, (int[] o1, int[] o2) -> o1[0] - o2[0]);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		long total = 0;
		int cnt = 0;
		int w = 0;
		
		for (int i = 0; i < K; i++) {
			w = bag[i];
			
			// 가방에 넣을 수 있는 모든 보석을 우선순위 큐에 넣는다.
			for (int j = cnt; j < N; j++) {
				if(jewelry[cnt][0] <= w) {
					pq.add(jewelry[cnt][1]);
					cnt++;
				}
				else break;
			}
			
			// 가방에 넣을 수 있는 보석 중 가장 가치가 높은 보석을 선택한다.
			if(pq.isEmpty()) continue;
			total += pq.poll();
		}
		
		System.out.print(total);
	}

}

```

## ✅ 후기
### 고생한 점
아무생각없이 int 썼다가 틀렸습니다..   
총 30만 개의 보석을 담을 수 있고, 각 보석의 최대 가격이 100만이라서   
최대 가격 합이 3조라서 int 범위를 뛰어넘습니다. 때문에 long 변수를 사용해야 합니다.
계속 놓치는 부분인 것 같아서 신경써서 매번 확인해봐야겠습니다ㅠㅠ
