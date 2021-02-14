import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_ColoringChess_1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] chess = new char[M][N];
        int[][] map = new int[M][N];

        int cp = 0;
        int result = 100;
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                chess[i][j] = str.charAt(j);//보드 받아오기
                if((i+j)%2 == 0 && chess[i][j] == 'W') map[i][j]++;
                if((i+j)%2 == 1 && chess[i][j] == 'B') map[i][j]++;
            }
        }
        for (int i = 0; i < M-7; i++) {
            for (int j = 0; j < N-7; j++) {
                cp = 0;
                for(int x = 0; x<8;x++){
                    for(int y = 0; y<8; y++){
                        if(map[i+x][j+y] != 0) cp++;
                    }
                }
                cp = Math.min(cp, 64 - cp);
                result = Math.min(cp, result);
            }
        }
        System.out.println(result);
    }
}