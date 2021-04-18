# 2293 동전1
[문제 보러가기](https://www.acmicpc.net/problem/2293)

## 🅰 설계
1. 처음엔 순열로 접근하려고 했으나 하나의 동전을 여러번 사용할 수 있고, 길이가 고정되어 있는것이 아니며 동전의 구성이 같고 순서가 다른 경우는 같은경우로 치는 조합적 성질도 가지고 있기에 적잖이 고민을 했습니다. 
2. 결국 dp로 접근해야 한다는것을 알았고, 바로 일반식을 도출해내기 위한 표를 그렸습니다.

<img src = "https://user-images.githubusercontent.com/69133236/112839327-07513e80-90d9-11eb-85d1-a2ff2b198a42.png" height = 700>

3. 위에서 도출한 일반식을 토대로 계산을 해보면 알맞은 경우의 수가 도출되는것을 볼 수 있습니다.
4. 일반식을 도출해낸 뒤 일반식을 적용할 수 있도록 해주는 초기 값이 있어야 하기에 d[0]을 1로 초기화 합니다.




## 주요 코드 설명
### 전체 소스

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2293동전1_dp {
	static int N, K;
	static int[] coins,d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//동전 단위 개수
		K = Integer.parseInt(st.nextToken());	//목표 금액
		//정의 : 여러 동전중 어떤 동전들을 조합하여 k원을 만들수 있는 경우의 수
		int[] d = new int[K+1];		//K원까지 확인해야하니 
		coins = new int[N];			//주어진 동전 단위를 받을배열

		for (int i = 0; i < N; i++) 
			coins[i] = Integer.parseInt(br.readLine());

		d[0]=1;
		
		for (int v : coins) {	//1,2,5 나옴
			for(int i=1; i<=K; i++) {
				if(v<i) {	//만들금액(idx,k원)이 현재 동전의 단위보다 크면
					// k원의 경우의수 = (기존의 k원을 구할 수 있는 경우의 수) + (k-동전단위 원의 경우의수) 
					d[i] = d[i-v]+d[i];
				}else if(v==i) {	//만들 금액과 현재 동전의 단위가 같으면
					//기존의 k원을 구할수 있는 경우의수 + 1 만 해주면된다.
					d[i] = d[i]+1;
				}
			}
		}
		System.out.println(d[K]);
	}
}

```

## ✅ 후기
### dp는 일반식만 알아낸다면 정말 빠르고 간편한 알고리즘 같습니다. 하지만 dp의 일반식을 도출해내기까지의 과정이 엄청난 생각과 수고로움이 동반되기 때문에 문제해결 능력을 키워야하는 수밖에 없는것 같습니다... 그러기 위해서는 문제를 많이 풀어봐야겠지요