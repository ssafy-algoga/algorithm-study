import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2293_coin1 {
	static int N, K;
	static int[][] DP;
	static int[] coin;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 코인수
		K = Integer.parseInt(st.nextToken()); // 목표 금액
		coin = new int[N];
		DP = new int[N][K+1]; // N개의 동전을 사용해 K를 만드는 경우의 수 저장
		
		for (int i = 0; i < N; i++) { // 코인 가치 입력
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < N; i++) { // coin[i] 까지의 코인을 사용해서
			for (int k = 1; k <= K; k++) { // 1~K 까지 만드는 경우의 수
				
				
				if(i == 0) { // 초기화 
					if(k % coin[0] == 0) DP[0][k] = 1;
				} else { 
					for (int v = 0; v <= k ; v += coin[i]) { // 0~k 금액을 현재 코인을 사용해 만들기
						if(v == k) { // 금액이 딱 맞으면
							DP[i][k]++; // 경우의 수 증가
						} else { // 금액이 남으면
							DP[i][k] += DP[i-1][k-v]; // 이전 코인까지 사용한 경우의 수를 더함
						}
					}
				}
			}
		}	
		System.out.println(DP[N-1][K]);
	}
}
