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