# 9251번: LCS

[문제 보러가기](https://www.acmicpc.net/problem/9251)

[코드](./answer.java)

## 🅰 설계

[paint](./9251_paint.jpg)

### 초기화

전형적인 DP 문제로, 입력받은 두 문자열에 대한 2차원 int map을 생성합니다.

이 map은 각각 비교하는 해당 시점에서 가장 많이 겹친 알파벳의 개수를 저장합니다.

padding을 위해 map의 크기는 (문자열1의 길이 +1) x (문자열2의 길이 + 1)로 설정합니다.

```jsx
// br = new BufferedReader(new InputStreamReader(System.in));

char[] a = br.readLine().toCharArray();
char[] b = br.readLine().toCharArray();
int[][] map = new int[b.length+1][a.length+1];
```

### 비교 로직

두번째 문자열을 행, 첫번째 문자열을 열로 생각하여 map 순회를 시작합니다

```jsx
for (int i = 0; i < b.length; i++)
	for (int j = 0; j < a.length; j++)
```

각 위치에서, 현재 첫번째 문자열과 두번째 문자열이 가리키고 있는 캐릭터를 비교합니다.

같다면, 현재 위치 기준으로 대각선 위쪽의 map 값 + 1 을 현재 map 값에 저장합니다.

다르다면, 현재 위치 기준으로 왼쪽이나 위쪽의 map 값 중 더 큰 값을 현재 map 값에 저장합니다.

```jsx
map[i+1][j+1] = (b[i] == a[j])? map[i][j]+1: Math.max(map[i][j+1], map[i+1][j]);
```

(이때, map의 패딩을 고려하여 i+1, j+1 위치에 값을 저장합니다.)

### 결과 출력

위와 같은 2중 반복문을 수행하고 나면, map의 가장 마지막 칸에 lcs의 값이 저장되어 있습니다.

그 값을 출력합니다.

```jsx
System.out.println(map[b.length][a.length]);
```

### 전체 코드

```jsx
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9251 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] a, b;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        map = new int[b.length+1][a.length+1];

        for (int i = 0; i < b.length; i++)
        for (int j = 0; j < a.length; j++)
		        map[i+1][j+1] = (b[i] == a[j])? map[i][j]+1: Math.max(map[i][j+1], map[i+1][j]);
        

        System.out.println(map[b.length][a.length]);
    }
}
```

[result](./9251_result.jpg)

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

좋은 DP 문제였습니다.

### 고생한 점

2차원 map 생성 시 패딩에 신경써야합니다.
