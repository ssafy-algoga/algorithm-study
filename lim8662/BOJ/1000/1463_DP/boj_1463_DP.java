package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1463_DP {
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int X = Integer.parseInt(br.readLine());
	
		dp(X, 0); // 1로 만들기
		
		System.out.println(min);
	}
	
	public static void dp(int x, int cnt) {
		
		if(x == 1) { // 1이면 연산 최솟값 갱신 후 리턴
			min = (min > cnt) ? cnt : min;
			return;
		}
		if(cnt + 1 >= min) return; // 연산 회수가 최솟값을 넘어간다면 연산 종료
		
		if(x % 3 == 0) dp(x/3, cnt+1); // 연산 1
		if(x % 2 == 0) dp(x/2, cnt+1); // 연산 2
		dp(x-1, cnt+1); // 연산 3
	}
}
