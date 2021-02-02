# kakao 2020 괄호변환
[문제 보러가기](https://programmers.co.kr/learn/courses/30/lessons/60058)

## 🅰 설계
위에서부터 순차적으로 수행하면 되는 친절한 문제였지만 자바로 문자열을 다루는게
파이썬에 비해 조금 불편하다는 느낌을 줍니다. 디버깅의 편의를 위해
메소드 두 가지를 따로 구현했습니다. 그 외는 다른 분들과 동일한 과정입니다

```java 
static int shortestBalancedSubstringIdx(String w) {
    int flag = 0;
    for(int i=0; i<w.length(); ++i) {
        flag += (w.charAt(i) == '(') ? 1 : -1;
        if (flag == 0) return i;
    }
    return w.length()-1;
}
```

```java 
static boolean isCorrect(String w) {
    int flag = 0;
    for(int i=0; i<w.length(); ++i) {
        flag += (w.charAt(i) == '(') ? 1 : -1;
        if(flag < 0) return false;
    }
    return true;
}
```

<br>
4-1 ~ 4-4는 재귀호출이 없고 String 객체를 썼을 때 시간적,
공간적 손해가 크다고 판단해서 이 부분만 StringBuilder를 사용했습니다.

```java 
// 4. if u is not correct
StringBuilder sb = new StringBuilder();
// 4-1 ~ 4-3 concatenate (, recursive(v) and )
sb.append("(").append(solution(v)).append(")");
// 4-4 remove first and last parenthesis and reverse direction
u = u.substring(1, u.length()-1);
StringBuilder nu = new StringBuilder();
for(char c : u.toCharArray()) {
    nu.append((c == '(') ? ')' : '(');
}
```

## ✅ 후기
자바로 String을 처리할 때 String 객체를 쓰느냐 StringBuilder를 쓰느냐 꽤
고민하게 됩니다. 둘 다 참조값을 가지는 객체인 것은 동일하지만 immutable인
String은 메소드에 매개변수로 들어가도 원본이 변할 걱정이 없어서 편한 반면
mutable인 StringBuilder는 원치 않는 변경이 되는지 계속 따져봐야 해서 번거롭습니다.
특히 이 문제는 계속해서 재귀호출이 일어나기 때문에 StringBuilder를 쓰기
힘들었습니다. 마지막 부분에 StringBuilder를 사용했지만 실제 코딩테스트 상황이였다면
일단 전부 String 객체로 구현한 다음 시간초과가 난다면 StringBuilder를 사용할
여지가 있는지 검토할 것 같습니다.