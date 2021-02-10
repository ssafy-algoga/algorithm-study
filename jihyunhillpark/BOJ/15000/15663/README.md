# 15663번: N과 M (9)

[문제 보러가기](https://www.acmicpc.net/problem/15663)

[코드 보기](./P15663.java)

## 🅰 설계

백 트래킹을 이용하여, 가지 않아도 되는 부분을 건너띄며 완전 탐색을 진행합니다

중복되는 결과 값을 방지하면서, 저장된 데이터의 순서를 보장하기 위해 LinkedHashSet을 이용했습니다

## 전체 코드

```jsx
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class P15663 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static int N, M;
    static int[] arr;
    static boolean[] visit;

    static void dfs(int count, String stack) {
        if (count==M) {
            set.add(stack+"\n");
            return;
        }

        for (int i=0; i<N; i++) {
            if (visit[i])
            continue;

            visit[i] = true;
            dfs(count+1,stack+" "+arr[i]);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        arr = new int[N];
        visit = new boolean[N];

        tokens = br.readLine().split(" ");
        for (int i=0; i<N; i++)
        arr[i] = Integer.parseInt(tokens[i]);

        Arrays.sort(arr);

        for (int i=0; i<N; i++) {
            visit[i] = true;
            dfs(1,Integer.toString(arr[i]));
            visit[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        for (String s: set) sb.append(s);
        System.out.print(sb);
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

데이터 삽입 순서를 보장하지 않는 자료구조를 이용하면, 답을 구할 수 없습니다

### 고생한 점

HashSet을 이용해서 틀렸습니다
