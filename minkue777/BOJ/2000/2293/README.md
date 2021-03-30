# 2293번 동전 1
[문제 보러가기](https://www.acmicpc.net/problem/2293)

## 🅰 설계
개인적으로는 실버1보다는 높은 티어를 받아야 한다고 생각하는 문제입니다.
(가장 난이도 측정을 정확하게 한다고 생각하는 jh05013님은 골드4를 주셨네요.)
넓은 범위에서는 dp 좀 더 좁은 범위로는 냅색문제에 들어가는 문제입니다.
사실 순열이였다면 쉽게 풀렸지만 동전의 순서를 고려하지 않는 다는 점과
무지막지한 시간, 메모리 제약으로 인해 고생했습니다.

풀기위한 핵심 아이디어는 동전마다 index를 부여해서 현재 사용하고 있는 동전보다
작은 index를 가진 동전은 다시 사용하지 않는 것입니다. 이를 통해 중복된 케이스를
제거할 수 있습니다.

만약 메모리 제약을 좀 더 빡빡하게 검사했다면 슬라이딩 윈도우를 통해서 DP 테이블을
2차원 배열이 아니라 1차원배열로 축소시켜 메모리 사용량을 줄일 수 있었겠지만 AC를 받아
굳이 그 부분까지 구현하진 않았습니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int[][] cache;
    static int[] coins;
    static int kindOfCoins;
    static int target;

    static int solve(int idx, int leftMoney) {
        if(idx == kindOfCoins) {
            return leftMoney == 0 ? 1 : 0;
        }
        int ret = cache[idx][leftMoney];
        if(ret != -1) return ret;
        ret = solve(idx+1, leftMoney);
        if(coins[idx] <= leftMoney) {
            ret += solve(idx, leftMoney - coins[idx]);
        }
        return cache[idx][leftMoney] = ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        kindOfCoins = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        coins = new int[kindOfCoins];
        cache = new int[kindOfCoins][target+1];
        for(int[] row : cache) Arrays.fill(row, -1);
        for(int idx = 0; idx < kindOfCoins; idx++) {
            coins[idx] = Integer.parseInt(br.readLine());
        }
        int ans = solve(0, target);
        System.out.println(ans);
    }
}
```

## ✅ 후기
최적화 문제를 풀 땐 top-down 방식의 dp가 확실히 편하다고 느끼는데
경우의 수 문제를 풀 땐 오히려 bottom-up이 편해보이는 경우가 있습니다.
bottom-up 풀이가 눈에 들어와도 일부러 top-down 으로 푸는 경우가 꽤 있었는데
당분간 좀 혼용해서 사용해 볼 생각입니다.