# 9020번 골드바흐의 추측
[문제 보러가기](https://www.acmicpc.net/problem/9020)

## 🅰 설계
10000 보다 작고 2보다 큰 짝수 n이 주어졌을 때, 이를 소수의 합으로 나타낼 수 있다.   
가장 차이가 작은 소수 쌍을 구하는 문제이다.

가장 차이가 작은 쌍을 찾아내야 하기 때문에 먼저 주어진 짝수 n을 반으로 나누고,   
각각 -1, +1을 해가며 두 개가 다 소수인지 확인하는 식으로 풀었습니다.   

짝수면 애초에 소수가 아니니 걸러줬습니다. 2는 소수니까 예외로 두었습니다.
```jsx
public static void findPartition(int n) {
	int a, b;
	
	a = b = n/2;
	
	for( ; a>0 && b<n; a--, b++) {
		if(a%2==0 && a!=2) continue;
		
		if(isPrime(a) && isPrime(b)) {
			sb.append(a).append(" ").append(b).append("\n");
			return;
		}
		
	}
}
```

소수인지 확인하는 알고리즘은 제곱근을 구해 2부터 거기까지 나눠보는 방식으로 했습니다.   
근데 2의 배수는 이미 2를 약수로 가지니까 또 걸러줬습니다.   
```jsx
public static boolean isPrime(int n) {
	int l = (int) Math.sqrt(n);
	
	for(int d=2; d<=l; d++) {
		if(d%2==0) continue;
		if(n%d==0) return false;
	}
	
	return true;
}
```

### 코드
```jsx
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9020_골드바흐의추측 {
	
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			findPartition(n);
		}
		
		sb.setLength(sb.length()-1);
		System.out.print(sb.toString());
	}
	
	public static void findPartition(int n) {
		int a, b;
		
		a = b = n/2;
		
		for( ; a>0 && b<n; a--, b++) {
			if(a%2==0 && a!=2) continue;
			
			if(isPrime(a) && isPrime(b)) {
				sb.append(a).append(" ").append(b).append("\n");
				return;
			}
			
		}
	}
	
	public static boolean isPrime(int n) {
		int l = (int) Math.sqrt(n);
		
		for(int d=2; d<=l; d++) {
			if(d%2==0) continue;
			if(n%d==0) return false;
		}
		
		return true;
	}

}

```

## ✅ 후기
### 고생한 점
처음에 짝수 빼주다가 2도 빼버려서 틀렸었습니다..
