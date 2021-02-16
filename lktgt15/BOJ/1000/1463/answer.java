import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int[] dp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		Arrays.fill(dp, -1);
		dp[1] = 0;
		System.out.println(f(n));
	}
	
	static int f(int n) {
		if(dp[n] != -1) return dp[n];
		dp[n] = Integer.MAX_VALUE;
		if(n%3 == 0) dp[n] = Math.min(dp[n], f(n/3)+1);
		if(n%2 == 0) dp[n] = Math.min(dp[n], f(n/2)+1);
		if(n-1 >= 1) dp[n] = Math.min(dp[n], f(n-1)+1);
		return dp[n];
	}
}