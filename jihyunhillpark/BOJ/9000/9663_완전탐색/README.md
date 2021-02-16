# 9663번 N-Queen
[문제 보러가기](https://www.acmicpc.net/problem/9663)

## 🅰 설계

N*N 체스판의 행을 탐색할 때 퀸이 위치할 수 있는 모든 열에 대한 경우의 수를 구합니다. 그리고 다음 행에서, 다다음행에, 쭉쭉쭉 마지막 행까지 위치할 수 있는 모든 열들을 타고 내려가는 경우의 수를 구해야 합니다.

**[제약 조건 1]** 퀸 위치의 왼쪽 수평선, 왼쪽위 대각선, 수직선 윗 방향, 오른쪽위 대각선,오른쪽 수평선, 오른쪽아래 대각선, 아래 수직선, 왼쪽아래 대각선은 퀸이 공격할 수 있는 범위이므로 다음 차례(행) 퀸이 놓일 수 없는 위치 입니다.             
-> 다음 차례(행) 퀸이 갈 수 없는 위치를 수업시간에 배운 8방탐색을 이용해 visited배열에 마킹했습니다.
```
private static int totalCnt = 0;
private static int N = 0;
private static int[] dr = {0,-1,-1,-1,0,1,1,1}; //가장 왼 --> 시계방향 8방 offset.
private static int[] dc = {-1,-1,0,1,1,1,0,-1};
```    

**[제약 조건 2]** 현재 퀸이 공격가능한 범위를(다음 행의 퀸이 갈 수 없는)체킹을 할 때 이전 퀸들의 체킹결과에 누적되어 반영되어야 합니다.    
-> visited배열을 boolean타입의 전역변수 배열로 선언하고 사용하다보니 복구시키는데 이전에 체킹했던 곳들의 값까지 unchecked로 변하는 현상이 생겼습니다.
1. visited를 int형 배열로 main함수에 선언한 뒤, Nqueen을 구하는 재귀함수의 인자로 넘겨주었습니다.   
2. 체크시 visited배열 요소의 값을 +1 해주었고, 요소의 값이 0일 경우에만 uncheckd한 곳으로 보도록 했습니다. 이후 복귀할 때는 -1을 하도록 했습니다.        

```
public static void main(String[] args) throws IOException {
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  N = Integer.parseInt(in.readLine());

  int[][] visited = new int[N][N];
  getNQueen(0, visited);
  System.out.println(totalCnt);
}
```


**[제약 조건 3]** 체스판 한 행에 대해 퀸이 놓일 수 있는 모든 체스판의 열을 타고 내려가봐야 합니다.        
-> visited배열을 사용해 공격할 수 있는 모든 곳을 체크한 뒤 다음 차례(다음 행) 퀸을 구하고자 했습니다. 이후 아직 행탐색이 끝나지 않았기 때문에 마킹했던 곳을 복구 시킨 뒤 같은 행에 퀸이 놓일 수 있는 다른 경우의 수를 구하도록 했습니다.
```
private static void getNQueen(int level, int[][] visited) {
  if(level == N) {
    totalCnt++;
    return;
  }
  for(int i = 0; i < N ; i++) { //level 행에 열들을 탐색하며..
    if(visited[level][i] == 0) { //방문하지 않은 곳에한해서
      visited[level][i] += 1;
      for(int j = 0; j < 8 ; j++) {
        //공격할수 있는 곳 모두 체크
        int nr = level + dr[j];
        int nc = i + dc[j];
        while(0<= nr && nr < N && 0 <= nc && nc < N) {
          visited[nr][nc] += 1;
          nr += dr[j];
          nc += dc[j];
        }
      }
      getNQueen(level+1, visited);
      for(int j = 0; j < 8 ; j++) {
        //원상복구
        int nr = level + dr[j];
        int nc = i + dc[j];
        while(0<= nr && nr < N && 0 <= nc && nc < N) {
          visited[nr][nc] -= 1;
          nr += dr[j];
          nc += dc[j];
        }
      }
      visited[level][i] -= 1;
    }

  }
}
```

## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점
// 고생한 점       

사실 이 풀이법은 약간 주먹구구식인듯한 느낌입니다. 다풀고 찾아보니 N-Queen은 꽤 유명한 문제더라구요?ㅇ0ㅇ!!고전적인 알고리즘은 어느정도 안정성이 있으니, 이방법으로 한번 풀어봐야겠어요
