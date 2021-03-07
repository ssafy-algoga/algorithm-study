import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int R = 12,C=6;
	static int result;
	static char [][] board = new char[R][C];
	static boolean [][] visit = new boolean[R][C];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int r=0;r<R;r++) {
			board[r]=br.readLine().toCharArray();
		}
		play();
		System.out.println(result);
	}
	static boolean isPuyo(int r,int c) { //빈공간인지 뿌요인지 판별
		if(board[r][c]=='R' || board[r][c]=='G' || board[r][c]=='B' || board[r][c]=='P' || board[r][c]=='Y')
		{
			return true;
		}
		return false;
	}
	static void fall() {
		boolean again;
		for(int i=R-1;i>0;i--) {
			again=false;
			for(int j=0;j<C;j++) {
				if(board[i][j]=='.' && isPuyo(i-1,j)){ //현재칸이 비어있고 그위가 뿌요인경우
					board[i][j]=board[i-1][j];
					board[i-1][j]='.';
					again=true;
				}
			}
			if(again) {
				i=R;
			}
		}
	}
	static boolean bfs(int r,int c, int count) {
		visit[r][c]=true;
		char now = board[r][c];
		boolean flag = false; //터트릴 수 있는지 여부를 나타냄
		if(count>=4) //뿌요가 4개이상 모이면 터짐
			flag = true;
		if(c!=0 && board[r][c-1]== now && !visit[r][c-1]) {
			if(bfs(r,c-1,count+1))
				flag=true;
		}
		if(c!=5 && board[r][c+1]== now && !visit[r][c+1]) {
			if(bfs(r,c+1,count+1))
				flag=true;
		}
		if(r!=0 && board[r-1][c] == now && !visit[r-1][c]) {
			if(bfs(r-1,c,count+1))
				flag=true;
		}
		if(r!=11 && board[r+1][c] == now && !visit[r+1][c]) {
			if(bfs(r+1,c,count+1))
				flag=true;
		}
		/*   R
		 * R R R 와 같은 경우 위의 과정만 거치면 터뜨릴 수 없음 이를 처리하기 위해 check4way사용 
		 */
		if(check4way(r,c)) {
			flag=true;
		}
		if(flag==true) { //터뜨리기
			board[r][c]='.';
			visit[r][c]=false;
			return true;
		}
		else {
			visit[r][c]=false;
			return false;
		}
	}
	static boolean check4way(int r,int c) {
		/*   R
		 * R R R 같은 경우 처리
		 * 기준점에서 사방을 보고 같은색 뿌요가 몇개인지 체크
		 */
		char now = board[r][c];
		visit[r][c]=true;
		int count=1;
		if(c!=0 && board[r][c-1]== now){
			count++;
		}
		if(c!=5 && board[r][c+1]== now) {
			count++;
		}
		if(r!=0 && board[r-1][c] == now) {
			count++;
		}
		if(r!=11 && board[r+1][c] == now) {
			count++;
		}
		if(count>=4) { //터뜨릴수 있는데 못터뜨린 경우 처리해줌
			if(c!=0 && board[r][c-1]== now && !visit[r][c-1])
				bfs(r,c-1,4);
			if(c!=5 && board[r][c+1]== now && !visit[r][c+1])
				bfs(r,c+1,4);
			if(r!=0 && board[r-1][c] == now && !visit[r-1][c])
				bfs(r-1,c,4);
			if(r!=11 && board[r+1][c] == now && !visit[r+1][c])
				bfs(r+1,c,4);
			return true;
		}
		return false;
	}
	static void play() {
		boolean boom=true;
		int r;
		while(boom) {
			boom=false;
			for(r=0;r<R;r++) {
				for(int c=0;c<6;c++) {
					if(isPuyo(r,c)) { // 뿌요인가?
						if(bfs(r,c,1)) { //터뜨릴수 있는가?
							boom=true;
						}
					}
				}
			}
			if(boom) { //터지면 떨어지고 연속횟수 추가
				fall();
				result++;
			}
		}
	}
}
