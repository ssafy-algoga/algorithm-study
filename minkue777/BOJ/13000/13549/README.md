# 13549번 숨바꼭질 3
[문제 보러가기](https://www.acmicpc.net/problem/13549)

## 🅰 설계
숨바꼭질 문제의 variation 입니다. 숨바꼭질을 이미 풀었던 상태여서
쉬운 문제라고 생각할 수도 있지만 생각보다 함정이 많고 올바른 풀이를
작성하기 힘든 문제입니다. 이 문제를 해결하면서 공부했던 내용과
새로 깨달았던 것들을 공유해보려 합니다.
<br /><br />
### 1. 착각하기 쉬운 풀이법 1
이 문제의 핵심은 **<span style="color:yellow"> '거리가 0인 노드를 
어떻게 처리하는가'</span>** 입니다.
<br />
그리고 필연적으로 이 고민에 대한 답으로

**<span style="color:#5882FA">'거리가 0인 노드를 거리가 1인 노드보다
먼저 큐에 삽입한다'</span>**

라는 결론을 내리게 됩니다. 이를 코드로 작성하면 다음과 같습니다.
```java 
int[] weight = new int[] {0, 1, 1};
// 현재 노드와 연결된 노드들을 배열로 선언
int[] nextNodes = new int[] {2*curNode, curNode-1, curNode+1};
// 배열을 순회하면서 방문하지 않은 노드인 경우 거리를 저장하고 큐에 삽입
for(int i=0; i<3; i++) {
    int nextNode = nextNodes[i];
    if (nextNode >= 0 && nextNode <= MAX_SIZE && distance[nextNode] == -1) {
        q.offer(nextNode);
        // 다음 노드와의 거리가 0인 경우와 아닌 케이스를 분리
        distance[nextNode] = distance[curNode] + weight[i];
```
실제로 이렇게 코드를 작성하고 제출하면 AC가 뜹니다. 하지만 이 알고리즘은 <br />
**<span style="color:yellow"> BFS를 이용해 최단 거리를 구한 알고리즘이 아니며 
문제의 특수성(혹은 테스트 케이스 부족)으로 인해 통과됐을 뿐 이렇게 구한 값은 
최단거리를 보장하지 않습니다.</span>** <br />
실제로 큐에 어떤 순서로 노드가 삽입되는지를 추적하면서 이 풀이의 문제점을 짚어보겠습니다.
<br /> 설명의 편의를 위해 MAX 값을 20으로 가정합니다.
<br /><br />
![큐 내부 모습](./figure/1.jpg)

문제가 되는 부분은 <span style="color:red">20(0)</span> 노드가 삽입되는 타이밍입니다.
**<span style="color:#5882FA"> 노드 20은 기준점 5와 최단거리가 0 이지만 
최단 거리가 1인 노드 4와 노드 6보다 늦은 타이밍에 큐에 삽입됩니다. 
만약 노드 20에서 방문 가능한 노드를 노드 4와 노드 6이 먼저 방문해버린다면
그 노드는 실제로 노드 20에서 이동하는 최단 경로가 있음에도 불구하고 
다른 값을 저장하게 되고 정작 노드 20을 통해 방문했을 경우 
이미 방문된 노드로 판단하고 값을 갱신하지 않습니다.</span>**
따라서 이 알고리즘으로는 대부분의 문제를 해결할 수 없습니다. <br />

실제로 방문 순서를 `{2*curNode, curNode-1, curNode+1}`에서 
`{2*curNode, curNode+1, curNode-1}`로 바꾸는 것만으로도 입력값 `4 6`에 대하여 올바른 정답
`1`이 아닌 `2`값을 반환합니다.
이는 실제 정답인`4 -> 3(1) -> 6(1)`의 루트보다 `4 -> 5(1) -> 6(2)`가 더 먼저 발생하기 때문입니다.
<br />

### 2. 착각하기 쉬운 풀이법 2
**<span style="color:#5882FA"> 그렇다면 거리가 0인 노드들을 전부 방문하여 
먼저 큐에 삽입한다면 어떨까요?</span>** 위의 **풀이1** 에서 노드 20이 늦게
삽입되는게 문제가 되었기 때문에 처음부터 노드 5와 거리가 0인 노드 10과 노드 20을 삽입하고 그 이후
노드 4와 노드 6을 삽입하는 것 입니다. 이는 제가 처음 시도했던 풀이 방법입니다.
```java 
int[] nextNodes = new int[] {2*curNode, curNode-1, curNode+1};
for(int i=0; i<3; i++) {
    int nextNode = nextNodes[i];
    // 거리가 0인 노드를 전부 큐에 삽입
    if(i == 0) {
        while(nextNode >= 0 && nextNode <= MAX_SIZE && distance[nextNode] == -1) {
            q.offer(nextNode);
            distance[nextNode] = distance[curNode];
            nextNode *= 2;
        }
    }
    // 이후 거리가 1인 노드를 큐에 삽입 
    else {
        if(nextNode >= 0 && nextNode <= MAX_SIZE && distance[nextNode] == -1) {
            q.offer(nextNode);
            distance[nextNode] = distance[curNode]+1;
        }
    }
}
```
얼핏 보기에는 그럴 듯해 보이지만 이 방법 역시 두 가지 문제점이 있습니다.
1. **메모리 사용량 증가**
<br> 이 방법은 큐에 많은 양의 데이터를 보관해야 하기 때문에 자칫하면 
   메모리 초과 판정을 받을 수 있습니다. 이 문제는 메모리 제한이 512MB로 넉넉하게
   잡혀 있지만 범위가 10만 이라는 것을 고려하면 생각보다 아슬아슬한 수치이고 실제로
   이 코드를 제출하면 메모리 초과 판정을 받습니다.
   <br /> (** 하지만 동일한 코드를 파이썬으로 작성하면 메모리 초과판정을 받지 않습니다. 자바가 다른 언어에 비해
   메모리 사용량이 많은 걸까요?)
   
   <br>
2. **실제로 해결되지 않는 삽입 순서**  
   사실 더 큰 문제가 있습니다. 해결될 것처럼 보이는 삽입 순서의 문제는 실제로 해결되지 않습니다. 
   예제에서 시간이 흐르면서 결국 노드 12에 의해 순서가 뒤집히게 됩니다.
   

   ![큐 내부 모습](./figure/2.jpg)


### 3. 어떻게 해결할 수 있을까?
이 문제를 해결하기 위해 가장 중요한 부분은 **<span style="color:#5882FA">
우리가 기존에 사용하던 BFS를 이용한 최단거리 알고리즘이 사실 
BFS의 탈을 쓴 다익스트라 알고리즘 이라는 것을 아는 것입니다.</span>** <br>
좀 더 정확히 이야기하면 BFS로 구현된 최단거리 알고리즘은 모든 간선의 가중치가
1인 경우에 한해 다익스트라 알고리즘을 약간 변형시킨 것에 불과합니다. 
그래서 다익스트라 알고리즘을 처음 접하게 되면 BFS를 이용한 최단거리 구하기
알고리즘과 매우 유사하다는 생각을 하게 됩니다. BFS를 사용한 최단거리 구하기 알고리즘과
다익스트라 알고리즘은 두 가지에서 차이가 있습니다.
1. 다익스트라 알고리즘은 방문한 노드를 저장한 컨테이너 내부에서 기준점으로부터 가장 거리가 가까운 노드를
선택하는 절차가 있습니다.
2. 다익스트라 알고리즘은 방문 여부를 체크하는 것이 아니라 재 방문시에 거리가 더 적은
경우로 최단거리를 갱신합니다.

BFS를 이용한 최단거리 구하기에서 1과 2가 빠져있는 이유는 모든 간선의 가중치가
1인 특별한 경우일 땐 1과 2를 고려할 필요가 없기 때문입니다. 방문 순서가
앞선 다는 것은 기준 노드와 거리가 더 가깝다는 것과 동일한 의미를 갖기 때문에
저장된 노드 중에 가장 먼저 방문한 노드를 선택하면 됩니다. 그리고 그 노드는
큐에 가장 앞에 저장되어 있기 때문에 `q.poll()`을 통해 `O(1)`시간에 간단하게
얻을 수 있습니다. 또한 특정 노드를 재 방문하는 경우는 더 늦게 방문했다는 
의미이므로 최단거리가 갱신될리 없습니다. 따라서 방문 여부만 검사하면 됩니다.

**<span style="color:#5882FA">
다익스트라 알고리즘에서는 컨테이너 내부에서 기준점으로 가장 거리가 가까운 노드를 선택
하기 위해 큐가 아닌 우선순위 큐를 사용합니다.</span>** 우선순위 큐를 사용한다면 노드
방문 순서와 관계 없이 기준점으로부터 최소거리를 가진 노드를 `O(logN)`시간에 
얻을 수 있기 때문에 목적에 가장 부합하는 자료구조입니다.

결론적으로 BFS를 사용한 최단거리 알고리즘은 모든 간선의 가중치가
동일할 때만 사용가능한 변형된 다익스트라 알고리즘입니다. 하지만 이 문제는
간선의 가중치가 0인 것이 포함되어 있기 때문에 기존의 알고리즘이 정답을
보장하지 않습니다. **<span style="color:yellow">그렇다면 해결책은 변형되지 않은 오리지날 다익스트라 알고리즘을
사용하는 것입니다.</span>** 다익스트라 알고리즘은 음수 간선이 없다면 작동하므로 이
문제에 적용하기에 아무 문제가 없습니다.

```java 
while(!pq.isEmpty()) {
    // 최소힙에서 거리가 최소인 노드 반환
    Node curNode = pq.poll();
    if(curNode.index == target) {
        System.out.println(distance[curNode.index]);
        return;
    }
    // 기준점으로부터 curNode에 도달하기 위한 최소 비용
    int dist = curNode.distance;
    // 현재 노드
    int now = curNode.index;
    // 이미 이전에 처리된 적이 있는 노드라면 통과
    if(distance[now] < dist) continue;
    // 현재 노드와 연결된 노드들을 차례대로 검사
    int[] weight = new int[] {0, 1, 1};
    int[] nextNodes = new int[] {2*now, now-1, now+1};
    for(int i=0; i<3; i++) {
        int next = nextNodes[i];
        if (next >= 0 && next <= MAX_SIZE) {
            int cost = distance[now] + weight[i];
            if(cost < distance[next]) {
                distance[next] = cost;
                pq.offer(new Node(next, cost));
            }
        }
    }
}
```
우선순위 큐를 사용한 다익스트라 알고리즘의 시간복잡도는 `O((V+E)*logV)`인데 
이 문제에서 `V = 10만, E = 3V`이므로 최종적으로 `O(V*logV)`가 되어 시간은 넉넉합니다.


### 4. 또 다른 해결책
다익스트라 알고리즘이 기존 BFS를 이용한 알고리즘과 속도 차이를 보이는 부분은
새로운 노드를 삽입, 삭제하는 시간이 `O(logN)`이라는 점 입니다.
(큐에 삽입 삭제시에는 `O(1)`)<br>
사실 이 문제처럼 값이 0과 1로만 이루어져 있는 경우엔 다익스트라 알고리즘을 쓰지
않고 BFS만으로 해결할 수 있는 방법이 있습니다.
이전 **풀이1** 과 **풀이2** 에서 문제가 됐던 것은 새로 방문하는 모든 노드를
뒤에서 삽입했기 때문입니다. **<span style="color:#5882FA">
그렇다면 거리가 0인 노드를 방문하는 경우엔
큐 앞에서 삽입하고 거리가 1인 노드를 방문하는 경우엔 큐 뒤에서 삽입한다면 
우선순위 큐를 사용하지 않고도 `O(1)`시간을 소요하여 완벽하게 정렬된 결과를
얻을 수 있습니다. 그리고 Deque는 우리가 원하는 모든 기능이 구현되어 있는 
훌륭한 컨테이너입니다. </span>**
```java 
while(!q.isEmpty()) {
    int curNode = q.poll();
    if(curNode == target) {
        System.out.println(distance[curNode]);
        return;
    }

    int[] weight = new int[] {0, 1, 1};
    int[] nextNodes = new int[] {2 * curNode, curNode-1, curNode+1};
    for(int i=0; i<3; i++) {
        int nextNode = nextNodes[i];
        if (nextNode >= 0 && nextNode <= MAX_SIZE && distance[nextNode] == -1) {
            // 거리가 0인 경우 덱의 앞에서 삽입 1인 경우 뒤에서 삽입
            if(i == 0) q.addFirst(nextNode);
            else q.addLast(nextNode);
            distance[nextNode] = distance[curNode] + weight[i];
        }
    }
}
```

## 전체 코드
* 다익스트라 알고리즘
```java
import java.util.*;

public class Main {
    static class Node {
        int index;
        int distance;

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    static final int INF = 987654321;
    static final int MAX_SIZE = 100000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int target = sc.nextInt();
        if(target <= start) {
            System.out.println(start - target);
            return;
        }

        int[] distance = new int[MAX_SIZE+1];
        Arrays.fill(distance, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((p,q)->p.distance-q.distance);
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
            if(curNode.index == target) {
                System.out.println(distance[curNode.index]);
                return;
            }
            // 기준점으로부터 curNode에 도달하기 위한 최소 비용
            int dist = curNode.distance;
            // 현재 노드
            int now = curNode.index;
            // 이미 이전에 처리된 적이 있는 노드라면 통과
            if(distance[now] < dist) continue;
            // 현재 노드와 연결된 노드들을 차례대로 검사
            int[] weight = new int[] {0, 1, 1};
            int[] nextNodes = new int[] {2 * now, now-1, now+1};
            for(int i=0; i<3; i++) {
                int next = nextNodes[i];
                if (next >= 0 && next <= MAX_SIZE) {
                    int cost = distance[now] + weight[i];
                    if(cost < distance[next]) {
                        distance[next] = cost;
                        pq.offer(new Node(next, cost));
                    }
                }
            }
        }
    }
}
```
* 덱을 사용한 BFS 최단거리 알고리즘
```java
import java.util.*;

public class Main {
    static final int MAX_SIZE = 100000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int target = sc.nextInt();
        if(target <= start) {
            System.out.println(start - target);
            return;
        }

        int[] distance = new int[MAX_SIZE+1];
        Arrays.fill(distance, -1);
        Deque<Integer> q = new ArrayDeque<>();

        distance[start] = 0;
        q.offer(start);

        while(!q.isEmpty()) {
            int curNode = q.poll();
            if(curNode == target) {
                System.out.println(distance[curNode]);
                return;
            }

            int[] weight = new int[] {0, 1, 1};
            int[] nextNodes = new int[] {2 * curNode, curNode-1, curNode+1};
            for(int i=0; i<3; i++) {
                int nextNode = nextNodes[i];
                if (nextNode >= 0 && nextNode <= MAX_SIZE && distance[nextNode] == -1) {
                    if(i == 0) q.addFirst(nextNode);
                    else q.addLast(nextNode);
                    distance[nextNode] = distance[curNode] + weight[i];
                }
            }
        }
    }
}

```

## ✅ 후기
생각보다 많을 것을 공부했고 많은 것을 얻을 수 있던 문제였습니다.
그리고 다른 분들에게도 도움이 될 것이라는 생각에 이번 리드미는 조금 자세하게
작성해 보았습니다. 좋은 공부 자료가 됐으면 좋겠네요!