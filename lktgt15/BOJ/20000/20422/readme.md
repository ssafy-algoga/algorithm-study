# 20422번 퀼린드롬(Easy)
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계
### 1. 대칭 변환
퀼린드롬 문제의 원본-대칭 매칭 표를 보고 하나하나 `HashMap`에 넣어 대칭은 원본으로, 원본은 대칭으로 변환시킬 수 있게 하였습니다.  

이 때, 대칭을 일관적으로 해주는게 중요했습니다.  

1. 대,소문자의 대칭문자가 같은 경우, 한쪽으로 일관적으로 Mapping (ex. W->W, w->W)  
2. 대,소문자의 대칭문자가 다른 경우, 대칭문자가 있는쪽으로 Mapping (ex. B->d, b->d)  
3. 둘 다 대칭문자가 없는 경우, 한쪽으로 일관적으로 Mapping (ex. F->F, f->F)  
```java
mp.put('B', 'd'); mp.put('b', 'd');
mp.put('D', 'b'); mp.put('d', 'b');
mp.put('E', '3'); mp.put('e', '3');
mp.put('F', 'F'); mp.put('f', 'F');
```

이렇게 하면 대소문자 구분 없이 대칭문자를 찾아낼 수 있습니다.  

### 2. 대칭 확인
for문을 돌면서, 현재 문자의 대칭점에 있는 문자가 위에서 등록한 대칭문자가 맞는지 확인합니다.  

```java
String in = br.readLine();
StringBuilder sb = new StringBuilder();
int size = in.length();
for(int i=0;i<size;i++) {
	if((size&1) == 1 && i == size/2) {
		sb.append(in.charAt(i));
		continue;
	}
	char fC = mp.get(in.charAt(i));
	char bC = mp.get(in.charAt(size-i-1));
	fC = mp.get(fC);
	if(fC != bC) return "-1";
	sb.append(fC);
}
```

여기서 `fC`는 앞쪽 문자, `bC`는 뒤쪽 문자가 됩니다.  

`fC`는 대칭문자 Mapping을 두번 시켰는데, 대소문자를 구분하지 않기위한 방법이었습니다.  

예를 들어 i에 있는 문자가 B였을 경우, B의 대칭문자는 d이고 d의 대칭문자는 b이므로 이대로 비교하면 오류가 납니다.  

B를 그대로 비교하는 대신 Mapping을 두번 시켜주면, B->d, d->b로 변환되어 예외없이 대칭검증이 가능합니다.  

이렇게 for문을 돌면서 `fC`와 `bC`가 같은지 확인만 해주면 됩니다.  

### 3. 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static HashMap<Character,Character> mp = new HashMap();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		mp.put('A', 'A'); mp.put('a', 'A');
		
		mp.put('B', 'd'); mp.put('b', 'd');
		
		mp.put('C', 'C'); mp.put('c', 'C');
		
		mp.put('D', 'b'); mp.put('d', 'b');
		
		mp.put('E', '3'); mp.put('e', '3');
		
		mp.put('F', 'F'); mp.put('f', 'F');
		
		mp.put('G', 'G'); mp.put('g', 'G');
		
		mp.put('H', 'H'); mp.put('h', 'H');
		
		mp.put('I', 'I'); mp.put('i', 'I');
		
		mp.put('J', 'J'); mp.put('j', 'J');
		
		mp.put('K', 'K'); mp.put('k', 'K');
		
		mp.put('L', 'l'); mp.put('l', 'l');
		
		mp.put('M', 'M'); mp.put('m', 'M');
		
		mp.put('N', 'n'); mp.put('n', 'n');
		
		mp.put('O', 'O'); mp.put('o', 'O');
		
		mp.put('P', 'q'); mp.put('p', 'q');
		
		mp.put('Q', 'p'); mp.put('q', 'p');
		
		mp.put('R', '7'); mp.put('r', '7');
		
		mp.put('S', '2'); mp.put('s', '2');
		
		mp.put('T', 'T'); mp.put('t', 'T');

		mp.put('U', 'U'); mp.put('u', 'U');

		mp.put('V', 'V'); mp.put('v', 'V');

		mp.put('W', 'W'); mp.put('w', 'W');

		mp.put('X', 'X'); mp.put('x', 'X');

		mp.put('Y', 'Y'); mp.put('y', 'Y');

		mp.put('Z', '5'); mp.put('z', '5');
		
		mp.put('0', '0');
		mp.put('1', '1');
		mp.put('2', 'S');
		mp.put('3', 'E');
		mp.put('4', '4');
		mp.put('5', 'Z');
		mp.put('6', '6');
		mp.put('7', 'r');
		mp.put('8', '8');
		mp.put('9', '9');
		
		System.out.println(solve());
	}
	
	static String solve() throws IOException {
		String in = br.readLine();
		StringBuilder sb = new StringBuilder();
		int size = in.length();
		for(int i=0;i<size;i++) {
			if((size&1) == 1 && i == size/2) { // 길이가 홀수인 경우 가운데 문자는 무조건 대칭임
				sb.append(in.charAt(i));
				continue;
			}
			char fC = mp.get(in.charAt(i)); // 앞 쪽 문자의 대칭문자
			char bC = mp.get(in.charAt(size-i-1)); // 뒤 쪽 문자의 대칭문자
			fC = mp.get(fC); // 앞 쪽 문자의 대칭문자의 대칭문자
			if(fC != bC) return "-1";
			sb.append(bC);
		}
		
		return sb.toString();
	}
}
```

## ✅ 후기
`HashMap`에 대칭문자를 하드코딩하다보니 실수나기가 쉬운 문제였던것 같습니다.  
*Manacher*라는 알고리즘이 있던데 코딩테스트에 나오기에는 너무 마이너하지않나.. 생각해서 굳이 찾아보지는 않았습니다.  
