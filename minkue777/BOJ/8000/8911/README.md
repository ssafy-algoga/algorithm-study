# 8911번 거북이
[문제 보러가기](https://www.acmicpc.net/problem/8911)

## 🅰 설계
문자열을 한단위씩 파싱한 후 문자에 맞게 거북이를 이동시킨 후에
좌표의 최대값, 최소값을 매 이동마다 갱신합니다.
사실 시간이 빡빡하다면 최적화할 여지는 있는 문제이지만
그런걸 의도하고 낸 문제는 아니라고 판단해서 효율성은 조금 떨어지지만
구현하기 쉬운 형태로 코딩하였습니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int minRow, maxRow, minCol, maxCol, curRow, curCol, curDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            minRow = maxRow = minCol = maxCol = 0;
            curRow = curCol = curDir = 0;
            String input = br.readLine();
            for(int idx = 0; idx<input.length(); idx++) {
                char cmd = input.charAt(idx);
                if(cmd == 'F') {
                    curRow += dr[curDir];
                    curCol += dc[curDir];
                } else if(cmd == 'B') {
                    curRow -= dr[curDir];
                    curCol -= dc[curDir];
                } else if(cmd == 'R') {
                    curDir = (curDir + 1) % 4;
                } else {
                    curDir = (curDir + 3) % 4;
                }
                minRow = Math.min(minRow, curRow);
                maxRow = Math.max(maxRow, curRow);
                minCol = Math.min(minCol, curCol);
                maxCol = Math.max(maxCol, curCol);
            }
            int area = (maxRow - minRow) * (maxCol - minCol);
            sb.append(area).append("\n");
        }
        System.out.println(sb);
    }
}
```

## ✅ 후기
특별히 고생할만한 점이 없는 단순한 구현문제라고 생각합니다.
코테에 나온다면 1번 정도의 난이도일까요