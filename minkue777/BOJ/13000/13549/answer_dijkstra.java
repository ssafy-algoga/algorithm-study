import java.util.*;

public class Main {
    static class Node {
        int index;
        int distance;

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    static final int INF = 987654321;
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
        Arrays.fill(distance, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((p,q)->p.distance-q.distance);
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
            if(curNode.index == target) {
                System.out.println(distance[curNode.index]);
                return;
            }
            // 기준점으로부터 curNode에 도달하기 위한 최소 비용
            int dist = curNode.distance;
            // 현재 노드
            int now = curNode.index;
            // 이미 이전에 처리된 적이 있는 노드라면 통과
            if(distance[now] < dist) continue;
            // 현재 노드와 연결된 노드들을 차례대로 검사
            int[] weight = new int[] {0, 1, 1};
            int[] nextNodes = new int[] {2 * now, now-1, now+1};
            for(int i=0; i<3; i++) {
                int next = nextNodes[i];
                if (next >= 0 && next <= MAX_SIZE) {
                    int cost = distance[now] + weight[i];
                    if(cost < distance[next]) {
                        distance[next] = cost;
                        pq.offer(new Node(next, cost));
                    }
                }
            }
        }
    }
}