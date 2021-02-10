# 11404번 플로이드
[문제 보러가기](https://www.acmicpc.net/problem/11404)

## 🅰 설계
플로이드 알고리즘 로직
(pseudo)
```java
for( 경유지 체크){
	for(시작도시){
		for(도착도시){
		//시작 == 도착이면 0
		//경유지 == 도착이면 continue;
		//map[시작][도착] = min ( , map[시작][도착] , map[시작][경유지] + map[경유지][도착지]);
		}
	}
}
```
입력은 BufferedReader로 출력은 StringBuilder로 모아서.
## 코드

```java
int[][] dist = new int[N][N];//각 도시로 가는 비용 정보가 들어감
for (int i = 0; i < N; i++) {
    Arrays.fill(dist[i], INF);//일단 최대로 초기화
}

for (int bus = 0; bus < M; bus++) {
    StringTokenizer  tokenizer = new StringTokenizer(br.readLine());
    int i = Integer.parseInt(tokenizer.nextToken());
    int j = Integer.parseInt(tokenizer.nextToken());
    int w = Integer.parseInt(tokenizer.nextToken());
    dist[i-1][j-1] = Math.min(dist[i-1][j-1], w);
}
for (int k = 0; k < N; k++) { //경유지
    for (int i = 0; i < N; i++) { //시작 도시
        for (int j = 0; j < N; j++) { //도착 도시
            if(k == j){
                continue;
            } else if (i == j) {
                dist[i][j] = 0;
            } else {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}
```


## ✅ 후기
플로이드 워셜 확실한 개념 정리

