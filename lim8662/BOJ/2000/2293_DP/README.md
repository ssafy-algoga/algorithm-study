# 2293번 동전1
[문제 보러가기](https://www.acmicpc.net/problem/2293)

## 🅰 설계

경우의 수를 구하는 전형적인 동적계획법 문제입니다.

하향식으로 메모이제이션을 사용해서 풀었습니다.

N개 까지의 동전을 사용해 K를 만드는 경우의 수를 DP(N, K)라 하면

N번째 동전을 사용해 만드는 금액들 v에 대한 DP(N-1, k-v)들로 나누어 지게 됩니다.

그러므로 첫번째 동전부터 1~K 금액에 대한 경우의 수를 채우고

N번째 동전까지 현재 동전으로 1~K 금액에 대해 나누어 떨어지면 경우의 수르 증가시키고

금액이 남는다면 남은 금액에 대한 이전 동전까지 사용해서 만드는 경우의 수를 더해주며 DP(N,K) 까지 구합니다.

---

### 1. 전체코드

```java
public class boj_2293_coin1 {
	static int N, K;
	static int[][] DP;
	static int[] coin;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 코인수
		K = Integer.parseInt(st.nextToken()); // 목표 금액
		coin = new int[N];
		DP = new int[N][K+1]; // N개의 동전을 사용해 K를 만드는 경우의 수 저장
		
		for (int i = 0; i < N; i++) { // 코인 가치 입력
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < N; i++) { // coin[i] 까지의 코인을 사용해서
			for (int k = 1; k <= K; k++) { // 1~K 까지 만드는 경우의 수
				
				
				if(i == 0) { // 초기화 
					if(k % coin[0] == 0) DP[0][k] = 1;
				} else { 
					for (int v = 0; v <= k ; v += coin[i]) { // 0~k 금액을 현재 코인을 사용해 만들기
						if(v == k) { // 금액이 딱 맞으면
							DP[i][k]++; // 경우의 수 증가
						} else { // 금액이 남으면
							DP[i][k] += DP[i-1][k-v]; // 이전 코인까지 사용한 경우의 수를 더함
						}
					}
				}
			}
		}	
		System.out.println(DP[N-1][K]);
	}
}
```

---

## ✅ 후기
조건을 나누어서 구현하면 되는데 남은 금액이 0이 되는 것까지 일반화하려다 시간 낭비를 하였습니다.

숏코딩 욕심을 자제해야 겠습니다.
