# 4963번 섬의 개수
[문제 보러가기](https://www.acmicpc.net/problem/4963)

## 🅰 설계
### 0. Land 클래스와 필요 변수 정의
- isLand : 지도에서 땅인 공간을 true로 표기하는 2차원 배열
- isVisited : 지도에서 가서 확인한 공간을 true로 표기하는 2차원 배열
- posLand : 지도에서 땅인 공간에 대한 위치를 저장하는 deque
- spaceToGo : 앞으로 가야하는 공간을 저장하는 deque
- Land 클래스 : 땅의 위치 정보에 대한 상태를 관리할 수 있는 class

```java
class Land {
  int x;
  int y;

  Land(int x, int y) {
      this.x = x;
      this.y = y;
  }
}
```

### 1. 지도 크기 입력받기
열 col, 행 row 으로 입력이 들어온다.

```java
int col = Integer.parseInt(line[0]);
int row = Integer.parseInt(line[1]);

// 0 0 일 때, 입력 중단
if (row == 0 && col == 0)
  break;
```

### 2. 지도에서 땅 위치 표기(true) 및 위치 정보 저장

```java
for (int i = 0; i < row; i++) {
  String[] l = br.readLine().split(" ");
    for (int j=0; j<col; j++) {
      if (Integer.parseInt(l[j]) == 1) {
          isLand[i][j] = true; // 땅 위치 표기
          posLand.add(new Position(i, j)); // 땅 위치 저장
      }
    }
}
```

### 3. 지도에서 땅인 공간을 확인해서 섬 개수 세기

```java

int count = 0; // 섬의 개수를 저장할 변수 선언

if (posLand.size() < 1) { // 땅이 하나도 없을 경우
  System.out.println(count);
  continue;
}
spaceToGo.add(posLand.poll()); // 맨 처음 땅을 가봐야하는 공간으로 추가

while (spaceToGo.size() > 0) { // 가봐야하는 공간이 없을 때까지 확인

  Land cur = spaceToGo.poll(); // 현재 확인하고 있는 공간

  for (int[] move : moves) { // 현재 공간 기준으로 상하좌우 대각선에 있는 공간 확인
      int nx = cur.x + move[0];
      int ny = cur.y + move[1];
      
      // 그 다음에 확인할 공간에 대한 범위체크 + 땅인지 체크 + 이미봤는지 체크
      if (0 <= nx && nx < row && 0 <= ny && ny < col && isLand[nx][ny] && (!isVisited[nx][ny])) {
        spaceToGo.add(new Land(nx, ny));
        isVisited[nx][ny] = true;
      }
  }

  if (spaceToGo.size() == 0) { // 가야할 공간이 없다면 섬 개수 1 증가
      count++;
      if (posLand.size() > 0) { // 더 볼 땅이 있다면(땅의 초기 위치가 있다면)
        while (posLand.size() > 0) {
            Land next = posLand.poll();
            if (!isVisited[next.x][next.y]) {
              spaceToGo.add(next); // 가야할 공간으로 추가
              break;
            }
        }
      }
  }
}
```

### 4. 다음 테스트케이스를 위해 초기화

```java
for (int i = 0; i < row; i++) { // 방문했던 공간에 대한 체크와 땅 체크를 초기화
  for (int j = 0; j < col; j++) {
      isVisited[i][j] = false;
      isLand[i][j] = false;
  }
}
```

## ✅ 후기

문제를 풀다 보니 import 되는 것이 많았다.. 혜빈님이 알려주신 건 이를 더 간편하게 import해서 쓰기 위해서 aestrik을 생활화하면 좋다고 하셨다.
`import java.io.*; import java.util.*;`

이번에도 시간초과로 고생을 했다. (1) `Scanner → BufferedReader`, (2) `ArrayList → ArrayDeque`, (3) `방문한 것을 true로 체크하는 코드 위치 변경`으로 시간을 줄였다.
- `Scanner → BufferedReader`

    시간 초과로 고민할 때, 성현님과 대화하며 Scanner에서 BufferedReader로 바꾸어 보았다. 입력이 많을 때, Scanner와 BufferedReader의 성능 차이가 7배가 난다고 한다. 사실은 BufferedReader가 너무 길어서 Scanner를 썼는데, 다음엔 편의보단 성능을 먼저 생각하고 긴 입력이 들어올 땐 무조건 이걸 써야겠다.
- `ArrayList → ArrayDeque`
    
    Queue를 쓰고 싶은데, 아직 Java에서 유용한 자료형을 몰라서 처음 문제 풀이에 ArrayList를 사용했다. ArrayList의 remove(0)으로 사용했는데, 민규오빠와 혜빈님께서 여기서 시간이 걸렸을 것 같다고 적절한 자료형을 쓰는 걸 권유하셨다. ArrayDeque라는 자료형을 처음 알게 되었다. 갓혜빈님께서 deque라 앞, 뒤로 빼올 수 있어서 편리하니 쓰는 걸 추천해주셨다. 아직 ArrayDeque 자료형에 대해서 이해가 부족한데 추가적으로 공부해야겠다. 

- `방문한 것을 true로 체크하는 코드 위치 변경`

  처음엔 가야할 공간 `spaceToGo`에서 첫 번째 공간을 `poll()`해서 얻은 현재 공간의 x, y 위치에 대한 isVisited를 true로 표기하였다.

  ```java
  while (spaceToGo.size() > 0) { // 가봐야하는 공간이 없을 때까지 확인

      Land cur = spaceToGo.poll(); // 현재 확인하고 있는 공간
      isVisited[cur.x][cur.y] = true;
      ...
  }
  ```

  이렇게 했을 경우, 하나의 공간이 중복해서 확인이 되는 문제가 발생한다. 가야할 공간 `spaceToGo`에 공간이 추가될 때, 이미 그 공간은 갈 예정으로 isVisited 배열에서 그 위치는 true가 되어야 한다. 미리 이 부분을 체크하지 않는다면, 서로 다른 공간이지만 각각 자기를 기준으로 주변을 탐색할 때 똑같은 공간을 탐색이 되는 경우엔 `spaceToGo`에 2번 추가된다. 이를 해소하기 위해 다음에 확인할 공간에 대한 범위체크 + 땅인지 체크 + 이미봤는지 체크를 하여 통과한 space에 대해 isVisited를 true로 표기한다.

  ```java
  while (spaceToGo.size() > 0) { // 가봐야하는 공간이 없을 때까지 확인

      Land cur = spaceToGo.poll(); // 현재 확인하고 있는 공간

      for (int[] move : moves) { // 현재 공간 기준으로 상하좌우 대각선에 있는 공간 확인
          int nx = cur.x + move[0];
          int ny = cur.y + move[1];
          
          // 그 다음에 확인할 공간에 대한 범위체크 + 땅인지 체크 + 이미봤는지 체크
          if (0 <= nx && nx < row && 0 <= ny && ny < col && isLand[nx][ny] && (!isVisited[nx][ny])) {
              spaceToGo.add(new Land(nx, ny));
              isVisited[nx][ny] = true;
          }
      }
  ...
  ```
