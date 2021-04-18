# 11404번 플로이드 
[문제 보러가기](https://www.acmicpc.net/problem/11404)

## 🅰 설계

흔히 알려진 공식적인 방법을 사용하여 해결하였습니다.

## 주요 코드
### 변수
```java
static int[][] graph; //도시간 거리를 저장할 2차원배열
static int cityCnt; // 도시 수
static int bus; // 버스 수
public static final int MAX = 1000000000;   //거리의 최대값 상수
```

### 동일한 경로, 다른 코스트가 나올경우
```java
graph[start][end] = Math.min(graph[start][end], cost); 
```

### 플로이드 부분
```java
private  static void floyd() {
    // 거쳐가는  K
    for(int k = 1; k <= cityCnt; k++) {
        // 출발하는  i
        for(int i=1; i <= cityCnt; i++) {
            // 도착하는 j
            for(int j=1; j <= cityCnt; j++) {
                //i-> k -> j 와  i-> j 거리를 비교해서 작은 값
                graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
            }
        }
    }
}
```


## ✅ 후기
### 사실 풀이법을 모른다면 못풀었을 것이다. 실제 테스트에서 처음 맞닥뜨린다면, 삽질만 하다가 실패할게 분명하고, 그런 상황을 방지하기 위해 다양한 알고리즘들을 접하고 익혀야할 필요가 있겠다.