# 11559 Puyo Puyo
[문제 보러가기](https://www.acmicpc.net/problem/11559)

## 🅰 설계
1. 각 기능을 담당하는 함수를 나누어 생성하였습니다.
2. init() : 게임에 필요한 요소를 초기화하는 함수
3. dfs() : 연결된 뿌요의 개수를 탐색하는 함수
4. pop() : 4개이상 모인 뿌요를 터뜨리는 함수
5. down() : 뿌요가 터진 후 밑으로 중력작용하는 함수




## 주요 코드 설명
### 게임 요소 초기화
```java
public static void init() {
    count = 0;
    link.clear();
    for (int r = 0; r < 12; r++) {
        for (int c = 0; c < 6; c++) {
            visit[r][c] = false;
        }
    }
}
```

### 뿌요 개수 탐색
```java
private static int dfs(int r, int c, char color) {
    visit[r][c] = true;
    link.add(new int[] { r, c }); // 현재 컬러와 연결된 좌표들을 리스트에 추가
    count++; // 들어오면 현재 컬러와 연결된 갯수를 증가시킨다.

    for (int d = 0; d < 4; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == color && !visit[nr][nc]) {
            dfs(nr, nc, color);
        }
    }
    return count;
}
```



### 4개 이상 뿌요 터뜨리기

```java
private static boolean pop() {
    boolean flag = false;
    for (int r = 11; r >= 0; r--) {
        for (int c = 0; c < 6; c++) {
            init();
            if (map[r][c] != '.' && !visit[r][c]) {
                if (dfs(r, c, map[r][c]) >= 4) { // 4개 이상 모였으면 터뜨리기
                    flag = true;
                    for (int[] l : link) {
                        map[l[0]][l[1]] = '.';
                    }
                }
            }
        }
    }
    return flag;
}
```



### 중력작용

```java
private static void down() {
    for (int c = 0; c < 6; c++) {
        int floorR = 11; // 낙하물이 안착할 바닥
        int dropR = 10; // 중력에 의해 떨어지는 낙하물
        while (floorR > 0 && dropR > 0) {

            // 바닥이 빈칸이 아니면 바닥 올려주기
            while (floorR > 0 && map[floorR][c] != '.') {
                floorR--;
                dropR--;
            }

            // 낙하물이 있으면
            while (dropR >= 0) {
                if (dropR >= 0 && map[dropR][c] != '.') {
                    map[floorR][c] = map[dropR][c];
                    map[dropR][c] = '.';
                    floorR--;
                    dropR--;
                } else {// 낙하물없으면
                    while (dropR >= 0 && map[dropR][c] == '.') {
                        dropR--;
                    }
                }
            }
        }
    }
}
```



## ✅ 후기

### 모든 답이 맞는 것 같지만 틀려서 반례를 찾느라 시간을 잡아먹었습니다.

### 문제의 이유는 다름 아닌 초기 설계 코드 중 일부인 맨 윗줄을 빈칸처리 해주는 코드였습니다.

### 이렇듯 초기 설계의 중요성과, 코드 수정을 할때는 과감하게 지워버리는 것이 좋다는 것을 알게 되었습니다.