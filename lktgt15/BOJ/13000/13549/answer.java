import java.io.*;
import java.util.*;

public class 숨바꼭질3 {
	static BufferedReader br;
	static StringTokenizer st;

	static int LIMIT = (int)2e5+1; // 최대 200,000까지 확인
	static int[] chk = new int[LIMIT]; // 위치 방문 체크 + 위치에 도달한 가장 빠른 시간
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<Integer>(); // BFS를 이용하기 위한 큐 선언
		Arrays.fill(chk, -1); // -1 은 방문하지 않은 위치
		
		q.offer(n);
		chk[n] = 0; // 시작점은 현재 시간 0
		while(!q.isEmpty()) {
			int cur = q.poll(); // 현재 위치
			if(cur == k) { // 현재 위치가 동생의 위치면 현재 시간을 바로 출력
				System.out.println(chk[cur]);
				return;
			}

			if(cur*2 < LIMIT && chk[cur*2] == -1) { // 순간이동
				q.offer(cur*2);
				chk[cur*2] = chk[cur];
			}
			if(cur-1 >= 0 && chk[cur-1] == -1) { // 현재위치 + 1
				q.offer(cur-1);
				chk[cur-1] = chk[cur]+1;
			}
			if(cur+1 < LIMIT && chk[cur+1] == -1) { // 현재위치 - 1
				q.offer(cur+1);
				chk[cur+1] = chk[cur]+1;
			}
		}
	}
}