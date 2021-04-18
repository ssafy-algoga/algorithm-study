# 3613번 Java vs C++
[문제 보러가기](https://www.acmicpc.net/problem/3613)

## 🅰 설계

입력된 문자열의 패턴에 따라 변환하여 출력하는 문자열 문제입니다.

정규표현식으로 패턴을 필터링을 하여 풀었습니다.
         
---

### 1. C++ 형식

```java
if(str.matches("^[a-z][a-z_?]*[a-z]$") && !str.contains("__")) { // C++
	String[] word = str.split("_");
	
	out.append(word[0]);
	for (int i = 1; i < word.length; i++) { // 두번째 단어부터 첫글자 대문자로
		out.append( word[i].substring(0, 1).toUpperCase() + word[i].substring(1) );
	}
	
	System.out.println(out.toString());
	return;
}
```

C++형식의 변수명은 `^[a-z][a-z_]*[a-z]$` 로 변수의 양끝은 소문자이고 

사이에 소문자 or _ 가 반복되는 패턴을 찾고 `__`을 포함하는 문자열을 제외하였습니다.

---

### 2. Java

```java
if(str.matches("^[a-z][a-zA-Z]*$")) { // Java
	char[] chars = str.toCharArray();
	
	for (int i = 0; i < chars.length; i++) {
		if( Character.isUpperCase(chars[i]) ) { // 대문자 찾으면 (_소문자)로 변경
			out.append('_').append( Character.toLowerCase(chars[i]) );
		} else 
			out.append(chars[i]);
	}
	System.out.println(out.toString());
	return;
}
System.out.println("Error!");
```
Java 형식의 변수명은 첫글자는 소문자고 그후 알파벳이 반복되는 패턴을 찾았습니다.

두 패턴에 부합하지 않은 문자열을 Error를 출력합니다.

---

## ✅ 후기
정규표현식을 복습할 수 있어서 좋았습니다.
