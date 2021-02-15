# 1463번 1로 만들기
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계
재귀로 완전탐색을 구현하고 반복되는 호출을 메모하여 바로 리턴해주는 간단한 문제였습니다  

f(n) = n에서 1을 만드는 최소 방법의 수 입니다  
```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int[] dp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		Arrays.fill(dp, -1);
		dp[1] = 0;
		System.out.println(f(n));
	}
	
	static int f(int n) {
		if(dp[n] != -1) return dp[n];
		dp[n] = Integer.MAX_VALUE;
		if(n%3 == 0) dp[n] = Math.min(dp[n], f(n/3)+1);
		if(n%2 == 0) dp[n] = Math.min(dp[n], f(n/2)+1);
		if(n-1 >= 1) dp[n] = Math.min(dp[n], f(n-1)+1);
		return dp[n];
	}
}
```

## ✅ 후기
피보나치와 굉장히 비슷한 문제같습니다 