import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static boolean[] col = new boolean[16], cross1 = new boolean[31], cross2 = new boolean[31]; // col, cross 체크
	static int n,ans;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		f(0,0); // 완전탐색 
		System.out.println(ans);
	}
	
	static void f(int r,int c) {
		if(r == n) { // r이 n에 도달하면 모든 row에 queen이 하나씩 세워지므로 경우의 수 +1
			ans++;
			return;
		}
		if(c == n) { // c가 n에 도달하면 해당 row에는 queen이 하나도 없으므로 return
			return;
		}
		
		int chk1 = n+r-c; // left to right 대각선 체크
		int chk2 = r+c; // right to left 대각선 체크
		if(!col[c] && !cross1[chk1] && !cross2[chk2]) {
			col[c] = cross1[chk1] = cross2[chk2] = true;
			f(r+1,0); // 해당 row,col에 queen을 세우고 다음 row를 체크
			col[c] = cross1[chk1] = cross2[chk2] = false;
		}
		f(r,c+1); // 해당 row,col에 queen을 세우지 않고 다음 row,col+1에 넘겨줌
	}
}
