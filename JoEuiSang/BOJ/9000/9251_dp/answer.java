package _1월_5주차;  

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LCS {
	static int size1; // 수열1의 길이
	static int size2; // 수열2의 길이
	static int answer = 0; // 답을 저장할 변수
	static String s1;
	static String s2;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("LCS.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		s1 = br.readLine();
		s2 = br.readLine();

		size1 = s1.length();
		size2 = s2.length();

		equalCheck(0, 0, 0);
		System.out.println(answer);
	}

	// 각 수열의 자리를 비교하여 재귀적으로 처리하는 함수 (수열1의 인덱스, 수열2의 인덱스, 맞은횟수)
	static void equalCheck(int idx1, int idx2, int equal) {
		// 위치가 문자열안에 있으면
		if (idx1 < size1 && idx2 < size2) {
			// 각 문자가 일치하면
			if (s1.charAt(idx1) == s2.charAt(idx2)) {
				if (answer < ++equal) // 증가 후 현재 횟수보다 크면 정답 갱신
					answer = equal;

				// 더 체크할 문자가 있으면 재귀
				if (idx1 < size1 - 1 && idx2 < size2 - 1) {
					equalCheck(idx1 + 1, idx2 + 1, equal);
				} else {// 없으면 종료
					return;
				}
			} else { // 각 문자가 일치하지 않으면 분기하여 재귀
				equalCheck(idx1 + 1, idx2, equal);
				equalCheck(idx1, idx2 + 1, equal);
			}
		}else return;
	}
}
