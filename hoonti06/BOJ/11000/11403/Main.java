import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] edge;

    static void solution() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (edge[i][k] == 0 || edge[k][j] == 0)
                        continue;

                    edge[i][j] = 1;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        edge = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++)
                edge[i][j] = Integer.parseInt(st.nextToken());
        }

        solution();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(edge[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}

