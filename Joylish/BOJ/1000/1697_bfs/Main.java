package com.boj.숨바꼭질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000001; // 인덱스 1부터 시작하기 때문에
    static Queue<Integer> q = new ArrayDeque<Integer>();
    static int N;
    static int K;
    static int[] visit = new int[MAX]; // 메모이제이션

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parse(st.nextToken()); //수빈이 위치
        K = parse(st.nextToken()); //동생 위치

        q.add(N); // 처음 수빈이 위치 q에 추가
        visit[N] = 0; // 처음 수빈이가 있는 자리이므로 0으로 시작

        while(!q.isEmpty()){
            N = q.poll();   // 현재 수빈이 위치

            if(N==K) {
                System.out.println(visit[N]); //동생을 만나기 위해 수빈이가 움직였던 누적이동거리 출력
                break;
            } // 수빈이와 동생 만남

            int backward = N-1; //수빈이 현재위치에서 한 칸 뒤로 가기
            int forward = N+1;  //수빈이 현재위치에서 한 칸 앞으로 가기
            int warp = N*2;     //수빈이 현재위치에서 순간이동
            if(0 <= backward && visit[backward] == 0){ // 뒤로 갔을 경우 0을 벗어나지 않고 그 자리에 누적합이 없을 경우
                q.add(backward);
                visit[backward] = visit[N] + 1;
            }
            if(forward < MAX && visit[forward] == 0){ // 앞으로 갔을 경우 MAX를 벗어나지 않고 그 자리에 누적합이 없을 경우
                q.add(forward);
                visit[forward] = visit[N] + 1;
            }
            if(warp < MAX && visit[warp] == 0){ // 순간이동 했을 경우 MAX를 벗어나지 않고 그 자리에 누적합이 없을 경우
                q.add(warp);
                visit[warp] = visit[N] + 1;
            }
        }
    }

    static int parse(String s){
        return Integer.parseInt(s);
    }
}
