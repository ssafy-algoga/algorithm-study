package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class boj_9020_goldbach {

	static boolean[] isPrime;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int Even = Integer.parseInt(br.readLine());
			solution1(Even);
			//solution2(Even);
		}	
	}
	// 제곱근 방식
	public static void solution1(int n) {
		int n1 = n/2, n2 = n/2; // 중간값부터 +-1하며 소수체크
		
		while(true) {
			if(isPrime(n1) && isPrime(n2)) {
				System.out.println(n1 + " " + n2);
				break;
			}
			n1--; n2++;
		}		
	}
	
	public static boolean isPrime (long num) { //소수인지 체크
		int n = (int) Math.sqrt(num);
		for(int i = 2; i <= n; i++) {
			if(num % i == 0) {
				return false;
			}	
		}
		return true;
	}
	
	// 에라토스테네스의 체
	public static void solution2(int n) throws IOException {
		isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
	
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (isPrime[i] == false) continue;

			for (int j = 2 * i; j <= n; j += i) 
				isPrime[j] = false;		
		}
		
		int n1 = n/2, n2 = n/2; // 중간값부터 +-1하며 소수체크
		
		while(true) {
			if(isPrime[n1] && isPrime[n2]) {
				bw.write(String.valueOf(n1) + ' ' + n2 + '\n');
				break;
			}
			n1--; n2++;
		}
		bw.flush();
	}
}
