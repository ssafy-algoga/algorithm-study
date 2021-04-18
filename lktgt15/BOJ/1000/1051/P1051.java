import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1051 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new char[N][M];

        for (int i=0; i<N; i++)
        map[i] = br.readLine().toCharArray();

        int maxDist = 0;
        for (int i=0; i<N; i++)
        for (int j=0; j<M; j++) {
            int count=1;
            while (true) {
                int nx = j+count;
                int ny = i+count;
                if (nx<0 || ny<0 || N<=ny || M<=nx)
                break;

                int r = map[i][j]^map[i][nx];
                int b = map[i][j]^map[ny][j];
                int d = map[i][j]^map[ny][nx];
                if (r+b+d == 0 && maxDist < count)
                maxDist = count;

                count++;
            }
        }
        maxDist++;
        System.out.println(maxDist*maxDist);
    }
}
