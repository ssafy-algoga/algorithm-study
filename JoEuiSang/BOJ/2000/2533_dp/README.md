# 2533 사회망 서비스(SNS)
[문제 보러가기](https://www.acmicpc.net/problem/2533)

## 🅰 설계

다음 사람이 얼리어답터가 될 수 있는 조건을 생각해보았습니다.

1. 본인이 얼리어답터가 아니라면 친구는 무조건 얼리어답터이다.
2. 본인이 얼리어답터이면 친구는 뭐가되든 상관없다.



위 조건을 고려하여, 시작노드가 얼리어답터일 때와 레이트어답터일 때의 2가지 경우를 나누어 풀이하는 문제입니다.




## 주요 코드 설명
### 친구관계 생성
```java
static LinkedList<Integer>[] graph;		//각 노드들의 연결정보	

//그래프 관계 생성
for (int i = 0; i < N - 1; i++) {
    st = new StringTokenizer(br.readLine());
    int u = Integer.parseInt(st.nextToken());
    int v = Integer.parseInt(st.nextToken());
    graph[u].add(v);
    graph[v].add(u);
}
```



### dfs를 통해 dp구현, 친구의 상태를 최소값으로 처리합니다.

```java
private static void dfs(int value) {
    visited[value] = true;		//방문처리
    dp[value][EARLY] = EARLY;	//얼리어답터 1처리
    dp[value][LATE] = LATE;		//레이트어답터 0처리

    //현재 노드의 연결링크들 모두 확인
    for (int link : graph[value]) {
        //이미 방문한곳이면 패스
        if (visited[link])
            continue;
        dfs(link);	//재귀
        
        //내가 얼리어답터면 다음 친구는 얼리어답터 또는 레이트어답터가 될 수 있다.
        dp[value][EARLY] += Math.min(dp[link][EARLY], dp[link][LATE]);	
        
        //내가 레이트어답터면 다음친구는 무조건 얼리어답터여야한다.
        dp[value][LATE] += dp[link][EARLY];								
    }
}
```



## ✅ 후기

#### 설명이 다소 부족하다고 느껴지는데요.. 잘 모르겠어서 아이디어를 살짝 보고 풀었습니다.
#### 이해한거 같으면서도 완벽하게 이해한거 같지 않은듯한 느낌이 듭니다.
#### 메모리를 상당히 많이 잡아먹는데 좀 더 고민해볼 필요성이 있습니다.
#### DP 문제를 풀면 항상 문제한테 지는 느낌이 듭니다. 대충 어떻게 접근해야겠다라는 생각조차도 들지 않으니 말이에요.. 다양한 문제를 풀면서 실력을 키워나가야 겠습니다..