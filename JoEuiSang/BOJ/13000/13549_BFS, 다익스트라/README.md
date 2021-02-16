# 13549 숨바꼭질

[문제 보러가기] (https://www.acmicpc.net/problem/13549)

## 🅰 설계

<img src="https://user-images.githubusercontent.com/69133236/107882232-845e9500-6f2b-11eb-9ed0-ad1318eb8162.png" height=500>

처음 시도했을때는 지난주에 풀었던 숨바꼭질1과 순간이동에서 걸리는 시간의 차이만 있을 뿐 똑같은 문제일거라고 쉽게 생각하였다.

하지만 결과는 처참했으며 그 후 여러번 고민과 수정을 반복하였지만 정답에 접근하지 못하였다.

<img src="https://user-images.githubusercontent.com/69133236/107882189-49f4f800-6f2b-11eb-9121-dd04e80d7af9.png" height=200>

문제해결에서 중요하게 생각한 부분은, 걷기보다 순간이동을 먼저 처리해야한다는 것이었고, 어떻게 해아할지 정말 여러가지 쓸데없는 시도를 해보았고,, 그 흔적은 https://www.acmicpc.net/status?user_id=ourjes&problem_id=13549&from_mine=1 에 있으니 궁금하신 분들은 구경해도 좋습니다...




## 주요 코드 설명
### 해당 위치까지 이동시간을 기록한 배열.
```java
time = new int[100001];
```

### bfs 부분 (이 부분은 좀 의문이 든다.)
```java
private static void move(int N) {
		queue.offer(N); //시작위치 입력
		time[N] = 0;	//시작위치 시간 셋팅
		while (!queue.isEmpty()) {
			int X = queue.poll();

			if (X == K)
				break;

            //순간이동
			if (X * 2 <= 100000 && time[X * 2] == -1) {
				queue.offer(X * 2);
				time[X * 2] = time[X];
			}
            //뒤로걷기
			if (X - 1 >= 0 && time[X - 1] == -1) {
				queue.offer(X - 1);
				time[X - 1] = time[X] + 1;
			}
            //앞으로 걷기
			if (X + 1 <= 100000 && time[X + 1] == -1) {
				queue.offer(X + 1);
				time[X + 1] = time[X] + 1;
			}

		}
	}
```

**위의 오답들를 보면 알겠지만, 여러번의 시도끝에 성공을 했습니다. 사실 순간이동의 우선적인 처리를 단순히 순간이동 조건문을 위로 올려주는 것만으로 완벽하게 처리가 될거같다는 생각은 들지 않아서, 검색을 해본 결과 우선순위 큐를 사용하여 가중치 값에 따른 삽입 제어를 해야 완벽한 풀이가 된다는 내용을 보았습니다. 아직 우선순위큐는 잘모르지만 수업시간에 열심히 배워 꼭 스스로 판단하고 문제에 활용할 수 있도록 열심히 하겠습니다!!**



## ✅ 후기

### 이 문제는 저에게는 과분했던 문제인 것 같습니다. 하지만 조만간 쉽게 해결 할 날이 올 거라 믿습니다.
### BFS에도 여러가지 자료구조를 활용해야한다는 사실을 알 수 있었습니다.