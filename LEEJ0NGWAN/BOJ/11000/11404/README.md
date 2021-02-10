# 11404번: 플로이드

[문제 보러가기](https://www.acmicpc.net/problem/11404)

[코드 보기](./P11404.java)

## 🅰 설계

플로이드 알고리즘을 이용하는 문제입니다

플로이드는 노드의 개수 n에 대해, O(n^3)의 시간 복잡도와 O(n^2)의 공간 복잡도를 가지기 때문에,

그래프를 구성하는 노드의 개수가 많을 때 권장되는 방법은 아닙니다

## 인접 행렬 초기화

그래프를 구성하는 노드와 엣지의 정보를 2차원 행렬로 표현하고, 엣지의 가중치를 행렬에 저장합니다

예를 들어,

`map[1][4] = 122`는 1번 노드에서 출발하여 4번노드로 향하는 유향 엣지의 가중치가 122라는 의미입니다

n은 노드의 개수, m은 엣지의 개수를 뜻합니다

엣지 가중치 정보를 입력으로 받을 때, 기존 가중치보다 작으면 업데이트를 해줍니다

```jsx
map = new int[n+1][n+1];

for (int i=1; i<=n; i++)
for (int j=1; j<=n; j++)
map[i][j] = (i==j)? 0: 9900001;

for (int i=0; i<m; i++) {
	StringTokenizer st = new StringTokenizer(br.readLine());
	int a = Integer.parseInt(st.nextToken());
	int b = Integer.parseInt(st.nextToken());
	map[a][b] = Math.min(map[a][b], Integer.parseInt(st.nextToken()));
}
```

## 최소 경로 업데이트

플로이드 기법의 핵심은 아래의 두가지 경로를 비교하며 더 작은 값으로 경로를 업데이트 하는 것입니다

- `시작 → 도착`
- `시작 → 경유` + `경유 → 도착`

i는 시작 노드, j는 도착 노드, v는 경유 노드를 의미합니다

```jsx
for (int v=1; v<=n; v++)
for (int i=1; i<=n; i++)
for (int j=1; j<=n; j++)
map[i][j] = Math.min(map[i][j], map[i][v]+map[v][j]);
```

→ 위의 동작이 끝나면, 갈 수 있는 목적 노드에 한하여, 모든 최소 경로 값이 인접 행렬에 저장되어 있습니다

## 전체 코드

```jsx
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11404 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        for (int i=1; i<=n; i++)
        for (int j=1; j<=n; j++)
        map[i][j] = (i==j)? 0: 9900001;

        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(map[a][b], Integer.parseInt(st.nextToken()));
        }

        for (int v=1; v<=n; v++)
        for (int i=1; i<=n; i++)
        for (int j=1; j<=n; j++)
        map[i][j] = Math.min(map[i][j], map[i][v]+map[v][j]);

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++)
            sb.append(map[i][j]%9900001+" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

재밌는 그래프 문제입니다

### 고생한 점

엣지 케이스에 대비하여, 갈 수 없는 노드에 대한 가중치 값을 9900001로 설정하는 것에 주의합니다
