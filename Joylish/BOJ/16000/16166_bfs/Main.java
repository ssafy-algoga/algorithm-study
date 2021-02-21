import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, target, min = Integer.MAX_VALUE;
    static int[] isVisited = new int[11];
    static HashMap<Integer, ArrayList<Integer>> lines, rel;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        lines = new HashMap<>(); //호선별 지하철역
        rel = new HashMap<>(); //호선별 연결관계
        ArrayList<Integer> zeroLines = new ArrayList<>(); // 0인 역이 있는 호선

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken()); // 역 갯수

            ArrayList<Integer> line = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                int station = Integer.parseInt(st.nextToken());
                line.add(station);
                if (station == 0) zeroLines.add(n);
            }
            line.sort(Comparator.comparingInt(a -> a));
            lines.put(n, line);
            rel.put(n, new ArrayList<>());
        }

        //호선별 연결관계 만들기
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (i == j) continue;
                for (int x : lines.get(i)) {
                    for (int y : lines.get(j)) {
                        if (x == y) {
                            rel.get(i).add(j);
                            rel.get(j).add(i);
                        }
                    }
                }
            }
        }

        target = Integer.parseInt(br.readLine());
        for (int line : zeroLines)
            bfs(line);

        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void bfs(int line) {
        Arrays.fill(isVisited, -1);
        q.clear();
        
        q.add(line); // 도착역이 있는 호선 추가
        isVisited[line] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (lines.get(now).contains(target)) {
                min = Math.min(min, isVisited[now]);
                return;
            }
            // 현재 line에 연결된 다른 호선
            for (int other : rel.get(now)) {
                if (isVisited[other] >= 0) continue;
                isVisited[other] = isVisited[now] + 1;
                q.add(other);
            }
        }
    }
}