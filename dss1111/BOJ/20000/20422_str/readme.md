# 20422번 퀼린드롬(Easy)
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계
```java
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makeMap();
		input = br.readLine().toCharArray();
		System.out.println(check());
```
문자열 입력받고 퀼린드롬표를 만든다음 결과를 출력하도록 설계했습니다.
기본 로직은 다음과 같습니다.  

1. 입력받은 문자열을 하나씩 읽으면서 퀼린드롬인지 확인한다.  
2. 퀼린드롬이면 결과문자열에 하나씩 추가한다. 퀼린드롬이 아니라면 대소문자 바꿔 한번 더 체크해본다.  

```java
	static char changeLetter(char l) {
		if((int)l >=65 &&  (int)l<=90) //대문자 알파벳
			return Character.toLowerCase(l);
		else if((int)l >=97 &&  (int)l<=122) 
			return Character.toUpperCase(l);
		else 
			return l; //숫자
	}
```
대소문자 바꾸는 changeLetter를 만들고 나서 생각해봤는데 바꿔서 테이블에 비교하느니 애초에 테이블에 넣어 놓고 생각하면 어떨까 생각하게 되었습니다.  
문자에서 소문자를 대문자로 출력해도 상관없다 했고 HashMap에서 key를 현재문자 value를 대칭인 문자로 매칭시키면 대소문자 변경을 빼도 되겠다 생각했습니다.  
```java
	static char getQu(char a) {
		if(map.containsKey(a)) {
			return map.get(a);
		}
		else 
			return '-';
	}
```
퀼린드롬 테이블에 매칭되지 않는 문자들도 있기 때문에 '-'을 임의로 리턴하도록 해주었습니다. 따라서 '-'가 리턴된다면 퀼린드롬이 아니게됩니다.

### 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	static HashMap <Character,Character> map = new HashMap<>();
	static char[] input;
	static StringBuilder sb1 = new StringBuilder();
	//static StringBuilder sb2 = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makeMap();
		input = br.readLine().toCharArray();
		System.out.println(check());
	}
	static String check() {
		int size =input.length;
		for(int i=0;i<size;i++) {
			if(size%2==1 && i == size/2) {
				sb1.append(input[i]);
				continue;
			}
			char front = getQu(input[i]);
			if(front=='-') { //매칭실패
				return "-1";
			}
      char back = getQu(input[size-i-1]);
			sb1.append(back);
		}
		return sb1.toString();
	}
	static char getQu(char a) {
		if(map.containsKey(a)) {
			return map.get(a);
		}
		else 
			return '-';
	}
	static void makeMap() {  //퀼린드롬표
		map.put('A', 'A'); map.put('a', 'A');
		map.put('B', 'd'); map.put('b', 'd');
		map.put('D', 'b'); map.put('d', 'b');
		map.put('E', '3'); map.put('e', '3');
		map.put('H', 'H'); map.put('h', 'H');
		map.put('I', 'I'); map.put('i', 'I');
		map.put('L', 'l'); map.put('l', 'l');
		map.put('M', 'M'); map.put('m', 'M');
		map.put('N', 'n'); map.put('n', 'n');
		map.put('O', 'O'); map.put('o', 'O');
		map.put('P', 'q'); map.put('p', 'q');
		map.put('Q', 'p'); map.put('q', 'p');
		map.put('R', '7'); map.put('r', '7');
		map.put('S', '2'); map.put('s', '2');
		map.put('T', 'T'); map.put('t', 'T');
		map.put('U', 'U'); map.put('u', 'U');
		map.put('V', 'V'); map.put('v', 'V');
		map.put('W', 'W'); map.put('w', 'W');
		map.put('X', 'X'); map.put('x', 'X');
		map.put('Y', 'Y'); map.put('y', 'Y');
		map.put('Z', '5'); map.put('z', '5');
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
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
없습니다.
### 고생한 점
이번에도 어마어마하게 헤맸습니다.  
처음에 입력된 문자열을 퀼린드롬으로 만들 수 있으면 퀼린드롬을 출력하고 아니면 -1을 출력한다는말을 잘못 이해했습니다.
dy2000 -> 좌우대칭이 아니므로 -1 출력인 줄 알았는데 예시를 보니 문자를 추가해서 만들 수 있는경우 퀼린드롬이더라구요.  
여기서 1시간 가량 걸렸고..  

AAATAAA 처럼 가운데 단어가 좌우 대칭으로 생긴 경우가 있고
AAABAAA 처럼 가운데 단어가 좌우 대칭이 아닌경우가 있는데,  
저는 위처럼 가운데 단어도 대칭이여야 퀼린드롬인 줄 알았습니다. 여기서 왜 틀리는지 몰라서 3시간..  
