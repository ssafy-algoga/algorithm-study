import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_Making1_1463 {
    public static int[] range;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        range = new int[N + 1];//여기부터 1까지 내려가기
        Arrays.fill(range, N);
        range[N] = 0;

        search();
        System.out.println(range[1]);
    }

    public static void search() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);

        while (!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int tmp = range[cur];

            if (cur % 3 == 0 && cur / 3 > 0) {
                deque.offer(cur / 3);
                range[cur / 3] = Math.min(tmp + 1, range[cur / 3]);
            }
            if (cur % 2 == 0 && cur / 2 > 0) {
                deque.offer(cur / 2);
                range[cur / 2] = Math.min(tmp + 1, range[cur / 2]);
            }
            if (cur - 1 >= 0) {
                deque.offer(cur - 1);
                range[cur - 1] = Math.min(tmp + 1, range[cur - 1]);
            }
            if (range[1] != N) break;
        }


    }
}
