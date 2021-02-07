package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15663 {
	static int n;
	static int m;
	static int[] arr;
	static StringBuilder sb;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 입력 받은 배열 sort
		ArrayList<Integer> result = new ArrayList<>(); // 수열 생성을 위한 배열 
		int depth = 0; // 재귀 몇 번 돌았는 지 세기 ( m과 비교함 )
		visited = new boolean[n]; 
		sb = new StringBuilder(); 
		func(0, depth, result);
		
	}

	private static void func(int idx, int depth, ArrayList<Integer> result) {
		if (depth == m) { // depth가 m이면 출력 
			for (int i : result) { // 5) arr[0] arr[1] 출력
				sb.append(i+" ");
			}
			String s = sb.toString();		
			System.out.println(s);
			sb.setLength(0);
			return;
		}
		int lastNum = -1; // 중복 방지 변수
		for (int i = 0; i < n; i++) {
			
			if (!visited[i] && lastNum != arr[i]) { // 1) arr[0] 3) arr[1] 7) arr[2] ... 반복
					
				visited[i] = true;
				lastNum = arr[i];
				result.add(arr[i]); // 2) arr[0] 4) arr[1]
				func(i, depth + 1, result); // depth 증가
				result.remove(result.size() - 1); // 6) arr[0] 만 남는다. 
				visited[i] = false;
			}
		}
	}
}
