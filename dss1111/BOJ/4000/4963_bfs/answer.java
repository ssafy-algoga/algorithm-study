import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int []dx = {-1,-1,1,0,1,0,1,-1};
	static int []dy = {0,1,1,1,0,-1,-1,-1};
	static int w,h;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int i=0;
		int j=0;
		
		while(true){
			st=new StringTokenizer(br.readLine());
			w=Integer.parseInt(st.nextToken()); //w받기
			h=Integer.parseInt(st.nextToken()); //h받기
			if(w==0 && h==0)
				break;
			int [][]world=new int[h][w];	//배열 땅,바다
			boolean [][]visit=new boolean[h][w]; //각땅의 탐색여부 체크
			for(i=0;i<h;i++)
			{
				st=new StringTokenizer(br.readLine());
				for(j=0;j<w;j++)
				{
					world[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			////////////////여기까지 입력완료/////////////
			getResult(world,visit); //결과보기
		}
	}
	public static void getResult(int[][] world,boolean [][] visit) {
		int count=0; //섬의 갯수
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				if(visit[i][j]==false && world[i][j]==1) { //미탐색이고 땅이면
					dfs(i,j,world,visit); //주변을 탐색하러 갑니다
					count++;			  //섬++
				}
			}
		}
		System.out.println(count);
	}
	public static void dfs(int x,int y, int[][] world,boolean[][] visit) {
		visit[x][y]=true; //탐색했음 체크

		for(int i=0;i<8;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx<0||nx>=h||ny<0||ny>=w) { //범위밖체크
				continue;
			}
			if(world[nx][ny]==1 && visit[nx][ny]==false) { //땅이있고 미탐색지역이면
				dfs(nx,ny,world,visit); //탐색
			}
		}
	}
}
