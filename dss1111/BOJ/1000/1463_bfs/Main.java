import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int [] value;
	static Queue<Integer> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		value = new int[num+1];
		q=new ArrayDeque<>();
		getResult(num);
	}
	public static void getResult(int start) {
		q.add(start);
		value[start]=0;
		int now;
		while(!q.isEmpty()) {
			now = q.poll();
			if(now==1) {
				System.out.println(value[now]);
				return;
			}
			if(now%3 == 0) {
				if(value[now/3]==0)
					value[now/3]=value[now]+1;
				else
					value[now/3]=Math.min(value[now/3], value[now]+1);
				q.add(now/3);
			}
			if(now%2 == 0) {
				if(value[now/2]==0)
					value[now/2]=value[now]+1;
				else
					value[now/2]=Math.min(value[now/2], value[now]+1);
				q.add(now/2);
			}
			if(value[now-1]==0)
				value[now-1]=value[now]+1;
			else
				value[now-1]=Math.min(value[now-1], value[now]+1);
			q.add(now-1);
		}
	}
}
