# 14594번 동방프로젝트 (Small)
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계
쉽다면 쉬운 문제이지만 생각보다 생각할 점도 많고 재미있었던 문제였습니다.
문제 풀이 방법엔 여러가지가 있지만 아마 대부분의 풀이가 벽의 유무를
체크하는 방식일것 같습니다. 대충 이런 느낌의 코드입니다.
```java 
for(int i=start; i<end; i++) {
    wall[i] = false;
}
```
이때 시간복잡도는 M번의 반복문을 돌면서 최대 N만큼의 반복처리를 해주어야 하니 
`O(NM)`이 되고 입력제한에서 N과 M이 100 이하이기 때문에 여유롭게 통과됩니다.
사실 이 문제는 집합의 분할(수학적으로는 partition 이라고 하는데 알고리즘에서는
보통 disjoint set 이라고 부릅니다.)을 처리할 수 있는지 묻는 문제였습니다.
다만 Small 문제에서는 입력크기를 확 낮춤으로써 여러가지 방법으로 구현이 가능하도록
한 것이죠. 

Disjoint set은 집합을 교집합이 없는 부분집합들로 나누는 것을 의미합니다.
예를 들어 생일이 같은 사람들끼리 같은 집합으로 묶는다거나 전교생을 학년과 반으로
구분해서 나누는 것도 disjoint set의 예가 됩니다. 가장 쉽게 접할 수 있는 disjoint set은
정수를 모듈러 연산이 같은 집합으로 분할하는 것입니다. 정수 집합을 N으로 나눈 나머지가 
동일한 N개의 disjoint set으로 분할하는 것이죠. 이 문제의 경우엔 합쳐지는 방끼리 
같은 집합으로 묶는다면 이것이 전체 방에 대한 disjoint set이 됩니다. 
그리고 최종적으로 몇개의 set으로 구성되어 있는지가 출력이 됩니다.

Disjoint set을 표현하기 위해서 유니온-파인드(Union-find)라는 트리를 이용한
특수한 자료구조를 사용합니다. 유니온-파인드의 아이디어는 두 노드의 루트가 같다면
동일한 집합으로 취급하는 것입니다. 그래서 서로 다른 두 집합이 같은 집합으로 합쳐지는
과정을 한 트리를 다른 트리의 자손으로 넣는것으로 구현합니다. 또한 부모에서 자식으로 
가는 포인터가 필요없기 때문에 연결리스트가 아닌 배열을 이용하여 트리를 구현합니다.
이는 배열에 부모노드가 무엇인지만 저장하는 방식입니다.

```java 
// x가 어느 집합에 속하는지(루트가 무엇인지)를 알려주는 메소드
int find(int x) {
    // 자신이 루트노드라면 자기자신을 반환
    if(parent[x] == x) return x;
    // 그렇지 않다면 자신의 부모노드의 루트를 반환
    return parent[x] = find(parent[x]);
}
// 서로 다른 집합에 속한 노드를 같은 루트를 가지도록 합치는 메소드
void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    // rootY의 부모노드를 rootX로 변경함으로써 같은 루트를 가지도록 변경
    if(rootX != rootY) room[rootY] = rootX;
}
```

이 문제는 union 메소드를 이용하여 계속 집합을 합치기만 하면 해결됩니다.

```java 
for(int i=0; i<m; i++) {
    st = new StringTokenizer(br.readLine());
    int rootX = find(Integer.parseInt(st.nextToken()));
    int rootY = find(Integer.parseInt(st.nextToken()));
    // 이미 같은 집합이라면 pass
    if(rootX == rootY) continue;
    // 다른 집합이라면 rooX ~ rootY 집합을 하나의 집합으로 합침
    for(int j=rootX; j<=rootY; j++) {
        union(rootX, j);
    }
}
```

얼핏보면 이중 for문이기 때문에 시간복잡도가 똑같아보이지만 집합이 계속 합쳐질수록
`if(rootX == rootY) continue`로 처리되기 때문에 실제론 `O(NM)`보다 훨씬 빠른 시간안에
동작합니다. 동방프로젝트 (Large) 문제에서는 `N <= 100만, M <= 5000` 이여서 `O(NM)`
알고리즘으론 해결이 안되고 이러한 방법을 사용해야합니다.
하지만 C++로 돌리면 0.1초도 안걸리는 이 알고리즘은 자바로 돌리면 시간초과가 납니다.
따라서 좀더 최적화 할 방법을 고민해야 합니다. 이 문제는 반드시 연속된 방들로만 집합이
합쳐지기 때문에 내부 for문을 rootX ~ rootY까지 돌지 않고 `O(1)`에 해결할 방법이 있습니다.

```java 
for(int i=0; i<m; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    // 단순히 start 노드의 부모노드를 end 노드로 바꿈
    room[start] = Math.max(room[start], end);
}
```

for문을 통해 rootX ~ rootY 까지 전부 처리하는 것이 아니라 오로지 start 노드만 end 값으로
변경해줍니다. 이것의 의미는 `start방부터 end방까지는 하나로 합쳐져 있는 방들이다`
라는 의미를 담고 있습니다.

![](./figure/1.png)

방의 개수를 카운트 하는 방법은 배열을 앞에서부터 순회하면서 max 값을 갱신합니다.
max 값의 의미는 `지금 방은 최소 max 방까지는 하나의 방으로 연결되어 있음`을 의미합니다.
연결된 방의 마지막 방은 max 값과 방 번호가 일치할 때의 방입니다. 그림에서 본다면
4번 방에서 max 값과 방번호가 일치하기 때문에 1~4 까지가 하나의 방으로 연결되어 있음을
의미하고 5번방 역시 max 값과 방번호가 일치하기 때문에 또 다른 방을 구성하고 있음을
나타냅니다. 따라서 `max == idx` 조건을 만족할 때 마다 count를 1씩 증가시키면 됩니다.
이 알고리즘의 경우 시간복잡도는 단순히 `O(N)`으로 N <= 100만 조건하에 시간안에 충분히
해결할 수 있습니다.

## 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        room = new int[n+1];
        // 초기상태 세팅 
        for(int i=1; i<=n; i++) room[i] = i;
        int numOfBreaks = Integer.parseInt(br.readLine());
        for(int i=0; i<numOfBreaks; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            room[start] = Math.max(room[start], end);
        }

        int count = 0;
        // count를 위한 max값을 담는 변수
        int criterion = room[1];
        for(int i=1; i<room.length; i++) {
            // 만약 room[i]가 더 크다면 max값 갱신
            if(room[i] > criterion) criterion = room[i];
            // 방번호와 max 값이 일치할 때 방 개수 +1
            if(i == criterion) count++;
        }
        System.out.println(count);
    }
}
```

## ✅ 후기
유니온-파인드 자료구조는 쓸 일이 많지가 않아서 애매모호하게 알고 있었는데 이번에
문제를 풀면서 확실하게 이해하게 됐습니다.