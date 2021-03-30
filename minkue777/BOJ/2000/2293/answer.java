import java.io.*;
import java.util.*;

public class Main {
    static int[][] cache;
    static int[] coins;
    static int kindOfCoins;
    static int target;

    static int solve(int idx, int leftMoney) {
        if(idx == kindOfCoins) {
            return leftMoney == 0 ? 1 : 0;
        }
        int ret = cache[idx][leftMoney];
        if(ret != -1) return ret;
        ret = solve(idx+1, leftMoney);
        if(coins[idx] <= leftMoney) {
            ret += solve(idx, leftMoney - coins[idx]);
        }
        return cache[idx][leftMoney] = ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        kindOfCoins = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        coins = new int[kindOfCoins];
        cache = new int[kindOfCoins][target+1];
        for(int[] row : cache) Arrays.fill(row, -1);
        for(int idx = 0; idx < kindOfCoins; idx++) {
            coins[idx] = Integer.parseInt(br.readLine());
        }
        int ans = solve(0, target);
        System.out.println(ans);
    }
}