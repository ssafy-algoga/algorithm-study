package 스터디._3월4주;

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

	private static void fall() {
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

	private static void winter() {
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
