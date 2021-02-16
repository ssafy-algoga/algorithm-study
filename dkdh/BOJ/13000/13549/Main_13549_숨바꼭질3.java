package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_13549_숨바꼭질3 {

	public static void main(String[] args) {
		
		int Limit = 100000;
		
		// 입력 받기
		Scanner sc = new Scanner(System.in);
	
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		sc.close();
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int[] times = new int[Limit+1];
		
		// bfs
		int cur;
		
		// 초기 루트 설정
		times[N] = 1;
		queue.add(N);
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			if(cur==K) {
				System.out.println(times[K]-1);
				break;
			}
			
			int ncur = cur*2;
			while(ncur>0 && ncur<=Limit) {
				if(times[ncur]==0) {
					times[ncur] = times[cur];
					queue.add(ncur);
				}
				ncur *= 2;
			}

			ncur = cur-1;
			if(ncur>=0 && times[ncur]==0) {
				times[ncur] = times[cur]+1;
				queue.add(ncur);
			}
			ncur = cur+1;
			if(ncur<=Limit && times[ncur]==0) {
				times[ncur] = times[cur]+1;
				queue.add(ncur);
			}
		}
		
	}

}
