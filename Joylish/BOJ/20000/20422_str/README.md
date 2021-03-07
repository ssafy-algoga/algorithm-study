# 20422번 퀼린드롬(Easy)

[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계

#### 1. 변수 선언 및 초기화

- **map** : 퀼린드롬 거울대칭표 정보 저장하는 HashMap
- **front**, **end** : 입력되는 문자열을 절반을 나누어 거울 대칭 정보를 저장하는 StringBuilder 

```java
static HashMap<Character, Character> map = new HashMap<>();

public static void main(String[] args) throws IOException {
	char[] name = br.readLine().toCharArray();
        StringBuilder front = new StringBuilder();
        StringBuilder end = new StringBuilder();
        setMap();
	...
}
```

#### 2. 문자열의 길이가 홀수일 경우 가운데에 있는 문자부터 확인

- **mid** : 주어진 문자열에서 중앙에 있는 문자를 저장하는 변수
- **isTerminated** : 퀼린드롬 확인하는 것을 멈출 것인지 확인하는 flag 변수

문자열 길이가 홀수일 경우 가운데 문자는 거울 대칭의 중심이 되기 때문에 해당 문자는 거울대칭표에 있는 문자여야 한다. 하지만 해당 문자가 거울대칭표에 없을 경우 퀼린드롬을 만들기 힘들기 때문에 -1을 리턴한다. 

```java
char mid = name[name.length / 2];
boolean isTerminated = false;
if (name.length % 2 == 1) {
    if (map.get(mid) == null) { // 중앙에 있는 문자가 거울대칭표에 없을 경우
        if (Character.isAlphabetic(mid)) { // 중앙에 있는 문자가 알파벳일 경우
            char alt = transferChar(mid); // 대체문자; 대문자는 소문자로, 소문자는 대문자로 
            if (map.get(alt) != null) mid = map.get(alt); // 대체문자의 대칭문자로
            else isTerminated = true; // 대체문자의 대칭문자가 없으면 종료
        } else isTerminated = true; // 중앙에 있는 문자가 거울대칭표에 없는데 숫자일 경우 종료
    }
    if (isTerminated) {
        System.out.println(-1);
        return;
    }
}
```

#### 3. 문자열 길이의 절반만큼 순회하며 퀼린드롬 찾기

```java
int half = name.length / 2;
for (int i = 0; i < half; i++) {
    char c = name[i]; // 주어진 문자열에서 현재 문자
    char _c = name[name.length - 1 - i]; // 가운데를 기준으로 현재 문자 c 위치의 맞은 편(줄여서 맞은편 문자)

    // 맞은편문자가 숫자일 경우 먼저 체크해줌
    // 숫잔데 대칭문자가 없을 경우 종료
    if (!Character.isAlphabetic(_c) && map.get(_c) == null) { 
        System.out.println(-1);
        return;
    }

    // 현재문자에 대한 대칭문자가 있을 경우
    // 대칭문자와 맞은편 문자와 같거나, 대칭문자와 맞은편 문자를 대소문자로 변환한 문자와 같을 때 
    if (map.get(c) != null && (map.get(c) == _c || map.get(c) == transferChar(_c))) {
        front.append(c);
        end.append(map.get(c));
        continue;
    }

    // 현재문자에 대한 대칭문자가 없을 경우
    if (map.get(c) == null) {
        if (Character.isAlphabetic(c)) {
            char alt = transferChar(c);
            if (map.get(alt) != null) {
                front.append(alt);
                end.append(map.get(alt));
                continue;
            }
        }
    }

    System.out.println(-1);
    return;
}
```

#### 4. 출력

```java
end = end.reverse();
System.out.println(name.length % 2 == 1 ? front.append(mid).append(end) : front.append(end));
```



## ✅ 후기

- 문제 정보가 길어지니까 풀 수 있을지 막연한 두려움과 어지러움😵

- 한번에 문제에서 주어진 입력제한을 이해하기 정말정말 힘들었음😨...

- 문제에 테스트케이스가 부족해서 문제 조건에 맞지 않은 솔루션이 통과되서 검증하는데 힘들었음

  흐어... 제출을 몇 번이나 했는지 ... ㅋ... ㅠ... 알고리즘 문제풀면서 한 문제에 이렇게 고민해보고 제출해본 적이 있었을까....

  - 데이터 요청 글 보러가기 https://www.acmicpc.net/board/view/64481

  <img src="./images/img.png"/>



