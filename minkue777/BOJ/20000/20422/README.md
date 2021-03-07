# 20422번 퀼린드롬 (Easy)
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계
이번 주 문제중에 가장 고생했던 문제였습니다. 일단 문제의 의도를 파악하는 것부터가
문제였는데 **"원본 닉네임의 대소문자를 적당히 바꾸면 가장 짧고 멋진 닉네임이 되거나, 
만드는게 불가능한 입력만 주어진다."** 이 문장의 뜻을 이해하는데 한참이 걸렸습니다.
결국 문장 길이는 유지한 채 영어의 대소문자만 바꿔보면서 퀼린드롬이 된다면 퀼린드롬을 출력
그렇지 않다면 -1을 출력하라는 이야기였습니다.

문제에서 정의한 룰이 있기 때문에 어쩔 수 없이 그 룰을 하나하나 코드로 옮겨야 합니다.
HashMap을 사용하여 key로 입력된 문자의 대칭 문자를 얻을 수 있도록 구현하였습니다.
```java 
static Map<Character, Character> matching = new HashMap<>();
static void initMap() {
    matching.put('A', 'A'); matching.put('E', '3'); matching.put('H', 'H');
    matching.put('I', 'I'); matching.put('M', 'M'); matching.put('O', 'O');
    matching.put('S', '2'); matching.put('T', 'T'); matching.put('U', 'U');
    matching.put('V', 'V'); matching.put('W', 'W'); matching.put('X', 'X');
    matching.put('Y', 'Y'); matching.put('Z', '5'); matching.put('b', 'd');
    matching.put('d', 'b'); matching.put('i', 'i'); matching.put('l', 'l');
    matching.put('m', 'm'); matching.put('n', 'n'); matching.put('o', 'o');
    matching.put('p', 'q'); matching.put('q', 'p'); matching.put('r', '7');
    matching.put('u', 'u'); matching.put('v', 'v'); matching.put('w', 'w');
    matching.put('x', 'x'); matching.put('0', '0'); matching.put('1', '1');
    matching.put('2', 'S'); matching.put('3', 'E'); matching.put('5', 'Z');
    matching.put('7', 'r'); matching.put('8', '8');
}
```
그 다음엔 대문자면 소문자로, 소문자면 대문자로 바꿔주는 메소드를 정의합니다.
```java 
static char changeAlphabet(char c) {
    // 대문자가 들어온다면 소문자로 변환
    if(c >= 65 && c <= 90)
        return (char)(c + 'a'-'A');
    // 소문자가 들어온다면 대문자로 변환
    else if(c >= 97 && c <= 122)
        eturn (char)(c + 'A'-'a');
    // 그 외의 문자는 그대로 반환
    else return c;
}
```

이 이후 문제를 해결하기 위해 밟은 단계는 다음과 같습니다.
1. 길이가 홀수인 문자열이 들어온다면 중앙에 있는 문자가 자기자신과 대칭인지 확인합니다.
<br> 만약 자기 자신과 대칭이 아니라면 위의 메소드를 사용해서 변환 후에 다시 확인합니다. 
   두 가지 전부 자기 자신과 대칭이 아니라면 퀼린드롬이 아니므로 -1을 출력합니다. <br>
    ```java 
    if(isEven == 1) {
        int center = input.length/2;
        if(!matching.containsKey(input[center])) {
            input[center] = changeAlphabet(input[center]);
        }
        if(!(matching.containsKey(input[center]) &&
                matching.get(input[center]) == input[center])) {
            System.out.println(-1);
            return;
        }
    }
    ```
2. 중앙에서부터 양 옆으로 한칸씩 이동하며 두 문자가 대칭이 될 수 있는지 확인합니다.
<br> 만약 대칭표에서 대칭되는 문자가 없는 문자라면 어떤 상황에서도 퀼린드롬이 될 수 없으므로
   위의 메소드를 사용하여 변환을 수행합니다
    ```java 
    if(!matching.containsKey(lc)) {
        input[left] = changeAlphabet(lc);
        lc = input[left];
    }
    if(!matching.containsKey(rc)) {
        input[right] = changeAlphabet(rc);
        rc = input[right];
    } 
    ```
3. 변환한 문자가 서로 대칭관계에 있는지 확인합니다. 만약 대칭관계가 성립하는 문자라면
<br> 대소문자 변환으로 대칭이 가능한 문자쌍이므로 패스합니다. 만약 변환한 문자가
   대칭관계에 없다면 `두 문자가 서로 자기대칭 관계에 있는 대소문자 관계인지 한번 더 확인합니다.`
   만약 이 확인과정이 없다면 `Uu` 같은 입력에 대해 정답인 `uu`나 `UU`가 아닌 `-1`로 출력합니다.
    ```java 
    if(matching.get(lc) != rc && lc != changeAlphabet(rc)) {
        System.out.println(-1);
        return;
    }
    if(lc == changeAlphabet(rc))
        input[right] = lc;
    ```

## ✅ 후기
처음 구현했을 때에는 `두 문자가 서로 자기대칭 관계에 있는 대소문자 관계인지 한번 더 확인합니다.`
라는 스텝이 없었는데 이 과정이 없다면 어느 케이스에서 예외가 발생하는지 찾는게 너무 힘들었습니다.
입력값을 이리저리 바꿔보며 100개도 넘게 넣어봤는데 `Uu`는 전혀 상상도 못한 입력값이였습니다.
내가 생각한 알고리즘이 모든 입력값을 커버할 수 있는지 감만으로 체크하는게 위험하다는 생각이 
들었습니다.