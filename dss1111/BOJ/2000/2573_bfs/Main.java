import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M,N;
	static int [][] map;
	static boolean [][] visit;
	static int ice; //남은 빙산수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
				if(map[n][m]!=0)	//빙산이면
					ice++;	//카운트
			}
		}
		int year=0;
		while(ice!=0) {
			year++;
			melt();
			if(countIce()>=2) {
				System.out.println(year);
				return;
			}
		}
		System.out.println(0);
	}
	static void melt() {
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]!=0) {
					int count=0;
					if(isSea(n-1,m)) count++;
					if(isSea(n+1,m)) count++;
					if(isSea(n,m-1)) count++;
					if(isSea(n,m+1)) count++;
					map[n][m]-=count;
					if(map[n][m]<=0)
						map[n][m]=-1; //녹을 빙산을 -1로처리 바로 0으로 처리하면 다른 빙산에 영향을줌
				}
			}
		}
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]==-1) {
					map[n][m]=0; //녹이기
					ice--;
				}
			}
		}
	}
	static int countIce() {
		visit = new boolean[N][M];
		int count = 0;
		for(int n=1;n<N-1;n++) {
			for(int m=1;m<M-1;m++) {
				if(map[n][m]!=0 && !visit[n][m]) { //방문하지 않은 빙산을만나면
					count++; // 그룹수 1추가
					group(n,m); // 그룹모두 체크
				}
			}
		}
		return count;
	}
	static void group(int n,int m) {
		visit[n][m]=true;
		//사방을 보면서 빙산이고 미방문이면 체크
		if(!isSea(n-1,m)&&!visit[n-1][m])group(n-1,m);
		if(!isSea(n+1,m)&&!visit[n+1][m])group(n+1,m);
		if(!isSea(n,m-1)&&!visit[n][m-1])group(n,m-1);
		if(!isSea(n,m+1)&&!visit[n][m+1])group(n,m+1);
	}
	static boolean isSea(int n,int m) {
		if(map[n][m]==0)
			return true;
		else 
			return false;
	}
}
