import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static boolean [] visited = new boolean[100001];
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		getResult();
		
	}
	public static void getResult() {
		//맨처음 위치 정보 추가
		q.add(N);
		visited[N] = true;
		int t = 0; //탐색시간
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int now = q.poll(); //큐에서 하나빼온다
				if(now == K) { // 결과 찾음
					System.out.println(t);
					return;
				}
				//수행할 수 있는 다음 탐색 3가지
				if(now>0 && !visited[now-1]) {
					visited[now-1]=true;
					q.add(now-1);
				}
				if(now<100000 && !visited[now+1]) {
					visited[now+1]=true;
					q.add(now+1);
				}
				if(now*2<=100000 && !visited[now*2]) { // *는 100000도 가능
					visited[now*2]=true;
					q.add(now*2);
				}
			}
			t++; // 탐색시간++
		}
	}
}
