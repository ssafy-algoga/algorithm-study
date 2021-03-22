import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> relation = new ArrayList<>();
    static int[] adapter;
    static boolean[] visited;
    static int cnt;

    static int isAdapter(int node) {
        if(adapter[node] != -1) return adapter[node];
        visited[node] = true;
        List<Integer> friends = relation.get(node);
        boolean flag = false;
        for(Integer friend : friends) {
            if(!visited[friend] && isAdapter(friend) == 0) flag = true;
        }
        if(flag) cnt++;
        return adapter[node] = flag ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfNodes = Integer.parseInt(br.readLine());
        adapter = new int[numOfNodes+1];
        visited = new boolean[numOfNodes+1];
        for(int i=0; i<=numOfNodes; i++) {
            relation.add(new ArrayList<>());
        }
        for(int i=0; i<numOfNodes-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int other = Integer.parseInt(st.nextToken());
            relation.get(one).add(other);
            relation.get(other).add(one);
        }
        Arrays.fill(adapter, -1);
        isAdapter(1);
        System.out.println(cnt);
    }
}