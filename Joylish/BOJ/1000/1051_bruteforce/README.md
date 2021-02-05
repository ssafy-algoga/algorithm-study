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

- **어떻게 조건을 처리해야 더 적게 반복문을 돌아 시간적으로 단축시킬 수 있는지**가 아직 힘들다..

  수업시간에 쌤께서 코드리뷰를 해주실 때 성능을 위해 참조해야하는 배열이나 기타 자료구조에 대해서 최대한 변수로 빼야 한다고 말씀해주시는 등 이런 부분에서 조언을 많이 해주신다. 숫자 정사각형 문제에서도 시간을 더 줄일 수 있을 것 같은데,, 아직까지 어떤 조건을 넣어야지 반복을 덜할지 말지에 대해서 아직 감이 안온다..😓 혹시 특정 조건을 추가하거나 변경해서 줄일 수 있는 부분 있으면 알려주시면 감사하겠습니다..

