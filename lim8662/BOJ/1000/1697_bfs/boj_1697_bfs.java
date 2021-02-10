package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1697_bfs {
	static int N, K, step;
	static boolean[] visited = new boolean[1000001]; // 해당 위치의 방문 여부 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bfs();
	}
	// 너비 우선 탐색
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visited[N] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) { // 현 단계의 모든 위치를 탐색
				int cur = q.remove();
				if(cur == K) { // 찾으면 해당 단계 출력
					System.out.println(step);
					return;
				}
				// 다음 단계의 탐색할 위치 추가
				int jump = cur * 2;
				if(jump <= 100000 && !visited[jump]) {
					q.add(jump); visited[jump] = true;
				}
				if(cur < 100000 && !visited[cur+1]) {
					q.add(cur+1); visited[cur+1] = true;
				}
				if(cur > 0 && !visited[cur-1]) {
					q.add(cur-1); visited[cur-1] = true;
				}
			}
	    	step++;	// 다음 단계로 넘어감 
		}
	}
}
