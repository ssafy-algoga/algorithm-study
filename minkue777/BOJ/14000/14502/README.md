# 14502번 연구소
[문제 보러가기](https://www.acmicpc.net/problem/14502)

## 🅰 설계
BFS 혹은 DFS에 시뮬레이션을 덧붙여야 하는 전형적인 삼성 SW 역량테스트 문제였습니다.
새로 설치할 수 있는 벽이 3개뿐이라는 것에서 완전탐색임을 파악할 수 있고 설치 후
최대 안전 영역을 구하는 부분은 첫 주차에 풀었던 섬의 개수와 동일한 문제로 변환됩니다.
코드는 길지만 사실 각각의 스텝은 실버 수준의 구현이였기 때문에 해결하는데 크게 어려움이
없었습니다.

```java
import java.io.*;
import java.util.*;

public class Main {
    static int maxSafeArea, row, col;
    static int[][] lab;
    static int[] dy = new int[] {-1, 0, 1, 0};
    static int[] dx = new int[] {0, 1, 0, -1};

    static void spread(int y, int x) {
        if(lab[y][x] != 2) lab[y][x] = -1;
        for(int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny >= 0 && ny < row && nx >= 0 && nx < col &&
                    lab[ny][nx] == 0) {
                spread(ny, nx);
            }
        }
    }

    static void combination(int cnt, int start) {
        if(cnt == 3) {
            for(int i=0; i<row; i++) {
                for(int j=0; j<col; j++) {
                    if(lab[i][j] == 2) spread(i, j);
                }
            }

            int count = 0;
            for(int i=0; i<row; i++) {
                for(int j=0; j<col; j++) {
                    if(lab[i][j] == 0) count++;
                    else if(lab[i][j] == -1) lab[i][j] = 0;
                }
            }

            maxSafeArea = Math.max(maxSafeArea, count);
            return;
        }

        for(int i=start; i<row*col; i++) {
            int y = i / col;
            int x = i % col;
            if(lab[y][x] == 0) {
                lab[y][x] = 1;
                combination(cnt + 1, i + 1);
                lab[y][x] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        lab = new int[row][col];

        for(int i=0; i<row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<col; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combination(0, 0);
        System.out.println(maxSafeArea);
    }
}
```


## ✅ 후기
매번 배열을 새로 복사하는 것이 싫어서 원본 배열을 계속 재활용하는 방식으로
구현했는데 배열을 복사하는 것과 일장일단이 있는 것 같습니다. 시뮬레이션이 전부 끝난 후
다시 처음 상태로 배열을 되돌리기 위한 추가적인 로직이 필요하다는 단점이 있고 대신 그만큼의
시간적 공간적 활용에서는 이점이 있습니다. 하지만 코딩테스트는 패스만 하면 전부 똑같기 때문에
굳이 추가적인 로직이 필요한 방식을 사용해야 하나 싶은 마음도 있습니다. 두 가지 방법 모두
사용해보면서 장단점을 느껴봐야겠습니다.