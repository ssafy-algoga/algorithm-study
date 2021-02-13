package algoga.week1;
import java.io.*;
import java.util.*;

public class 체스판다시칠하기 {
	static BufferedReader br;
	static StringTokenizer st;

	static int[][] map = new int[50][50];
	static int n,m,ans;
	static char[] in;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for(int i=0;i<n;i++) {
			in = br.readLine().toCharArray();
			for(int j=0;j<m;j++) {
				if(in[j] == 'B') map[i][j] = 1; // B인경우 1로 초기화, W로 바꿔도 상관 없음
			}
		}
		
		ans = Integer.MAX_VALUE;
		for(int i=0;i<n-7;i++) for(int j=0;j<m-7;j++) f(i,j);
		System.out.println(ans);
	}
	
	static void f(int cury,int curx) {
		int subans1 = 0, subans2 = 0;
		for(int i=0;i<8;i++) for(int j=0;j<8;j++) {
			subans1 += map[cury+i][curx+j]^((i+j)&1); // i+j가 홀수인 경우 1, 아니면 0과 XOR
			subans2 += map[cury+i][curx+j]^((i+j+1)&1);// i+j가 홀수인 경우 0, 아니면 1과 XOR
		}
		ans = Math.min(ans,Math.min(subans1, subans2)); // 가장 적은게 답이 됨
	}
}