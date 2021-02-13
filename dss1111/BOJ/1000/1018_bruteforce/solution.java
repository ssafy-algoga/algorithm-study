import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char [][] board;
	static char [][] answer1 = {{'W','B','W','B','W','B','W','B'}, //1번 체스판
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'}}; 
	static char [][] answer2 = {{'B','W','B','W','B','W','B','W'}, //2번 체스판
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'},
								{'B','W','B','W','B','W','B','W'},
								{'W','B','W','B','W','B','W','B'}}; 
	static int N,M;
	static int result=987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board= new char[N][M];
		for(int i=0;i<N;i++) {
			board[i]=br.readLine().toCharArray();
		}
		for(int i=0;i<=N-8;i++) {
			for(int j=0;j<=M-8;j++) {
				getResult(i,j);
			}
		}
		System.out.println(result);
	}
	public static void getResult(int y,int x) {
		int count1=0; //1번 체스판과 비교한 결과
		int count2=0; //2번 체스판과 비교한 결과
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[y+i][x+j]!=answer1[i][j])count1++; 
				if(board[y+i][x+j]!=answer2[i][j])count2++;
			}
		}
		result = Math.min(result, Math.min(count1, count2));
	}
}
