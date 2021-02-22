package 스터디._2월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Boj20422퀼린드롬 {
	static HashMap<Character, Character> map = new HashMap<Character, Character>();
	static char[] str, ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		str = br.readLine().toCharArray();
		ans = new char[str.length];
		setMap();

		int mid = 0;
		
		//중간 인덱스 설정
		if (str.length % 2 == 0) {
			mid = str.length / 2;
		} else {
			mid = (str.length / 2) + 1;
		}
		
		
		for (int i = 0; i < mid; i++) {
			int idxB = str.length - 1 - i;

			if (i == mid - 1) { // 마지막 검사위치일때
				if (str.length % 2 == 1) { // 홀수 퀼린드롬이면
					// 대칭표에 있고, 대칭과 원본이 같은 값이면
					if (map.containsKey(str[i]) && map.get(str[i]) == str[i]) {
						ans[i] = str[i];
						break;
					}
				}
			}

			// 아닐때
			if (map.containsKey(str[i])) { // 대칭표에 있으면 현재값과 대칭값 넣어주기
				ans[i] = str[i];
				ans[idxB] = map.get(str[i]);
			} else {// 대칭표에 없을때
				char c;
				if (Character.isAlphabetic(str[i])) { // 알파벳이면
					if (Character.isUpperCase(str[i])) { // 대문자이면 소문자로
						c = Character.toLowerCase(str[i]);
					} else { // 소문자이면 대문자로
						c = Character.toUpperCase(str[i]);
					}

					if (map.containsKey(c)) { // 대소변환한 문자가 대칭표에 있으면 현재값과 대칭값 넣어주기
						ans[i] = c;
						ans[idxB] = map.get(c);
					} else {
						System.out.println("-1");
						return;
					}
				} else {
					System.out.println("-1");
					return;
				}
			}

		}

		for (char c : ans) {
			sb.append(c);
		}
		System.out.println(sb.toString());
	}

	private static void setMap() {
		map.put('A', 'A');
		map.put('E', '3');
		map.put('H', 'H');
		map.put('I', 'I');
		map.put('M', 'M');
		map.put('O', 'O');
		map.put('S', '2');
		map.put('T', 'T');
		map.put('U', 'U');
		map.put('V', 'V');
		map.put('W', 'W');
		map.put('X', 'X');
		map.put('Y', 'Y');
		map.put('Z', '5');
		map.put('b', 'd');
		map.put('d', 'b');
		map.put('i', 'i');
		map.put('l', 'l');
		map.put('m', 'm');
		map.put('n', 'n');
		map.put('o', 'o');
		map.put('p', 'q');
		map.put('q', 'p');
		map.put('r', '7');
		map.put('u', 'u');
		map.put('v', 'v');
		map.put('w', 'w');
		map.put('x', 'x');
		map.put('0', '0');
		map.put('1', '1');
		map.put('2', 'S');
		map.put('3', 'E');
		map.put('5', 'Z');
		map.put('7', 'r');
		map.put('8', '8');
	}

}
