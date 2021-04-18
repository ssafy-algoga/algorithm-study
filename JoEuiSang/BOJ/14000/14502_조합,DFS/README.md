# 14502 연구소
[문제 보러가기](https://www.acmicpc.net/problem/14502)

## 🅰 설계
조합을 사용해 벽을 세우는 작업을 한 뒤 임시맵에 복사 후 DFS를 돌려 바이러스를 퍼뜨려 주는 방식을 사용하였다.

**특별한 점은 바이러스의 좌표를 리스트에 저장하여, 맵 전체를 검사하지 않고 바이러스가 있는 곳에서만 DFS를 하도록 하였다.**



## 주요 코드

**벽 3개를 세우는 조합 / 기저조건이 충족되면 바이러스를 퍼뜨린다.**

```java
//벽세우기 조합
	static void setWall(int start, int cnt) {
		if (cnt == 3) {
			copyMap();

			for (Virus v : virus) {
				spreadVirus(v.r, v.c);
			}

			ans = Math.max(ans, SafeArea());
			return;
		}

		for (int i = start; i < N * M; i++) {
			int x = i / M;	//행 계산
			int y = i % M;	//열 계산

			if (map[x][y] == 0) {
				map[x][y] = 1;
				setWall(i + 1, cnt + 1);
				map[x][y] = 0;
			}
		}
	}
```



**바이러스를 퍼뜨리는 재귀함수**

```java
static void spreadVirus(int r, int c) {
	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];

		if (0 <= nr && N > nr && 0 <= nc && M > nc && tmpMap[nr][nc] == 0) {
			tmpMap[nr][nc] = 2;
			spreadVirus(nr, nc);
		}
	}
}
```


## ✅ 후기
### 이전에 풀었을때는 힘겹게 풀었던건 같은데 최근에 조합과 완전탐색을 배워서 인지 문제를 많이 풀어봐서 그런지 손쉽게 해결 할 수 있었다. 역시 사람은 적응의 동물이다. 하지만 응용 문제가 나온다면 곤란해질 것 같다.