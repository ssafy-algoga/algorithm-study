package com.boj.숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 100001; // 인덱스 1부터 시작하기 때문에
    static Queue<Integer> q = new ArrayDeque<Integer>();
    static int N;
    static int K;
    static int[] visit = new int[MAX]; // 메모이제이션

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parse(st.nextToken()); //수빈이 위치
        K = parse(st.nextToken()); //동생 위치

        if(N>=K) {
            System.out.println(N-K);
            return;
        }

        q.add(N);
        Arrays.fill(visit, -1);
        visit[N] = 0;

        while(!q.isEmpty()){
            N = q.poll();   // 현재 수빈이 위치

            if(N==K) {
                System.out.println(visit[N]);
                break;
            } // 수빈이와 동생 만남

            int backward = N-1; //수빈이 현재위치에서 한 칸 뒤로 가기
            int forward = N+1;  //수빈이 현재위치에서 한 칸 앞으로 가기
            int warp = N*2;     //수빈이 현재위치에서 순간이동

            // 순간이동은 0초가 걸리기 때문에 앞 뒤로 움직일때보다 항상 먼저
            if(warp < MAX && visit[warp] == -1){
                q.add(warp);
                visit[warp] = visit[N]; // 0초걸리기 때문에 현재 값
            }

            if(0 <= backward && visit[backward] == -1){
                q.add(backward);
                visit[backward] = visit[N] + 1;
            }

            if(forward < MAX && visit[forward] == -1){
                q.add(forward);
                visit[forward] = visit[N] + 1;
            }

        }

    }
    static int parse(String s){
        return Integer.parseInt(s);
    }
}