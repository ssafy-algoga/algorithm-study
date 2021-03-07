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