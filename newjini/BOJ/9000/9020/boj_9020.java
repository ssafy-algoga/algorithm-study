package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9020 {
	
	static int N; // 짝수 n
	static int[] found; // 소수 찾은 배열
	static int res; // 가장 작은 골드바흐 파티션의 차이
	static int[] result; // 골드바흐 파티션 2개 

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		int[] num = new int[tc];
		for (int t = 0; t < tc; t++) {
			num[t] = Integer.parseInt(br.readLine());
			N = num[t];
			found = new int[num[t]];
			found[0] = found[1] = 1;
			res = Integer.MAX_VALUE; // 최댓값으로 초기화
			result = new int[2];
			findNum(num[t]); // 소수 찾기
			System.out.println(result[0] + " " + result[1]);
		}

	}

	private static void findNum(int num) {
		
		for (int i = 2; i * i < num; i++) { // 에라토스테네스 체로 소수 찾기
			for (int j = i * i; j < num; j += i) {
				found[j] = 1; // 소수가 아닌 경우, 1로 표시
				
			}
		}
		
		
		for (int i = 2; i < num; i++) {
			if (found[i] == 0) { // i가 소수인 경우,
				int isPrime = N-i; // i의 짝꿍 찾고
				if(found[isPrime] == 0) { // i의 짝꿍도 소수라면,
					if(res > Math.abs(isPrime-i)){ // 서로 가장 작은 차이가 나는 소수 한 쌍 찾기
						res = Math.abs(isPrime-i);
						result[0] = i;
						result[1] = isPrime;
					}
				}
			}
		}
	}

}
