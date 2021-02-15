import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int count;
    static void sol(int row, int colMask, int leftMask, int rightMask) {
        // n개의 퀸이 모두 배치됐다면 count를 증가
        if(row == n) {
            count++;
            return;
        }
        // 모든 column들을 순회하면서
        for(int col=0; col<n; col++) {
            // 만약 다른 모든 퀸들과 공격할 수 없는 위치라면
            if((colMask & (1 << col)) == 0 &&
                    (leftMask & (1 << row+col)) == 0 &&
                    (rightMask & (1 << row-col+n-1)) == 0) {
                // 이 위치를 Mask에 저장하고 다음 인자로 전달
                sol(row+1, colMask | (1 << col),
                        leftMask | (1 << row+col),
                        rightMask | (1 << row-col+n-1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sol(0, 0, 0, 0);
        System.out.println(count);
    }
}