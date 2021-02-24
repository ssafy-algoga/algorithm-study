import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean [] wall;
	static int room;
	static int start,end;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		room = Integer.parseInt(br.readLine());
		wall = new boolean[room];
		init();
		int act = Integer.parseInt(br.readLine());
		for(int a=0;a<act;a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			crush();
		}
		System.out.println(count());
	}
	static void init() {
		for(int i=0;i<room;i++) {
			wall[i]=true;
		}
	}
	static void crush() {
		for(int i=start-1;i<end-1;i++) {
			wall[i] = false;
		}
	}
	static int count() {
		int count=0;
		for(int i=0;i<room;i++) {
			if(wall[i]==true)
				count++;
		}
		return count;
	}
}
