package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_15663 {
	static int n;
	static int m;
	static int[] arr;
	static StringBuilder sb;
	static boolean[] isSelected;
	static LinkedList<Integer> result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[n];
		result = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 입력 받은 배열 sort
		ArrayList<Integer> result = new ArrayList<>(); // 수열 생성을 위한 배열 
		isSelected = new boolean[n]; 
		sb = new StringBuilder(); 
		permu(0);
		
	}

	private static void permu(int cnt) {
		if (cnt == m) { // depth가 m이면 출력 
			for (int i : result) { 
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		int lastNum = -1; // 중복 방지 변수
		for (int i = 0; i < n; i++) {
			if(isSelected[i] || lastNum == arr[i]) continue;
			
			result.add(arr[i]);
			lastNum = arr[i];
			isSelected[i] = true;
			permu(cnt+1);
			isSelected[i] = false;
			result.remove(result.size()-1);
		}
	}
}
