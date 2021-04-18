# 16166번 서울의 지하철
[문제 보러가기](https://www.acmicpc.net/problem/16166)

## 🅰 설계

처음에 트리를 사용해서 역들을 정렬해서 최단 경로를 찾으려 했습니다.

접근이 잘못된 것 같아서 엎고 규태님의 코드를 참고해서 호선을 노드로 하여 BFS를 하였습니다.

---

### 1. 선언부

```java
static int N, D, cnt = 0;
static HashMap<Integer, List<Integer>> map = new HashMap<>(); // key : 역번호, value : 연결된 호선 리스트 
static List<Integer>[] lines; // 호선별 역정보 
static boolean[] visited; // 호선 방문 여부
```
map에 역과 그 역을 지나는 호선들을 저장했습니다.

따로 호선별 역정보를 리스트배열에 저장하였고,

boolean배열로 호선들의 방문 여부를 체크했습니다.

---

### 1. main

```java
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
```
1호선부터 입력받으며 map과 list에 각 정보를 입력하고 dfs를 실행했습니다.

---

### 2. bfs

```java
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
```
너비 우선 탐색을 하며 출발역의 모든 호선을 탐색하고,

그 호선들과 이어지는 다른 호선들을 환승하여 다음 단계에 탐색하였습니다.

---

## ✅ 후기
입력 받은 정보를 탐색에 용이하게 전처리를 하여 맵이나 리스트에 저장하는 과정이 익숙하지 않았습니다.

트리를 사용한 집합과 맵이라는 유형의 문제들을 더 풀어보며 감을 잡아가겠습니다.
