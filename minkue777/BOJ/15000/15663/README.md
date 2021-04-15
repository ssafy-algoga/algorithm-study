# 15663 N과 M (9)
[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계
전체적인 설계는 수업시간에 다룬 combination 버전과 동일합니다.
중복된 케이스를 어떻게 제거하느냐가 핵심인데 isSelected 변수를 추가해서
해결하였습니다. 단순히 숫자가 중복된다고 패스하는 경우는 둘다 선택하는 경우를
지나치기 때문에 1. 이전 숫자와 지금 숫자가 같을 것 2. 이전 숫자가 지금 선택되지 않았을 것
두 가지 조건으로 중복 케이스를 체크하였습니다.
```java
if(!isSelected[i]) {
    if(i != 0 && !isSelected[i-1] && array[i] == array[i-1]) continue;
    isSelected[i] = true;
    numbers[cnt] = array[i];
    combination(cnt + 1);
    // back-tracking
    isSelected[i] = false;
}
```

## ✅ 후기
백준에 N과 M이 1번부터 12번까지 있는데 9번이 잘 풀리지 않아 
복습할 겸 아이디어 얻을 겸 1번부터 쭉 풀다보니 combination 문제이지만 
permutation에 사용한 isSelected 변수를 쓸 수 있겠다는 생각이 떠올랐습니다.
사실 9번 문제를 풀 수 있다면 1번~12번 모두 수월하게 해결할 수 있으니 다들
월요일 시험 대비용으로 풀어보기를 추천드립니다ㅎㅎ