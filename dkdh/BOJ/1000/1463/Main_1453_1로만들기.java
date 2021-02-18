package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// bfs
public class Main_1453_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		sc.close();
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		int now;
		
		int[] cnts = new int[N+1];
		
		cnts[N] = 1;
		q.add(N);
		while(!q.isEmpty()) {
			now = q.poll();
			
			if(now==1) {
				System.out.print(cnts[now]-1);
				break;
			}
			
			if(now%3==0 && cnts[now/3]==0) {
				q.add(now/3);
				cnts[now/3] = cnts[now]+1;
			}
			if(now%2==0 && cnts[now/2]==0) {
				q.add(now/2);
				cnts[now/2] = cnts[now]+1;
			}
			if(cnts[now-1]==0) {
				q.add(now-1);
				cnts[now-1] = cnts[now]+1;
			}
			
		}
	}

}
