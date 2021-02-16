# 9663번: N-Queen

[문제 보러가기](https://www.acmicpc.net/problem/9663)

[코드 보기](./answer.java)

## 🅰 설계

### 규칙

N개의 퀸을 배치하되, 각각의 퀸은 같은 행, 열, 대각선에 위치할 수 없다는 점을 착안하여 pruning을 구현합니다

### 사용 변수

```java
int[] arr;
static boolean[] col, ul, ur;
```

**arr**

정수형 배열로 각 N개의 퀸이 체스 보드에서 어떤 좌표에 위치할 것인지 저장하는 배열입니다

예를 들어, N=4 일때, arr 배열은 다음처럼 구성할 수 있습니다

```java
arr[0] = 1; // (0,1) 위치에 퀸을 배치합니다
arr[1] = 3; // (1,3) 위치에 퀸을 배치합니다
arr[2] = 0; // (2,0) 위치에 퀸을 배치합니다
arr[3] = 2; // (3,2) 위치에 퀸을 배치합니다

/*

0 1 0 0
0 0 0 1
1 0 0 0
0 0 1 0

*/
```

**col**

boolean 배열로 각각의 퀸이 배치될 때마다 그 퀸의 열을 true로 체크합니다

예를 들어, N=4 일때, col 배열은 다음처럼 사용할 수 있습니다

```java
arr[0] = 1; // (0,1) 위치에 퀸을 배치합니다
col[1] = true; // 1 열은 현재 사용 중 입니다

arr[1] = 3; // (1,3) 위치에 퀸을 배치합니다
col[3] = true; // 3 열은 현재 사용 중 입니다
```

**ul**

boolean 배열로 각각의 퀸이 배치될 때마다 그 퀸의 왼쪽으로 기울어진 대각선을 true로 체크합니다

예를 들어, N=4 일때, ul 배열은 다음처럼 사용할 수 있습니다

```java
arr[0] = 1; // (0,1) 위치에 퀸을 배치합니다
ul[1] = true; // 왼쪽으로 기울어진 대각선들 중에서 1번 대각선은 현재 사용 중입니다

/*

왼쪽으로 기울어진 대각선은 아래와 같이 0번부터 6번까지 존재합니다

0 1 2 3
1 2 3 4
2 3 4 5
3 4 5 6

*/
```

**ur**

boolean 배열로 각각의 퀸이 배치될 때마다 그 퀸의 오른쪽으로 기울어진 대각선을 true로 체크합니다

예를 들어, N=4 일때, ur 배열은 다음처럼 사용할 수 있습니다

```java
arr[0] = 1; // (0,1) 위치에 퀸을 배치합니다
ur[2] = true; // 오른쪽으로 기울어진 대각선들 중에서 2번 대각선은 현재 사용 중입니다

/*

오른쪽으로 기울어진 대각선은 아래와 같이 0번부터 6번까지 존재합니다

3 2 1 0
4 3 2 1
5 4 3 2
6 5 4 3

*/
```

### 동작 원리

N-Queen 문제는 N개의 행에 퀸을 배치하는 것으로, 0번째 행부터 N-1번째 행까지 완전탐색을 수행합니다

```java
static int count=0; // N-Queen 경우의 수 count

static void dfs(int i) {
        if (i==N) {
            count++;
            return;
        }
				// 어떤 위치 (i,j)에 새로운 퀸을 배치했을 때 유효성 검사 및 재귀
        for (int j=0; j<N; j++) {

            // 어떤 위치 (i,j)는 이미 다른 퀸에 의해 사용할 수 없다면 패스
            if (col[j] || ul[i+j] || ur[i+Math.abs(j-N+1)])
            continue;

            arr[i] = j;
            col[j] = ul[i+j] = ur[i+Math.abs(j-N+1)] = true;
            dfs(i+1);
            col[j] = ul[i+j] = ur[i+Math.abs(j-N+1)] = false;
        }
    }
```

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class P9663 {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, count=0;
    static int[] arr;
    static boolean[] col, ul, ur;

    static void dfs(int i) {
        if (i==N) {
            count++;
            return;
        }

        for (int j=0; j<N; j++) {

            if (col[j] || ul[i+j] || ur[i+Math.abs(j-N+1)])
            continue;

            arr[i] = j;
            col[j] = ul[i+j] = ur[i+Math.abs(j-N+1)] = true;
            dfs(i+1);
            col[j] = ul[i+j] = ur[i+Math.abs(j-N+1)] = false;
        }
    }
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        col = new boolean[N];
        ur = new boolean[2*N];
        ul = new boolean[2*N];

        dfs(0);

        bw.write(count+"\n"); bw.flush();
        bw.close(); br.close();
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

pruning, 즉, 최적화가 잘 이루어지지 않는다면 시간초과가 발생할 것입니다...

### 고생한 점

최적화가 굉장히 어려웠고 며칠을 고민했던 것 같습니다
