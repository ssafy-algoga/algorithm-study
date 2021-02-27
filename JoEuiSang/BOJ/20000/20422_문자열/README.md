# 20422 퀼린드롬
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계
문제를 잘 이해하지 못한 채 풀어서 잘못된 방법으로 접근하였다.

처음 시도한 방법으로는, N길이의 문자열을 가운데 기준으로 앞뒤로 같은 위치에 있는 두 문자를 비교하여 서로 대소변환을 하여 대칭관계를 이루면 정답에 넣는 방식으로 하여 상당히 삽질을 많이하였다.

뒤늦게 문제를 제대로 파악하였고, 문제가 요구하는 풀이는 문자열을 반으로 잘라 뒤쪽 그룹은 버린뒤 앞에서부터 검사하여 대칭문자가 있다면 함께 넣어주는 방식이었고 쉽게 풀 수 있었다.

문자열을 이용한 구현문제이기 때문에 특별한 풀이 방법은 없이 조건만 잘 따져준다면, 쉽게 풀 수 있는 문제입니다.






## 전체 코드
```java
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

		// 중간 인덱스 설정
		if (str.length % 2 == 0) {
			mid = str.length / 2;
		} else {
			mid = (str.length / 2) + 1;
		}

		for (int i = 0; i < mid; i++) {
			int idxB = str.length - 1 - i;

			if (i == mid - 1) { // 마지막 검사위치일때
				if (str.length % 2 == 1) { // 홀수 퀼린드롬이면
					if (Character.isAlphabetic(str[i])) { // 알파벳일떄
						// 대칭표에 있고, 대칭과 원본이 같은 값이면
						if (map.containsKey(str[i]) && map.get(str[i]) == str[i]) {
							ans[i] = str[i];
							break;
						}// 대칭표에 있고, 대칭과 원본이 다른 값이면 대소 변환 해보기
						else if(map.containsKey(str[i]) && map.get(str[i]) != str[i]) {
							if(smallToBig(i)) return;
						}
						if (!map.containsKey(str[i])) { //대칭표에없으면 대소 변환후 대칭표보기
							if(smallToBig(i)) return;
						}

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
	
	//트루이면 퀼린드롬 불가능
	private static boolean smallToBig(int i) {
		char c;
		if (Character.isUpperCase(str[i])) { // 대문자이면 소문자로
			c = Character.toLowerCase(str[i]);
		} else { // 소문자이면 대문자로
			c = Character.toUpperCase(str[i]);
		}
		
		if (map.containsKey(c) && map.get(c) == c) {
			ans[i] = c;
			return false;
		}
		System.out.println("-1");
		return true;
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

```

### 

## ✅ 후기
### 가장 중요한것은 자료구조를 잘 쓰는 것도 알고리즘을 잘 구현하는 것도 아닌 문제를 제대로 이해하는 것이다.