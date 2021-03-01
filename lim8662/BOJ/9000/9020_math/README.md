# 9020번 골드바흐의 추측
[문제 보러가기](https://www.acmicpc.net/problem/9020)

## 🅰 설계

소수 체크를 하는 수학 유형의 문제입니다.

소수의 범위가 짝수의 반인 5000까지로 좁기에 제곱근 방식으로 풀었습니다.

이후에 에라토스테네스의 체를 사용해서도 풀어보았습니다.

---

### 1. 제곱근 방식

```java
public static void solution1(int n) {
	int n1 = n/2, n2 = n/2; // 중간값부터 +-1하며 소수체크
	
	while(true) {
		if(isPrime(n1) && isPrime(n2)) {
			System.out.println(n1 + " " + n2);
			break;
		}
		n1--; n2++;
	}		
}

public static boolean isPrime (long num) { //소수인지 체크
	int n = (int) Math.sqrt(num);
	for(int i = 2; i <= n; i++) {
		if(num % i == 0) {
			return false;
		}	
	}
	return true;
}
```
짝수의 중간값에서 1씩 더하고 빼면서 두 값이 소수인지 체크를 하여

가장 차가 적은 소수 파티션을 구하였습니다.

소수 체크는 해당 수의 제곱근 까지의 범위의 수로 나누어보았습니다.


특정 수 x를 두 약수의 곱으로 표현한다면,

x = m * n(m >= n)를 만족하는 모든 m, n의 쌍에서 n의 최대값은 √x가 됩니다. 

그래서 2 <= n <= √x 범위의 자연수로 나누어 떨어지지 않는다면 소수가 됩니다. 

ex) 6 = 3 * 2 = 2 * 3는 n = 2인 같은 쌍

---

### 2. 에라토스테네스의 체

```java
public static void solution2(int n) throws IOException {
	isPrime = new boolean[n + 1];
	Arrays.fill(isPrime, true);

	for (int i = 2; i <= Math.sqrt(n); i++) {
		if (isPrime[i] == false) continue;

		for (int j = 2 * i; j <= n; j += i) 
			isPrime[j] = false;		
	}
	
	int n1 = n/2, n2 = n/2; // 중간값부터 +-1하며 소수체크
	
	while(true) {
		if(isPrime[n1] && isPrime[n2]) {
			bw.write(String.valueOf(n1) + ' ' + n2 + '\n');
			break;
		}
		n1--; n2++;
	}
	bw.flush();
}
```
짝수의 중간값부터 소수를 체크하는 방식은 동일하나

소수 체크하는 방법을 에라토스테네스의 체로 미리 소수를 구해 배열에 담아두고

해당 수가 소수인지 확인하는 방식으로 구현했습니다.

---

## ✅ 후기
문제와 로직의 특성상 소수 체크하는 연산 수가 그리 많지 않기 때문인지 

제곱근 방식이 에라토스테네스의 체 보다 메모리는 물론이고 속도에서도 성능이 우수했습니다.
