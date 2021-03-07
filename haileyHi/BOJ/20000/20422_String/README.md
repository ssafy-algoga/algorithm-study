# 20422번 단어 뒤집기 2
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계

```java
String orig = sc.next();
for(int i = 0; i<= len/2; i++){
	char c = orig.charAt(i);
	//
}
```
여기서 c를 확인하는 방법을 세 가지로 분류
1. 대칭으로 못 만드는 문자인가?
2. 대칭 == 원본인 문자라면 반대쪽에도 같은 문자가 있는지
3. 위 둘 다 아니면 뒤집어서 대칭으로 만들 수 있는 문자가 반대편에 있는지 확인하기.

-> 반대쪽에 orig.charAt(len - i - 1)!=c 이면 c로 바꿔버리기
(리드미 쓰면서 반대편에 있는 문자가 대소문자 변환으로 해결 안 되는 경우에 -1을 리턴해줘야 한다는 문제점을 발견.....)

단순한 방법으로
1번 2번 3번에 해당하는 문자들을 각각 다른 배열에 담고 찾는 문자가 포함되어 있으면 인덱스를, 없으면 -1을 반환하는 has 메서드를 만들어주었습니다.
3번에 뒤집어서 대칭이 되는 문자는 2개씩 한 쌍을 이루고 있으니 찾은 c의 인덱스가 %2로 0이 되면 현재 인덱스+1에 있는 문자로 바꾸고 %2가 1이면 현재 인덱스 -1의 문자로 바꾸기

+ 홀수 퀼린드롬 가운데 문자가 원본 == 대칭인 문자가 아니라면 -1 리턴

## 전체 코드
```java
public class BOJ_Quilindrome_20422 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] uniq = {'B', 'C', 'D', 'F', 'G', 'J', 'K', 'L', 'N', 'P', 'Q', 'R', 'a', 'c', 'e', 'f', 'g', 'h', 'j', 'k', 's', 't', 'y', 'z', '4', '6', '9'};
        //원본 == 대칭인 문자들
        char[] mirror = {'A', 'H', 'I', 'M', 'O', 'T', 'U', 'V', 'W', 'X', 'Y', 'i', 'l', 'm', 'n', 'o', 'u', 'v', 'w', 'x', '0', '1', '8'};
        //다른 문자로 대치할 수 있는 문자들
        char[] change = {'E', '3', 'S', '2', 'Z', '5', 'b', 'd', 'p', 'q', 'r', '7'};//i%2 == 0이면 홀수 번째니까 +1로 바꾸기. i%2 == 1이면 짝수번째니까 -1로 바꾸기.


        String origStr = sc.next();
        int len = origStr.length();
        StringBuilder sb = new StringBuilder(origStr);
        int idx;
        for (int i = 0; i <= (len-1) / 2; i++) {
            char c = origStr.charAt(i);
            if (has(uniq, c) >= 0) {
                if(Character.isAlphabetic(c)){//c를 대문자나 소문자로 바꿔서 mirror나 change가 될 수 있는지 체크해보기
                    int check1 = -1, check2 = -1;
                    if(c >= 'a' && c <= 'z'){//소문자면 대문자로 바꿔서 살펴보기
                        check1 = has(mirror,Character.toUpperCase(c));
                        check2 = has(change,Character.toUpperCase(c));
                    }else {//대문자는 소문자로 바꿔서 확인하기.
                        check1 = has(mirror, Character.toLowerCase(c));
                        check2 = has(change, Character.toLowerCase(c));
                    }
					if (i == len / 2 && len % 2 == 1 && check1 == -1) {//홀수 번째 가운데 인데 원본==대칭 아닌 경우
                        sb.setLength(0);
                        sb.append("-1");
                        break;
                    }

                    char charHere = (check1 < check2) ? change[check2] : mirror[check1];
                    char charThere = (check1 < check2) ? (check2 %2 == 0 ? change[check2 + 1] : change[check2 - 1]): mirror[check1];
                    sb.replace(i, i + 1, String.valueOf(charHere));
                    sb.replace(len - i - 1, len - i, String.valueOf(charThere));
                    continue;
                }

                //숫자는 구제 못 합니다..
                sb.setLength(0);
                sb.append("-1");
                break;

            }
            if (has(mirror, c) >= 0) {
                if(sb.charAt(len - i -1) != c)
                   sb.replace(len - i - 1, len - i, String.valueOf(c));
            } else {
				if (i == len / 2 && len % 2 == 1) {
                    sb.setLength(0);
                    sb.append("-1");
                    break;
                }
                idx = has(change, c);//현재 위치 기준으로 반대편이 change[idx]가 아니라면 바꾸기
                char tmp = (idx % 2 == 0) ? change[idx + 1] : change[idx-1];
                if (sb.charAt(len - i - 1) != tmp) {//대칭이 아니면
                    sb.replace(len - i - 1, len - i, String.valueOf(tmp));
                }
            }
        }
        System.out.println(sb);
    }

    public static int has(char[] arr, char c) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }
}

```
## ✅ 후기
- len/2만큼 for문 돌릴 때 가운데 문자를 놓쳐서 시간 낭비했다...
- 자꾸 놓친 조건 하나 찾을 때마다 이것저것 추가했더니 코드가 너무 조각보 같아서 반성했습니다