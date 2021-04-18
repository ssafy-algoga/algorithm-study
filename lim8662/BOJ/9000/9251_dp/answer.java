import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9251 {

	static int[][] dp;
	static char[] f, s;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		f = br.readLine().toCharArray();
		s = br.readLine().toCharArray();
		dp = new int[f.length + 1][s.length + 1];
		
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < s.length; j++) {
				dp[i + 1][j + 1] = ( f[i] == s[j] ) ? dp[i][j] + 1 : Math.max(dp[i+1][j], dp[i][j+1]) ;
			}
		}
		System.out.println(dp[f.length][s.length]);	
	}
}
