# 19539번 사과나무
[문제 보러가기](https://www.acmicpc.net/problem/19539)

## 🅰 설계
1과 2가 동일 횟수만큼 사용되어야 한다는 사실과
2에서 1은 양도가 가능 하지만 1에서 2는 양도가 불가하다는 사실
두가지를 관찰해야 합니다. 매 나무의 키마다 2가 최대로 기여할 수 있는
부분과 1이 최소로 기여할 수 있는 부분으로 나눈 뒤 횟수가 같아지도록
2에서 1로 양도합니다. 이때 y - n = x + 2n 이라는 방정식이 성립해야 하므로
`y >= x & (y - x) % 3 = 0` 이 성립해야 합니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int numOfTress = Integer.parseInt(br.readLine());
        int numOfOnes = 0;
        int numOfTwos = 0;
        int[] height = new int[numOfTress];
        st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < numOfTress; idx++) {
            height[idx] = Integer.parseInt(st.nextToken());
            numOfOnes += height[idx] % 2;
            numOfTwos += height[idx] / 2;
        }
        if(numOfTwos >= numOfOnes && (numOfTwos-numOfOnes) % 3 ==0) {
            System.out.println("YES");
        } else System.out.println("NO");
    }
}
```
## ✅ 후기
문제 유형은 그리디인데 꼭 그리디 문제인지는 잘 모르겠습니다.
단순 수학문제에 가까운 것 같아요. 관찰하기 쉬운 규칙은 아니였기 때문에
잘 안보인다면 한 없이 안보일 수도 있는 문제였다고 생각합니다.