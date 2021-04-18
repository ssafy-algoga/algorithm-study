# 16235 나무 재테크
[문제 보러가기](https://www.acmicpc.net/problem/16235)

## 🅰 접근
1. 구현 & 시뮬레이션 문제라서 주어진 시나리오만 적용하면 간단하게 풀 수 있을 것 같은 문제지만 시간초과라는 벽이 있는 무시무시한 문제였습니다. 괜히 골드가 아니었습니다.
2. ArrayList, LinkedList를 이용한 코드는 모두 시간초과로 터졌는데 아무래도 나무들이 사방으로 번식을 하는 가을 부분에서 연산이 많이 일어나서 그런게 아닌가 싶습니다.(정확한건 아닙니다.)
3. 앞 뒤로 삽입, 삭제가 가능한 Deque 자료구조를 이용하여 해결하였습니다.
4. 결국 이 문제는 적절한 자료구조를 파악하고 활용할 수 있는지를 묻는 문제였던 것 같습니다.




## 주요 코드 설명
### .Tree : 나무의 행, 열, 나이를 담을 클래스

```java
	static class Tree {
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
```

### 주요 변수

```java
static int N, M, K; // N 맵 크기, M나무 개수, K 지난 년수
static int[][] nowFood, plusFood; // 현재 양분상태를 나타낼 맵, 추가될 양분을 저장할 맵
static Deque<Tree> trees = new LinkedList<>();	//현재 살아있는 나무 리스트
static Queue<Tree> deadTrees = new LinkedList<>();	//죽은 나무 리스트
static Queue<Tree> breedingTrees = new LinkedList<>();	//번식할 나무 리스트
```

### spring() : 봄 함수

```java
static void spring() {
    // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
    // 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
    // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
    // 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.

    Queue<Tree> tmp = new LinkedList<>();
    while (!trees.isEmpty()) {
        Tree t = trees.pollFirst();

        if (nowFood[t.r][t.c] >= t.age) {
            nowFood[t.r][t.c] -= t.age;
            t.age++;
            if (t.age % 5 == 0) {
                breedingTrees.add(t);
            }
            tmp.add(t);
        } else {
            deadTrees.offer(t);
        }
    }
    trees.addAll(tmp);
}
```

### summer() : 여름 함수

```java
static void summer() {
    // 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
    // 소수점 아래는 버린다.
    while (!deadTrees.isEmpty()) {
        Tree t = deadTrees.poll();
        nowFood[t.r][t.c] += t.age / 2;
    }
}
```

### fall() : 가을 함수

```java
static void fall() {
    // 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    // 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1),
    // (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
    // 땅을 벗어나는 칸에는 나무가 생기지 않는다.
    int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] dc = { -1, 0, 1, 1, -1, -1, 0, 1 };

    while (!breedingTrees.isEmpty()) {
        Tree t = breedingTrees.poll();
        for (int d = 0; d < 8; d++) {
            int nr = t.r + dr[d];
            int nc = t.c + dc[d];

            if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                trees.addFirst(new Tree(nr, nc, 1));
            }
        }
    }
}
```

### winter() : 겨울 함수

```java
static void winter() {
    // 겨울
    // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고
    // 입력으로 주어진다.
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            nowFood[i][j] += plusFood[i][j];
        }
    }
}
```

### 전체 소스

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16235나무제테크 {
	static int N, M, K; // N 맵 크기, M나무 개수, K 지난 년수
	static int[][] nowFood, plusFood; // 현재 양분상태를 나타낼 맵, 추가될 양분을 저장할 맵
	static Deque<Tree> trees = new LinkedList<>();
	static Queue<Tree> deadTrees = new LinkedList<>();
	static Queue<Tree> breedingTrees = new LinkedList<>();

	static class Tree {
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nowFood = new int[N + 1][N + 1];
		plusFood = new int[N + 1][N + 1];

		// 기본 양분 5, 겨울마다 추가할 양분 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				nowFood[i][j] = 5;
				plusFood[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 나무의 좌표 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());

			trees.offer(new Tree(r, c, age));
		}

		Collections.sort((List<Tree>) trees, (o1, o2) -> o1.age - o2.age);
		while (K-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(trees.size());
	}

	static void spring() {
		// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
		// 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
		// 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
		// 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.

		Queue<Tree> tmp = new LinkedList<>();
		while (!trees.isEmpty()) {
			Tree t = trees.pollFirst();

			if (nowFood[t.r][t.c] >= t.age) {
				nowFood[t.r][t.c] -= t.age;
				t.age++;
				if (t.age % 5 == 0) {
					breedingTrees.add(t);
				}
				tmp.add(t);
			} else {
				deadTrees.offer(t);
			}
		}
		trees.addAll(tmp);
	}

	static void summer() {
		// 여름
		// 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
		// 소수점 아래는 버린다.
		while (!deadTrees.isEmpty()) {
			Tree t = deadTrees.poll();
			nowFood[t.r][t.c] += t.age / 2;
		}
	}

	static void fall() {
		// 가을
		// 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
		// 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1),
		// (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
		// 땅을 벗어나는 칸에는 나무가 생기지 않는다.
		int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dc = { -1, 0, 1, 1, -1, -1, 0, 1 };

		while (!breedingTrees.isEmpty()) {
			Tree t = breedingTrees.poll();
			for (int d = 0; d < 8; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];

				if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
					trees.addFirst(new Tree(nr, nc, 1));
				}
			}
		}
	}

	static void winter() {
		// 겨울
		// 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고
		// 입력으로 주어진다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nowFood[i][j] += plusFood[i][j];
			}
		}
	}
}

```

## ✅ 후기

### 상황에 맞는 적절한 자료구조를 알아내는 훈련을 한 것 같습니다. 평소 디큐(덱)를 많이 사용해 본적이 별로 없어서 낯설었기에 디큐를 떠올리기까지 시간이 좀 걸렸습니다. 이런 상황에 빠르게 대처할 수 있도록 다양한 자료구조를 항상 익숙한 상태를 유지하도록 자주 사용해야겠다는 생각이 들었습니다.