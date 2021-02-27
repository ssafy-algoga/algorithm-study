import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int n;
    static int[][] cache;
    static int[][] dist;
    static int minCost = INF;

    // 현재 위치와 방문도시목록이 주어졌을 때
    // 나머지 도시를 방문하는 부분 경로의 최소 길이를 반환하는 함수
    static int shortestPath(int cur, int visited) {
        // 기저 케이스 모든 도시를 다 방문했을 때는 처음 위치로 돌아감
        if(visited == (1 << n)-1) return dist[cur][0];
        // 이미 기존에 계산한 상황이면 그 값을 그대로 반환
        if(cache[cur][visited] != -1) return cache[cur][visited];
        int cost = INF;
        for(int next=1; next<n; next++) {
            // 아직 방문하지 않은 도시라면
            if((visited & (1 << next)) == 0)
                // 현재 비용과 그 도시를 방문했을 때 최소비용을 비교하여 비용을 갱신
                cost = Math.min(cost, dist[cur][next] +
                        shortestPath(next, visited + (1 << next)));
        }
        // 캐시에 값을 저장 후 반환
        return cache[cur][visited] = cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cache = new int[n][1<<n];
        dist = new int[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int d = Integer.parseInt(st.nextToken());
                // 이동할 수 없는 경로는 INF로 설정
                dist[i][j] = (d != 0) ? d : INF;
            }
        }
        // 캐시를 -1로 초기화하여 방문하지 않음을 표시
        for(int[] row : cache) Arrays.fill(row, -1);
        // 처음 시작 도시는 0번 도시로 고정
        minCost = Math.min(minCost, shortestPath(0, 1));
        System.out.println(minCost);
    }
}