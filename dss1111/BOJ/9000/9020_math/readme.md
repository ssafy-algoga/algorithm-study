# 9020번 골드바흐의 추측
[문제 보러가기](https://www.acmicpc.net/problem/9020)

## 🅰 설계
전에 소수찾는 문제로 고생한 경험이 있어서 에라토스테네스의 체라는 방법을 사전지식으로 알고 있었습니다.  
소수는 1과 자기자신으로만 나누어지는 수이기 때문에 2의 배수를 모두 소수에서 제외하고, 3의 배수를 모두 제외하고.... 이를 반복하여 소수를 얻는 방법입니다.  
```java
	static boolean[] notPrime=new boolean[10001];

	static void init() { //에라토스테네스의 체
		notPrime[0]=true;
		notPrime[1]=true;
		for(int i=2;i<10001;i++) {
			for(int j=2;i*j<10001;j++) {
				notPrime[i*j]=true;
			}
		}
	}
```
boolean의 초기값이 false여서 notPrime배열을 통해 소수인지 아닌지를 체크합니다.
```java
			for(int i=0;i<n/2;i++) {
				if(!notPrime[half-i] && !notPrime[half+i]) {
					System.out.println((half-i)+" "+(half+i));
					break;
				}
			}
```
n = 8이라면 중간값인 4,4가 소수인지 체크하고 이후 3,5가 소수인지 체크하고 이런 방식으로 값을 벌려 나가면서 차이가 가장 적은 소수들의 합이 n인 경우를 찾습니다.  

이를 통해 값의 차이가 작은 것 부터 탐색할 수 있습니다.  

### 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] notPrime=new boolean[10001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		int test = Integer.parseInt(br.readLine());
		for(int t=0;t<test;t++) {
			int n = Integer.parseInt(br.readLine());
			int half = n/2;
			for(int i=0;i<n/2;i++) {
				if(!notPrime[half-i] && !notPrime[half+i]) {
					System.out.println((half-i)+" "+(half+i));
					break;
				}
			}
		}
	}
	static void init() { //에라토스테네스의 체
		notPrime[0]=true;
		notPrime[1]=true;
		for(int i=2;i<10001;i++) {
			for(int j=2;i*j<10001;j++) {
				notPrime[i*j]=true;
			}
		}
	}
}

```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
소수문제가 나올때 유용하게 쓰일만한 방법이였습니다.  
### 고생한 점
없습니다.
