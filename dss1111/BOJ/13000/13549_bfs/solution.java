import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static final int SIZE = 100001;
	static int N,K;
	static int [] time=new int[SIZE]; //해당 위치의 시간을 기록하기 위한 배열
	public static void main(String[] args) throws IOException {
		Arrays.fill(time, 654321); //매우 큰 값으로 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		getResult();
	}
	public static void getResult() {
		time[N]=0;
		Deque <Integer> q = new ArrayDeque<>(); //위치
		q.add(N);
		while(!q.isEmpty()) {
			int now = q.poll();
			int t = time[now];
			if(now==K) {
				System.out.println(t);
				return;
			}
			if(now*2<=SIZE-1 && time[now*2]>t) { //x*2 순간이동
				time[now*2]=t;
				q.addFirst(now*2);
			}
			if(now<SIZE-1 && time[now+1]>t){ //+1
				time[now+1]=t+1;
				q.addLast(now+1);
			}
			if(now>0 && time[now-1]>t) {  //-1
				time[now-1]=t+1;
				q.addLast(now-1);
			}
		}
	}
}
