# 11559번: Puyo Puyo

[문제 보러가기](https://www.acmicpc.net/problem/11559)

## 🅰 설계
일단 뿌요뿌요를 해봤기 때문에 룰은 알고 있었습니다.  

Main 
1. 입력받아서 저장
2. play()호출
3. 결과출력

fall() - 아래가 비었을 경우 뿌요 떨어뜨리기  
isPuyo() - 뿌요인지 빈공간인지 체크  
play() - 뿌요뿌요 수행  
bfs() - 탐색해서 뿌요 터트리기  

기본적으로 이렇게 메소드를 대충 생각해놓고 코드를 짰습니다.  

### play()
처음에는 이중포문을 돌면서 뿌요를 만나면 탐색을 수행하고 터뜨리고 떨어뜨리고 난 뒤 다음 뿌요를 찾아가고 이런 방식으로 짰었습니다.  

```
RYYYY       ...YY
RRRPP       .YYPP  
```
R과 Y가 동시에 터져야 하는데 위처럼 되어서 떨구는건 다돌고 나서 해야한다는 것을 알게되었습니다.

```java
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
```
최종코드는 이중포문을 돌면서 터뜨리고 이후에 fall()을 수행하고 그 상태에서 다시 board를 돌면서 탐색하도록 되어있습니다. 터지면 이를 다터지고 떨어진 뒤의 상태에서 다시 이중포문을 돌아서 
이후 상태에서도 처리할 수 있도록 했습니다.  

### bfs
뿌요를 탐색하면서 터뜨릴수 있는 것을 찾습니다. 코드가 매우 길어졌는데, 제가 코드짜는 실력이 부족해서 생각나는대로 짜다보니 길어졌네요.  

짤때는 bfs라고 했는데 dfs인지 bfs인지 저도 잘 모르겠습니다.. 아무튼..  

boolean flag를 두어서 터질 수 있는 조건을 체크했습니다. 뿌요가 4개이상 모이는 것이 조건이고 bfs는 boolean을 리턴하게 해서 탐색한 다른 뿌요들이 터지면
같이 터지도록 설계했습니다.

그런데 생각한대로 구현했더니 
```
.R.
RRR
```
이런 건 count가 3까지 밖에 안돌아서 안터지는 문제가 있었습니다. 그래서 후술할 check4way를 추가로 만들었습니다.  

```java
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
```
### check4way()
이것도 굉장히 코드가 긴데... 쉽게 설명하면 나를 기준으로 사방을 보면서 같은 색의 뿌요가 몇개인지 보고 내가 터질조건이면 주변 뿌요들한테 전파하는 메소드입니다.  
이를 통해 위에서 처리하지 못했던 경우도 처리할 수 있었습니다.  
```java
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
```
### fall()
빈공간에 뿌요를 떨구는 메소드입니다. 

```java
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
```
간단한 코드이긴 한데.. again은 아래와 같은 상황이 생겨서 두었습니다.
```
.R.
..R
..R
```
R이 두칸떨어져야하는데 아래행부터 반복문을 돌면 R이 한칸만 떨어지는 문제가 있어서 떨구고 나서 다시 루프를 돌게하려고 again을 두었습니다.  
### 전체코드 
```java
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
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점
생각해보니 isPuyo가 아니라 isBlank 이런 것으로 했으면 굳이 여러 색에 대한 비교를 안했을 것 같네요.   
중간중간 Row Column값이 아니라 11,5이런 값을 쓴게 있는데 애교로 봐주세욥..  

### 고생한 점
정확히는 안재봤는데 2~3시간 정도 걸린 것 같아요ㅠㅠ..  

