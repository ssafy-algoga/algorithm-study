# 16166번 서울의 지하철

[문제 보러가기](https://www.acmicpc.net/problem/16166)

## 🅰 설계

※ 👹 : **비트마스킹으로 했을 때 추가한 변수 및 코드일 경우 해당 이모지를 추가했습니다.**

#### 1. 입력 데이터 처리 및 저장

- **min** :서울역이 있는 모든 호선에 대한 bfs 중 최소 환승 횟수정보
- **zeroLines** : 0인 서울역이 있는 호선 정보
- **lines** : 호선별 지하철역 정보
- **adj** : 호선별 연결관계 정보
- **stations** : 역별 호선정보 👹

```java
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
            stations.put(n, stations.get(station) | 1 << n); // 👹해당 역에 n호선이 있다는 정보 추가
        // + 연산자 우선순위가 더 높음 따라서 쓰기에 or이 더 적합
    }
    lines.put(n, line); // 해당 호선에 있는 역 정보 저장
    adj.put(n, new ArrayList<>()); // 호선 간 인접리스트 저장
}
```

#### 2. 호선 간 인접리스트 만들기

bfs에 앞서 호선 간 연결관계를 만들었습니다. 

호선 간 인접리스트를 만들기 위해 처음 작성한 코드는 다음과 같습니다.

```java
for (int i = 1; i <= N; i++) {
    for (int j = i + 1; j <= N; j++) {
        if (i == j) continue;
        for (int x : lines.get(i)) {
            for (int y : lines.get(j)) {
                if (x == y) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
    }
}
```

👹 앞서 입력정보를 처리하여 저장할 때, `stations` 에 역별 호선정보를 `bit`로  저장하였습니다.  이를 이용하여 호선 간 인접리스트를 만든 코드는 다음과 같습니다.

```java
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
```

#### 3. 서울역이 있는 모든 호선에 대한 bfs

- **target**  : 도착역의 번호

```java
target = Integer.parseInt(br.readLine());
for (int line : zeroLines) bfs(line); // 서울역이 있는 모든 호선을 시작점으로 bfs

if (min == Integer.MAX_VALUE) System.out.println(-1);
else System.out.println(min);
```

bfs 는 **서울역이 있는 호선**부터 시작합니다.

```java
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
```

## ✅ 후기

- **문제풀이 아이디어의 막연함**에 이어 **어떤 정보를 정점으로 볼 것인지 선택**의 어려움 😰

  이 문제를 접했을 때 어떤 자료구조를 이용하여 주어진 역 정보를 저장해야 할지도 막막했습니다. 

  처음에 disjoint set으로 환승할 수 있는 역이 있다면 합쳐버리는 형태로 집합을 만들었습니다. 하지만 disjoint set에서 하나의 집합에 있는 모든 정점은 최상위 부모노드의 인덱스로 되기 때문에 기존 union-find 코드에서 depth를 저장하는 식으로 바꿔보려고 했습니다. 하지만 변형이 힘들어 환승 횟수를 구하기가 쉽지 않아 해당 솔루션은 아니구나를 깨닫고 엎었습니다.

  이 문제로 태희쌤과 종완이와 대화를 하며 역이 아닌 **호선** 을 정점으로 보면 되는구나라는 것을 깨닫게되었습니다. 그 다음 풀이로, 도착역이 있는 호선에서 시작역이 있는 호선을 찾는 bfs로 접근했습니다. 아뿔싸! **도착역이 여러 개일 경우** 최소 환승횟수가 달라질 수 있다는 것을 알게되었습니다. 입력 형식을 보면 모든 호선에 대한 역 정보를 주고 도착역을 주기 때문에 도착역을 입력받고 다시 호선별 역정보를 순회하며 도착역이 있는 호선을 찾는 것은 비효율적이라는 생각이 들었습니다. 

  돌고 돌아 0번인 서울역이 있는 호선을 호선별 역정보가 들어올 때 저장하여, 서울역이 있는 모든 호선을 시작점으로 여러번 bfs를 시행했습니다.

- ArrayList의 얕은 복사와 깊은 복사

  **line**을 static 변수로 따로 선언하여 사용해보았습니다. 처음에는 line을 매 호선마다 초기화해주기 위해 `line.clear()`를 사용하고  **line**에 역정보 입력이후 **lines**에 저장하였습니다. 이 때, lines에 저장되는 것은 line 객체의 레퍼런스이기 때문에 마지막 호선에 대한 역정보로 모두 바뀌어버리는 불상사가 발생했습니다.

  따라서 호선에 대한 역정보가 한 줄 단위로 입력이 들어올때마다 line 객체를 새롭게 만들어 넣어주는 형식으로 바꾸었습니다. 

- HashMap의 `putIfAbsent()`

- Bit 연산 시 `+` 보다 `or`가 더 적합하구나

- Integer의 `Bitcount()`