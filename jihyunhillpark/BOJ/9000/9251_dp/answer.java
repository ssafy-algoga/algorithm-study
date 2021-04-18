import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9251 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] a, b;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        map = new int[b.length+1][a.length+1];

        // dp 시작
        for (int i = 0; i < b.length; i++)
        for (int j = 0; j < a.length; j++)
            // 두 문자가 같으면 맵의 왼쪽 대각선 위의 값+1, 아니라면 왼쪽이나 위의 값 중 큰 것을 그대로 받아옴
            map[i+1][j+1] = (b[i] == a[j])? map[i][j]+1: Math.max(map[i][j+1], map[i+1][j]);
        
        // map의 제일 구석 값이 lcs
        System.out.println(map[b.length][a.length]);
    }
}
