# 2573번 빙산
[문제 보러가기](https://www.acmicpc.net/problem/2573)

## 🅰 설계
수업시간에 풀었던 치즈와 상당히 유사한 문제였습니다. Connected Component의 개수를
세는 것은 알고리즘 스터디 첫주차에 풀었던 섬의 개수 문제에서도 다뤘던 알고리즘입니다.
각 칸마다 녹는 정도를 저장해두고 한 번에 녹인 후 연결요소의 개수를 체크합니다.
연결요소가 2개 이상이거나 0개이면 반복문을 종료합니다.

각 칸마다 녹는 정도를 맵에 바로 업데이트 한다면 빙산이 다 녹아서 높이가 0이 됐을 때
그 이후 좌표에서 빙산이 녹아서 0이 된건지 처음부터 바다였는지 판별할 수 없기 때문에
조심해야합니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int row;
    static int col;
    static int[][] map;
    static int[][] volume;
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static void melt(int r, int c) {
        if(map[r][c] != 0) return;
        int nr, nc;
        for(int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if(nr >= 0 && nr < row && nc >= 0 && nc < col &&
                    map[nr][nc] > volume[nr][nc]) {
                volume[nr][nc]++;
            }
        }
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;
        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr >= 0 && nr < row && nc >= 0 && nc < col &&
                    !visited[nr][nc] && map[nr][nc] > 0) {
                dfs(nr, nc);
            }
        }
    }

    static int calcNumOfComponent() {
        int count = 0;
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(!visited[r][c] && map[r][c] > 0) {
                    dfs(r, c);
                    count++;
                }
            }
        }
        return count;
    }

    static void init() {
        for(int[] row : volume) {
            Arrays.fill(row, 0);
        }
        for(boolean[] row : visited) {
            Arrays.fill(row, false);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        volume = new int[row][col];
        visited = new boolean[row][col];
        for(int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int numOfComponent = 1;
        int time = 0;
        while(numOfComponent == 1) {
            init();
            // 빙산이 녹는 높이 계산
            for(int r = 0; r < row; r++) {
                for(int c = 0; c < col; c++) {
                    melt(r, c);
                }
            }
            // 녹은 만큼 빙산 높이 조정
            for(int r = 0; r < row; r++) {
                for(int c = 0; c < col; c++) {
                    map[r][c] -= volume[r][c];
                }
            }
            // 연결된 요소의 개수 계산
            numOfComponent = calcNumOfComponent();
            time++;
        }
        System.out.println(numOfComponent == 0 ? 0 : time);
    }
}
```

## ✅ 후기
실제 상황에서는 동시에 벌어지더라도 코드상에서는 어쩔 수 없이 
하나하나 처리해야하는데 이 부분을 이용하여 문제를 한번 꼬아내는 것은
구현문제(특히 삼성 역량테스트)에서 자주 마주치는 패턴이네요.
구현문제는 디버깅도 오래 걸리기 때문에 처음 설계할 때부터 주의해야겠습니다..