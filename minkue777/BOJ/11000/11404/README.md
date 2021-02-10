# 11404 플로이드
[문제 보러가기](https://www.acmicpc.net/submit/11404/26017749)

## 🅰 설계
플로이드 알고리즘의 구현은 정형화되어 있기 때문에 신경써야 할 부분은
인접행렬을 초기화 하는 것 뿐이었습니다. 
```java 
int numOfCities = Integer.parseInt(br.readLine());
// Adjacent matrix initialized to INF
distance = new int[numOfCities+1][numOfCities+1];
for(int[] row : distance) Arrays.fill(row, INF);
    for(int i=1; i<=numOfCities; i++) distance[i][i] = 0;

    int numOfEdges = Integer.parseInt(br.readLine());
    for(int i=0; i<numOfEdges; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        distance[start][end] = Math.min(distance[start][end], weight);
```
가중치가 항상 양수이기 때문에
다익스트라 알고리즘을 모든 노드에 대해 돌릴 수도 있지만 플로이드 알고리즘이
워낙 구현이 간단하기 때문에 굳이 라는 생각이 듭니다.

## ✅ 후기 
처음에 INF를 너무 작은 값으로 주어서 에러가 났습니다. 앞으로는 넉넉하게 주거나
Integer.MAX_VALUE 값을 써야겠습니다 ㅎㅎ 다익스트라나 플로이드 알고리즘은 최단 경로의
길이만 찾아줄 뿐 최단 경로를 구성하는 노드들의 목록을 구해주지 않기 때문에
정점들을 출력하는 코드를 짜보는 것도 좋은 연습문제가 될 것 같습니다.