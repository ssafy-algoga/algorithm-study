package 스터디._2월1주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질 {
	static int N;
	static int K;
	static int[] time;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = new int[100001];

		move(N);
		System.out.println(time[K]);

	}

	private static void move(int N) {
		queue.offer(N);

		while (!queue.isEmpty()) {
			int X = queue.poll();

			if (X == K)
				break;

			if (X - 1 >= 0 && time[X - 1] == 0) {
				queue.offer(X - 1);
				time[X - 1] = time[X] + 1;
			}
			if (X + 1 <= 100000 && time[X + 1] == 0) {
				queue.offer(X + 1);
				time[X + 1] = time[X] + 1;
			}
			if (X * 2 <= 100000 && time[X * 2] == 0) {
				queue.offer(X * 2);
				time[X * 2] = time[X] + 1;
			}
		}
	}
}
