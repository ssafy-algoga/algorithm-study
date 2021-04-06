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