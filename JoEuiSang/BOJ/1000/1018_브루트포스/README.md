# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계

<img src="https://user-images.githubusercontent.com/69133236/107893941-471cf600-6f71-11eb-8401-c2c8e9354ed7.png" height="400">

**전체맵 최대 50X50, 체스판 8X8 을 계산해보면 대략 10만정도의 크기가 나오기 때문에 완전탐색으로 접근하였다.**



## 💬주요 코드
### white, black 부터 시작할 때 바꿔야할 횟수, 정답 변수
```java
static int ansW, ansB, answer = Integer.MAX_VALUE;
```

### 8*8 크기를 완전탐색하여 답을 얻어내는 함수
```java
private static int check(int partR, int partC) {
    int wb = 0; //계속 반전될 수
    ansW = 0;	//white 시작할때 다시 칠 할 횟수 카운트
    ansB = 0;	//black 시작할때 다시 칠 할 횟수 카운트
    
    //체스판 크기 만큼만
    for (int r = partR; r < partR + 8; r++) {
        for (int c = partC; c < partC + 8; c++) {
            if (map[r][c] != wb)	//white 시작일 때 기준 값과 다르면
                ansW++;
            else {					//black 시작일 때 기준 값과 다르면
                ansB++;
            }
            wb = wb ^ 1;			//값 반전
        }
        wb = wb ^ 1;				//줄바꿈 값 반전
    }
    return Math.min(ansW, ansB);	//작은값 리턴
}
```


## ✅ 후기
### 큰 어려움은 없었고, 생각보다 간단하게 풀었다. 조금씩 성장하는 느낌을 받는다랄까... 이런 느낌 자주 받고 싶다