# 17413번 단어뒤집기2
[문제 보러가기](https://www.acmicpc.net/problem/17413)

## 🅰 설계

#### 1. 뒤집을 단어가 있는 문자열 입력

```java
char[] str = br.readLine().toCharArray();
```

#### 2. 문자열에 대한 문자 하나씩 확인

- sb : 최종적으로 출력할 문자열을 저장하는 StringBuilder 변수
- sbForReversed : 뒤집을 단어를 저장하는 StringBuilder 변수
- isInTag: 태그(<>)안에 있는 문자인지 체크하는 boolean 변수
- isInWord: 태그가 아닌 특정 단어 안에 있는 문자인지 체크하는 boolean 변수

반복문을 통해 문자열의 모든 문자를 순회한다. 이 때  (1) 문자가 '<'인지 (2) 문자가 '>'인지 (3) 문자가 ' '(띄어쓰기)인지 (4) 문자(a-z, 0-9)인지 확인한다.

```java
sb = new StringBuilder();
sbForReversed = new StringBuilder();

boolean isInTag = false;
boolean isInWord = true;

for (int j = 0; j < str.length; j++) {
    if (str[j] == '<') {
        if (isInWord) {
            sb.append(sbForReversed.reverse());
            sbForReversed.setLength(0);
            isInWord = false;
        }
        isInTag = true;
        sb.append(str[j]);
    }

    else if (str[j] == '>') {
        sb.append(str[j]);
        isInTag = false;
        isInWord = true;
    }

    else if (str[j] == ' ') {
        if (sbForReversed.length() > 0) {
            sb.append(sbForReversed.reverse());
            sbForReversed.setLength(0);
        }
        sb.append(' ');
    } else {
        if (isInTag)
            sb.append(str[j]);
        if (isInWord) {
            sbForReversed.append(str[j]);
            if (j == str.length - 1) {
                sb.append(sbForReversed.reverse());
                sbForReversed.setLength(0);
            }
        }
    }

}
```

#### 3. StringBuffer sb 출력

	System.out.println(sb);



## ✅ 후기
- br.readLine().toCharArray();

  처음에 문제를 풀 때, readLine()으로 한 줄 단위로 입력받으면 자료형이 String이어서 String 길이만큼 char 배열을 생성하고 반복문을 이용해서 문자를 넣어주는 형식으로 했었다. 

  혜빈님이 알려주신 String의 `toCharArray()`를 썼더니 String을 char로 바꾸기 위해 했던 불필요한 코드들을 한 줄로 줄일 수 있었다. 

- Stack → StringBuilder

  처음에 문제를 풀 때, 문자가 태그 안에 있지 않을 때 뒤집어서 출력하기 위해 Stack을 이용하였다. 하지만 솔루션을 제출하였을 때 시간초과가 떠서 어떻게 하면 시간을 줄일 수 있을까 고민했었다. 

  혜빈님과 성현님께서 Stack이 아닌 StringBuilder를 이용하면 시간이 줄어들 것이라고 조언을 해주셔서 앞서 Stack에서 pop하고 append했던 방식에서 StringBuilder에서  reverse한 문자열을 append하는 형식으로 수정해보았다. StringBuilder를 초기화해주기 위해 StringBuilder의 setLength를 써서 초기화했다.

  **왜 Stack이 StringBuilder보다 시간이 더 오래 걸리는지** 좀 더 찾아봐야겠다.

- 고민했던 또 다른 솔루션

  시간초과로 인해 시간을 어떻게 줄일 수 있을까 고민을 하다가 최대 길이가 100000이 될 수 있는 문자열을 처음부터 끝까지 순회하는 것이 왠지 비효율적일 것 같다라는 생각이 들었다. 그래서 성현님과의 대화에서 '<'와 '>'의 위치를 기록해서 문자가 아닌 단어별로 순회하면 더 효율적일 것 같다는 생각을 하게 되었다. 이 솔루션은 비록 아직 도전중이지만..