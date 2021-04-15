# 16235번 나무 재테크
[문제 보러가기](https://www.acmicpc.net/problem/16235)

## 🅰 설계
봄 여름 가을 겨울에 대응하는 메소드를 각각 분리하여 구현하는 문제입니다.
사실 각각의 구현만 놓고보면 그렇게 어렵지 않은데 이상한 부분에서
계속 실수가 나와서 디버깅을 꽤 오래했던 문제였습니다..

문제에서 제일 고민해야 하는 부분은 같은 위치에 여러 나무가 심어져 있을 수 있고
**나무의 나이가 적은 순서대로 양분을 먹는다** 라는 점을 어떻게 구현할까 하는 부분입니다.
어떤 식으로든 일종의 정렬이 들어갈 수 밖에 없는데 저는 우선순위 큐를 통해
이 부분을 구현했습니다. 현재 생존해 있는 나무들을 전부 우선순위 큐에 넣고
나이가 적은 순서대로 꺼내서 양분을 먹습니다. 이러면 자연스럽게 나이 순으로 양분을
먹게 할 수 있습니다. 양분을 섭취 후 한살 더 먹은 나무를 바로 다시 큐에 넣는다면
다시 꺼내질 염려가 있기 때문에 다른 곳에 임시로 보관했다가 반복문이 끝나고
한번에 넣어줘야 했습니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int size;
    static int numOfTrees;
    static int year;
    static int[][] ground;
    static int[][] nutrient;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static PriorityQueue<Tree> treeList;
    static Queue<Tree> deadTreeList;
    static Queue<Tree> temp;
    static class Tree {
        int r;
        int c;
        int age;
        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }

    static void spring() {
        int size = treeList.size();
        if(treeList.isEmpty()) return;
        for(int idx = 0; idx < size; idx++) {
            Tree tree = treeList.poll();
            if(ground[tree.r][tree.c] >= tree.age) {
                ground[tree.r][tree.c] -= tree.age;
                tree.age++;
                temp.offer(tree);
            } else {
                deadTreeList.add(tree);
            }
        }
        while(!temp.isEmpty()) {
            treeList.offer(temp.poll());
        }
    }

    static void summer() {
        while(!deadTreeList.isEmpty()) {
            Tree tree = deadTreeList.poll();
            ground[tree.r][tree.c] += tree.age / 2;
        }
    }

    static void autumn() {
        for(Tree tree : treeList) {
            if(tree.age % 5 != 0) continue;
            for(int dir = 0; dir < 8; dir++) {
                int nr = tree.r + dr[dir];
                int nc = tree.c + dc[dir];
                if(nr < 0 || nr >= size || nc < 0 || nc >= size) {
                    continue;
                }
                temp.offer(new Tree(nr, nc, 1));
            }
        }
        while(!temp.isEmpty()) {
            treeList.offer(temp.poll());
        }
    }

    static void winter() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                ground[r][c] += nutrient[r][c];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        numOfTrees = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
        ground = new int[size][size];
        nutrient = new int[size][size];
        for(int[] row : ground) Arrays.fill(row, 5);
        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < size; c++) {
                nutrient[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        treeList = new PriorityQueue<>((a, b) -> a.age - b.age);
        deadTreeList = new ArrayDeque<>();
        temp = new ArrayDeque<>();
        for(int idx = 0; idx < numOfTrees; idx++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            treeList.offer(new Tree(r-1, c-1, age));
        }

        while(year-- > 0 ) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(treeList.size());
    }
}
```

## ✅ 후기
구현문제는 한큐에 통과했던 기억이 없네요.. 항상 디버깅을 해봐야 문제를 파악하는데
설계를 할 때부터 좀 더 신중해야겠다는 생각이듭니다..