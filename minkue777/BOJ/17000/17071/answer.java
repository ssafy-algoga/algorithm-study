import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VAL = 500000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[][] dist = new int[2][MAX_VAL + 1];
        for(int[] row : dist) Arrays.fill(row, -1);
        Queue<Integer> q = new ArrayDeque<>();
        dist[0][start] = 0;
        q.offer(start);
        int accel = 0;
        while(!q.isEmpty()) {
            target += accel;
            if(target > MAX_VAL) break;
            if(dist[accel % 2][target] != -1) {
                System.out.println(accel);
                return;
            }

            int size = q.size();
            for(int i = 0; i < size; i++) {
                int curNode = q.poll();
                int[] nextNode = {curNode - 1, curNode + 1, 2 * curNode};
                for(int next : nextNode) {
                    if(next >= 0 && next <= MAX_VAL && dist[(accel+1) % 2][next] == -1) {
                        dist[(accel+1) % 2][next] = accel+1;
                        q.offer(next);
                    }
                }
            }
            accel++;
        }
        System.out.println(-1);
    }
}