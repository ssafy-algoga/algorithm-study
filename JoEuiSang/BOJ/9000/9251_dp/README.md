# 9251번 문제제목
[문제 보러가기](https://www.acmicpc.net/problem/9251)

문제 아직 성공 못했습니다...
시간초과떴는데 일단 이거라도 올려보겠습니다. 어느부분이 문제일지 리뷰 부탁드려요!!

## 🅰 설계
<ima src = "https://user-images.githubusercontent.com/69133236/106415974-bdaaf580-6493-11eb-889b-4b9518ab2131.jpg" height = 700>

각 수열을 단계단계로 뻗어나가는 방식으로 접근해봤습니다.
두 문자가 같으면 인덱스를 둘다 증가시켜주고, 아니라면 수열1, 수열2 의 인덱스가 각각 증가하는 경우를 둘다 체크하도록 하였습니다.

### 클래스변수
```java
static int size1; // 수열1의 길이
static int size2; // 수열2의 길이
static int answer = 0; // 답을 저장할 변수
static String s1; //수열1
static String s2; //수열2
```

### 입력은 BufferedReader를 사용했습니다.


### 각 수열의 자리를 비교하여 재귀적으로 처리하는 함수 (수열1의 인덱스, 수열2의 인덱스, 맞은횟수)
```java
static void equalCheck(int idx1, int idx2, int equal) {
		// 위치가 문자열안에 있으면
		if (idx1 < size1 && idx2 < size2) {
			// 각 문자가 일치하면
			if (s1.charAt(idx1) == s2.charAt(idx2)) {
				if (answer < ++equal) // 증가 후 현재 횟수보다 크면 정답 갱신
					answer = equal;

				// 더 체크할 문자가 있으면 재귀
				if (idx1 < size1 - 1 && idx2 < size2 - 1) {
					equalCheck(idx1 + 1, idx2 + 1, equal);
				} else {// 없으면 종료
					return;
				}
			} else { // 각 문자가 일치하지 않으면 분기하여 재귀
				equalCheck(idx1 + 1, idx2, equal);
				equalCheck(idx1, idx2 + 1, equal);
			}
		}else return;
	}
```

### 전체코드
```java
package _1월_5주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LCS {
	static int size1; // 수열1의 길이
	static int size2; // 수열2의 길이
	static int answer = 0; // 답을 저장할 변수
	static String s1;
	static String s2;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("LCS.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		s1 = br.readLine();
		s2 = br.readLine();

		size1 = s1.length();
		size2 = s2.length();

		equalCheck(0, 0, 0);
		System.out.println(answer);
	}

	// 각 수열의 자리를 비교하여 재귀적으로 처리하는 함수 (수열1의 인덱스, 수열2의 인덱스, 맞은횟수)
	static void equalCheck(int idx1, int idx2, int equal) {
		// 위치가 문자열안에 있으면
		if (idx1 < size1 && idx2 < size2) {
			// 각 문자가 일치하면
			if (s1.charAt(idx1) == s2.charAt(idx2)) {
				if (answer < ++equal) // 증가 후 현재 횟수보다 크면 정답 갱신
					answer = equal;

				// 더 체크할 문자가 있으면 재귀
				if (idx1 < size1 - 1 && idx2 < size2 - 1) {
					equalCheck(idx1 + 1, idx2 + 1, equal);
				} else {// 없으면 종료
					return;
				}
			} else { // 각 문자가 일치하지 않으면 분기하여 재귀
				equalCheck(idx1 + 1, idx2, equal);
				equalCheck(idx1, idx2 + 1, equal);
			}
		}else return;
	}
}

```

## ✅ 후기
#### 시간초과가 뜨는데 적절한 자료구조를 사용, 또는 효율적인 로직을 짜야겠다. 그러기 위해선 열심히 공부하고 연구해야겠지요..
#### 다 맞았을때, 수열1만 증가, 수열2만 증가 이렇게 3개의 경우의수로 체크하는 로직자체도 생각해내는데 상당히 오래걸렸고, 구현하는데에도 오래걸렸다. 아직도 많이 부족하다. 좀 더 신속하고 정확하게 효율적인 로직을 생각할수 있도록 열심히 해야겠다.