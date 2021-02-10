import java.util.*;
import java.io.*;

public class Main {
    static int[][] distance;
    // INF means unconnected nodes
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int numOfCities = Integer.parseInt(br.readLine());
        // Adjacent matrix initialized to INF
        distance = new int[numOfCities+1][numOfCities+1];
        for(int[] row : distance) Arrays.fill(row, INF);
        for(int i=1; i<=numOfCities; i++) distance[i][i] = 0;

        int numOfEdges = Integer.parseInt(br.readLine());
        for(int i=0; i<numOfEdges; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            distance[start][end] = Math.min(distance[start][end], weight);
        }
        // Floyd-Warshall algorithm
        for(int k=1; k<=numOfCities; k++) {
            for(int i=1; i<=numOfCities; i++) {
                for(int j=1; j<=numOfCities; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        // Output result
        for (int i=1; i<=numOfCities; i++) {
            for (int j=1; j<=numOfCities; j++) {
                if(distance[i][j] == INF) sb.append("0").append(" ");
                else sb.append(distance[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}