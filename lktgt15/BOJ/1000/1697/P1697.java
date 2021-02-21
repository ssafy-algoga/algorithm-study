import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class P {
    int x, second;
    public P (int x, int second) {
        this.x = x;
        this.second = second;
    }
}

public class P1697 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static boolean[] visit;

    static int[] dx = {-1, 1, 0};
    static final int MAX=100000;

    static int bfs() {

        Queue<P> que = new LinkedList<>();

        que.offer(new P(n,0));
        visit[n] = true;

        while (!que.isEmpty()) {

            P p = que.poll();

            if (p.x == k)
                return p.second;

            dx[2] = p.x;
            for (int i = 0; i < 3; i++) {
                int nx = p.x + dx[i];

                if (nx<0 || MAX<nx)
                    continue;
                if (visit[nx])
                    continue;
                
                visit[nx] = true;
                que.add(new P(nx,p.second+1));
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");

        n = Integer.parseInt(tokens[0]);
        k = Integer.parseInt(tokens[1]);

        visit = new boolean[MAX+1];
        
        System.out.println(bfs());
    }
}