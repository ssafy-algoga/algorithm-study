import java.util.*;

public class Main {
    static final int INF = 987654321;
    static final int MAX_SIZE = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int target = sc.nextInt();
        if(target<=start) {
            System.out.println(start - target);
            return;
        }

        int[] distance = new int[MAX_SIZE + 1];
        Arrays.fill(distance, INF);
        Deque<Integer> q = new ArrayDeque<>();

        distance[start] = 0;
        q.addFirst(start);

        while (!q.isEmpty()) {
            int curNode = q.poll();
            if(curNode == target) {
                System.out.println(distance[curNode]);
                return;
            }

            int[] weight = new int[]{0, 1, 1};
            int[] nextNodes = new int[]{2*curNode, curNode-1, curNode+1};
            for(int i = 0; i<3; i++) {
                int nextNode = nextNodes[i];
                // 다음 노드가 범위 내에 있고 값이 갱신될 수 있다면 더 적은 값으로 갱신
                if (nextNode >= 0 && nextNode <= MAX_SIZE &&
                        distance[nextNode] > distance[curNode] + weight[i]) {
                    distance[nextNode] = distance[curNode] + weight[i];
                    // 가중치가 0 이라면 큐 앞에서 1 이라면 큐 뒤에서 삽입
                    if(i == 0) q.addFirst(nextNode);
                    else q.addLast(nextNode);
                }
            }
        }
    }
}