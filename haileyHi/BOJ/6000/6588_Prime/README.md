# 6588번 골드바흐
[문제 보러가기](https://www.acmicpc.net/problem/6588)

## 🅰 설계

소수 구하기는 기존에 에라토스테네스를 이용해서 풀어본 적이 있어서 어렵지 않게 범위 내의 소수를 구할 수 있었습니다.

```java
for(int i = 2; i <= 1000000; i++){
    if(checkPrime[i]) {
        for (int j = 2 * i; j <= 1000000; j += i) {
            checkPrime[j] = false;
        }
    }
}
```

탐색한 수 i가 소수라면 모든 i의 배수는 소수가 아니므로 false 처리하기.

사용자부터 입력받은 수(num)가 두 홀수의 합으로 나타낼 수 있는지 찾는 방법을 여러 개를 궁리해보았습니다...

처음에
```largeIdx, smallIdx를 두고 두 소수의 합이 num이면 출력하고
num보다 크면 largeIdx--; 
num보다 작으면 smallIdx++;
```
이렇게 짰더니 시간초과가 떠서 고민을 더 하게 됐습니다.


두 번째로 설계한 방법은
```모든 소수를 리스트에 담아서 
num 미만의 가장 큰 소수의 인덱스(largeIdx)를 구하고 num - largeIdx가 소수라면 두 소수를 출력
```
이것도 당연하게 시간초과 였습니다. ;;;

마지막은 2번을 배열 자체에서 확인하는 방법을 만들었습니다.
3이상 num/2이하의 범위에서 i를 증가시키면서
`if(checkPrime[i] && checkPrime[num - i])`가 모두 소수라면 출력하도록 했습니다.

...
야호!


## 전체 코드
```java
public static boolean[] checkPrime = new boolean[1000001];
    public static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        Arrays.fill(checkPrime, true);
        setPrime();
        while (true){
            int n = sc.nextInt();
            if(n == 0) break;
            find(n);
        }
        System.out.println(sb);
    }

    public static void setPrime(){
        for(int i = 2; i <= 1000000; i++){
            if(checkPrime[i]) {
                for (int j = 2 * i; j <= 1000000; j += i) {
                    checkPrime[j] = false;
                }
            }
        }
    }

    public static void find(int num) {

        for(int i = 3; i <= num/2; i++){
            if(checkPrime[i] && checkPrime[num - i]){
                sb.append(num).append(" = ").append(i).append(" + ").append(num - i).append("\n");
                return;
            }
        }
        sb.append("Goldbach's conjecture is wrong.\n");
        return;
    }

```
## ✅ 후기
시간을 줄일 방법을 깊이 생각해보겠습니다..