package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11404_플로이드 {
	
	private static int INF = 10000001;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] shortest = new int[N+1][N+1];
		
		// 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				shortest[i][j] = INF;
			}
		}
		
		// 입력으로 초기값 결정
		// 같은 노선의 버스는 무조건 비용이 낮은 것 선택
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int r = shortest[a][b];
			if(r!=0) {
				shortest[a][b] = r < c? r : c;
			}else {
				shortest[a][b] = c;
			}

		}
		
		// 각 노드를 거쳐 갈 때
		for (int node = 1; node <= N; node++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j || node==i || node==j) continue;
					if(shortest[i][j]>shortest[i][node]+shortest[node][j])
						shortest[i][j] = shortest[i][node]+shortest[node][j];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(shortest[i][j]==INF)
					sb.append(0).append(" ");
				else
					sb.append(shortest[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}
