package com.boj.서울의지하철;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, target, min = Integer.MAX_VALUE; // min은 서울역이 있는 모든 호선에 대한 bfs 중 최소 환승 횟수 저장
    static ArrayList<Integer> zeroLines = new ArrayList<>(); // 0인 서울역이 있는 호선들
    static ArrayList<Integer> line;
    static HashMap<Integer, ArrayList<Integer>> lines = new HashMap<>(), adj = new HashMap<>(); //호선별 지하철역, 호선별 연결관계
    static HashMap<Integer, Integer> stations = new HashMap<>(); // 역별 호선정보
    static int[] isVisited = new int[11]; // 호선에 방문했는지 여부와 환승횟수 정보
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken()); // 역 갯수

            line = new ArrayList<>(); //  static에 미리 정의하고 line.clear하면 안됨! 쓰면 얕은 복사가 되어 리스트 정보를 공유해서 안됨
            for (int j = 0; j < K; j++) {
                int station = Integer.parseInt(st.nextToken());
                line.add(station);
                if (station == 0) zeroLines.add(n); // 서울역이 있는 호선 정보 기록
                stations.putIfAbsent(station, 1 << n); // 새로운 역일 경우 = stations에 없는 역일 경우
                if ((stations.get(station) & 1 << n) == 0) // 해당 역이 n호선에 있다는 정보가 없을 경우
                    stations.put(n, stations.get(station) | 1 << n); // 해당 역에 n호선이 있다는 정보 추가
                // + 연산자 우선순위가 더 높음 따라서 쓰기에 or이 더 적합
            }
            lines.put(n, line); // 해당 호선에 있는 역 정보 저장
            adj.put(n, new ArrayList<>()); // 호선 간 인접리스트 저장
        }

        // station = 각 station이 어느 호선에 있는지 나타낸다
        // 예시) station = 1110 해당 역은 1호선, 2호선, 3호선에 있다는 의미
        for (int station : stations.values()) {
            if (Integer.bitCount(station) == 1) continue;
            for (int i = 1; i <= N; i++) // 전체호선 순회
                // 1110 (station 비트 정보)
                // 0010 (1<<1 = 1호선)
                if ((station & 1 << i) != 0) // hasStation[i][station] == true : i번째 호선에 해당 역이 있는지 확인하는 의미 동일
                    for (int j = i + 1; j <= N; j++) // 현재 호선 제외 다른 호선 찾기
                        if (i != j && (station & 1 << j) != 0) { // hasStation[j][station] == true : j번째 호선에 해당 역이 있는지 확인하는 의미 동일
                            adj.get(i).add(j);
                            adj.get(j).add(i);
                        }
        }

        target = Integer.parseInt(br.readLine());
        for (int line : zeroLines) bfs(line); // 서울역이 있는 모든 호선을 시작점으로 bfs

        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void bfs(int startLine) {
        Arrays.fill(isVisited, -1); // -1로 환승횟수에 대한 초기화

        q.add(startLine); // 시작역이 있는 호선 추가
        isVisited[startLine] = 0; // 시작역이 있는 호선에서 환승횟수는 0회

        while (!q.isEmpty()) {
            int now = q.poll(); // 현재 호선

            if (lines.get(now).contains(target)) { // 해당 역에 도착역이 있다면
                min = Math.min(min, isVisited[now]); // 도착역까지 최소환승횟수 min 업데이트
                continue;
            }
            for (int other : adj.get(now)) { // 현재 호선에 연결된 다른 호선
                if (isVisited[other] >= 0)
                    continue; // 이미 해당 호선을 방문했으면 무시, why? 먼저 호선을 방문했을 때의 최소환승횟수가 이후 방문했을 때보다 항상 작기 때문
                isVisited[other] = isVisited[now] + 1; // 환승 횟수 1 증가
                q.add(other);
            }
        }
    }
}