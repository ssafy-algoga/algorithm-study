package com.ssafy.algostudy;

import java.util.Scanner;

public class boj_1463 { // 1로 만들기
	static int[] dp = new int[10000000];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
		int count = 0; // 연산 횟수
		dp[1] = 0;
		for(int i=2; i<=n; i++) {
			count = dp[i-1] + 1; // 1을 뺐을 경우의 연산 횟수 
			if(i%3==0) count = Math.min(count, dp[i/3]+1); // 3으로 나눴을 때의 연산 횟수와 1을 뺐을 경우의 연산 횟수의 최솟값 
			if(i%2==0) count = Math.min(count, dp[i/2]+1); // 2으로 나눴을 때의 연산 횟수와 1을 뺐을 경우의 연산 횟수의 최솟값
			dp[i] = count; // 최솟값 입력
			
		}
		System.out.println(dp[n]); 

	}

}

//private static int func(int n) { // 2 부터 시작하는거임 탑다운
//	// 기저 조건
//	if(n==1) return 0;
//	if(dp[n] != -1) return dp[n];
//	
//	int res = func(n-1) + 1;
//	if(n%3==0) {
//		res = Math.min(res, func(n/3)+1);
//	}
//	if(n%2==0) {
//		res = Math.min(res, func(n/2)+1);
//	}
//	dp[n] = res;
//	return res;
//}