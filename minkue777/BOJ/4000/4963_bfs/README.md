# 4963번 섬의 개수
[문제 보러가기](https://www.acmicpc.net/problem/4963)

## 🅰 설계
dfs와 bfs 알고리즘은 일반적인 코드에서 크게 벗어나지 않게 사용했습니다.
다만 visited 배열을 사용하기 번거로워 2차원배열 위에 직접 마킹을 하는
방법을 택했습니다.

```java 
field[node.y][node.x] = -1;
```
dfs나 bfs함수를 호출할 때마다 count변수를 1씩 증가시키는 방법이 아니라
dfs와 bfs의 return값을 int로 받아 함수값을 더하는 코드로 작성했습니다.
```java 
if(field[i][j] == 1) answer += bfs(new Node(i, j));
```

사실 dfs만 사용했다면 Node 클래스는 굳이 선언할 필요 없었지만 
bfs에선 Node 클래스가 없으면 queue에 넣기 까다로워서 사용했습니다.

## ✅ 후기
일반적으로 대각선의 경우에는 분리된 것으로 취급하는 경우가 많은데 
나름 훼이크였을까요..? 연습삼아 dfs와 bfs 둘 다 구현해봤는데 아무래도
queue를 사용해야 하는 bfs보단 dfs쪽이 사용하기 훨씬 편합니다.
둘 다 사용가능해보이는 문제라면 주저없이 dfs로 풀 것 같아요.