# 1697번 숨바꼭질
[문제 보러가기](https://www.acmicpc.net/problem/1697)

## 🅰 설계
현재 위치 X에서 X+1, X-1, 2*X로 갈 수 있다 => node 간의 연결관계 => 그래프 사용 <br>
찾을 수 있는 가장 빠른 시간 => BFS 사용
일단 BFS 문제라는 것만 캐치한다면 그 후엔 생각할 것이 많지 않습니다.
방문하지 않은 노드를 체크하기 위해 따로 boolean 배열을 만들지 않고
노드 값이 0인지 아닌지를 통해 판단하였습니다.
```java 
q.offer(start);
// bfs algorithm to the shortest distance
while(!q.isEmpty()) {
    int next = q.poll();
    // target node를 방문한다면 bfs를 종료
    if(next == target) {
        System.out.println(distance[target]);
        break;
    }
    if((next+1) < MAX_SIZE && distance[next+1] == 0) {
        distance[next+1] = distance[next] + 1;
        q.offer(next+1);
    }
    if((next-1) >= 0 && distance[next-1] == 0) {
        distance[next-1] = distance[next] + 1;
        q.offer(next-1);
    }
    if((2*next) < MAX_SIZE && distance[2*next] == 0) {
        distance[2*next] = distance[next] + 1;
        q.offer(2*next);
    }
}
```
## ✅ 후기
사실 처음엔 이전에 풀던 DP 문제들과 비슷해보여서 재귀호출을 통한 dfs로 구현했었는데요<br>
아무리 최적화를 해봐도 stack overflow를 피할 수 없었습니다.. 입력값의 범위가 꽤 큰편이라
처음부터 dfs를 사용하려 했던 생각이 실수였구나라는 생각을 했습니다. <br> 
아무래도 가중치가 1인  최단거리 문제가 아니면 거의 쓰지 않는 bfs 이다보니 dfs부터 생각하게 되는 것 같아요.

원래 Queue 인터페이스의 구현클래스로 LinkedList를 많이 이용했었는데 찾아보니 
ArrayDeque이 입출력 처리가 더 빠르더군요. 활용도도 덱이 더 많구요. 앞으로는
고정적으로 ArrayDeque을 사용할 것 같습니다.