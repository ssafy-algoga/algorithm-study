# 18352번 특정 거리의 도시 찾기

[문제 보러가기](https://www.acmicpc.net/problem/18352)

## 🅰 설계

### 1. 도시 간 관계

도시 간 관계는 양방향이 아니라 단방향 관계이다

```java
ArrayList<Integer>[] adj = new ArrayList[numOfCity + 1];
for (int i = 1; i <= numOfCity; i++) {
    adj[i] = new ArrayList<Integer>();
}

for (int i = 0; i < numOfRoad; i++) {
    st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    adj[a].add(b);
}
```

### 2. 전체 도시 탐방

주어진 최소 거리만큼 떨어진 도시가 있는지 확인 

```java
Queue<Integer> q = new ArrayDeque<>();
int[] isVisited = new int[numOfCity + 1];
Arrays.fill(isVisited, -1);

q.add(startCity);
isVisited[startCity] = 0;
boolean isThere = false;
while (!q.isEmpty()) {
    int now = q.poll();
    for (int next : adj[now]) {
        if (isVisited[next] != -1) continue;
        q.add(next);
        isVisited[next] = isVisited[now] + 1;
        if (isVisited[next] == minDist) isThere = true;
    }
}
```

### 3. 결과 도출

- `isThere` : 주어진 최소거리만큼 떨어진 도시가 있다면 true가 되는 변수

  isThere이 true가 아니면 주어진 최소거리만큼 떨어진 도시가 없다는 것으로 -1을 출력한다. 그 반대이면 주어진 최소거리만큼 떨어진 도시를 StringBuilder에 추가한다.

```java
if (!isThere) {
    System.out.println(-1);
    return;
}
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= numOfCity; i++) {
    if (minDist != isVisited[i]) continue;
    sb.append(i).append("\n");
}
System.out.println(sb);
```



## ✅ 후기

- 싸피에서 자주 푸는 그래프 문제 유형이라서 bfs로 접근하여 빠르게 푼 것 같습니다

