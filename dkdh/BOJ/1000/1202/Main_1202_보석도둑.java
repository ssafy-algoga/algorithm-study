package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1202_보석도둑 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] jewelry = new int[N][2];
		int[] bag = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			jewelry[i][0] = Integer.parseInt(st.nextToken());
			jewelry[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		// 입력 끝
		
		Arrays.sort(bag);
		Arrays.sort(jewelry, (int[] o1, int[] o2) -> o1[0] - o2[0]);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		long total = 0;
		int cnt = 0;
		int w = 0;
		
		for (int i = 0; i < K; i++) {
			w = bag[i];
			
			// 가방에 넣을 수 있는 모든 보석을 우선순위 큐에 넣는다.
			for (int j = cnt; j < N; j++) {
				if(jewelry[cnt][0] <= w) {
					pq.add(jewelry[cnt][1]);
					cnt++;
				}
				else break;
			}
			
			// 가방에 넣을 수 있는 보석 중 가장 가치가 높은 보석을 선택한다.
			if(pq.isEmpty()) continue;
			total += pq.poll();
		}
		
		System.out.print(total);
	}

}
