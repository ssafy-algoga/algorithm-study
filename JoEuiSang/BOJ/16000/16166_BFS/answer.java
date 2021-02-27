package 스터디._2월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//지하철 환승횟수 최소로하기
// 항상 0에서 출발 : 출발역인 서울역은 항상 0이다.
// 역 : 정점, 간선 : 역의 연결 관계 , 지하철은 양방향으로 이동가능,  
public class Boj16166지하철4 {
	// 호선 수, 각 호선의 역 개수, 도착 역 번호, 도착 호선
	static int N, K, targetStation, targetLine = -1, ans = Integer.MAX_VALUE;
	static List<ArrayList<Integer>> stationList = new ArrayList<ArrayList<Integer>>(); // 각 호선의 역 리스트
	static List<ArrayList<Integer>> linkGragh = new ArrayList<ArrayList<Integer>>(); // 각 호선의 연결관계
	static List<Integer> startLine = new ArrayList<>(); // 시작 호선: 0번역이 있는 호선 리스트
	static boolean[] visited; // 호선 방문여부

	static class Link {
		int line; // 호선
		int cnt; // 최소환승수

		public Link(int line, int cnt) {
			super();
			this.line = line;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];

		// 입력
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			K = Integer.parseInt(st.nextToken());
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int i = 0; i < K; i++) {
				int val = Integer.parseInt(st.nextToken());
				temp.add(val);
			}
			stationList.add(temp);
		}

		targetStation = Integer.parseInt(br.readLine());

		// 호선 연결 관계 만들어주기
		linkGraghSet();
		if (targetLine != -1) {
			// 0번역이 있는 호선을 출발점으로 각각 시작
			for (Integer a : startLine) {
				Arrays.fill(visited, false);
				bfs(a);
			}
		}

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void bfs(int startLine) {
		Queue<Link> q = new LinkedList<>();
		q.add(new Link(startLine, 0));

		while (!q.isEmpty()) {
			Link now = q.poll(); // 큐에서 하나 뽑기
			if (now.line == targetLine) { // 현재의 호선이 가야할 호선이면
				ans = Math.min(ans, now.cnt);
				return;
			}
			if (!visited[now.line]) {
				ArrayList<Integer> linkLine = linkGragh.get(now.line);
				visited[now.line] = true;
				for (int i = 0; i < linkLine.size(); i++) {
					q.add(new Link(linkLine.get(i), now.cnt + 1));
				}
			}
		}
	}

	// 각 호선의 연결 리스트 만들기
	private static void linkGraghSet() {
		for (int i = 0; i < stationList.size(); i++) {
			linkGragh.add(new ArrayList<>());
		}

		// 각 호선의 연결관계
		for (int i = 0; i < stationList.size(); i++) {
			for (int j = 0; j < stationList.size(); j++) {
				if (i == j) // 같은 호선이면 넘기기
					continue;
				for (int k = 0; k < stationList.get(i).size(); k++) {
					for (int l = 0; l < stationList.get(j).size(); l++) {
						if (stationList.get(j).get(l) == 0) { // 0번역이면 시작 호선 등록
							startLine.add(j);
						}
						if (stationList.get(j).get(l) == targetStation) { // 도착역번호이면
							targetLine = j;
						}
						if (stationList.get(i).get(k) == stationList.get(j).get(l)) { // 같은 역번호가있으면
							linkGragh.get(i).add(j);	//연결관계 추가
							linkGragh.get(j).add(i);
							break;
						}
					}
				}
			}
		}
	}
}
