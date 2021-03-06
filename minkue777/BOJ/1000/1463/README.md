# 1463번 1로 만들기
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계
유명한 DP 문제입니다. 점화식이 `f(n) = min{f(n-1), f(n/2), f(n/3)} + 1` 로 단순했기 때문에
DP 문제라는 것을 캐치한다면 어렵지 않게 해결할 수 있는 문제였습니다. 스터디 하시는 분들의
대부분이 Bottom-up 방식의 DP를 선호하기 때문에 Top-down 으로 구현한 코드에 대해 몇번
질문을 받았던 적이 있습니다. 그래서 이번 리드미에는
문제 자체보다는 DP에 익숙하지 않은 분들을 위해 동적계획법에 대한 간단한 설명 +
동적계획법의 두가지 방법 Top-down과 Bottom-up 방식의 장단점에 대해 써보겠습니다.

### **동적계획법(Dynamic Programming)**
   
   
동적계획법을 알고리즘이라고 말하는 경우도 있지만 사실 정확히는 문제를 해결할 수
있게 하는 알고리즘이라기보단 문제 해결 시간을 단축시켜주는 설계 테크닉에 가깝습니다.
구글에 동적계획법에 대해 검색해보면 `점화식, 메모이제이션, 과거의 구한 해를
활용하는 방법, 큰 문제를 작은 문제를 사용해 해결하는 방법` 등 여러가지 용어들이
등장합니다. 용어마다 조금씩 차이는 있지만 동적계획법에 가장 근간이 되는 두가지는

1. 재귀적으로 생각하기
2. 불필요한 계산 줄이기 
   
입니다. 이 중에서 동적계획법으로 문제를 해결하기 위해 더 중요한 것은 
`재귀적으로 생각하기` 입니다. 문제를 재귀적으로 생각하여 부분 문제(sub-problem)로
잘 분해했다면 불필요한 계산 줄이기는 누구나 할 수 있는 잡기술에 불과합니다.
   
이 문제에서 `P(n) : 숫자 n을 1로 만들기 위한 연산 횟수의 최솟값` 의 부분 문제는 

`P(n-1) : 숫자 n-1을 1로 만들기 위한 연산 횟수의 최솟값` <br>
`P(n/2) : 숫자 n/2를 1로 만들기 위한 연산 횟수의 최솟값` <br>
`P(n/3) : 숫자 n/3을 1로 만들기 위한 연산 횟수의 최솟값`
   
이 됩니다. 만약 누군가가 `P(n-1), P(n/2), P(n/3)에 대한 답을 미리 구해놨다면 어떨까요?` <br>
아직 답이 구해지지 않은 문제에 대해 이미 해결되었다고 가정하는게 이상하게 들릴 수도 있지만 <br>
**부분 문제는 이미 해결되어 있다는 믿음을 가지고 큰 문제의 해결에 집중하는 것**이 재귀적으로 <br>
생각하기의 핵심입니다. 이는 꼭 동적계획법에만 해당되는 것이 아닙니다. 재귀 호출을 필요로하는 <br>
모든 문제들을 해결할 때 항상 가지고 있어야하는 생각입니다. (교수님께서는 이 부분을 `flat하게` <br>
`생각해라`라고 이야기하십니다.) 만약 3가지의 부분 문제가 이미 해결되어있다면 이를 통해 우리는 <br>
큰 문제의 답을 `P(n) = min{P(n-1), P(n/2), P(n/3)} + 1 로 이미 해결된 부분 문제들의 답을` <br>
`이용하여 쉽게 구할 수 있습니다.` 여기까지는 Top-down 방식으로 해결하든 bottom-up 방식으로 <br>
해결하든 공통적으로 거쳐야하는 단계입니다. 

동적계획법으로 문제를 풀기 위해 한 가지 중요하게 짚고 넘어가야 하는 개념이 있습니다. <br>
**6을 1로 만들기 위한 연산 횟수의 최솟값은 이전에 어떤 경로를 통해 n이 6이 되었는지에 <br>
의존하지 않습니다**. 예를 들어 12에서 2로 나누어서 6이 되었건, 18에서 3으로 나누어 6이 <br>
되었건 간에 상관없이 6을 1로 만들기 위한 연산 횟수는 고정된 값을 가지며 변하지 않습니다. <br>
이를 `부분 최적 구조(Optimal Substructure)라고 하며 동적계획법으로 문제를 해결하기 위해`<br>
`반드시 가져야 하는 성질입니다.` 사실 거창한 용어에 비해 대부분의 문제는 1로 만들기 문제처럼 <br>
부분 최적 구조를 가지고 있다는 것이 직관적으로 자명한 경우가 많습니다.

