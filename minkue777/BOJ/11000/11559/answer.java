import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] field;
    static boolean[][] isBurst;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int dfs(int r, int c) {
        char ch = field[r][c];
        isBurst[r][c] = true;
        int count = 1;
        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < 12 && nc >=0 && nc < 6 &&
                    field[nr][nc] == ch && !isBurst[nr][nc]) {
                count += dfs(nr, nc);
            }
        }
        return count;
    }

    static void Boom(int cluster) {
        for(int r=0; r<12; r++) {
            for(int c=0; c<6; c++) {
                if(isBurst[r][c]) {
                    if(cluster >= 4) field[r][c] = '.';
                    isBurst[r][c] = false;
                }
            }
        }
    }

    static void gravity() {
        for(int r=10; r>=0; r--) {
            for(int c=0; c<6; c++) {
                if(field[r][c] != '.') {
                    int next = r + 1;
                    while (next < 12 && field[next][c] == '.') {
                        next++;
                    }
                    if(next != r+1) {
                        field[next - 1][c] = field[r][c];
                        field[r][c] = '.';
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        field = new char[12][];
        isBurst = new boolean[12][6];
        for(int i = 0; i<12; i++) {
            field[i] = br.readLine().toCharArray();
        }

        int count = 0;
        boolean flag = true;
        while(flag) {
            flag = false;
            for(int r = 0; r<12; r++) {
                for(int c = 0; c<6; c++) {
                    if(field[r][c] != '.') {
                        int cluster = dfs(r, c);
                        if(cluster >= 4) flag = true;
                        Boom(cluster);
                    }
                }
            }
            gravity();
            count += flag ? 1 : 0;
        }
        System.out.println(count);
    }
}