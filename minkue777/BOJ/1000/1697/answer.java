import java.util.*;

public class Main {
    static final int MAX_SIZE = 100001;
    static int[] distance = new int[MAX_SIZE];
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int target = sc.nextInt();
        q.offer(start);
        // bfs algorithm to the shortest distance
        while(!q.isEmpty()) {
            int next = q.poll();
            if(next == target) {
                System.out.println(distance[target]);
                break;
            }
            if((next+1) < MAX_SIZE && distance[next+1] == 0) {
                distance[next+1] = distance[next] + 1;
                q.offer(next+1);
            }
            if((next-1) >= 0 && distance[next-1] == 0) {
                distance[next-1] = distance[next] + 1;
                q.offer(next-1);
            }
            if((2*next) < MAX_SIZE && distance[2*next] == 0) {
                distance[2*next] = distance[next] + 1;
                q.offer(2*next);
            }
        }
    }
}