import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numberOfRow, numberOfCol, numberOfGroup, numberOfIceberg;
    static boolean[][] isVisited;
    static int[][] map, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<Iceberg> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        numberOfRow = Integer.parseInt(st.nextToken());
        numberOfCol = Integer.parseInt(st.nextToken());

        map = new int[numberOfRow][numberOfCol];
        for (int i = 0; i < numberOfRow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numberOfCol; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) q.add(new Iceberg(i, j, map[i][j]));
            }
        }

        int time = 0;
        isVisited = new boolean[numberOfRow][numberOfCol];
        while (!q.isEmpty()) {
            numberOfIceberg = q.size();
            numberOfGroup = 0;
            for (int i = 0; i < numberOfRow; i++) {
                for (int j = 0; j < numberOfCol; j++) {
                    if (isVisited[i][j] || map[i][j] == 0) continue;
                    dfs(i, j, numberOfIceberg);
                    numberOfGroup++;
                }
                if (numberOfGroup >= 2) {
                    System.out.println(time);
                    return;
                }
            }
            time++;
            melt();
            for (int i = 0; i < numberOfRow; i++)
                Arrays.fill(isVisited[i], false);
        }
        System.out.println(0);
    }

    static void melt() {
        Queue<Iceberg> temp = new ArrayDeque<>();
        while (!q.isEmpty()) {
            Iceberg iceberg = q.poll();
            int water = 0;
            for (int[] d : dir) {
                int nx = iceberg.x + d[0];
                int ny = iceberg.y + d[1];
                if (0 > nx || nx >= numberOfRow || 0 > ny || ny >= numberOfCol) continue;
                if (map[nx][ny] > 0) continue;
                water++;
            }
            iceberg.height -= water;
            temp.add(iceberg);
        }
        while (!temp.isEmpty()) {
            Iceberg iceberg = temp.poll();
            if (iceberg.height > 0) q.add(iceberg);
            map[iceberg.x][iceberg.y] = Math.max(iceberg.height, 0);
        }
    }

    static void dfs(int x, int y, int count) {
        isVisited[x][y] = true;
        if (count == 0) return;
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (0 > nx || nx >= numberOfRow || 0 > ny || ny >= numberOfCol) continue;
            if (isVisited[nx][ny] || map[nx][ny] == 0) continue;
            dfs(nx, ny, count - 1);
        }
    }

    static class Iceberg {
        int x, y, height;

        Iceberg(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}