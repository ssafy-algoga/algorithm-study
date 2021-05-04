# 17070번 파이프 옮기기 1
[문제 보러가기](https://www.acmicpc.net/problem/17070)

## 🅰 설계

DP로 풀었습니다.

파이프가 ㅡ, \, | 방향으로 어떤 좌표 (i, j)에 도달하는 가짓수를 배열로 저장했습니다.   
좌표는 파이프를 미는 방향이 오른쪽, 아래, 대각선 오른쪽 아래로 한정되어 있기 때문에 파이프의 오른쪽 끝이 해당 좌표에 위치할 때가 기준입니다.

어떤 좌표 (i, j) 에 파이프가 위치할 가짓수는 해당 좌표에 파이프가 ㅡ, \, | 방향으로 위치하는 가짓수를 모두 더한 값이고,
```java
// 결과출력
System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
```

해당 좌표에 파이프가 가로 방향으로 위치하는 가짓수는   
바로 왼쪽 좌표에 위치한 파이프가
  - 가로 방향 -> 가로 방향
  - 대각선 방향 -> 가로 방향   
으로 이동한 경우이고,

해당 좌표에 파이프가 대각선 방향으로 위치하는 가짓수는   
왼쪽 위 좌표에 위치한 파이프가
 - 가로 -> 대각선
 - 대각선 -> 대각선
 - 세로 -> 대각선   
 으로 이동한 경우이고,
 
해당 좌표에 파이프가 세로 방향으로 위치하는 가짓수는   
위 좌표에 위치한 파이프가
 - 대각선 -> 세로
 - 세로 -> 세로   
 으로 이동한 경우입니다.

```java
for (int i = 1; i <= N; i++) {
  for (int j = 3; j <= N; j++) {
    if(map[i][j] != 0) continue;

    // ㅡ
    if(j-1>=0)
      dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];

    // \
    if(i-1>=0 && j-1>=0
        && map[i-1][j] == 0 && map[i][j-1] == 0)
      dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];

    // |
    if(i-1>=0)
    dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
  }
}
```

dp 배열을 채울때, (1, 2) 좌표의 가로 방향을 1로 초기화해 채우고,   
시작이 (1, 2) 좌표의 가로 방향이기 때문에 1열과 2열에는 어떤 방향의 파이프도 올 수 없기 때문에 빼고 채웠습니다.