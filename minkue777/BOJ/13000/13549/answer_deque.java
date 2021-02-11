import java.util.*;

public class Main {
    static final int MAX_SIZE = 100000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int target = sc.nextInt();
        if(target <= start) {
            System.out.println(start - target);
            return;
        }

        int[] distance = new int[MAX_SIZE+1];
        Arrays.fill(distance, -1);
        Deque<Integer> q = new ArrayDeque<>();

        distance[start] = 0;
        q.offer(start);

        while(!q.isEmpty()) {
            int curNode = q.poll();
            if(curNode == target) {
                System.out.println(distance[curNode]);
                return;
            }

            int[] weight = new int[] {0, 1, 1};
            int[] nextNodes = new int[] {2 * curNode, curNode-1, curNode+1};
            for(int i=0; i<3; i++) {
                int nextNode = nextNodes[i];
                if(i == 0) {
                    while(nextNode>=0 && nextNode<=MAX_SIZE && distance[nextNode] == -1) {
                        q.addFirst(nextNode);
                        distance[nextNode] = distance[curNode] + weight[i];
                        nextNode *= 2;
                    }
                }
                else {
                    q.addLast(nextNode);
                    distance[nextNode] = distance[curNode] + weight[i];
                }
            }
        }
    }
}