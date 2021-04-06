# 17070번 파이프 옮기기 1
[문제 보러가기](https://www.acmicpc.net/problem/17070)

## 🅰 설계
문제에서 재귀 형태를 분명하게 제시하고 있기 때문에 재귀식을
구성하는 건 어렵지 않지만 생각보다 실수할 여지가 있던 문제였습니다.
DP 테이블은 각각의 좌표를 저장하기 위한 2차원과 마지막으로 놓인
파이프의 상태를 저장하기 위한 1차원 총 3차원 배열로 구성해야 했습니다.
마지막 도착점이 벽이 있을수도 있다는 점과 도착 후에도 주위 탐색을
한번 더 해야한다는 사실에 유의해야합니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int size;
    static int[][] map;
    static long[][][] cache;

    static boolean inRange(int r, int c) {
        return (r >= 0 && r < size && c >= 0 && c < size);
    }
    static long solve(int r, int c, int state) {
        if(r == size-1 && c == size-1) {
            if(map[r][c] != 0) return 0;
            if(state == 1 && (map[r-1][c] == 1 || map[r][c-1] == 1)) return 0;
            return 1;
        }

        if(!inRange(r, c)) return 0;
        if(map[r][c] == 1) return 0;
        if(state == 1 && (map[r-1][c] == 1 || map[r][c-1] == 1)) return 0;
        long ret = cache[r][c][state];
        if(ret != -1) return ret;
        ret = 0;
        if(state == 0) {
            ret += solve(r, c+1, 0) + solve(r+1, c+1, 1);
        } else if(state == 1) {
            ret += solve(r, c+1, 0) + solve(r+1, c+1, 1) + solve(r+1, c, 2);
        } else if(state == 2) {
            ret += solve(r+1, c+1, 1) + solve(r+1, c, 2);
        }
        return cache[r][c][state] = ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        cache = new long[size][size][3];
        for(long[][] twoD : cache) {
            for(long[] oneD : twoD) Arrays.fill(oneD, -1);
        }
        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve(0, 1, 0));
    }
}
```
 
## ✅ 후기
파이프 옮기기 2도 있어서 뭔가 더 어려운 버전인가 했는데
1과 거의 동일한 문제였네요. 1은 dp를 적용하지 않아도 통과되는
입력 사이즈인것 같습니다.