# 16166번 서울의 지하철
[문제 보러가기](https://www.acmicpc.net/problem/16166)

## 🅰 설계
문제를 다 풀고 난 후에 이 문제를 곱씹어보면 노드와 간선정보가 다 주어진 상태에서
최단거리를 찾는 가장 심플한 버전의 BFS를 이용한 최단거리 구하기 문제였습니다.
하지만 실제 문제에서 주어진 정보에 불필요한 정보가 많았고 그 중에서 필요한 정보만
골라 단순한 문제로 환원시키는 과정이 실버3 이라기엔 어려웠지 않나 싶습니다.

일단 문제를 봤을 때 대부분은 그래프를 이용하려고 생각했을 것입니다. 이때 노드와 간선을
어떤 것으로 설정하느냐가 이 문제의 난이도를 결정합니다.

1. 지하철의 역(station)을 Node로 표현하고 역 간의 이동 가능성을 간선으로 표현
2. 지하철의 호선(line)을 Node로 표현하고 환승 가능성을 간선으로 표현

만일 1번으로 문제를 모델링해서 풀려고 했다면 이 문제는 실버 3이 아니라 골드3 보다도
어려운 문제로 바뀝니다. 하지만 2번으로 모델링을 했다면 기존의 풀었던 BFS 문제와 동일한
문제가 되어 쉽게 풀 수 있습니다.

```java 
// 지하철을 표현하기 위한 자료구조 List of Set
static List<Set<Integer>> subway = new ArrayList<>();
// 지하철 호선의 수
static int numOfLine;
// 최단거리를 저장하기 위한 distance 배열
static int[] transfer;
// 출발점이 여러 호선에 있을 수 있기 때문에 최소환승을 갱신하기 위한 변수
static int shortest = 100;
```
여러 호선과 이동 가능한 역이 있는 지하철을 표현하기 위해 어떤 자료구조를 쓸지 오래 고민
했는데 순환선 때문에 역이 중복되는 것을 방지하기 위해 HashSet 자료구조를 리스트에 담아 사용했습니다.
하지만 중복된다고 해봐야 겨우 한 개 중복되는 것이기 때문에 이중 리스트를 사용해도 아무런
차이가 없었을 거라 생각합니다.

```java 
// line1과 line2가 환승가능한지 여부를 반환하는 메소드
static boolean isTransfer(Set<Integer> line1, Set<Integer> line2) {
    // line1의 모든 역을 순회하면서
    for(int station : line1) {
        // line2에도 동일한 역이 존재한다면 true를 반환
        if(line2.contains(station)) return true;
    }
    // 겹치는 역이 없다면 false를 반환
    return false;
}
```

어쩌면 이 문제의 핵심이라고 할 수 있는 간선정보에 대한 처리입니다. 사실 이 메소드를 사용해서
인접리스트를 만들수도 있었지만 지하철 호선이 최대 10개이고 호선당 역도 최대 10개이므로
굳이 인접리스트를 만들지 않고 매번 메소드를 호출해도 크게 오버헤드가 발생할 것이라고 생각하지
않았습니다.

```java 
// 서울역이 어느 호선에 있는지 알 수 없으므로 모든 호선을 순회
for(int start = 0; start < numOfLine; start++) {
    // 만약 서울역이 있다면 그 호선을 시작노드로 하여 BFS 수행
    if(subway.get(start).contains(0)) {
        // 매번 새로운 BFS 수행을 위해 거리정보와 큐를 초기화
        Arrays.fill(transfer, -1);
        q.clear();
        q.offer(start);
        transfer[start] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            // 목적지가 있는 호선이라면 거리를 저장하고 BFS 종료
            if(subway.get(now).contains(target)) {
                shortest = Math.min(shortest, transfer[now]);
                break;
            }
            // 모든 호선을 순회하며 아직 방문하지 않았고 환승가능할 경우 방문
            for(int next = 0; next < numOfLine; next++) {
                if(next == now || transfer[next] != -1) continue;
                if(isTransfer(subway.get(now), subway.get(next))) {
                    q.offer(next);
                    transfer[next] = transfer[now] + 1;
                }
            }
        }
    }
}
```

BFS 구현부는 특별할 것이 없지만 BFS를 한번만 하고 끝내는 것이 아니라 시작점이 여러노드가
가능한 경우 여러번 BFS를 수행해야 하기 때문에 모든 변수들을 초기화 해줘야 하는 것에 주의
해야 합니다. 특히 `q.clear()`부분은 평소 BFS 구현에 쓰지 않기 때문에 놓치기 쉽습니다.

## ✅ 후기
`q.clear()` 하나 때문에 디버깅에 반나절을 소모했습니다.. 전역변수를 초기화 하지 않아서 
통과되지 않았던 경우는 수도 없이 많기 때문에 전역변수 초기화는 정말 꼼꼼히 신경쓰는데
큐는 전역변수도 아니였기 때문에 전혀 생각하지 못한 변수였습니다.