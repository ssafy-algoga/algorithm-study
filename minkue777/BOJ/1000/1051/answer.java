import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] array;
    // calculate area of a square if it is
    static int calcRectangle(int luy, int lux, int size) {
        if(array[luy][lux] == array[luy+size][lux] &&
                array[luy][lux] == array[luy][lux+size] &&
                array[luy][lux] == array[luy+size][lux+size])
            return (size+1)*(size+1);
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                array[i][j] = s.charAt(j) - '0';
            }
        }

        int max = 1;
        for(int i=0; i<n-1; i++) {
            for (int j=0; j<m-1; j++) {
                // point (i, j) is left-upper point of a square
                for (int size = Math.min(n-i-1, m-j-1); size >= 1; size--) {
                    // back-tracking
                    if ((size + 1) * (size + 1) <= max) break;
                    // update max area of a square
                    max = Math.max(max, calcRectangle(i, j, size));
                }
            }
        }
        System.out.println(max);
    }
}
