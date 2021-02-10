import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FloydWarshall {
    static int INF = 90000000;//Integer.MAX_VALUE줬다가 출력 초과됐어요....
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int bus = 0; bus < M; bus++) {
            StringTokenizer  tokenizer = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(tokenizer.nextToken());
            int j = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            dist[i-1][j-1] = Math.min(dist[i-1][j-1], w);
        }
        for (int k = 0; k < N; k++) { //경유지
            for (int i = 0; i < N; i++) { //시작 도시
                for (int j = 0; j < N; j++) { //도착 도시
                    if(k == j){
                        continue;
                    } else if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                if(dist[i][j] == INF) sb.append(0).append(" ");
                else sb.append(dist[i][j]).append(" ");
            }
            System.out.println(sb);
        }
    }
}
