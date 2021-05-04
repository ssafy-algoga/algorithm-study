import java.io.*;
import java.util.*;

public class Main {

    static int N, size;
    static boolean[] deleted;
    static PriorityQueue<Node> minHeap, maxHeap;

    static class Node implements Comparable<Node> {
        int idx;
        long num;
        Node(int idx, long num) {
            this.idx = idx;
            this.num = num;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.num, o.num);
        }
    }

    static void solution(int idx, char cmd, long num) {
        if (cmd == 'D') {
            if (size == 0) {
                minHeap.clear();
                maxHeap.clear();
                return;
            }

            if (num == 1) { // max
                while (true) {
                    Node del = maxHeap.poll();
                    if (!deleted[del.idx]) {
                        deleted[del.idx] = true;
                        break;
                    }
                }
            } else { // min
                while (true) {
                    Node del = minHeap.poll();
                    if (!deleted[del.idx]) {
                        deleted[del.idx] = true;
                        break;
                    }
                }
            }
            size--;
        } else {
            minHeap.add(new Node(idx, num));
            maxHeap.add(new Node(idx, -num));
            size++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>();
            size = 0;

            N = Integer.parseInt(in.readLine());
            deleted = new boolean[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                char cmd = st.nextToken().charAt(0);
                long num = Long.parseLong(st.nextToken());
                solution(i, cmd, num);
            }

            if (size > 0) {
                while (!minHeap.isEmpty() && deleted[minHeap.peek().idx])
                    minHeap.poll();
                while (!maxHeap.isEmpty() && deleted[maxHeap.peek().idx])
                    maxHeap.poll();

                sb.append(-maxHeap.peek().num).append(" ").append(minHeap.peek().num);
            } else sb.append("EMPTY");

            sb.append("\n");
        }
        System.out.print(sb);
    }
}
