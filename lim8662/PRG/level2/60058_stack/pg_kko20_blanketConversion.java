
public class pg_kko20_blanketConversion {

	// 프로그래머스 카카오 20 lv2 괄호변환
	// https://programmers.co.kr/learn/courses/30/lessons/60058
	public static void main(String[] args) {
		System.out.println(new pg_kko20_blanketConversion().solution("()))((()"));
	}

	public String conversion(String p) {
		StringBuilder sb = new StringBuilder();

		if (p.equals(""))
			return p;

		int lcnt = 0;
		int rcnt = 0;
		String u = "";
		String v = "";
		// 2
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(')
				lcnt++;
			else
				rcnt++;
			// 분리
			if (lcnt == rcnt) {
				u = p.substring(0, i + 1);
				v = p.substring(i + 1);
				break;
			}
		}
		// 3
		if (isMatch(u)) {
			sb.append(u).append(conversion(v));
		}
		// 4
		else {
			sb.append('(').append(conversion(v)).append(')');
			for (int i = 1; i < u.length() - 1; i++) {
				if (u.charAt(i) == '(')
					sb.append(')');
				else
					sb.append('(');
			}
		}

		return sb.toString();
	}

	// 올바른 문자열인지 확인
	public boolean isMatch(String s) {
		int pair = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				pair++;
			else
				pair--;

			if (pair < 0) {
				return false;
			}
		}
		return true;
	}

	public String solution(String p) {
		StringBuilder sb = new StringBuilder();

		sb.append(conversion(p));

		return sb.toString();
	}

}
