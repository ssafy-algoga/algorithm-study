import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberRectangle {
    static int[][] square;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        square = new int[n][m];
        int maxSquare = 1;
        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            for (int j = 0; j < m; j++) {
                square[i][j] = (j== m-1)? Integer.parseInt(s.substring(j)):Integer.parseInt(s.substring(j,j+1));

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int check = square[i][j];
                for (int cnt = 1; cnt + i < n&& cnt + j < m ; cnt++) {//cnt 더해서 n,m을 벗어나지 않는 범위에서 반복
                    if (square[i + cnt][j] == check && square[i][j + cnt] == check && square[i + cnt][j + cnt] == check) {
                        maxSquare = Math.max(maxSquare, (cnt + 1) * (cnt + 1));
                    }

                }
            }
        }
        System.out.println(maxSquare);
    }
}
