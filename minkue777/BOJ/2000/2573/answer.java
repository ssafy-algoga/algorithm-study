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