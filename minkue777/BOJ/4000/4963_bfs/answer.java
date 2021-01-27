import java.util.*;
import java.io.*;

// Node class for 2-dimensional coordinate (y, x)
class Node {
    public int y;
    public int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static int[][] field;
    // clockwise direction from 12
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    /* dfs algorithm
    check the value of the node visited by -1 instead of using conventional visited array
     */
    public static int dfs(Node node) {
        field[node.y][node.x] = -1;
        for(int i=0; i<8; ++i) {
            int ny = node.y + dy[i];
            int nx = node.x + dx[i];
            if(nx < 0 || ny < 0 || ny >= field.length || nx >= field[0].length)
                continue;
            if(field[ny][nx] == 1) dfs(new Node(ny, nx));
        }
        return 1;
    }

    // bfs algorithm
    public static int bfs(Node startNode) {
        field[startNode.y][startNode.x] = -1;
        Queue<Node> q = new LinkedList<>();
        q.offer(startNode);
        while (!q.isEmpty()) {
            Node node = q.poll();
            field[node.y][node.x] = -1;
            for(int i=0; i<8; ++i) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (nx < 0 || ny < 0 || ny >= field.length || nx >= field[0].length)
                    continue;
                if (field[ny][nx] == 1) q.offer(new Node(ny, nx));
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            int answer = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;
            field = new int[h][w];
            for(int i=0; i<h; ++i) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; ++j) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // Perform dfs or bfs algorithm
            for(int i=0; i<h; ++i) {
                for(int j=0; j<w; ++j) {
                    if(field[i][j] == 1) answer += bfs(new Node(i, j));
                }
            }
            System.out.println(answer);
        }
    }
}