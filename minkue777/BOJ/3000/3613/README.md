# 3613번 Java vs C++
[문제 보러가기](https://www.acmicpc.net/problem/3613)

## 🅰 설계
메소드를 4개로 분리했습니다.
1. C++ 변수명인지 검사
2. java 변수명인지 검사
3. C++ 변수명을 java 변수명으로 변경
4. java 변수명을 C++ 변수명으로 변경

사실 _a, a__b, a_ 같은 변수명이 C++ 변수명에 해당하지 않는다는걸
일찍 알았으면 설계를 좀 다르게 했을 것 같은데 나중에 틀리고 나서
반례를 찾다가 알아서 전체적인 설계가 꼬인 느낌이 있습니다..
처음부터 두 카테고리에 해당하지 않는 변수명을 필터링했다면 훨씬 깔끔한
설계가 되지 않을까 싶습니다.

C++ 검사는 _를 delimeter로 구분하면 쉽게 체크할 수 있고
java 검사 역시 대문자를 기준으로 구분하면 됩니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static boolean isJavaStyle(String str) {
        if(str.contains("_")) return false;
        String[] strArr = str.split("(?=[A-Z])");
        for(int idx = 0 ; idx <strArr.length; idx++) {
            char ch = strArr[idx].charAt(0);
            if(idx == 0 && !Character.isLowerCase(ch)) {
                return false;
            } else if (idx > 0 && !Character.isUpperCase(ch)) {
                return false;
            }
        }
        return true;
    }

    static boolean isCStyle(String str) {
        if(str.contains("__")) return false;
        if(str.charAt(str.length()-1) == '_') return false;
        String[] strArr = str.split("_");
        for(String s : strArr) {
            if(s.equals("") || !s.equals(s.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    static String cToJava(String str) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = str.split("_");
        for(int idx = 0; idx < strArr.length; idx++) {
            String s = strArr[idx];
            sb.append(idx == 0 ? s.charAt(0) : Character.toUpperCase(s.charAt(0)));
            sb.append(s.substring(1));
        }
        return sb.toString();
    }

    static String javaToC(String str) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = str.split("(?=[A-Z])");
        for(int idx = 0; idx < strArr.length; idx++) {
            String s = strArr[idx];
            sb.append(Character.toLowerCase(s.charAt(0)))
                    .append(s.substring(1));
            if(idx != strArr.length-1) {
                sb.append("_");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        if(isCStyle(input)) {
            System.out.println(cToJava(input));
        } else if(isJavaStyle(input)) {
            System.out.println(javaToC(input));
        } else {
            System.out.println("Error!");
        }
    }
}
```

## ✅ 후기
정규표현식을 연습하기 위한 좋은 문제라고 생각했지만 생각만큼 많이
활용하지 못해서 아쉽네요. 정규표현식을 더 많이 사용해서 다시 풀어봐야겠습니다.