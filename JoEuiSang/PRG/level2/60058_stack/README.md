# 프로그래머스 : 괄호 변환
[문제 보러가기](https://programmers.co.kr/learn/courses/30/lessons/60058)

## 🅰 설계
<img src ="https://user-images.githubusercontent.com/69133236/106154898-725ac380-61c3-11eb-8d5c-b8bf0b03fab8.jpg" height=1000>

문제를 꼼꼼히 읽었어야 했다.

원본 문자열에서 계속 나누어 반복작업을 하기때문에 재귀함수를 이용하기로 생각했다.

최초 문자열을 입력 받는 변수

```java
String w = br.readLine();
```

문자열을 재귀적으로 처리하여 답을 도출하는 함수
```java
static String divide(String w)

int idx = 0;	//문자열의 인덱스변수
int open = 0;	// ( 의 갯수
int close = 0;	// ) 의 갯수
String u;		// u를 담을 변수
String v;		// v를 담을 변수
String ans = ""; //답을 담을 변수
```


균형잡인 인덱스 체크
```java
if (open == close && open != 0)
```

현재 인덱스 기준으로 부분 괄호문자열 u v를 나눈다
```java
u = w.substring(0, idx);	
v = w.substring(idx);
```


올바른 괄호 유무에 따라 분기
```java
// 올바른 괄호이면
if (u.charAt(0) == '(') {
    ans = u + divide(v);
    return ans;
} else {// 올바르지 않은 괄호이면
    ans = "(" + divide(v) + ")" + trim(u);
    return ans;
}
```


## ✅ 후기
// 재귀함수의 내부로직을 구현하는데 시간을 오래 잡아 먹었다.
// 당황하지 않고 천천히 다시 시작하는 마음으로 노트에 차근차근 로직을 풀어나가니 해결방법을 찾게되었다.

// 아직 논리적이 사고가 재빠르지 않은 듯 하다. 오랜 고민끝에 답을 얻었지만 문제를 해결법을 찾아내는 방법을 깨달은 유의미한 시간이었다. 앞으로 이런 재귀함수를 활용하는 스택문제에 좀 더 자신감을 가질 수 있을거 같다.
// 아직도 어떤 자료구조를 사용해야할지 어떤 입력 데이터를 사용해야할지 감이 안잡힌다. 얼른 나만의 방식을 만들어 초반에 입력으로 잡아먹는 시간을 줄여 나가야 할 필요성이 있다.