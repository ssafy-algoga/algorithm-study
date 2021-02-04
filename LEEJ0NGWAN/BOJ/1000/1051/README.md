# 1051번: 숫자 정사각형

[문제 보러가기](https://www.acmicpc.net/problem/1051)

[코드 보기](./P1051.java)

## 🅰 설계

별로 어렵지 않은 문제로 2차원 map을 (0,0) 좌표부터 내려가며 순회하며 정사각형을 체크합니다

xor 비트 연산을 이용해서 각 꼭지점이 같은 값을 가지는지 확인했습니다

## 전체 코드

```jsx
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1051 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new char[N][M];

        for (int i=0; i<N; i++)
        map[i] = br.readLine().toCharArray();

        int maxDist = 0;
        for (int i=0; i<N; i++)
        for (int j=0; j<M; j++) {
            int count=1;
            while (true) {
                int nx = j+count;
                int ny = i+count;
                if (nx<0 || ny<0 || N<=ny || M<=nx)
                break;

                int r = map[i][j]^map[i][nx];
                int b = map[i][j]^map[ny][j];
                int d = map[i][j]^map[ny][nx];
                if (r+b+d == 0 && maxDist < count)
                maxDist = count;

                count++;
            }
        }
        maxDist++;
        System.out.println(maxDist*maxDist);
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

없습니다

### 고생한 점

없습니다
