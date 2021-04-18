package 스터디._3월4주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2293동전1_dp {
	static int N, K;
	static int[] coins,d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//동전 단위 개수
		K = Integer.parseInt(st.nextToken());	//목표 금액
		//정의 : 여러 동전중 어떤 동전들을 조합하여 k원을 만들수 있는 경우의 수
		int[] d = new int[K+1];		//K원까지 확인해야하니 
		coins = new int[N];			//주어진 동전 단위를 받을배열

		for (int i = 0; i < N; i++) 
			coins[i] = Integer.parseInt(br.readLine());

		d[0]=1;
		
		for (int v : coins) {	//1,2,5 나옴
			for(int i=1; i<=K; i++) {
				if(v<i) {	//만들금액(idx,k원)이 현재 동전의 단위보다 크면
					// k원의 경우의수 = (기존의 k원을 구할 수 있는 경우의 수) + (k-동전단위 원의 경우의수) 
					d[i] = d[i-v]+d[i];
				}else if(v==i) {	//만들 금액과 현재 동전의 단위가 같으면
					//기존의 k원을 구할수 있는 경우의수 + 1 만 해주면된다.
					d[i] = d[i]+1;
				}
			}
		}
		System.out.println(d[K]);
	}
}
