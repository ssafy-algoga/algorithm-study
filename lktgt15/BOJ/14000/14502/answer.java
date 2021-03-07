package algoga.week2;

import java.io.*;
import java.util.*;

public class 연구소 {
	static BufferedReader br;
	static StringTokenizer st;

	static List<p> colList = new ArrayList<>(), virusList = new ArrayList<>();
	static int[][] map = new int[8][8];
	static int[] dy = {1,-1,0,0}, dx = {0,0,1,-1};
	static int n,m,safe = -3, ans;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					safe++; // safe한 영역의 개수를 세어 놓음
					colList.add(new p(i,j)); // 기둥을 세울 수 있는 좌표 List
				}
				else if(map[i][j] == 2) {
					virusList.add(new p(i,j)); // 바이러스가 있는 좌표 List
				}
			}
		}
		
		f(0,0);
		System.out.println(ans);
	}
	
	static boolean isValid(int y,int x) {
		return y>=0 && y<n && x>=0 && x<m && map[y][x] == 0;
	}
	
	static void reset() {
		for(int i=0;i<n;i++) for(int j=0;j<m;j++) map[i][j] = map[i][j] == 3 ? 0 : map[i][j];
	}
	
	static int dfs(int y,int x) {
		int ret = 0;
		for(int i=0;i<4;i++) {
			int nxty = y+dy[i];
			int nxtx = x+dx[i];
			if(isValid(nxty,nxtx)) {
				map[nxty][nxtx] = 3;
				ret += dfs(nxty,nxtx)+1;
			}
		}
		return ret;
	}
	
	static void f(int idx,int cnt) {
		if(cnt == 3) {
			int cursafe = safe;
			for(p virus : virusList) {
				cursafe -= dfs(virus.y,virus.x);
			}
			ans = Math.max(ans, cursafe);
			
			reset();
			
			return;
		}
		
		for(int i=idx;i<colList.size();i++) {
            p col = colList.get(i);
			if(map[col.y][col.x] == 0) { // colList의 i번째 위치에 있는 좌표에 기둥을 세운적이 있는지 체크
				map[col.y][col.x] = 1;
				f(i+1,cnt+1);
				map[col.y][col.x] = 0;
			}
		}
	}
	
	static class p{
		int y,x;
		public p(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
}