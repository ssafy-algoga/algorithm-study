import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2573_hi {
    static int N, M;
    static int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};
    static int[][] map;
    static boolean[][] visited;
    static int i_cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        int ibCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) ibCnt++;
            }
        }
        int yearCnt = 0;
        boolean separate = false;
        while(true){
            visited = new boolean[N][M];
            if(ibCnt == 0 || separate) break;

            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    if(map[i][j] == 0) continue;
                    int tmp = waterNear(i, j);
                    if(map[i][j] <= tmp){
                        visited[i][j] = true;
                        map[i][j] = 0;
                    }else {
                        map[i][j] -= tmp;
                    }
                }
            }

            ibCnt = countIceberg();

            visited = new boolean[N][M];

            boolean check = false;
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    i_cnt = 0;
                    if(map[i][j] == 0 || visited[i][j]) continue;
                    checkConnection(i, j);
                    if(i_cnt != ibCnt) {
                        separate = true;
                    }
                }
                if(separate) break;
            }
            yearCnt++;
        }
        if(!separate) System.out.println("0");
        else System.out.println(yearCnt);

    }

    private static int countIceberg(){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0) cnt++;
            }
        }

        return cnt;
    }

    private static void checkConnection(int x, int y) {
        visited[x][y] = true;
        i_cnt++;
        int nX, nY;
        for (int i = 0; i < 4; i++) {
            nX = x + dir[i][0];
            nY = y + dir[i][1];
            if (0 > nX || nX >= N || 0 > nY || nY >= M) continue;
            if (map[nX][nY] != 0 && !visited[nX][nY]) {
                checkConnection(nX, nY);
            }
        }
    }

    private static int waterNear(int x, int y){
        int nX, nY;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            nX = x + dir[i][0];
            nY = y + dir[i][1];
            if(0 > nX || nX >= N || 0> nY || nY >= M)continue;
            if(map[nX][nY] == 0 && !visited[nX][nY]) cnt++;
        }
        return cnt;
    }
}
