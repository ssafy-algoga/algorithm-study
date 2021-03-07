# 17143번 낚시왕
[문제 보러가기](https://www.acmicpc.net/problem/17143)

## 🅰 설계
크게 두 가지의 메소드로 나누어서 구현했습니다.
1. 물고기를 잡는 `fishing()` 메소드
2. 상어가 1초동안 움직이는 `move()` 메소드

사실 fishing 메소드는 구현이 간단합니다. `map`의 row를 탐색하면서 상어가 있으면
잡아서 그 상어를 `dead` 처리 해주면 됩니다.

```java 
static void fishing(int col) {
    for(int r=0; r<row; r++) {
        if(map[r][col] != 0) {
            int idx = map[r][col];
            caughtWeight += sharks[idx].size;
            map[r][col] = 0;
            sharks[idx].alive = false;
            break;
        }
    }
}
```

문제는 `move()`인데요. `move()`의 구현을 어렵게 만드는 요소는 <b> 같은 칸에 중복되게 상어가
존재할 수 있다는 점입니다.</b> 만약 상어를 동시에 움직이게 할 수 있다면 중복되는 곳에서
잡아먹는 부분만 구현하면 되지만 실제 코드상에선 어쩔 수 없이 상어를 한마리씩 움직여야 하기 때문에
상어가 맵에 중복되게 있어도 잡아먹어도 되는지 아닌지를 판단해야 합니다. 이 부분을 위해 
`Shark`클래스에 일견 필요없어 보이는 idx 필드를 추가해서 상어마다 번호를 부여했습니다.
그리고 idx 순서대로 상어를 이동시키면서 만약 중복되는 곳에 나보다 idx가 작은 상어가 존재한다면
한쪽을 잡아먹고 idx 크다면 다음턴에 이동할 상어이므로 그냥 그대로 놔둡니다.

```java 
int already = map[r][c];
if(already == 0 || already > idx) {
    map[r][c] = idx;
    return;
}
if(sharks[already].size > sharks[idx].size) {
    sharks[idx].alive = false;
} else {
    map[r][c] = idx;
    sharks[already].alive = false;
}
```

나머지 부분은 코드만 길었지 특별할 것 없는 단순 구현입니다.

###전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static class shark {
        int r;
        int c;
        int speed;
        int dir;
        int size;
        int idx;
        boolean alive;

        shark(int r, int c, int speed, int dir, int size, int idx, boolean alive) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.idx = idx;
            this.alive = alive;
        }

        void move() {
            if(map[r][c] == idx) map[r][c] = 0;
            int cnt = 0;
            int nr;
            int nc;

            while(cnt != speed) {
                nr = r + dr[dir];
                nc = c + dc[dir];
                if(nr < 0 || nr >= row || nc < 0 || nc >= col) {
                    dir = change[dir];
                }
                r += dr[dir];
                c += dc[dir];
                cnt++;
            }

            int already = map[r][c];
            if(already == 0 || already > idx) {
                map[r][c] = idx;
                return;
            }
            if(sharks[already].size > sharks[idx].size) {
                sharks[idx].alive = false;
            } else {
                map[r][c] = idx;
                sharks[already].alive = false;
            }
        }
    }

    static void fishing(int col) {
        for(int r=0; r<row; r++) {
            if(map[r][col] != 0) {
                int idx = map[r][col];
                caughtWeight += sharks[idx].size;
                map[r][col] = 0;
                sharks[idx].alive = false;
                break;
            }
        }
    }

    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[] change = {1, 0, 3, 2};
    static int row;
    static int col;
    static int numOfSharks;
    static shark[] sharks;
    static int caughtWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        numOfSharks = Integer.parseInt(st.nextToken());
        sharks = new shark[numOfSharks+1];
        for(int idx = 1; idx<=numOfSharks; idx++) {
            st = new StringTokenizer(br.readLine());
            sharks[idx] = new shark(Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    idx, true);
            map[sharks[idx].r][sharks[idx].c] = idx;
            if(sharks[idx].dir == 0 || sharks[idx].dir == 1) {
                sharks[idx].speed %= 2*(row-1);
            }
            else sharks[idx].speed %= 2*(col-1);
        }

        for(int cur = 0; cur < col; cur++) {
            fishing(cur);
            for(int idx = 1; idx <= numOfSharks; idx++) {
                if(sharks[idx].alive) {
                    sharks[idx].move();
                }
            }
        }
        System.out.println(caughtWeight);
    }
}
```


## ✅ 후기
매번 느끼지만 구현 문제는 참 리드미쓰기가 어렵습니다.. 긴 구현 내용을 다 설명하기도
그렇고 어떤 부분을 공유하는게 좋을지도 애매하고.. 음 그냥 그렇다구요.