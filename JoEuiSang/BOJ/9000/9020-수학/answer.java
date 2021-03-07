package 스터디._2월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9020골드바흐의추측 {
	static boolean number[];
	static int prime[], primeCnt;

	public static void main(String[] args) throws Exception {
		// 소수 구분해주기
		number = new boolean[10001];
		for (int i = 2; i <= 10000; i++) {
			//현재 i가 소수이면, 소수의 배수는 합성수이다.
			if (number[i] == false) {
				primeCnt++;	//소수 배열 사이즈 카운트
				for (int j = 2; j * i <= 10000; j++) {
					number[i * j] = true;
				}
			}
		}

		// 소수만 모아놓은 배열 선언
		prime = new int[primeCnt];
		int idx = 0;
		for (int i = 2; i <= 10000; i++) {
			if (!number[i])
				prime[idx++] = i;
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테케입력
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			int front = 0, back = 0;

			//시작 인덱스 잡아주기
			for (int i = 0; i < primeCnt; i++) {
				//입력 정수 N의 반절보다 현재 소수가 크다면 바로 전 소수를 시작인덱스를 잡아주었다.
			    //N=10일경우 N/2 는 5이고, 5보다 큰 소수는 7이고 바로 전의 소수는 5이기때문에
				if (prime[i] > N / 2) {
					front = i - 1;
					back = i - 1;
					break;
				}
			}

			// 소수의 합이 N일 때 까지 반복
			while (prime[front] + prime[back] != N) {
				if (prime[front] + prime[back] > N) {
					front--;
				} else if (prime[front] + prime[back] < N) {
					back++;
				}
			}
			System.out.println(prime[front] + " " + prime[back]);
		}
	}
}