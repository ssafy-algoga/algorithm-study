package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1697_숨바꼭질 {

	public static Queue<Integer> queue;
	public static int[] times;
	
	public static int K;

	public static void main(String[] args) {
		// 입력 받기
		Scanner sc = new Scanner(System.in);
	
		int N = sc.nextInt();
		K = sc.nextInt();
		
		queue = new LinkedList<Integer>();
		times = new int[100001];
		
		// 수빈이가 동생보다 오른쪽에 있으면 무조건 -1로 계속 가야 하므로
		if(N >= K) {
			System.out.print(N-K);
		}
		else { // 아니면 탐색 시작
			bfs(N);
		}
		
		sc.close();
	}
	
	public static void bfs(int x) {
		// visited 표시를 위해 1로 바꿔준다.
		times[x] = 1;
		// 루트 위치 큐에 입력
		queue.add(x);
		
		while(!queue.isEmpty()) {
			// 현재 탐색 기준 위치는 cur
			int cur = queue.poll();
			
			// 만약 목적 위치인 K에 다다랐다면 걸린 시간 출력하고 종료
			if(cur==K) {
				System.out.print(times[cur]-1);
				break;
			}
			
			// 현재 위치 기준 탐색해야 할 -1, +1, *2 위치를 큐에 넣는다.
			// 경계 확인 + 방문했는지 안 했는지 확인
			// 걸린 시간 갱신
			if(cur-1>0 && times[cur-1]==0) {
				queue.add(cur-1);
				times[cur-1] = times[cur]+1;
			}
			if(cur+1<=100000 && times[cur+1]==0) {
				queue.add(cur+1);
				times[cur+1] = times[cur]+1;
			}
			if(2*cur<=100000 && times[2*cur]==0) {
				queue.add(2*cur);
				times[2*cur] = times[cur]+1;
			}
		}
		
	}

}