```java 
int f(int n) {
    ...
    return min(f(n-1), f(n/2), f(n/3)) + 1;    
}
```
이런 식으로 함수를 구현한다면 이는 재귀호출을 이용한 완전탐색 알고리즘으로 문제를 
해결한 것 입니다. 이 함수의 유일한 문제점은 한번의 함수 호출이 최대 3번의 함수 호출로 
연결되며 이는 대부분 지수 시간만큼의 시간복잡도(여기선 O(3<sup>n</sup>))를 가지게 하는 
원인이 된다는 것입니다. 하지만 실제로 n이 1이 되기 위해 거쳐야 하는 숫자는 많아봐야 n-1개 이므로
비둘기집 원리에 의해 중복되는 함수 호출이 많을거라 추정할 수 있습니다. 여기서 불필요한
계산 줄이기가 필요합니다. 계산 줄이기는 두 가지 방법으로 가능한데
1. 이미 계산된 함수값을 저장해두고 다시 함수가 호출될 때 저장된 값을 읽어오는 방식
2. 가장 밑바닥의 부분 문제부터 해결하며 반복문을 통해 문제를 거슬러 올라가는 방식

1번 방식으로 문제를 해결하는 것을 Top-down 방식, 2번 방식으로 문제를 해결하는 것을 
Bottom-up 방식이라고 합니다. Top-down  방식으로 문제를 해결하기 위해 이미 계산된 
함수값을 저장해놓는 장소를 `cache(캐시) 혹은 DP테이블 이라고 부르며 이러한 행위를 
메모이제이션(memoization)이라 합니다.` 사실 동적계획법을 대표하는 용어인 메모이제이션은
Top-down 방식의 문제풀이를 지칭하는 말입니다.

```java 
int makeOne(int n) {
    // 만약 이전에 답을 구한적이 있다면 바로 그 값을 반환
    if(cache[n] != 0) return cache[n];
    // 재귀 탈출을 위한 기저 케이스
    if(n == 1) return 0;
    // 부분 문제의 정답을 통한 큰 문제의 정답 도출
    int a = makeOne(n-1);
    if(n % 2 == 0) a = Math.min(a, makeOne(n/2));
    if(n % 3 == 0) a = Math.min(a, makeOne(n/3));
    // 리턴과 동시에 캐시에 값을 저장
    return cache[n] = a+1;
}
```

Top-down 방식은 큰 문제와 부분 문제의 관계성을 그대로 코드로 옮겨놓기 때문에 좀 더 직관적인
코드를 짤 수 있다는 장점이 있습니다. 따라서 부분 문제가 잘 해결되게끔 기저 케이스를 구성하는 
것에만 집중할 수 있습니다. 하지만 반복적인 재귀호출로 인해 Bottom-up 방식에 비해 일반적으로
더 느리게 동작하며 메모리 사용량도 더 많습니다. **또한 스택 오버플로우를 향상 유의해야 합니다.**

```java 
int makeOne1(int n) {
    cache[1] = 0;
    for(int i=2; i<=n; i++) {
        int a = cache[i-1];
        if(i % 2 == 0) a = Math.min(a, cache[i/2]);
        if(i % 3 == 0) a = Math.min(a, cache[i/3]);
        cache[i] = a+1;
    }
    return cache[n];
}
```
Bottom-up 방식은 Top-down 방식에 비해 구현이 대게 더 짧습니다. 또한 재귀 호출에 필요한 부하가 없기
때문에 Top-down 방식보다 조금 더 빠르게 동작합니다. 하지만 구현이 좀 더 비직관적이고
부분 문제 간의 의존 관계를 고려해 계산되는 순서(문제가 풀리는 순서)를 고민해야 합니다.
1로 만들기 문제에서는 시작점이 명확하기 때문에 이러한 점이 단점으로 생각되지 않을 수 있지만
문제가 조금만 복잡해져도 계산 순서에 대해 고민해야 합니다.

## ✅ 후기
이번 문제를 계기로 그동안 머리속에서 돌아다니던 동적계획법을 정리하는 시간을 가질 수
있어서 좋았습니다. 코딩테스트에서 나오는 거의 모든 동적계획법 문제는 Top-down 방식,
Bottom-up 방식 두 가지 모두로 해결가능하게 출제되기 때문에 한가지 방법을 선택해서
일관되게 연습해야 시간을 아끼며 버그없는 코드를 짤 수 있습니다.