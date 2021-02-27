import java.io.*;
import java.util.*;

public class Main {
    static List<Set<Integer>> subway = new ArrayList<>();
    static int numOfLine;
    static int[] transfer;
    static int shortest = 100;

    static boolean isTransfer(Set<Integer> line1, Set<Integer> line2) {
        for(int station : line1) {
            if(line2.contains(station)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new ArrayDeque<>();
        numOfLine = Integer.parseInt(br.readLine());
        transfer = new int[numOfLine];
        for(int i=0; i < numOfLine; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfStation = Integer.parseInt(st.nextToken());
            subway.add(new HashSet<>());
            for(int j=0; j < numOfStation; j++) {
                subway.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
        int target = Integer.parseInt(br.readLine());
        for(int start = 0; start < numOfLine; start++) {
            if(subway.get(start).contains(0)) {
                Arrays.fill(transfer, -1);
                q.clear();
                q.offer(start);
                transfer[start] = 0;
                while (!q.isEmpty()) {
                    int now = q.poll();
                    if(subway.get(now).contains(target)) {
                        shortest = Math.min(shortest, transfer[now]);
                        break;
                    }
                    for(int next = 0; next < numOfLine; next++) {
                        if(next == now || transfer[next] != -1) continue;
                        if(isTransfer(subway.get(now), subway.get(next))) {
                            q.offer(next);
                            transfer[next] = transfer[now] + 1;
                        }
                    }
                }
            }
        }
        System.out.println(shortest != 100 ? shortest : -1);
    }
}