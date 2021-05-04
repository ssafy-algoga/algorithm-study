## 🅰 설계

* 해당 정점의 depth를 저장하는 배열 (-1로 초기화)

  ```java
  static int[] visited;
  ```

  

* bfs로 주어진 K depth 까지만 수행하면서 각 정점의 depth를 저장

  ```java
  Queue<Integer> q = new ArrayDeque<>();
  q.offer(X);
  visited[X] = 0;
  
  int cnt = 1;
  while (!q.isEmpty() && cnt <= K) {
    for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
      int cur = q.poll();
  
      for (int i = 0; i < edge[cur].size(); i++) {
        int next = edge[cur].get(i);
        if (visited[next] != -1) continue;
        visited[next] = cnt;
        q.offer(next);
      }
    }
    cnt++;
  }
  ```

* K depth에 해당하는 정점들을 StringBuilder에 저장

  ```java
  StringBuilder sb = new StringBuilder();
  for (int i = 1; i <= N; i++)
    if (visited[i] == K)
      sb.append(i).append('\n');
  return sb.length() == 0 ? "-1" : sb.toString();
  ```

  

## ✅ 후기

- dijkstra로 풀었을 때는 시간이 1296ms 소요되고, bfs로 풀었을 때는 시간이 1096ms 소요됐는데 의미 있는 시간 차이인지는 모르겠습니다.