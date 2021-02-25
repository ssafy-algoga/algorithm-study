import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14594_dongbang {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] wall = new int[N]; // 벽 상태 저장

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int w = x; w < y; w++) {
				wall[w] = -1; // 벽 허뭄
			}
		}
		int cnt = 0;
		for(int i : wall) {
			if(i == 0) cnt++; // 벽의 수를 센다.
		}
		System.out.println(cnt);
	}
}
