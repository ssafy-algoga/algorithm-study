import java.util.Scanner;

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
        for (int i = 0; i < len / 2; i++) {
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

                    char charHere = (check1 < check2) ? change[check2] : mirror[check1];
                    char charThere = (check1 < check2) ? (check2 %2 == 0 ? change[check2 + 1] : change[check2 - 1]): mirror[check1];
                    sb.replace(i, i + 1, String.valueOf(charHere));
                    sb.replace(len - i - 1, len - i, String.valueOf(charThere));
                    continue;
                }

                //숫자는 구제 못 합니다. ^^
                sb.setLength(0);
                sb.append("-1");
                break;

            }
            if (has(mirror, c) >= 0) {
                if(sb.charAt(len - i -1) != c)
                   sb.replace(len - i - 1, len - i, String.valueOf(c));
            } else {
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
