# 1051번 숨바꼭질

[문제 보러가기](https://www.acmicpc.net/problem/1697)

## 🅰 설계

### 1. 수빈이 처음 위치 queue에 저장

- N : 수빈이 위치
- K : 동생 위치
- q : 수빈이가 이동할 수 있는 위치를 담는 큐
- visit: 수빈이가 이동하며 누적되는 거리를 저장할 배열

```java
N = parse(st.nextToken()); 
K = parse(st.nextToken()); 

q.add(N); // 처음 수빈이 위치 q에 추가
visit[N] = 0; // 처음 수빈이가 있는 자리이므로 0으로 시작
```

### 2. 수빈이 이동 시작

```java
while(!q.isEmpty()){
    N = q.poll();   // 현재 수빈이 위치

    if(N==K) {
        System.out.println(visit[N]); //동생을 만나기 위해 수빈이가 움직였던 누적이동거리 출력
        break;
    } // 수빈이와 동생 만남

    int backward = N-1; //수빈이 현재위치에서 한 칸 뒤로 가기
    int forward = N+1;  //수빈이 현재위치에서 한 칸 앞으로 가기
    int warp = N*2;     //수빈이 현재위치에서 순간이동
    if(0 <= backward && visit[backward] == 0){ // 뒤로 갔을 경우 0을 벗어나지 않고 그 자리에 누적합이 없을 경우
        q.add(backward);
        visit[backward] = visit[N] + 1;
    }
    if(forward < MAX && visit[forward] == 0){ // 앞으로 갔을 경우 MAX를 벗어나지 않고 그 자리에 누적합이 없을 경우
        q.add(forward);
        visit[forward] = visit[N] + 1;
    }
    if(warp < MAX && visit[warp] == 0){ // 순간이동 했을 경우 MAX를 벗어나지 않고 그 자리에 누적합이 없을 경우
        q.add(warp);
        visit[warp] = visit[N] + 1;
    }
}
```



## ✅ 후기

- 처음 문제를 봤을 때 뒤로 갔을 때, 앞으로 갔을 때, 순간이동할 때 경우의 수를 나누어 반복문으로 각각 동생이랑 만날 때까지 돌아야하나라는 생각에서 시작했었습니다. 3가지 케이스로 돌면서 앞서 지나갔던 곳이면 가지 못하게 하기 위해 처음에 visit 배열을 boolean으로 선언해서 사용했었습니다. 생각해보니 이동거리를 누적하면 되지 않을까라는 생각에 int로 변경하여 누적이동거리를 저장하도록 하였습니다. 


