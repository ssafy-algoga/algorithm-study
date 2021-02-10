package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051_숫자정사각형 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		// 입력 받기
		for (int i = 0; i < N; i++) {
			// 한 줄 씩 입력 받기
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				// 각 문자를 숫자로 변환해서 배열에 저장
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		int max = N < M ? N : M;
LOOP: 	while(max>0) {
			for (int i = 0; i <= N-max; i++) {
				for (int j = 0; j <= M-max; j++) {
					if(map[i][j]==map[i+max-1][j] 
							&& map[i][j+max-1]==map[i+max-1][j+max-1]
									&& map[i][j]==map[i][j+max-1])
						break LOOP;
				}
			}
			max--;
		}
		
		System.out.println(max*max);
	}

}
