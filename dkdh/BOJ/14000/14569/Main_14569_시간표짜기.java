package boj.m4w1_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14569_시간표짜기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		long[] classes = new long[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				long time = 1;
				time <<= Integer.parseInt(st.nextToken());
				classes[i] |= time;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		long[] students = new long[M];
		Arrays.fill(students, Long.MAX_VALUE);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			for (int j = 0; j < p; j++) {
				long time = 1;
				time <<= Integer.parseInt(st.nextToken());
				students[i] ^= time;
			}
		}
		
		for (int i = 0; i < M; i++) {
			int possible = 0;
			for (int j = 0; j < N; j++) {
				if( (students[i] & classes[j]) == 0) {
					possible++;
				}
			}
			sb.append(possible).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
