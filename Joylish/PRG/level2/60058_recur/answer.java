package com.programmers.괄호변환;

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("(()())()"));
//		System.out.println(s.solution("()"));
		System.out.println(s.solution("()))((()"));
	}
}

class Solution {

	public String solution(String p) {
		return checkP(p);
	}

	public String checkP(String p) {
		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
		if (p.equals(""))
			return p;
		// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
		int point = findCuttingPoint(p);
		String u = p.substring(0, point);
		String v = p.substring(point, p.length());
		// 3. 문자열 u가 "올바른 괄호 문자열" 이라면
		// 문자열 v에 대해 1단계부터 다시 수행합니다.
		if (isCorrect(u)) {
			return u + checkP(v);
		} else {
			// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
			// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
			// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
			// 4-3. ')'를 다시 붙입니다.
			String _v = '(' + checkP(v) + ')';
			// 4-4. u의 첫 번째와 마지막 문자를 제거하고,
			// 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
			u = u.substring(1,u.length()-1);
			return _v + reverse(u);
		}
	}

	private boolean isCorrect(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				++count;
			else if (s.charAt(i) == ')')
				--count;
			if (count < 0)
				return false;
		}
		return true;
	}

	public int findCuttingPoint(String s) {
		int openNum = 0;
		int closeNum = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				++openNum;
			else if (s.charAt(i) == ')')
				++closeNum;
			if (openNum == closeNum)
				return i + 1;
		}
		return -1;
	}

	public String reverse(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') result += '(';
			else if (s.charAt(i) == '(') result += ')';
		}
		return result;
	}
}