import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Space{
		int r;
		int c;
		Space(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
	static int [][] arr; //원본
	static int [][] copy; //바이러스 확산시킬 카피본
	static int emptyCount; //빈공간 카운트
	static int virusCount; //바이러스 카운트
	static Space [] virus; //바이러스 위치들 저장할 배열
	static int [] select; // 빈공간 조합돌릴 배열
	static int N,M; 
	static int oneStep; // 1루프결과를 담을 int
	static int result; // 최종결과
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr=new int [N][M];
		Space [] emptyArr = new Space[N*M];
		virus = new Space[N*M];
		emptyCount=0;
		virusCount=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				int now = Integer.parseInt(st.nextToken());
				arr[i][j]=now;
				if(now==0) {
					emptyArr[emptyCount++]=new Space(i,j); //빈공간 위치 저장
				}
				if(now==2) {
					virus[virusCount++]=new Space(i,j); //바이러스 위치 저장
				}
			}
		}
		select=new int[emptyCount]; 
		for(int i=emptyCount-1;i>=3;i--) { //벽으로 바꿀애들 선정, 조합
			select[i]=1;
		}
		do {
			copy = arr.clone(); //원본에 바이러스 확산하면 초기화 해야하므로 copy에 돌림
			for(int i=0;i<emptyCount;i++) { //빈공간 3개뽑아서 벽으로
				if(select[i]==1) {
					arr[emptyArr[i].r][emptyArr[i].c]=0;
				}
				else {
					arr[emptyArr[i].r][emptyArr[i].c]=1;
				}
			}
			oneStep = emptyCount-3;	//이번 루프에서 빈공간 = 빈공간 - 벽으로 바꾼3개
			for(int v=0;v<virusCount;v++) { //바이러스 위치기록한거 돌면서 확산시키기
				getResult(virus[v].r,virus[v].c);
			}
			result=Math.max(result, oneStep); //결과들중에 빈공간이 제일 많은것 result에 저장
		}while(nextP());
		System.out.println(result);
	}
	static void getResult(int r,int c) { //탐색하면서 확산하면 빈공간 --
		if(r!=0 && copy[r-1][c]==0) {
			oneStep--;
			copy[r-1][c]=2;
			getResult(r-1,c);
		}
		if(c!=0 && copy[r][c-1]==0) {
			oneStep--;
			copy[r][c-1]=2;
			getResult(r,c-1);
		}
		if(c<M-1 && copy[r][c+1]==0) {
			oneStep--;
			copy[r][c+1]=2;
			getResult(r,c+1);
		}
		if(r<N-1 && copy[r+1][c]==0) {
			oneStep--;
			copy[r+1][c]=2;
			getResult(r+1,c);
		}
	}
	static boolean nextP() {
		int i = emptyCount-1;
		while(i>0 && select[i-1]>=select[i])i--;
		if(i==0)return false;
		
		int j = emptyCount -1;
		while(select[i-1]>=select[j])j--;
		
		swap(i-1,j);
		
		int k = emptyCount -1;
		while(i<k) {
			swap(i++,k--);
		}
		return true;
	}
	static void swap(int i,int j) {
		int temp = select[i];
		select[i]=select[j];
		select[j]=temp;
	}
}
