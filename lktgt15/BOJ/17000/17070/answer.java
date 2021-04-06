import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static long[][][] dp = new long[32][32][3]; // 메모용 dp 배열
	static int[][] map = new int[32][32]; // 벽을 체크해줄 맵
	static int[] dy= {0,1,1}, dx= {1,1,0}; // 타입에 따른 다음 파이프를 놓을 지점
	static int n; // 전체 격자판 규격
	static boolean[][] validtype = {{true,true,false},{true,true,true},{false,true,true}}; // 타입에 따른 다음으로 놓을 파이프 타입이 유효한지 체크
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				Arrays.fill(dp[i][j], -1);
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(f(0,1,0));
	}
	
	static long f(int cy,int cx,int type) {
		long ret = dp[cy][cx][type]; // 현재 좌표,type을 memo
		if(ret != -1) return ret;
		if(cy == n-1 && cx == n-1) return dp[cy][cx][type] = 1;
		ret = 0;
		
		for(int i=0;i<3;i++) {
			int ny = cy+dy[i];
			int nx = cx+dx[i];
			if(isValid(ny,nx,i) && validtype[type][i]) { // 다음 파이프가 놓일 지점이 유효한지 검사
				ret += f(ny,nx,i); // 다음 파이프를 놓고 경우의 수 계산
			}
		}
		
		return dp[cy][cx][type] = ret;
	}
	
	static boolean isValid(int cy,int cx,int type) {
		return cy < n && cx < n && map[cy][cx] == 0 && (type == 1 ? map[cy-1][cx] == 0 && map[cy][cx-1] == 0 : true);
	}
}
