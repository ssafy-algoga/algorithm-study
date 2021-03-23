import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] costs;

    static int find(int u) {
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return;
        if(costs[u] <= costs[v]) {
            parent[v] = u;
        } else {
            parent[u] = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> minCost = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfStudents = Integer.parseInt(st.nextToken());
        parent = new int[numOfStudents+1];
        for(int idx = 1; idx <= numOfStudents; idx++) {
            parent[idx] = idx;
        }
        int numOfUnions = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        costs = new int[numOfStudents+1];
        st = new StringTokenizer(br.readLine());
        for(int idx = 1; idx <= numOfStudents; idx++) {
            costs[idx] = Integer.parseInt(st.nextToken());
        }

        for(int idx = 0; idx < numOfUnions; idx++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int sum = 0;
        for(int idx = 1; idx <= numOfStudents; idx++) {
            if(parent[idx] == idx) {
                sum += costs[idx];
            }
        }

        System.out.println(sum <= money ? sum : "Oh no");
    }
}