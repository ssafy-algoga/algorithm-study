import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class P13549 {
    
    static final int MAX = 100000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Queue<Integer> que = new ArrayDeque<>();
    static int N, K;
    static int[] visit = new int[MAX+1];

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(visit, -1);
        que.offer(N);

        visit[N] = 0;
        while (!que.isEmpty()) {
            int x = que.poll();

            if(visit[K]!=-1 && visit[x] >= visit[K]) break;

            int jump = 2*x;
            if (jump <= MAX && visit[jump]==-1) {
                visit[jump] = visit[x];
                que.offer(jump);
            }
            int left = x-1;
            if (0<=left && visit[left]==-1) {
                visit[left] = visit[x]+1;
                que.offer(left);
            }
            int right = x+1;
            if (right<=MAX && visit[right]==-1) {
                visit[right] = visit[x]+1;
                que.offer(right);
            }
        }
        bw.write(visit[K]+"\n");
        bw.flush();
        br.close(); bw.close();
    }
}

