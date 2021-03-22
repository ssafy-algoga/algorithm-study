# 12015번 가장 긴 증가하는 부분 수열 2
[문제 보러가기](https://www.acmicpc.net/problem/12015)

## 🅰 설계
### 시작은 완전탐색
완전탐색을 하기 위해서 일단 문제의 재귀적인 속성부터 파악해야합니다.
수열 `[10, 20, 10, 30, 20, 50]` 에서 첫번째 원소 `10` 을 골랐다고 가정합니다.
그렇다면 그 이후에 올 수 있는 원소들은 10보다 큰 원소들이기 때문에 그 원소들로 이루어진 수열
`[20, 30, 20, 50]` 에서 동일한 로직을 반복해서 수행합니다. 가장 알기 쉬운 방법은
함수 `lis()` 를 `인자로 들어온 리스트에서 만들 수 있는 가장 긴 부분 증가 수열의 길이` 로 정의하는 것
입니다. 이 함수의 대략적인 느낌은 다음과 같습니다.

```java 
int lis(List<Integer> sequence) {
    if(sequence.isEmpty()) return 0;
    int ret = 0;
    for(int i=0; i<size; i++) {
        List<Integer> subsequence = new ArrayList<>()
        for(int j=i+1; j<size; j++) {
            if(sequence[i] < sequence[j]) {
                subsequence.add(sequence[j]);
            }
        }
        ret = Math.max(ret, 1 + list(subsequence))
    }
    return ret
}
```

여기서 동일한 subsequence가 함수의 인자로 여러번 들어갈 수 있기 때문에 메모이제이션을 적용하고 싶지만
함수의 인자가 List이기 때문에 메모이제이션을 적용하기 영 까다롭습니다. 이런 경우에 부분수열을
대응하는 정수값으로 바꾸어 함수의 인자로 넘겨주는 경우가 많습니다. 함수의 정의를 다음과 같이 바꿔봅시다.
`lis(start) = sequence[start]에서 시작하는 부분 증가 수열 중 가장 긴 증가 수열의 길이`
이러면 메모이제이션을 적용하기 수월해집니다. 

```java 
int lis(int start) {
    if(cache[start] != -1) return cache[start];
    int ret = 1;
    for(int next = start+1; next < size; next++) {
        if(sequence[start] < sequence[next]) {
            ret = Math.max(ret, lis(next) + 1);
        }
    }
}
```

여기까지가 `정석적인 절차를 밟은 동적계획법 풀이`입니다. 알고리즘 설계에 정석적인 절차가 어디있냐고
말할 수도 있지만 사실 알고리즘 설계의 대부분은 퍼뜩이는 영감보다는 여러 전략적인 선택에 따라
좌우되는 경우가 많습니다. 예를 들어 최적화 문제를 동적계획법을 이용하여 풀때는 
대부분 다음과 같은 절차를 따르게 됩니다.
1. 모든 경우를 탐색하고 그 중에 최적해를 반환하는 완전탐색 알고리즘을 설계합니다.
2. 전체 답의 점수를 반환하는 것이 아니라, <b>앞으로 남은 선택들에 해당하는 점수만을
반환하도록 부분 문제 정의를 바꿉니다.</b>
3. 재귀 호출 입력 이전의 선택에 관련된 정보가 있다면 꼭 필요한 것만 남기고 줄입니다.
만약 optimal substructure가 성립한다면 이전 정보를 완전히 없앨 수도 있습니다.
4. 입력이 배열이라면 적절한 정수값으로 변환하여 메모이제이션이 가능하도록 합니다.
5. 메모이제이션을 적용합니다.

위에 제시한 풀이는 이 1-5의 과정을 완벽하게 따랐을 때 필연적으로 도달하게 되는 풀이입니다.
그리고 2주 전에 풀었던 외판원 순회 문제도 마찬가지입니다. 1-5의 절차를 밟는다면 필연적으로
저와 규태님이 풀었던 풀이에 도달하게 됩니다. 대부분의 문제는 여기서 문제가 해결되지만
LIS 문제의 경우 위의 `O(n<sup>2</sup>)` 풀이를 `O(n*logn)`으로 줄일 수 있는 방법이 있습니다.

### O(N * logN) 풀이
이 풀이는 부분 문제의 정의를 다음과 같이 바꾸면서 시작합니다.
`lis(end) = sequence[end]로 끝나는 부분 증가 수열 중 가장 긴 증가 수열의 길이`
이 값을 이전에 해결한 부분 문제를 이용하여 구하기 위해서는 `sequence[end]보다 작은 값으로 끝나는
증가하는 부분 수열 중 최장 길이 부분 수열`을 찾고 그 값에 1을 더해주기만 하면 됩니다.
예를 들어 `[2, 5, 3, 4, 1, 5]`의 수열에서 `5로 끝나는 부분 수열 중 가장 긴 증가 수열의 길이`
를 찾고 싶다면 `5보다 작은 값으로 끝나는 증가 수열 중 가장 긴 증가 수열인 [2, 3, 4]`를 찾고
이 수열의 길이에 1을 더해주는 식입니다. 이 함수의 대략적인 느낌은 다음과 같습니다.

```java 
static int lis(int end) {
    if(cache[end] != -1) return cache[end];
    int ret = 1;
    for(int prev=0; prev<end; prev++) {
        if(sequence[prev] < sequence[end]) {
            ret = Math.max(ret, lis(prev)+1);
        }
    }
    return cache[end] = ret;
}
```

여기까지만 본다면 처음 풀이와 마찬가지로 O(N<sup>2</sup>) 풀이입니다만 이 풀이에서는
함수 내부의 for문을 한번 더 최적화시킬 방법이 있습니다. for문을 `0부터 end-1`까지 순회하는 이유는
`sequence[end]보다 작은 값으로 끝나는 부분 수열 중 최장 길이를 갖는 부분 수열의 길이`를
찾기 위해서 였습니다. 이 풀이의 핵심 아이디어는 만약 `길이 i인 증가 부분 수열 중 마지막 값의 최소값`을 
따로 저장해 둔다면 for문을 전부 돌지 않고도 이진 탐색을 통해 빠르게 구할 수 있다는 생각입니다.
`[2, 5, 3, 4, 1, 5]`의 수열에서 `5로 끝나는 부분 수열 중 가장 긴 증가 수열의 길이` 예를 다시 보면
`[2, 5, 3, 5, 1]` 로 만들 수 있는 부분 증가 수열 중에서 길이가 1인 부분 증가 수열은 
`[2], [5], [3], [5], [1]` 5가지가 있는데 그 중 1이 최소입니다. 이를 `C[1] = 1`으로 저장해둡니다.
길이가 2인 부분 증가 수열은 `[2, 3], [2, 4], [2, 5], [3, 4], [3, 5], [4, 5], [1, 5]`가 있는데
이 중 마지막 값의 최소값은 3입니다. 따라서 `C[2] = 3`으로 저장해둡니다. 마찬가지 방법으로
`C[3]`을 구해보면 최소값은 4가 나옵니다. 5는 4 뒤에 붙일 수 있고 4로 끝나는 부분 증가 수열의 최장 길이가
3이므로 `5로 끝나는 최장 증가 부분 수열의 길이`는 4가 됩니다. 이때 배열 C는 필연적으로
증가 수열이 되기 때문에 이진 탐색을 통해 5가 들어가야할 위치를 빠르게 찾을 수 있습니다.

```java 
import java.io.*;
import java.util.*;

public class Main {
    static int[] longest;
    static final int INF = 987654321;
    
    // A[i-1] < target <= A[i] 인 i를 반환하는 이분 탐색 메소드
    static int binarySearch(int[] array, int high, int target) {
        int low = -1;
        while(low+1 < high) {
            int mid = (low + high)/2;
            if(array[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        longest = new int[size+1];
        Arrays.fill(longest, INF);
        longest[0] = -INF;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int low = -1;
        int high = 0;
        for(int i=0; i<size; i++) {
            int input = Integer.parseInt(st.nextToken());
            int idx = binarySearch(longest, high+1, input);
            if(longest[idx] == input) continue;
            longest[idx] = input;
            high = Math.max(high, idx);
        }
        System.out.println(high);
    }
}
```

## ✅ 후기
개인적으로 대회 목적이 아닌 코딩테스트만을 준비한다면 최적화된 풀이 방법은 몰라도
O(N<sup>2</sup>) 풀이만 구현할 수 있으면 충분하다고 생각합니다.