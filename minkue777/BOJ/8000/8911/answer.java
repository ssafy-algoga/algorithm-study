import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int minRow, maxRow, minCol, maxCol, curRow, curCol, curDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            minRow = maxRow = minCol = maxCol = 0;
            curRow = curCol = curDir = 0;
            String input = br.readLine();
            for(int idx = 0; idx<input.length(); idx++) {
                char cmd = input.charAt(idx);
                if(cmd == 'F') {
                    curRow += dr[curDir];
                    curCol += dc[curDir];
                } else if(cmd == 'B') {
                    curRow -= dr[curDir];
                    curCol -= dc[curDir];
                } else if(cmd == 'R') {
                    curDir = (curDir + 1) % 4;
                } else {
                    curDir = (curDir + 3) % 4;
                }
                minRow = Math.min(minRow, curRow);
                maxRow = Math.max(maxRow, curRow);
                minCol = Math.min(minCol, curCol);
                maxCol = Math.max(maxCol, curCol);
            }
            int area = (maxRow - minRow) * (maxCol - minCol);
            sb.append(area).append("\n");
        }
        System.out.println(sb);
    }
}