package boj.m3w5_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19539_사과나무 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] tree = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		int one = 0;
		int two = 0;
		for (int i = 0; i < N; i++) {
			two += tree[i]/2;
			one += tree[i]%2;
		}
		
		if(two >= one && (two - one)%3 == 0) System.out.println("YES");
		else System.out.println("NO");
	}

}
