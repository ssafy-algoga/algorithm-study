# 9663번 N-Queen Gold5 
[문제 보러가기](https://www.acmicpc.net/problem/9663)

## 🅰 설계

퀀이 배치된 체스판을 노드로하는 상태공간트리를 DFS하여 구현하였습니다.

다만, 제한 조건을 충족하기 위해 탐색중 유효한 상태인지 검사를 하여 함수호출 수를 줄였습니다. 

---

### 1. 선언부

```java
static int N, cnt;
static int[] queen; // 각 행에서 퀸의 열 위치를 표시
```
N은 놓아야 할 퀸의 개수, cnt는 완성된 체스판 상태의 개수를 저장합니다.

그리고 퀸들의 배치 위치를 저장하기 위해서

행 좌표를 인덱스로 하고 열 좌표를 저장하는 int 배열(queen)을 사용했습니다.

---

### 2. 깊이 우선 탐색

```java
// 재귀로 N번 퀸을 놓는다.
public static void nQueen(int n, int flag) {
	if (n == N) {// N번 배치했으면 카운트 증가
		cnt++;
		return;
	}

	for (int i = 0; i < N; i++) {
		if ((flag & 1 << i) != 0) continue; // 열 중첩은 비트마스킹으로 검사
		queen[n] = i; // n번째 행, i번째 열에 퀸을 놓는다.

		if (isPossible(n)) { // 대각선 유효한 배치면 다음 퀸을 놓는다.
			nQueen(n + 1, flag | 1 << i);
		}
	}
}
```

퀸을 놓을 행 n에서 각 열에 대해 유효성 검사를 하고 배치를 합니다.

열 중첩은 flag를 이용한 비트마스킹으로 체크 하였습니다.

무효한 배치라면 해당 배치에 대한 재귀 탐색을 종료합니다. 

만약 N번 배치가 되었다면 완성 체스판 수를 증가시키고 복귀합니다.

---

### 3. 대각선 중첩 검사

```java
public static boolean isPossible(int n) {
	for (int j = 0; j < n; j++) { // 기존 퀸과의 대각선 검사
		if (Math.abs(queen[n] - queen[j]) == n - j) {
			return false;
		}
	}
	return true;
}
```
현 배치에 대해 기존 퀸들과의 대각선 중첩 여부를 검사합니다.

열 좌표의 차와 행 좌표의 차가 같다면 무효한 상태로 판단합니다.

---

## ✅ 후기
모든 상태 검사를 비크마스킹으로 구현하려 했지만

방법이 떠오르지 않아서 대각선 검사는 따로 구현하였습니다.




