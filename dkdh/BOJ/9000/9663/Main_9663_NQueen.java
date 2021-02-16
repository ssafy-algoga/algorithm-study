package boj;

import java.util.Scanner;

// 13872kb 2984ms
public class Main_9663_NQueen {
	
	public static int N;
	
	public static boolean[] rowSelected;
	public static boolean[] colSelected;
	public static boolean[] crossSelected1;
	public static boolean[] crossSelected2;
	
	public static int totalCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		sc.close();

		rowSelected = new boolean[N]; // 가로
		colSelected = new boolean[N]; // 세로
		crossSelected1 = new boolean[2*N-1]; // 차 (대각 왼위->오아래)
		crossSelected2 = new boolean[2*N-1]; // 합 (대각 오위->왼아래)
		
		nQueen(0);
		System.out.println(totalCnt);
	}
	
	public static void nQueen(int rStart) {
		
		if(rStart==N) {
			totalCnt++;
			return;
		}
		
		for (int i = rStart; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!rowSelected[i] && !colSelected[j]
						&& !crossSelected1[i-j+N-1]
								&& !crossSelected2[i+j]) {
					rowSelected[i] = true;
					colSelected[j] = true;
					crossSelected1[i-j+N-1] = true;
					crossSelected2[i+j] = true;
					
					nQueen(i+1);
					
					rowSelected[i] = false;
					colSelected[j] = false;
					crossSelected1[i-j+N-1] = false;
					crossSelected2[i+j] = false;
				}
			}
			// 한 행에 선택된 칸이 하나도 없으면 경우의 수가 없는 것이므로 return
			if(!rowSelected[i]) {
				return;
			}
		}
		
		return;
	}
	
	
}
