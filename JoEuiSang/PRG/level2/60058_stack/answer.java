package _1월_5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 괄호변환 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String w = br.readLine();

		System.out.println(divide(w));

	}

	//문자열 w에 대해 u,v로 나누어 올바름, 균형잡힘 여부를 확인 후 답 리턴
	static String divide(String w) {
		if (w.equals(""))	//빈문자열 입력하면 리턴
			return "";

		int idx = 0;	//문자열의 인덱스변수
		int open = 0;	// ( 의 갯수
		int close = 0;	// ) 의 갯수
		String u;		// u를 담을 변수
		String v;		// v를 담을 변수
		String ans = ""; //답을 담을 변수

		// 문자열 w의 최대 길이까지 반복
		while (idx < w.length()) {
			char c = w.charAt(idx); // 현재 위치의 괄호

			if (c == '(') // open 괄호이면
				open++;
			else if (c == ')') // close 괄호이면
				close++;

			idx++;

			if (open == close && open != 0) { // 균형잡힌 괄호 문자열이 완성됐으면
				u = w.substring(0, idx);	//현재 인덱스를 기준으로 u와 v를 나누어줌
				v = w.substring(idx);
				
				// 올바른 괄호이면
				if (u.charAt(0) == '(') {
					ans = u + divide(v);
					return ans;
				} else {// 올바르지 않은 괄호이면
					ans = "(" + divide(v) + ")" + trim(u);
					return ans;
				}
			}
		}
		return ans;
	}

	//올바르지않은 괄호 문자열 다듬는 함수
	static String trim(String u) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < u.length() - 1; i++) {
			if (u.charAt(i) == '(')
				sb.append(")");
			else
				sb.append("(");
		}
		return sb.toString();
	}
}
