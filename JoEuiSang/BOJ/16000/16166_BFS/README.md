# 16166 서울의 지하철
[문제 보러가기](https://www.acmicpc.net/problem/16166)

# **아직 못풀었습니다. 뭐가 문제일까요..**

## 🅰 설계
<img src="https://user-images.githubusercontent.com/69133236/108726159-7253a680-756a-11eb-9f25-7e50e6150cfc.png" height="500">



각 호선을 정점으로 하고, 겹치는 역이 있다면 연결되었다는 리스트를 만들었고, 그 리스트로 BFS를 돌았다.

출발 역인 0 이 여러 호선에 있는 경우도 고려하여 코딩하였지만 정답을 보진 못하였다. 어느 부분이 문제일지..



## 주요 코드

**각 호선들을 연결하기**

```java
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
```



**호선끼리 연결된 그래프를 가지고서 BFS를 하는 함수**

```java
private static void bfs(int startLine) {
		Queue<Link> q = new LinkedList<>();
		q.add(new Link(startLine, 0));

		while (!q.isEmpty()) {
			Link now = q.poll(); // 큐에서 하나 뽑기
			if (now.line == targetLine) { // 현재의 호선이 가야할 호선이면
				ans = Math.min(ans, now.cnt);
				return;
			}
			if (!visited[now.line]) { //방문을 안한 호선이면
				ArrayList<Integer> linkLine = linkGragh.get(now.line);
				visited[now.line] = true; //방문처리
				for (int i = 0; i < linkLine.size(); i++) { //환승가능한 호선을 큐에삽입
					q.add(new Link(linkLine.get(i), now.cnt + 1));
				}
			}
		}
	}
```


## ✅ 후기
### 반례를 찾는것도 실력인 것 같다. 도대체 어느 반례가 숨어있을까..

### 골드 문제인 연구소를 쉽게 풀어 DFS/BFS에 자신감이 생겼는데 비슷한 유형 실버3 문제인 지하철을 못풀어서 조금은 서글프다. 그만큼 나는 아직 부족하고, 모자라고, 지하철을 탈 자격이 없다.