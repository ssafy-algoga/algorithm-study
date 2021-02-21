import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11404 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        for (int i=1; i<=n; i++)
        for (int j=1; j<=n; j++)
        map[i][j] = (i==j)? 0: 9900001;

        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(map[a][b], Integer.parseInt(st.nextToken()));
        }

        for (int v=1; v<=n; v++)
        for (int i=1; i<=n; i++)
        for (int j=1; j<=n; j++)
        map[i][j] = Math.min(map[i][j], map[i][v]+map[v][j]);

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++)
            sb.append(map[i][j]%9900001+" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
