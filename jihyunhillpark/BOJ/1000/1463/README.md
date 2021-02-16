# 1463번 1로 만들기
   
[문제 보러가기](https://www.acmicpc.net/problem/1463)

## 🅰 설계
임의의 숫자 n을 1로 만들기 위한 연산의 최솟값은 다음의 과정을 거쳐 구할 수 있습니다.

1. **n-1의 연산 최솟값 + 1** 을 구합니다.
2. n % 2가 0(2로 나눌 수 있다면)일 경우, **n/2의 연산 최솟값 + 1** 구합니다.
3. n % 2가 0(2로 나눌 수 있다면)일 경우, **n/2의 연산 최솟값 + 1** 구합니다.

<br>
이들 중 가장 작은 값이 **n의 연산 최솟값**이 됩니다.  

연산 최솟 값을 담는 배열을 ret이라고 하고, 위의 과정을 식으로 적자면
<br>
ret[n] = min(ret[n-1], ret[n/2], ret[n/3]) (n이 3과 2로 모두 나누어 떨어질 때)
ret[n] = min(ret[n-1], ret[n/2]) (n이 3으로만 나누어 떨어질 때)
ret[n] = min(ret[n-1], ret[n/3]) (n이 2로만 나누어 떨어질 때)
ret[n] = ret[n-1] (n이 3과 2로 나누어 떨어지지 않을 때)   
<br>
이 됩니다.

##### Bottom-up방식을 사용
<br>
1부터 n까지 연산의 최솟값을 ret배열만들어 인덱스에 해당하는 element에 저장하도록 한뒤, 재사용했습니다.

```java
int n = Integer.parseInt(in.readLine());
int ret[] = new int[n+1];
int min;
// DP - bottom up
// step2 and over
for(int i = 1; i <= n ; i++) {
  // step1 - base case
  if(i == 1) ret[i] = 0;
  else if(i == 2 || i == 3) ret[i] = 1;
  else {  // step2 - inductive
    min = 1 + ret[i-1];
    if(i%3 == 0) {
      int temp = 1 + ret[i/3];
      min = (temp < min)? temp : min;
    }
    if(i%2 == 0) {
      int temp = 1 + ret[i/2];
      min = (temp < min)? temp : min;
    }
    ret[i] = min;
  }
}
// output
System.out.println(ret[n]);
```



## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점
// 고생한 점
##### 이문제를 풀면서 오랜만에(?) DP를 풀게 되었습니다. 처음에 어떻게, 왜 DP를 쓸지를 잘 보이지 않아 다른 문제들에 비해 고민을 많이 했었습니다. 문제를 접근할 때 사용할 도구(알고리즘이나 자료구조)를 왜, 어떻게 사용해야하는지 알고 접근하는 것이 중요하다는 것을 느꼈습니다.
\+ DP로 문제를 접근할 때 sub문제는 한번씩만 푼다는 것을 명심해야겠습니다. 이를 인지하고 푸는 것과 그렇지 않은 것의 차이란
