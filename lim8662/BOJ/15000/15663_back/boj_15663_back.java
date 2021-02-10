package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class boj_15663_back {
	static int N, M;
	static int[] sub; // 뽑은 수열
	static boolean[] isSelected; // 수의 사용 여부
	static int[] nSet; // N개의 수의 집합
	static LinkedHashSet<String> sqSet = new LinkedHashSet<>(); // 입력 순서대로 수열 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nSet = new int[N];
		sub = new int[M];
		isSelected = new boolean[N];
		// 수의 집합 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nSet[i] = Integer.parseInt(st.nextToken());
		}
		// 사전 순 수열 탐색을 위해서 오름차순 정렬 
		Arrays.sort(nSet);
		// 모든 수열 뽑기
		permutation(0);
		//출력
		String[] arr  = sqSet.toArray(new String[0]);
		for(String s : arr)
			System.out.println(s);
	}
	
	//nPm : 모든 수열 경우의 수
	static void permutation(int idx) {
		// 기저조건
		if(idx==M) { 
			sqSet.add(arrayToString(sub)); // Set에 저장해 중복 제거
			return;
		}
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;

			sub[idx] = nSet[i];
			isSelected[i] = true;
			
			permutation(idx+1);
			isSelected[i] = false;
		}
	}
	//수열을 문자열로 변환
	static String arrayToString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i: arr) sb.append(i).append(" ");
		return sb.deleteCharAt(sb.lastIndexOf(" ")).toString();
	}
}

