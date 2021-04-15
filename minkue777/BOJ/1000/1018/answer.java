import java.io.*;
import java.util.*;

public class Main {
    static int differ(char[][] board, int r, int c) {
        int sum1 = 0;
        int sum2 = 0;
        for(int i=r; i<r+8; i++) {
            for(int j=c; j<c+8; j++) {
                sum1 += ((i+j)%2 == 0 && board[i][j] != 'B') ? 1 : 0;
                sum1 += ((i+j)%2 == 1 && board[i][j] != 'W') ? 1 : 0;
                sum2 += ((i+j)%2 == 0 && board[i][j] != 'W') ? 1 : 0;
                sum2 += ((i+j)%2 == 1 && board[i][j] != 'B') ? 1 : 0;
            }
        }
        return Math.min(sum1, sum2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int minVal = 65;
        char[][] board = new char[n][m];
        for(int i=0; i<n; i++) {
            String s=  br.readLine();
            for(int j=0; j<m; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for(int i=0; i<n-7; i++) {
            for(int j=0; j<m-7; j++) {
                minVal = Math.min(minVal, differ(board, i, j));
            }
        }
        System.out.println(minVal);
    }
}