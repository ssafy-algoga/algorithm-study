package boj.m3w5_study;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_17071_숨바꼭질5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		if(N==K) {
			System.out.println("0");
			System.exit(0);
		}
		
		sc.close();
		
		int MAX = 500000;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		boolean[][] visited = new boolean[MAX+1][2];
		int dest = K;
		int time = 0;
		
		q.offer(N);
		visited[N][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();

			time++;
			dest += time;
			
			// time초 후 방문하는 모든 위치에 대해
			while(size-- > 0) {
				int cur = q.poll();
				
				for (int ncur : new int[]{cur-1, cur+1, cur*2}) {
					if(ncur>=0 && ncur<=MAX
							&& !visited[ncur][time%2]) {
						visited[ncur][time%2] = true;
						q.offer(ncur);
					}

				}
				
			}
			
			
			if(dest > MAX) break;
			
			if(visited[dest][time%2]) {
				System.out.println(time);
				System.exit(0);
			}
		}
		
		System.out.println("-1");
		
	}

}
