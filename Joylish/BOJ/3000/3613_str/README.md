# 3613번 Java VS C++

[문제 보러가기](https://www.acmicpc.net/problem/3613)

## 🅰 설계

이 문제는 정규표현식을 공부하고 최대한 정규표현식을 이용하여 풀려고 노력했습니다. 

### 1. 예외처리

입력받는 문자열이 다음 조건을 지키지 않을 경우 "Error!"를 출력하고 실행을 종료시켰습니다.

```markdown
1. 영소문자 또는 대소문자 또는 `_`가 아닌 문자가 있을 경우 `[^a-zA-Z_]`
2. 첫 번째 단어가 영소문자가 아닐 경우 `^[^a-z]`
3. 마지막 문자가 `_`일 경우 `[_]$`
4. `_`가 연속으로 2번이상 반복될 경우 `[_]{2}`
5. 영대문자와 `_`가 동시에 있을 경우 `[A-Z].*[_]|[_].*[A-Z]`
```

```java
String patternE = "[^a-zA-Z_]|^[^a-z]|[_]$|[_]{2}|[A-Z].*[_]|[_].*[A-Z]";
Matcher matcherE = Pattern.compile(patternE).matcher(str);
if(matcherE.find()){
    System.out.println("Error!");
    return;
}
```

### 2. StringBuffer에 첫 번째 단어 추가

첫 번째 단어는 언어에 상관없이 영소문자로 추가되어 `StringBuffer`에 먼저 추가했습니다.

```java
StringBuffer sb = new StringBuffer();
Matcher matcherS = Pattern.compile("^[a-z]+").matcher(str);
while (matcherS.find()) {
    String start = matcherS.group();
    sb.append(start);
}
```

### 3. 언어 변환

#### C++ → Java

matcherC를 이용하여 처음 `_`가 나온 이후 문자열, 즉 첫 단어를 제외한 문자열을 이용하였습니다.

(예시) `long_and_mnemonic_identifier`일 경우  `_and_mnemonic_identifier`

그 다음 C++ 형식`_[영소문자]` 을 Java 형식`영대문자`으로 바꾸었습니다.

```java
Matcher matcherC = Pattern.compile("[_]+.*").matcher(str); 
if (matcherC.find()) {
    Pattern pattern = Pattern.compile("(_[a-z])");
    matcherC = pattern.matcher(matcherC.group());
    while (matcherC.find()) {
        String upper = matcherC.group().replace("_", "").toUpperCase();
        matcherC.appendReplacement(sb, upper);
    }
    matcherC.appendTail(sb);
}
```

#### Java → C++

matcherJ를 이용하여처음 영대문자가 나온 이후 문자열, 즉 첫 단어를 제외한 문자열을 이용하였습니다. 

(예시) `bAEKJOON`일 경우  `AEKJOON`

그 다음 Java 형식`영대문자`을 C++ 형식`_[영소문자]` 으로 바꾸었습니다.

```java
Matcher matcherJ = Pattern.compile("[A-Z]+.*").matcher(str);
else if(matcherJ.find()) {
    Pattern pattern = Pattern.compile("([A-Z])");
    matcherJ = pattern.matcher(matcherJ.group());
    while (matcherJ.find()) {
        String lower = "_".concat(matcherJ.group().toLowerCase());
        matcherJ.appendReplacement(sb, lower);
    }
    matcherJ.appendTail(sb);
}
```



## ✅ 후기

- 처음 예외처리할 때 정규표현식을 이용해서 `올바른 문자열 형태가 아닐 경우`를 체크하고 싶었습니다. 하지만 아직 정규표현식을 다루는 능력이 부족해서 예외인 경우를 찾아 `|`로 나열했습니다. 

- 자바스크립트로 문자열 문제를 풀 때 정규표현식을 사용한 적이 있습니다. 이번 문제를 풀면서 다시 정규표현식을 복습했습니다. 느낀점은 정규표현식은 해도 해도 새롭고.. 끝이 알 수 없는 녀석..이란 걸 알게 되었습니다 ^ -^

  **어려웠던 부분**

  - ?와 ??, 역참조, 그룹화와 캡처화,  후방탐색, 전방탐색, ...

  - 공부가 더 필요한 정규표현식

    | 공부가 더 필요한 정규표현식                                  |
    | ------------------------------------------------------------ |
    | [비캡처링 그룹화`(?:x)`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Regular_Expressions#special-non-capturing-parentheses) |
    | [`x(?=y)`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Regular_Expressions#special-lookahead) |
    | [`x(?!y)`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Regular_Expressions#special-negated-look-ahead) |

  기본 정규표현식 관련 설명은 [자바스크립트의 MDN](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Regular_Expressions)과 [블로그 글](https://wormwlrm.github.io/2020/07/19/Regular-Expressions-Tutorial.html)을 참고했습니다.

  정규표현식 관련 Java 메서드를 공부해볼 수 있어 좋았습니다.

  - `java.util.regex.Pattern`
  - `java.util.regex.Matcher`

  처음에 C++ 형식에 따라 `영대문자`를 `_영소문자`로 바꿀 때 `replaceAll`을 이용해서 바꾸고 싶었습니다.`replaceAll()`을 이용하면 패턴(정규표현식)에 해당하는 문자열을 `특정 문자열`로 바꾸는 것까지 성공했습니다. 하지만 기존단어를 소문자로 변환시키는 방법은 모르겠어서 검색해보았더니 비슷한 경험을 했던 블로거를 보았습니다. [이 블로그 글](https://heodolf.tistory.com/98)을 참조하여 `Matcher`의 `group`,`appendReplacement`, `appendTail()`으로 문자열에서 맨 앞에 있는 단어 하나를 대소문자로 치환할 수 있었습니다.  

  