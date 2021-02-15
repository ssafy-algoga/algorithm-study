# 1463번 1로 만들기
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계

<img src="https://user-images.githubusercontent.com/69133236/107942141-2e8ef900-6fce-11eb-8d4d-aa87f34915e6.png" height="400">

**bottom up 방식으로 접근하여, 앞의 연산 계산 수 보다 +1 해준 뒤 2로 나누어 떨어지면 비교, 3으로 나누어 떨어지면 비교하여 작은값을 넣어주도록 하였다. (요런 방식은 어떤 알고리즘 종류인지 아시는분 계실까요??)**



## 💬주요 코드
### 연산 수를 구하는 반복문
```java
for (int i = 2; i < N + 1; i++) {
			arr[i] = arr[i - 1] + 1;	//현재는 앞에꺼 + 1번
			if (i % 2 == 0)				//현재가 2로 나눠지면
				arr[i] = Math.min(arr[i], arr[i / 2] + 1);	//현재와  (나누기2)+1 중 작은값 대입 
			if (i % 3 == 0)				//3으로 나눠지면
				arr[i] = Math.min(arr[i], arr[i / 3] + 1);	//작은값 대입
		}
```



### 전체 코드

```java
import java.util.Scanner;

public class _1로만들기_bottomUp {
	static int N;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N + 1];

		//0과 1값 0셋팅
		arr[0] = 0;
		arr[1] = 0;

		//2~N까지 반복
		for (int i = 2; i < N + 1; i++) {
			arr[i] = arr[i - 1] + 1;	//현재는 앞에꺼 + 1번
			if (i % 2 == 0)				//현재가 2로 나눠지면
				arr[i] = Math.min(arr[i], arr[i / 2] + 1);	//현재와  (나누기2)+1 중 작은값 대입 
			if (i % 3 == 0)				//3으로 나눠지면
				arr[i] = Math.min(arr[i], arr[i / 3] + 1);	//작은값 대입
		}
		System.out.println(arr[N]);
	}
}

```



### 처음 BFS로 구현한 코드

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _1로만들기 {
	static int N, answer;
	static class Pair {
		int value;
		int depth;

		public Pair(int value, int depth) {
			super();
			this.value = value;
			this.depth = depth;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		bfs(N);
		System.out.println(answer);
	}

	private static void bfs(int N) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(N, 0));

		Pair p;
		int now = 0;
		int depth = 0;
		while (!q.isEmpty()) {
			p = q.poll();
			now = p.value;
			depth = p.depth;

			if (now == 1) {
				answer = depth;
				break;
			}

			if (now % 3 == 0)
				q.offer(new Pair(now / 3, depth + 1));

			if (now % 2 == 0)
				q.offer(new Pair(now / 2, depth + 1));

			if (now - 1 >= 1)
				q.offer(new Pair(now - 1, depth + 1));
		}
	}
}

```

**연산의 횟수인 depth를 기록하기 위해 클래스를 선언하다보니 공간복잡도 커지는 현상이 발생하였다.**



## ✅ 후기

### 정답을 맞출수는 있지만, 접근 방식에 따라 메모리, 실행 시간에서의 큰 차이가 나기때문에, 문제를 풀기에 앞서 항상 분석을 철저히 한 뒤에 시작해야 한다는 것을 다시한번 느꼈다.