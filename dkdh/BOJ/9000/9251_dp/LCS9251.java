package boj;

import java.util.Scanner;

public class LCS9251 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//문자열 두 개 입력받기
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		
		int size1 = str1.length();
		int size2 = str2.length();
		
		//각 부분 문자열에 대한 LCS를 기억할 배열 생성
		int lcs[][] = new int[size1+1][size2+1];
		
		//표 채워넣기
		for (int i = 0; i < size1; i++) {
			for (int j = 0; j < size2; j++) {
				if(str1.charAt(i)==str2.charAt(j))
					lcs[i+1][j+1] = lcs[i][j] + 1;
				else
					lcs[i+1][j+1] = Math.max(lcs[i][j+1], lcs[i+1][j]);
			}
		}
		
		//결과 출력
		System.out.print(lcs[size1][size2]);
		
		sc.close();
	}

}
