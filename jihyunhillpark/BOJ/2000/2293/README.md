# 2293번 동전1
[문제 보러가기](https://www.acmicpc.net/problem/2293)

## 🅰 설계

처음 이 문제를 보았을 때 "오오 이 문제 RGB거리 문제처럼 각 동전에 대한 DP를 구해서 더하는 것?" 이라고 생각했습니다.<br/>
하지만 이 문제는 최적해가 아닌 모든 경우의 수를 구한다는 점에서 RGB거리(최적해)와는 차이가 있습니다.

1. coins 1차원 int배열을 만들어 동전의 가치를 저장했습니다.
```java
int coins[] = new int[N];
for(int i = 0 ; i < N ; i++)
  coins[i] = Integer.parseInt(in.readLine());
```
2. coins 배열을 오름차순으로 정렬했는데, 이는 코인의 수를 늘려감에 따라 __기존 있는 조합에 새로운 조합을 추가__ 하기 위해서 입니다.

3. dp라는 2차원 형태의 동적테이블은 coins배열의 0번부터 row번까지의 동전들만 사용하여 col의 동전가치를 만들어낼 수 있는 경우의 수를 저장하기 위함입니다(dp[row][col]).
```java
int dp[][] = new int[N][K+1]; //dp[row][col] = coin[0]~coin[row]까지 해당하는 동전들을 고려하여 가치의 합이 col이 되도록 하는 경우의 수.
```

### 전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;

		st = new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int dp[][] = new int[N][K+1]; //dp[row][col] = coin[0]~coin[row]까지 해당하는 동전들을 고려하여 가치의 합이 col이 되도록 하는 경우의 수.
		int coins[] = new int[N];
		for(int i = 0 ; i < N ; i++)
			coins[i] = Integer.parseInt(in.readLine());

		//Sort 안 하는 방법은 없을까?-?
		Arrays.sort(coins);

		//제일 작은 동전만 쓰는 경우
		for(int i = 1; i <= K ; i++) {
			if((i % coins[0]) == 0) dp[0][i] = 1;
		}

		//이후 고려하는 동전들을 늘려가며...
		for(int i = 1; i < N ; i++) {
			int curValue = coins[i];
			for(int j = coins[0]; j <= K; j++) {
				if(j < curValue ) dp[i][j] = dp[i-1][j]; //동전의 가치가 담을 수 있는 총량보다 클 때 - 못 담을 때
				else if(j == coins[i]) dp[i][j] = 1 + dp[i-1][j];  //동전의 가치가 담을 수 있는 총량과 같을 때 - 경우의 수 하나 추가됨.
				else dp[i][j] = dp[i-1][j] + dp[i][j-curValue]; //(미리 구해 놓은)이전에 고려했던 동전들에서 나올 수 있는 경우의 수에 새로운 경우의 수 추가추가!
			}
		}
		System.out.println(dp[N-1][K]);
	}

}
```

## ✅ 후기
#### 새롭게 알게되거나 공유해서 알게된 점
지금까지 주로 최적해를 구하는 DP문제를 풀어왔던 것같은데, 경우의 수를 이렇게 dp로 풀수 있어서 신기했습니다.
#### 고생한 점
리드미.md가 짧아 보이지만 사실 DP를 어떻게 정의할 것인가?의 문제를 두고 많이 고민했던 것 같습니다. 또한 sort를 하지 않고 풀 수 있는 방법과 top-down으로 푸는 방법이 있을 것 같은데 한번 찾아봐야겠습니다.  
