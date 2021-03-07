# 14594번 동방프로젝트 small
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계
이 문제는 푸신 분들마다 풀이법이 다양할 것으로 생각합니다.

### 동방 개수만큼의 크기를 가지는 배열을 만들어 방번호를 저장했습니다.

```java
int[] clubs = new int[N]; //동아리 방 번호를 담을 배열입니다.
for(int i = 0; i < N; i++)
  clubs[i] = i+1;
```

### 부술 구간에 따라 동아리 방번호를 다시 메겼습니다.
입력으로 부술 구간이 주어집니다. 이 때 구간을 시작하는 방번호로 구간에 있는 모든 방까지 합쳐지는 것이므로 시작하는 방의 번호를 저장합니다.<br><br>그런 다음 시작범위에서 끝에 이르는 범위까지 모든 방의 범위를 시작하는 방의 번호로 메꿉니다.(모든 방 통합하는 과정)<br><br>그런데 구간 끝에 있는 **마지막 방이 이미 뒷 방과 합쳐져 있는 경우**가 존재합니다. 이에 이전에 합쳐진 방들도 다 통합될 수 있도록 추가적으로 메꿔주도록 했습니다.

```java
for(int i = 0 ; i < M;i++) {
  st = new StringTokenizer(in.readLine()," ");
  from = Integer.parseInt(st.nextToken()); //부술 동아리 구간 처음 부분
  to = Integer.parseInt(st.nextToken()); //부술 동아리 구간 끝 부분
  fill = clubs[from-1]; // fill = 첫번 쨰로 시작되는 방으로 통합 되는 것임. 채울 방 번호를 담음  
  intgrt = clubs[to-1]; // intgrt = 부술 동아리 구간의 끝 방 번호로, 뒤에 통합되었던 방들까지 바꿔줘야 함.
  Arrays.fill(clubs, from, to, fill); //입력받은 구간 끝 까지 방을 통합한다.
  for(int j = to; j < N ;j++) { // 구간 끝에 해당하는 방 역시 전에 뒷방들과 통합되었다면 같이 통합해줘야 한다.
    if( clubs[j] == intgrt ) clubs[j] = fill;
    else break;
  }
}
```

### 3. 모든 작업이 끝난 최종 동아리방 배열을 순회하며, 방의 갯수를 구합니다.

```java
for(int i = 1; i < N; i++)
  if(clubs[i] != clubs[i-1]) cnt++; //방번호가 달라지면 방 갯수를 +1하며 동아리 방의 개수를 구한다.
System.out.println(cnt);
```

## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점
// 고생한 점
#### 주어진 구간에 포함된 방의 통합된 방들까지 방번호를 바꾸어 주는 것을 놓쳐서 통과하지 못 했습니다!
