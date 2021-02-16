# 13594번 숨바꼭질(3)
[문제 보러가기](https://www.acmicpc.net/problem/13549)

## 🅰 설계
너비 우선 탐색이 익숙하지 않아서 디버깅을 돌려보면서 어떻게 작동하는지 꼼꼼히 확인했습니다.
1. 각 케이스별로 갈 수 있는 상황 쪼개기
	1-1. 순간이동은 0초에 하니까 제일 먼저 확인하기((ex)입력이 1 2일 때 +1이 먼저 계산되지 않도록)
	1-2. 각 이동이 움직일 수 있는 범위 안에 있는지 확인하기
	```java
	if(curr * 2 < len && !checked[2 * curr]){
	
	}
	
	if(curr -1 >= 0 && !checked[curr -1]){
	
	}
    if(curr + 1 < len && !checked[curr + 1]){
	
	}
	```
2. while 문으로 1을 반복하면서 동생 위치에 도달한 시간이 기록 됐다면 반복문 종료

문제를 풀기 전에 지난 주에 못 풀었던 숨바꼭질을 풀고 수정해서 접근했습니다.
기존 숨바꼭질에서는 이동 거리가 0이 되는 경우가 수빈이와 동생 위치가 같은 경우만 해당됐지만
3번째 숨바꼭질에서는 수빈이의 순간 이동에 0초가 소요됐기에 소요된 시간을 담아 놓은 배열에서 if(hasVisited[curr] != 0)조건으로 비교할 수 없었음..

접근 방법
1. 방문했는지 체크하는 boolean 형 배열 
2. 순간이동 횟수만큼 cnt해서 tmp에 담을 때 빼고 담기


**코드**
```java
public class HideAndSeek3 {
    public static int N, K;
    public static int[] hasVisited;
    public static boolean[] checked;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();//수빈 위치
        K = sc.nextInt();//동생 위치

        hasVisited = new int[100001];
        checked = new boolean[100001];
        hasVisited[N] = 0;//수빈이 시작 위치 표시하기.
		checked[N] = true;
        int seconds = 0;

        if(N != K){
            bfs();
            seconds = hasVisited[K];
        }
        System.out.println(seconds);
    }

    public static void bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        int len = hasVisited.length;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int tmp = hasVisited[curr];

            if(checked[K]) break;
            if(curr * 2 < len && !checked[2 * curr]){
                queue.offer(2 * curr);
                hasVisited[2 * curr] = tmp;
                checked[2 * curr] = true;
            }

            if(curr -1 >= 0 && !checked[curr -1]){
                queue.offer(curr - 1);
                hasVisited[curr -1] = tmp + 1;
                checked[curr - 1] = true;
                if (curr > K) {
                    continue;
                }
            }
            if(curr + 1 < len && !checked[curr + 1]){
                queue.offer(curr + 1);
                hasVisited[curr +1] = tmp + 1;
                checked[curr + 1] = true;
            }

        }
    }
}

```

**틀려서 추가한 부분**	

```java
hasVisited[N] = 0;//이걸 추가 안 했더니 출발위치를 다시 방문해서 문제가 생기는 것 같았다.
```

## ✅ 후기
1번으로 구현했지만 비효율적으로 메모리를 사용하는 것 같다.. **-> 초기값을 0이 아닌 다른 값으로 고정하고 이건 안 쓰는 게 좋을 것 같다...**


