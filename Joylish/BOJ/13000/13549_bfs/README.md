# 13549번 숨바꼭질3

[문제 보러가기](https://www.acmicpc.net/problem/13549)

## 🅰 설계

### "1697번 숨바꼭질 문제 풀이에서 순간이동에 대한 우선 처리만 해주기"
### 1. 수빈이 처음 위치 queue에 저장

- N : 수빈이 위치
- K : 동생 위치
- q : 수빈이가 이동할 수 있는 위치를 담는 큐
- visit: 수빈이가 이동하며 누적되는 거리를 저장할 배열

**N이 K보다 크거나 같을 경우, 즉 수빈이가 동생보다 앞에 있을 경우 + 수빈이가 동생이랑 바로 만난 경우는 먼저 처리합니다. 수빈이가 동생보다 앞에 있을 경우에는 수빈이는 후진밖에 못하기 때문입니다. 

수빈이가 이동하면서 처음 위치에 다시 올 경우가 있습니다. visit 배열을 -1로 초기화해주지 않고 default인 0으로 했을 경우 수빈이의 초기 위치 인덱스에 0으로 들어가기 때문에 이를 다시 수행할 수 있기 때문에 -1로 초기화해주었습니다.
```java
N = parse(st.nextToken()); //수빈이 위치
K = parse(st.nextToken()); //동생 위치

if(N>=K) {
    System.out.println(N-K);
    return;
}

q.add(N);
Arrays.fill(visit, -1);
visit[N] = 0;
```

### 2. 수빈이 이동 시작
기존 숨바꼭질 문제 풀이 코드에서 세 가지 이동 중 순간이동 부분을 앞으로 꺼내왔다. 그리고 반복문을 통해 queue에 순간이동할 수 있는 위치를 모두 넣어주었다.

```java
while(!q.isEmpty()){
  N = q.poll();   // 현재 수빈이 위치

  if(N==K) {
      System.out.println(visit[N]);
      break;
  } // 수빈이와 동생 만남

  int backward = N-1; //수빈이 현재위치에서 한 칸 뒤로 가기
  int forward = N+1;  //수빈이 현재위치에서 한 칸 앞으로 가기
  int warp = N*2;     //수빈이 현재위치에서 순간이동

  // 순간이동은 0초가 걸리기 때문에 앞 뒤로 움직일때보다 항상 먼저
   if(warp < MAX && visit[warp] == -1){
        q.add(warp);
        visit[warp] = visit[N]; // 0초걸리기 때문에 현재 값
    }

    if(0 <= backward && visit[backward] == -1){
        q.add(backward);
        visit[backward] = visit[N] + 1;
    }

    if(forward < MAX && visit[forward] == -1){
        q.add(forward);
        visit[forward] = visit[N] + 1;
    }

}
```



## ✅ 후기

- 백준에서 예제 인풋 `5 17`에서 어떻게 `2`가 나오는지 바로 이해가 되지 않았습니다. `5(0) -> 4(1) -> 4*2(1) -> 4*2*2(1) -> 17(2)`로 2가 나온다는 것을 알게되었습니다.

- 순간이동이 0초 걸린다는 것이 달라진 점으로 생각이 되어 숨바꼭질 기존 코드에서 순간이동 부분에서 `visit[N] + 1`을 `visit[N]`로 수정했습니다. 1697번 숨바꼭질 코드에서 뒤로 이동, 앞으로 이동, 순간이동 순으로 조건문을 작성하였습니다. 3개의 조건문 순서를 유지하고 visit할당하는 부분만 수정하면 틀리게 됩니다. 인풋이 `1 2`일 때 앞으로 가는 조건문이 순간 이동하는 조건문보다 먼저 처리되기 때문에 queue에서 앞으로 갈 경우가 순간이동하는 경우보다 앞에 오게 되기 때문입니다.
