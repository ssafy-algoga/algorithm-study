# 3613번 Java vs C++
[문제 보러가기](https://www.acmicpc.net/problem/3613)

## 🅰 설계
입력 String이 Java형식을 따르는지 C++형식을 따르는지 체크하고 변환시켜야 합니다.

### 입력

#### Java형식
1. 첫 단어는 소문자로 쓴다.
2. 다음 단어부터는 첫 문자만 대문자로 쓴다.
3. 모든 단어는 붙여쓴다.

Java형식인지 체크해주려면 위의 조건들을 체크해주면 됩니다.  

그런데 입력에서는 단어와 단어가 구분이 안되기 때문에 예시에 `bAEKJOON`과같은 형태도 가능한걸 보여준 것 같습니다.  

입력은 알파벳과 `_`만 들어오므로 결론적으로 1. 첫번째 문자가 대문자가 아니고 2. 언더바가 없으면 자바형식입니다.  


#### C++형식
1. 변수명에 소문자만 사용한다.
2. 단어와 단어를 구분하기 위해 `_`를 사용한다.

C++형식은 1. 대문자가 있으면 안되고 2. 단어와 단어 사이에 `_`가 있어야 합니다.  

2번 조건을 파고들면 더 작은 조건들로 쪼갤 수 있습니다.  

2-1. 입력의 처음, 마지막에는 `_`를 사용할 수 없다.  
2-2. `_`는 연속해서 나타날 수 없다.  

```java
int underbar_streak = 0; // 연속해서 언더바가 나오는 경우
boolean java = false,c = false,error = false; // java인 경우, C++인 경우, 조건에 맞지 않는 경우
for(int i=0;i<in.length();i++) {
	char cur = in.charAt(i);
	if(cur == '_') { // _를 사용하면 C++형식일 가능성이 있음
		c = true;
		if(i == in.length()-1 || i == 0 || underbar_streak == 1) error = true; // _를 사용하면서 C++형식에 위배됨
		underbar_streak++; // 연속해서 나오는 _ 개수를 위해 값을 증가시킴
	}
	else if(cur >= 'A' && cur <= 'Z') { // 대문자를 사용하면 Java형식일 가능성이 있음
		java = true;
		if(i == 0) error = true; // 첫 문자가 대문자면 Java형식에 위배됨
		underbar_streak = 0; // _의 개수 초기화
	}
	else underbar_streak = 0; // _의 개수 초기화
}
if(java && c || error) {
	System.out.println("Error!");
	return;
}
```
위의 내용을 반영한 형식 체크입니다.  

Java형식을 따르면서 C++형식을 동시에 따르거나, 위의 조건에 위배되면 올바른 형식이 아닙니다.  

`underbar_streak`은 boolean으로 해도 될것같습니다. 저는 숫자로 보는게 편해서 int로 사용했습니다.  

### 변환

#### Java to C++
Java형식을 체크한 후에 C++형식으로 변환한다면 대문자인 캐릭터를 언더바 + 소문자로 바꿔주면 됩니다.  
```java
static String toC(String in) {
	StringBuilder sb = new StringBuilder();
	for(int i=0;i<in.length();i++) {
		char cur = in.charAt(i);
		if(cur >= 'A' && cur <= 'Z') { // 대문자인 경우
			sb.append('_').append(Character.toLowerCase(cur)); // _를 추가하고 lowercase로 변환
		}
		else sb.append(cur);
	}
	return sb.toString();
}
```

#### C++ to Java
C++형식을 체크한 후에 Java형식으로 변환한다면 언더바인 캐릭터를 없애고 그 다음 문자를 대문자로 바꿔주면 됩니다.
```java
static String toJava(String in) {
	StringBuilder sb = new StringBuilder();
	for(int i=0;i<in.length();i++) {
		char cur = in.charAt(i);
		if(cur == '_') { // _인 경우
			sb.append(Character.toUpperCase(in.charAt(++i))); // _는 무시하고 다음 문자를 uppercase로 변환
		}
		else sb.append(cur);
	}
	return sb.toString();
}
```
### 전체코드
```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		
		int underbar_streak = 0; // 연속해서 언더바가 나오는 경우
		boolean java = false,c = false,error = false; // java인 경우, C++인 경우, 조건에 맞지 않는 경우
		for(int i=0;i<in.length();i++) {
			char cur = in.charAt(i);
			if(cur == '_') { // _를 사용하면 C++형식일 가능성이 있음
				c = true;
				if(i == in.length()-1 || i == 0 || underbar_streak == 1) error = true; // _를 사용하면서 C++형식에 위배됨
				underbar_streak++; // 연속해서 나오는 _ 개수를 위해 값을 증가시킴
			}
			else if(cur >= 'A' && cur <= 'Z') { // 대문자를 사용하면 Java형식일 가능성이 있음
				java = true;
				if(i == 0) error = true; // 첫 문자가 대문자면 Java형식에 위배됨
				underbar_streak = 0; // _의 개수 초기화
			}
			else underbar_streak = 0; // _의 개수 초기화
		}
		if(java && c || error) {
			System.out.println("Error!");
			return;
		}
		if(java) System.out.println(toC(in));
		else System.out.println(toJava(in));
	}
	
	static String toC(String in) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<in.length();i++) {
			char cur = in.charAt(i);
			if(cur >= 'A' && cur <= 'Z') { // 대문자인 경우
				sb.append('_').append(Character.toLowerCase(cur)); // _를 추가하고 lowercase로 변환
			}
			else sb.append(cur);
		}
		return sb.toString();
	}
	
	static String toJava(String in) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<in.length();i++) {
			char cur = in.charAt(i);
			if(cur == '_') { // _인 경우
				sb.append(Character.toUpperCase(in.charAt(++i))); // _는 무시하고 다음 문자를 uppercase로 변환
			}
			else sb.append(cur);
		}
		return sb.toString();
	}
}
```


## ✅ 후기
개인적으로 문자열 처리를 좋아하는 코딩테스트와 굉장히 비슷한 문제라고 생각했습니다.  
조건에 따른 구현만 하면 되는 문제지만 정답률이 보여주듯이 생각보다 까다로운 문제입니다.
