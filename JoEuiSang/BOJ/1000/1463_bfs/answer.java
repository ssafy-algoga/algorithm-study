package 스터디._2월2주;

import java.util.Scanner;

public class _1로만들기_bottomUp {
	static int N;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N + 1];

		//0과 1값 0셋팅
		arr[0] = 0;
		arr[1] = 0;

		//2~N까지 반복
		for (int i = 2; i < N + 1; i++) {
			arr[i] = arr[i - 1] + 1;	//현재는 앞에꺼 + 1번
			if (i % 2 == 0)				//현재가 2로 나눠지면
				arr[i] = Math.min(arr[i], arr[i / 2] + 1);	//현재와  (나누기2)+1 중 작은값 대입 
			if (i % 3 == 0)				//3으로 나눠지면
				arr[i] = Math.min(arr[i], arr[i / 3] + 1);	//작은값 대입
		}
		System.out.println(arr[N]);

	}
}
