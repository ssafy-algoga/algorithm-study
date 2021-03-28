import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;

		st = new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int dp[][] = new int[N][K+1]; //dp[row][col] = coin[0]~coin[row]까지 해당하는 동전들을 고려하여 가치의 합이 col이 되도록 하는 경우의 수.
		int coins[] = new int[N];
		for(int i = 0 ; i < N ; i++)
			coins[i] = Integer.parseInt(in.readLine());

		//Sort 안 하는 방법은 없을까?-?
		Arrays.sort(coins);

		//제일 작은 동전만 쓰는 경우
		for(int i = 1; i <= K ; i++) {
			if((i % coins[0]) == 0) dp[0][i] = 1;
		}

		//고려하는 동전들을 늘려가며...
		for(int i = 1; i < N ; i++) {
			int curValue = coins[i];
			for(int j = coins[0]; j <= K; j++) {
				if(j < curValue ) dp[i][j] = dp[i-1][j]; //동전의 가치가 담을 수 있는 총량보다 클 때 - 못 담을 때
				else if(j == coins[i]) dp[i][j] = 1 + dp[i-1][j];  //동전의 가치가 담을 수 있는 총량과 같을 때 - 경우의 수 하나 추가됨.
				else dp[i][j] = dp[i-1][j] + dp[i][j-curValue]; //이후 포함포함포함!!
			}
		}
		System.out.println(dp[N-1][K]);
	}

}
