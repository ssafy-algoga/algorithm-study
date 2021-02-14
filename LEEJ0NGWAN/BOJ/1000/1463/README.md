# 1463번: 1로 만들기

[문제 보러가기](https://www.acmicpc.net/problem/1463)

[코드 보기](./answer.java)

## 🅰 설계

### bottom up DP

배열 및 메모이제이션을 이용하여 bottom up DP를 구성했습니다

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P1463 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        for (int i=2; i<=N; i++) {
            arr[i] = arr[i-1]+1;
            if (i%2==0)
            arr[i] = Math.min(arr[i/2]+1, arr[i]);
            if (i%3==0)
            arr[i] = Math.min(arr[i/3]+1, arr[i]);
        }

        bw.write(arr[N]+"\n"); bw.flush();
        bw.close(); br.close();
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

없습니다

### 고생한 점

없습니다
