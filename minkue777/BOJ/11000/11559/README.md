# 11559번 Puyo Puyo
[문제 보러가기](https://www.acmicpc.net/problem/11559)

## 🅰 설계
전체 과정을 다음과 같은 3가지 스텝으로 분리했습니다.
1. 맵 전체를 돌며 DFS를 하며 cluster가 4개이상인 뿌요 탐색
2. cluster가 4개이상인 뿌요가 없다면 종료. 있다면 터트림
3. 뿌요을 터트린 이후 재배치
4. 종료될 때까지 1-3 반복

각각의 스텝은 따로 메소드로 분리했습니다.

1. cluster의 개수를 세는 dfs 메소드

```java 
static int dfs(int r, int c) {
    char ch = field[r][c];
    isBurst[r][c] = true;
    int count = 1;
    for(int i=0; i<4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if(nr >= 0 && nr < 12 && nc >=0 && nc < 6 &&
                field[nr][nc] == ch && !isBurst[nr][nc]) {
            count += dfs(nr, nc);
        }
    }
    return count;
}
```

자기자신을 포함해 현재 위치에서 몇개의 뿌요랑 연결되어 있는지 체크합니다.
사실 이 부분을 어떻게 구현할지 가장 고민을 많이 했는데 bfs로 구현했을 때와
dfs로 구현했을 때 어느쪽이 제 스타일과 맞을지 계속 상상해봤습니다.
저는 최단거리 구하기 이외의 그래프 문제에서 bfs보다 dfs를 선호하기 때문에
dfs로도 크게 구현에 어려움이 없다고 판단되어서 dfs를 사용했습니다.

2. 뿌요를 터트리는 메소드

```java 
static void Boom(int cluster) {
    for(int r=0; r<12; r++) {
        for(int c=0; c<6; c++) {
            if(isBurst[r][c]) {
                if(cluster >= 4) field[r][c] = '.';
                isBurst[r][c] = false;
            }
        }
    }
}
```
간단한 메소드였지만 나중에 코드를 되짚어보면서 설계에 후회를 했던 메소드입니다.
`isBurst`에 `true` `false`를 저장하는 방식이였기 때문에 다시 맵 전체를 탐색하면서
뿌요를 터트려야 했습니다. 이 메소드는 최적화 할 수 있는 방법이 많을거란 생각이 듭니다.

3. 뿌요를 터트린 후 재배치하는 메소드

```java 
static void gravity() {
    for(int r=10; r>=0; r--) {
        for(int c=0; c<6; c++) {
            if(field[r][c] != '.') {
                int next = r+1;
                while (next < 12 && field[next][c] == '.') {
                    next++;
                }
                if(next != r+1) {
                    field[next-1][c] = field[r][c];
                    field[r][c] = '.';
                }
            }
        }
    }
}
```

아래에 있는 뿌요에서부터 탐색하면서 필드 아래가 비어있다면 그곳으로 값을 변경해줍니다.
이 메소드도 리드미를 적으면서 보니 if문을 한번만 쓸 수 있었는데 쓸데없이 분리되어서
가독성을 떨어트리네요.

## ✅ 후기
시뮬레이션이나 복잡한 구현 문제는 특별한 아이디어를 요구하지 않아도 평소에 많이 풀어두지 않으면
꽤 고생하는 것 같습니다. 매주 이런류의 문제가 적어도 한개는 포함되어 있었으면 좋겠습니다.