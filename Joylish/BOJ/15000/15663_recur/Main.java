package com.boj.N과M9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] numbers;
	static boolean[] isSelected;
	static int[] choices;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		st =new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) { 
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers); //숫자 정렬
		
		isSelected = new boolean[N]; //해당 숫자를 골랐는지 확인하는 용도로 쓰이는 배열
		choices = new int[M];	//고른 숫자를 담는 배열
		
		permutation(0); // 순열 시작
		
		System.out.println(sb);
	}
	
	static void permutation(int count) {
		if(count == M) { //뽑고 싶은 수만큼 뽑았다면
			append();	// 뽑은 모든 수를 Stringbuilder에 저장
			return;	// 리턴
		}
		int past = -1; // 이전에 뽑은 숫자
		for(int i=0; i<N; i++) {
			int now = numbers[i]; // 숫자 고르기
			if(isSelected[i] || past == now) continue; //숫자를 이미 골랐거나 이전에 뽑은 숫자와 동일한지 확인
			past = now; //이전에 뽑은 숫자를 지금 뽑은 숫자로 업데이트
			choices[count] = now; //현재 뽑은 숫자 담기
			isSelected[i] = true; // 현재 뽑은 숫자 뽑았다고 체크  
			permutation(count+1); //숫자를 하나 뽑았으므로 뽑을 숫자 인덱스 증가
			isSelected[i] = false; // 현재 뽑은 숫자 다시 뽑을 수 있도록 false
		}
	}
	
	// choices 배열에 담긴 출력형식에 맞춰 Stringbuilder에 담기
	static void append() {
		for(int c : choices) {
			sb.append(c).append(" ");
		}
		sb.append("\n");
	}

}
