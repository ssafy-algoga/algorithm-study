# 1051번 숫자 정사각형

[문제 보러가기](https://www.acmicpc.net/problem/1051)

## 🅰 설계

### 1. 사용자 입력 및 변수 초기화

<img src="./images/memo.jpg" height=300/>

- **dir** :  오른쪽 아래쪽 오른아래대각선을 움직일 수 있도록 X와 Y 이동에 대한 배열
- **N** : 직사각형 가로길이
- **M** : 직사각형 세로길이
- **map** : N*M 직사각형에 대한 2차원 배열
- **rowLen** : 순회할 행의 범위
- **colLen** : 순회할 열의 범위
- **numLen** : 움직임에 대해 확장 범위
- **max** : 가장 큰 정사각형 변에 대한 최대값

```java
static int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

int N = parse(st.nextToken());
int M = parse(st.nextToken());

int[][] map = new int[N][];
for (int i = 0; i < N; ++i)
    map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

int rowLen = N - 1;
int colLen = M - 1;
int numLen = (N < M) ? N - 1 : M - 1;
int max = 1;
```

### 2. 직사각형의 모든 좌표 순회를 하며 가장 큰 정사각형의 변 구하기 

- now : 직사각형 중 현재 좌표

직사각형의 특정 좌표마다 `+1`씩 확장시키며 정사각형 꼭짓점이 동일한지 확인한다.  

```java
for (int X = 0; X < rowLen; X++) {
    for (int Y = 0; Y < colLen; Y++) {
        int now = map[X][Y];
        EXPAND : for (int num = 1; num <= numLen; num++) {
            for(int[] dir: dir) {
                int nextX = X + dir[0] * num;
                int nextY = Y + dir[1] * num;
                boolean isInMap = 0<=nextX && nextX <= rowLen && 0<=nextY && nextY <= colLen;

                if(!isInMap)
                    break EXPAND;
                if(now != map[nextX][nextY])
                    continue EXPAND;
            }
            max = Math.max(max, num+1);
        }
    }
}
```



## ✅ 후기

- 스트림 api로 보다 더 간단하게 사용자 입력에 대해 처리가 가능하다!

  ```java
  Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
  ```

  

   

