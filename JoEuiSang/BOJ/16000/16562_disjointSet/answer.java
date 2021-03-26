package 스터디._3월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16562친구비 {
	static int N, M, K;
	static int[] friendGroup, money;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 친구관계 수 (간선 수)
		K = Integer.parseInt(st.nextToken()); // 가진 돈
		friendGroup = new int[N + 1]; // 친구 그룹을 나타낸다.
		money = new int[N + 1]; // 친구비 용

		// 친구비 셋팅
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}

		// 친구 그룹 작업셋팅
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}

		int price = 0;

		for (int i = 1; i <= N; i++) {
			if (friendGroup[i] < 0) {
				price += money[i];
				if (price > K) {
					System.out.println("Oh no");
					return;
				}
			}
		}
		System.out.println(price);
	}

	private static void make() {
		for (int i = 0; i <= N; i++) {
			friendGroup[i] = -1;
		}
	}

	static boolean union(int s1, int s2) {
		int s1Root = findRoot(s1);
		int s2Root = findRoot(s2);

		if (s1Root == s2Root) // 둘이 친구 그룹이 같으면
			return false;

		int small = 0;
		int big = 0;
		if (money[s1Root] < money[s2Root]) {
			small = s1Root;
			big = s2Root;
		} else {
			small = s2Root;
			big = s1Root;
		}

		friendGroup[big] = small;	//친구비가 큰놈의 대장을 친구비가 작은놈으로 바꿔준다
		return true;
	}

	static int findRoot(int s) {
		if (friendGroup[s] < 0)
			return s;

		return friendGroup[s] = findRoot(friendGroup[s]);
	}
}
