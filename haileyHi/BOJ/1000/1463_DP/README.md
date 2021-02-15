# 1463번 1로 만들기
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계
bfs로 갈 수 있는 가짓수마다 체크해서 N부터 1이 될 때까지 연산하도록 했습니다.

1. 3으로 나눌 수 있으면 3으로 나누고
2. 2로 나눌 수 있으면 2로 나누고
3. 아니면 1 빼기

각 수에 이르는 최소 횟수를 담기 위해 
```java
range[새로 만들 수 있는 수] = Math.min( 현재 수까지의 횟수 + 1, range[새로 만들 수 있는 수]);
```

반복문은 1이 되는 연산 횟수가 초기값인 N이 아닐 때 종료.

**코드**
```java
public class BOJ_Making1_1463 {
    public static int[] range;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        range = new int[N + 1];//여기부터 1까지 내려가기
        Arrays.fill(range, N);
        range[N] = 0;

        search();
        System.out.println(range[1]);
    }

    public static void search() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);

        while (!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int tmp = range[cur];

            if (cur % 3 == 0 && cur / 3 > 0) {
                deque.offer(cur / 3);
                range[cur / 3] = Math.min(tmp + 1, range[cur / 3]);
            }
            if (cur % 2 == 0 && cur / 2 > 0) {
                deque.offer(cur / 2);
                range[cur / 2] = Math.min(tmp + 1, range[cur / 2]);
            }
            if (cur - 1 >= 0) {
                deque.offer(cur - 1);
                range[cur - 1] = Math.min(tmp + 1, range[cur - 1]);
            }
            if (range[1] != N) break;
        }

    }
}

```


## ✅ 후기
최소값을 담고 싶으니 비교해서 더 최소인 값을 저장할 수 있도록 초기값은 0이 아닌 N으로 줄 것.

어설프지만 bfs 재밌는 것 같다ㅎㅎ
