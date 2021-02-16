import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HideAndSeek3 {
    public static int N, K;
    public static int[] hasVisited;
    public static boolean[] checked;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        hasVisited = new int[100001];
        checked = new boolean[100001];
        hasVisited[N] = 0;
        int seconds = 0;

        if(N != K){
            bfs();
            seconds = hasVisited[K];
        }
        System.out.println(seconds);
    }

    public static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        int len = hasVisited.length;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int tmp = hasVisited[curr];

            if(checked[K]) break;
            if(curr * 2 < len && !checked[2 * curr]){
                queue.offer(2 * curr);
                hasVisited[2 * curr] = tmp;
                checked[2 * curr] = true;
            }

            if(curr -1 >= 0 && !checked[curr -1]){
                queue.offer(curr - 1);
                hasVisited[curr -1] = tmp + 1;
                checked[curr - 1] = true;
                if (curr > K) {
                    continue;
                }
            }
            if(curr + 1 < len && !checked[curr + 1]){
                queue.offer(curr + 1);
                hasVisited[curr +1] = tmp + 1;
                checked[curr + 1] = true;
            }

        }
    }
}
