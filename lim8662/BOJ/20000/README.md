# 20422번 퀼린드롬(Easy?)
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계

엣지 케이스를 조심해야 하는 문자열 문제입니다.

문자열의 양 끝에서 중앙으로 이동하며 문자를 비교해나가며 체크를 하였고

최대한 다양한 케이스들을 생각해보고 하나하나 처리하도록 구현하였습니다.


---

### 1. main

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class boj_20422_str {
	
	static HashMap<Character, Character> map = new HashMap<>();
	
	static void initMap() {
        map.put('A', 'A'); map.put('E', '3'); map.put('H', 'H');
        map.put('I', 'I'); map.put('M', 'M'); map.put('O', 'O');
        map.put('S', '2'); map.put('T', 'T'); map.put('U', 'U');
        map.put('V', 'V'); map.put('W', 'W'); map.put('X', 'X');
        map.put('Y', 'Y'); map.put('Z', '5'); map.put('b', 'd');
        map.put('d', 'b'); map.put('i', 'i'); map.put('l', 'l');
        map.put('m', 'm'); map.put('n', 'n'); map.put('o', 'o');
        map.put('p', 'q'); map.put('q', 'p'); map.put('r', '7');
        map.put('u', 'u'); map.put('v', 'v'); map.put('w', 'w');
        map.put('x', 'x'); map.put('0', '0'); map.put('1', '1');
        map.put('2', 'S'); map.put('3', 'E'); map.put('5', 'Z');
        map.put('7', 'r'); map.put('8', '8');
    }
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		initMap(); // 대칭 표

		System.out.println(solve(str));
	}

	public static String solve(String str) {
		int len = str.length();
		if(len == 0) return "-1"; // 길이가 0이면 무효
			
		int s = 0, e = len-1; // 문자열 시작과 끝 인덱스
		while(s <= e) {
			char lc = str.charAt(s); // 왼쪽 문자 
			char rc = str.charAt(e); // 오른쪽 문자
			if(map.containsKey(lc) && map.get(lc) == rc) { // 대칭이면 넘어감
				s++; e--; continue;
			}
			else { // 대칭이 아니면
				char nc; // 변환된 문자
				if(!map.containsKey(lc)) { // 대칭이 없는 문자를 대소문자 변환
					if(Character.isLowerCase(lc)) nc = Character.toUpperCase(lc);
					else nc = Character.toLowerCase(lc);
					
					if(!map.containsKey(nc)) return "-1"; // 바꾸어도 대칭이 없다면 무효
					
					if(map.get(nc) == rc) { // 대칭이면 바꾸고 넘어감
						str = str.replace(lc, nc);
						s++; e--; continue;
					} else if(lc == rc && map.get(nc) == nc){ // 원본이 같은 문자고 변환 문자가 홀로 대칭이면 둘다 변환
						str = str.replace(lc, nc);
						s++; e--; continue;
					} else if(!map.containsKey(rc)) { // 오른쪽 문자도 대칭이 없다면 변환
						char nrc; // 바꾼 오른쪽 문자
						if(Character.isLowerCase(rc)) nrc = Character.toUpperCase(rc);
						else nrc = Character.toLowerCase(rc);
						
						if(map.get(nc) == nrc) { // 대칭이면 바꾸고 넘어감
							str = str.replace(lc, nc);
							str = str.replace(rc, nrc);
							s++; e--; continue;
						} else {
							return "-1";
						}	
					} else {
						return "-1";
					}
					
				}
				else { // 오른쪽 문자가 대칭이 있다면 왼쪽 문자 변환
					if(Character.isLowerCase(rc)) nc = Character.toUpperCase(rc);
					else nc = Character.toLowerCase(rc);
				
					if(map.get(nc) == lc) { // 대칭이면 바꾸고 넘어감
						str = str.replace(rc, nc);
						s++; e--; continue;
					} else {
						return "-1";
					}
				}			
			}
		}
		return str;
	}
}

```


---

## ✅ 후기
제가 가장 기피하는 문제 유형으로 맨 마지막에 풀곤 합니다...  

시간은 시간대로 소비하고 히든 케이스 하나라도 놓치면

문제를 틀리는 경우가 빈번했기 때문입니다. 

저처럼 하나씩 예외처리를 하는 것보다 깔끔한 풀이 방법이 있을지 궁금합니다.




