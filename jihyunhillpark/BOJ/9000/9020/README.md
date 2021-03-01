# 9020번 골드바흐의 추측 
[문제 보러가기](https://www.acmicpc.net/problem/9020)

## 🅰 설계
파티션으로 나눈 두 수를 소수인지 빠르게 판별하는 것이 이문제의 핵심이라고 생각했습니다.
<br>
소수를 판별하는 방법은 여러가지가 있을 수 있지만, 널리 알려진 에라토스테네스의 체를 사용하여 문제를 풀었습니다.

1. 10000크기의 배열을 만들어 소수를 관리한다. - notPrime배열
```java
static boolean[] notPrime = new boolean[size+1]; //소수면 false, 소수 아니면 true
```
<br>
2. 입력받기 전에 미리 소수 확인 배열 완성하자.
```java
public static void main(String[] args) throws NumberFormatException, IOException {
		eratostenes();
		...
}
private static void eratostenes() {
		int root = 100;
		for(int i = 2 ; i <= root; i++) {
			int pos = i+i;
			while(pos <= size) {
				notPrime[pos] = true;
				pos += i;
			}
		}
	}
```



## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점

#### 보니까 예전에 이 문제를 푼 적이 있더라구요! 궁금해서 봤는데 그 때와 동일한 방식으로 접근해서 눈물 흘렸습니다💦💦

