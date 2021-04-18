# 9663번 N-Queen
[문제 보러가기](https://www.acmicpc.net/problem/9663)

## 🅰 설계

**전체맵 최대 15X15, 대각방향을 탐색하는 경우 최대 15를 계산해보면 대략 3375정도의 크기가 나오기 때문에 완전탐색으로 접근하였지만 시간복잡도를 계산하는 방법이 틀린거같다.. 저대로라면 6초라는 시간은 절대 나올수가 없을거 같다. 혹시 계산법이 맞는지, 틀리다면 제대로된 계산법 아시는 분.. 지식을 공유해주세요!.**



## 💬주요 코드
### 행이 채워진 개수를 담을 변수
```java
static int fillRow;
```

### 퀸이 놓여져 있는 열을 기억할 배열
```java
static boolean[] col;
```

### 모든 대각선을 확인 할 함수(요약)

```java
private static boolean diagonal(int r, int c) {
		offset = 0;
		repeatR = Math.max(N - r - 1, r);
		repeatC = Math.max(N - c - 1, c);
		repeat = Math.min(repeatR, repeatC);
		while (repeat-- > 0) {
			offset++;
			// 좌상, 우상, 우하, 좌하
			int[] dr = { r - offset, r - offset, r + offset, r + offset };
			int[] dc = { c - offset, c + offset, c + offset, c - offset };

			// 좌상단 
			if (dr[0] >= 0 && dc[0] >= 0) {
				if (map[dr[0]][dc[0]] == true) // 대각방향에 다른 퀸이 있으면
					return false;
			}
		}
		return true;
	}
```





## ✅ 후기

#### - 상당히 고생한 문제.. 풀긴 풀었지만, 문제에서 요구하는 메모리를 통과하지 못하여 찜찜한 문제이다. 어느 부분에서 오버헤드가 발생했는지 잘 모르겠다..

#### - 시간복잡도는 코드의 순서도 크게 좌우한다는 것을 깨닫게 되었다. 끝내는 조건을 위로 올릴수록 좋다.