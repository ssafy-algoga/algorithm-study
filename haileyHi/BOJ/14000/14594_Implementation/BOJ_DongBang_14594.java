import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_DongBang_14594 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int commandNum = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x, y;
        int res = N;
        int[] rooms = new int[N + 1];

        for (int i = 0; i < commandNum; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            for (int j = x + 1; j <= y; ++j) {
                if (rooms[j] == 0) {
                    rooms[j] = 1;
                    --res;
                }
            }

        }

        System.out.println(res);
    }

}

