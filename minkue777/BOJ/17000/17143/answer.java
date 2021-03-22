import java.io.*;
import java.util.*;

public class Main {
    static class shark {
        int r;
        int c;
        int speed;
        int dir;
        int size;
        int idx;
        boolean alive;

        shark(int r, int c, int speed, int dir, int size, int idx, boolean alive) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.idx = idx;
            this.alive = alive;
        }

        void move() {
            if(map[r][c] == idx) map[r][c] = 0;
            int cnt = 0;
            int nr;
            int nc;

            while(cnt != speed) {
                nr = r + dr[dir];
                nc = c + dc[dir];
                if(nr < 0 || nr >= row || nc < 0 || nc >= col) {
                    dir = change[dir];
                }
                r += dr[dir];
                c += dc[dir];
                cnt++;
            }

            int already = map[r][c];
            if(already == 0 || already > idx) {
                map[r][c] = idx;
                return;
            }
            if(sharks[already].size > sharks[idx].size) {
                sharks[idx].alive = false;
            } else {
                map[r][c] = idx;
                sharks[already].alive = false;
            }
        }
    }

    static void fishing(int col) {
        for(int r=0; r<row; r++) {
            if(map[r][col] != 0) {
                int idx = map[r][col];
                caughtWeight += sharks[idx].size;
                map[r][col] = 0;
                sharks[idx].alive = false;
                break;
            }
        }
    }

    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[] change = {1, 0, 3, 2};
    static int row;
    static int col;
    static int numOfSharks;
    static shark[] sharks;
    static int caughtWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        numOfSharks = Integer.parseInt(st.nextToken());
        sharks = new shark[numOfSharks+1];
        for(int idx = 1; idx<=numOfSharks; idx++) {
            st = new StringTokenizer(br.readLine());
            sharks[idx] = new shark(Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    idx, true);
            map[sharks[idx].r][sharks[idx].c] = idx;
            if(sharks[idx].dir == 0 || sharks[idx].dir == 1) {
                sharks[idx].speed %= 2*(row-1);
            }
            else sharks[idx].speed %= 2*(col-1);
        }

        for(int cur = 0; cur < col; cur++) {
            fishing(cur);
            for(int idx = 1; idx <= numOfSharks; idx++) {
                if(sharks[idx].alive) {
                    sharks[idx].move();
                }
            }
        }
        System.out.println(caughtWeight);
    }
}