package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_15663 {
	
	static int[] numbers;
	static int N;
	static int M;
	static int[] p;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		p = new int[M];
		isSelected = new boolean[N];
		
		StringTokenizer s = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(s.nextToken());
		}
		
		Arrays.sort(numbers);
		
		permutation1(0);
		
		System.out.println(sb.toString());
	}
	
	public static void permutation(int cnt) {
		
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				sb.append(p[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			if(i!=0 && !isSelected[i-1]  && numbers[i-1]==numbers[i]) continue;
			
			p[cnt] = numbers[i];
			isSelected[i] = true;
			
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	public static void permutation1(int cnt) {
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				sb.append(p[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
	
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			set.add(numbers[i]);
		}
		for (int num : set) {
			int i = 0;
			for (int j = 0; j < N; j++) {
				if(numbers[j]==num) {
					i = j;
					if(isSelected[j]==false) {
						break;
					}
				}
			}
			
			if(isSelected[i]) continue;
			
			p[cnt] = num;
			isSelected[i] = true;
			
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
}
