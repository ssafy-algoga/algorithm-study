# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계

모든 경우의 수와 정사각형을 체크해도 

50 * 50 * 8 * 8 * 2 = 32만 < 1억 이므로

Brute-Force로 풀었습니다.

---

### 1. main

```java
// 보드 입력받기
for (int i = 0; i < N; i++) {
	board[i] = br.readLine().toCharArray();
}
// 모든 경우에 대해 체스판 비교해보기
for (int i = 0; i <= N - 8; i++) {
	for (int j = 0; j <= M - 8; j++) {
		compare(i, j);
	}
}
System.out.println(min);
```
입력받은 보드에서 8*8크기의 체스판이 가능한 모든 경우의 수에 대해 

비교 연산을 하였습니다.  

---

### 2. compare 함수

```java
public static void compare(int x, int y) {
	int cnt = 0; // 칠해야 할 정사각형 개수

	// W시작 체스판과 비교
	for (int row = x; row < x + 8; row++) {
		for (int col = y; col < y + 8; col++) {
			if (row % 2 == 0) { // W시작 줄
				if (col % 2 == 0) { // W
					if (board[row][col] != 'W') cnt++;
				} else { // B
					if (board[row][col] != 'B') cnt++;
				}
			} else { // B시작 줄
				if (col % 2 == 0) { // B
					if (board[row][col] != 'B') cnt++;
				} else { // W
					if (board[row][col] != 'W') cnt++;
				}
			}
		}
	}
	min = (min > cnt) ? cnt : min;
	cnt = 0;
  
  // B시작 체스판과 비교
  ...
```
W로 시작하는 8*8 체스판과 비교를 하여 

다른 정사각형이 있다면 cnt로 개수를 세고 min(최소 개수)를 갱신하였습니다.

이 연산을 B로 시작하는 체스판에 대해서 똑같이 합니다.

---

## ✅ 후기
코드는 길지만 비슷한 연산이 반복되어 

복사 붙여넣기를 통해 빠르게 구현하였습니다.
