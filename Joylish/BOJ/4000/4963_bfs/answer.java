package com.ssafy.섬의개수;

import java.io.*;
import java.util.*;

class Land {
   int x;
   int y;

   Land(int x, int y) {
      this.x = x;
      this.y = y;
   }
}

public class Main {
   static BufferedReader br;
   static int MAX_SIZE = 50;
   static boolean[][] isLand = new boolean[MAX_SIZE][MAX_SIZE];
   static boolean[][] isVisited = new boolean[MAX_SIZE][MAX_SIZE];
   static Queue<Land> posLand = new ArrayDeque<Land>();
   static Queue<Land> spaceToGo = new ArrayDeque<Land>();

   // 상하좌우 대각선
   static int[][] moves = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };

   public static void main(String[] args) throws IOException {
      System.setIn(new FileInputStream("섬의개수_input.txt"));
      br = new BufferedReader(new InputStreamReader(System.in));

      while (true) {
    	   String[] line = br.readLine().split(" ");
         int col = Integer.parseInt(line[0]);
         int row = Integer.parseInt(line[1]);

         // 0 0 일 때, 입력 중단
         if (row == 0 && col == 0)
            break;

         // 지도에서 땅 위치 저장
         for (int i = 0; i < row; i++) {
        	String[] l = br.readLine().split(" ");
             for (int j=0; j<col; j++) {
                if (Integer.parseInt(l[j]) == 1) {
                   isLand[i][j] = true; // 땅 위치 표기
                   posLand.add(new Land(i, j)); // 땅 위치 저장
                }
             }
          }

         int count = 0; // 섬의 개수를 저장할 변수 선언

         if (posLand.size() < 1) { // 땅이 하나도 없을 경우
            System.out.println(count);
            continue;
         }
         spaceToGo.add(posLand.poll()); // 맨 처음 땅을 가봐야하는 공간으로 추가

         while (spaceToGo.size() > 0) { // 가봐야하는 공간이 없을 때까지 확인

            Land cur = spaceToGo.poll(); // 현재 확인하고 있는 공간

            for (int[] move : moves) { // 현재 공간 기준으로 상하좌우 대각선에 있는 공간 확인
                  int nx = cur.x + move[0];
                  int ny = cur.y + move[1];
                  
                  // 그 다음에 확인할 공간에 대한 범위체크 + 땅인지 체크 + 이미봤는지 체크
                  if (0 <= nx && nx < row && 0 <= ny && ny < col && isLand[nx][ny] && (!isVisited[nx][ny])) {
                     spaceToGo.add(new Land(nx, ny));
                     isVisited[nx][ny] = true;
                  }
            }

            if (spaceToGo.size() == 0) { // 가야할 공간이 없다면 섬 개수 1 증가
                  count++;
                  if (posLand.size() > 0) { // 더 볼 땅이 있다면(땅의 초기 위치가 있다면)
                  while (posLand.size() > 0) {
                     Land next = posLand.poll();
                     if (!isVisited[next.x][next.y]) {
                        spaceToGo.add(next); // 가야할 공간으로 추가
                        break;
                     }
                  }
               }
            }
         }
         
         // 방문했던 공간에 대한 체크와 땅 체크를 초기화
         for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
               isVisited[i][j] = false;
               isLand[i][j] = false;
            }
         }

         System.out.println(count);
      }
   }
}
