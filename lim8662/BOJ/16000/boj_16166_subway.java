package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 서울의 지하철 S3

public class boj_16166_subway {
	
	static int N, D, cnt = 0;
	static HashMap<Integer, List<Integer>> map = new HashMap<>(); // key : 역번호, value : 연결된 호선 리스트 
	static List<Integer>[] lines; // 호선별 역정보 
	static boolean[] visited; // 호선 방문 여부
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine());
		lines = new List[N+1];
		visited = new boolean[N+1];
		StringTokenizer st;
		
		for (int l = 1; l <= N; l++) { // 호선 번호
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 호선의 역 개수
			ArrayList<Integer> line = new ArrayList<>(); // 현 호선의 역정보
			
			for (int j = 0; j < K; j++) {
				int n = Integer.parseInt(st.nextToken()); // 역 번호
				line.add(n);
				
				if(map.containsKey(n)) { // 이미 있는 역이면 호선 추가
					List<Integer> lines = map.get(n);
					lines.add(l);
					map.put(n, lines);
				}
				else  { // 없던 역이면 map에 추가
					List<Integer> lines = new ArrayList<>();
					lines.add(l);
					map.put(n, lines); 
				}
			}
			lines[l] = line;
		}
		D = Integer.parseInt(br.readLine()); // 종착역 번호
		
		bfs();
		
		System.out.println(cnt);
	}
	
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>(); // 방문할 호선을 저장 
		
		for(int line : map.get(0)) { 
			q.add(line); // 출발역의 호선 큐에 저장
			visited[line] = true; // 호선 방문 처리
		}
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) { // 현 단계의 모든 호선 탐색
				int cur = q.remove(); // 현재 호선
				
				if(lines[cur].contains(D)) return; // 도착역이 있는 호선이면 탐색 종료
				
				// 현 호선 역들의 환승 호선 찾기
				for(int num : lines[cur]) 
				for(int line : map.get(num)) {
					if(visited[line]) continue;
					q.add(line);
					visited[line] = true;
				}
			}
	    	cnt++;	// 다음 단계로 넘어감 
		}
		cnt = -1; // 도착역을 못찾은 경우
	}
}
