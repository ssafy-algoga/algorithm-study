# 9020번 골드바흐의 추측
[문제 보러가기](https://www.acmicpc.net/problem/9020)

## 🅰 설계
10000 이하의 소수를 먼저 구해놓고 탐색을 돌리면 되는데, 저는 이분탐색을 사용했습니다.  

### 1. 소수 구하기
```java
for(int i=2;i<=LIMIT;i++) {
	if(!chk[i]) {
		primes.add(i);
		for(int j=i*2;j<=LIMIT;j+=i) {
			chk[j] = true;
		}
	}
}
```

에라토스테네스의 체를 이용해서 primes 리스트를 만듭니다.  

### 2. 골드바흐 파티션 만들기
```java
int n = Integer.parseInt(br.readLine());
int a = 0;
int b = Integer.MAX_VALUE;
for(int i=0;i<primes.size();i++) {
	if(primes.get(i) > n/2) break;
	int idx = Collections.binarySearch(primes, n-primes.get(i));
	if(idx>=0) {
		a = primes.get(i);
		b = primes.get(idx);
	}
}
System.out.println(a+" "+b);
```
**n-primes.get(i)**라는 원소가 primes에 존재하는지 이분탐색을 통해 알아냅니다.  
idx가 음수면 **primes.get(i)**로는 골드바흐 파티션을 만들 수 없습니다.  

### 3. 전체코드
```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static List<Integer> primes = new ArrayList<>();
	static boolean[] chk = new boolean[10001];
	static int LIMIT = 10000;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=2;i<=LIMIT;i++) {
			if(!chk[i]) {
				primes.add(i);
				for(int j=i*2;j<=LIMIT;j+=i) {
					chk[j] = true;
				}
			}
		}
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<t;i++) solve();
	}
	
	static void solve() throws IOException {
		
		int n = Integer.parseInt(br.readLine());
		int a = 0;
		int b = Integer.MAX_VALUE;
		for(int i=0;i<primes.size();i++) {
			if(primes.get(i) > n/2) break;
			int idx = Collections.binarySearch(primes, n-primes.get(i));
			if(idx>=0) {
				a = primes.get(i);
				b = primes.get(idx);
			}
		}
		System.out.println(a+" "+b);
	}
}
```


## ✅ 후기
다른 사람들은 `primes`리스트가 아니라 boolean 배열으로 isPrime을 만들고 n/2부터 투포인터같은 느낌으로 한번에 찾을 수 있게 풀던데 굉장히 좋은것 같습니다.  

