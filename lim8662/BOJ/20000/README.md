# 20422번 퀼린드롬(Easy) 
[문제 보러가기](https://www.acmicpc.net/problem/20422)

## 🅰 설계
엣지 케이스를 조심해야 하는 문자열 문제입니다.

문자열의 양 끝에서 중앙으로 이동하며 문자를 비교해나가며 체크를 하였고

최대한 다양한 케이스들을 생각해보고 하나하나 처리하도록 구현하였습니다.

---

### 1. 전체 

```java
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
```
 

---

## ✅ 후기
제가 가장 기피하는 문제 유형으로 맨 마지막에 풀곤 합니다...

시간은 시간대로 소비하고 히든 케이스 하나라도 놓치면

문제를 틀리는 경우가 빈번했기 때문입니다.

저처럼 하나씩 예외처리를 하는 것보다 깔끔한 풀이 방법이 있을지 궁금합니다.
