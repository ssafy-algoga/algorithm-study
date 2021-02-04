# 15663번 N과 M(9)
[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계
N개의 자연수 중에서 M개를 고른 수열을 사전 순으로 출력하는 문제입니다.

수업 때 배운 Permutation 골격과 비슷하게 설계했습니다. 자연수를 저장한 배열은 정렬해서 사용합니다.   
다만 수업 때와 달리 N개의 자연수 중 중복이 있을 수 있기 때문에 이 중복을 해결하는 것이 문제였습니다.

제가 생각한 핵심은 함수에서 숫자 하나를 고를 때는 중복을 허용하지 않고,   
그 다음에 재귀로 함수를 불러 숫자를 고를 때는 중복을 허용해야 한다는 거였습니다.

isSelected[] 변수를 사용해서 중복을 피하는 방법을 생각했었는데 생각이 잘 정리가 안 되어서   
Set을 도입했습니다.

N개의 자연수를 저장한 numbers 배열과, 이들의 사용 여부를 저장할 isSelected 배열을 두고,   
숫자를 선택할 때는 해당 시점에서 선택되지 않은 numbers 배열의 자연수들을 Set에 저장해 중복을 제거한 것들 중에서 선택하고   
```jsx
HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			set.add(numbers[i]);
		}
```
선택 후에는 다시 numbers 배열의 인덱스를 찾아 동일한 인덱스를 사용하는 isSelected의 값을 true로 바꿔주는 식입니다.
```jsx
if(numbers[j]==num && isSelected[j]==false) {
					p[cnt] = num;
					isSelected[j] = true;
					
					permutation1(cnt+1);
					isSelected[j] = false;
					break;
				}
```

### 코드
```jsx
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_15663 {
	
	static int[] numbers;
	static int N;
	static int M;
	static int[] p;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		p = new int[M];
		isSelected = new boolean[N];
		
		StringTokenizer s = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(s.nextToken());
		}
		
		Arrays.sort(numbers);
		
		permutation1(0);
		
		System.out.println(sb.toString());
	}
	
	public static void permutation(int cnt) {
		
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				sb.append(p[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			// 지금 확인하려는 숫자가 이전 숫자랑 같고 이전 숫자가 선택된 적 없다면
			//  -> 앞의 숫자가 선택되지 않았는데 뒤의 숫자가 선택된다는 상황은 있을 수 없으므로 중복이 발생한 것
			if(i!=0 && !isSelected[i-1]  && numbers[i-1]==numbers[i]) continue;
			
			p[cnt] = numbers[i];
			isSelected[i] = true;
			
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	public static void permutation1(int cnt) {
		// 기저 조건
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				sb.append(p[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
	
		// 중복 제거를 위한 Set 자료 구조
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			set.add(numbers[i]);
		}
		
		// Set에 있는 자연수들에 대해
		for (int num : set) {
			for (int j = 0; j < N; j++) {
				if(numbers[j]==num && isSelected[j]==false) {
					p[cnt] = num;
					isSelected[j] = true;
					
					permutation1(cnt+1);
					isSelected[j] = false;
					break;
				}
			}

		}
	}
	
}

```

## ✅ 후기
#### 새롭게 알게되거나 공유해서 알게된 점
#### 고생한 점
Set을 사용해서 풀긴 했는데 isSelected 만 사용하는 방법도 미련이 생겨서 뭘 놓쳤나 생각해봤는데
```jsx
if(i!=0 && !isSelected[i-1]  && numbers[i-1]==numbers[i]) continue;
```
여기서 ```!isSelected[i-1]``` < 요 조건을 놓쳤었습니다.

1 7 9 9 라는 numbers가 있을 때, 함수에서 숫자를 하나씩 선택하므로 9가 중복되는 것을 피하고 싶다면 isSelected가 모두 false인 상태에서 생각했어야 했는데,   
뒤의 9를 선택하려고 할 때 앞의 9가 이미 선택이 되었다면 선택하면 안 되구... 이런 식으로 생각할 때 이게 배열의 상태랑 생각이랑 얽혀서 잔뜩 헷갈렸습니다.

결국 수열의 n번째 숫자를 선택할 때 같은 숫자는 선택하면 안 되는 건데, 그 떄 isSelected 변수는 그 같은 숫자에 대해 다 false일 것이고 (그러니까 아무 조치 안 하면 중복이 발생)   
반복문에 의해 앞에서부터 순서대로 숫자를 선택하므로 같은 값의 숫자라면 인덱스가 앞선 숫자가 무조건 선택되기 때문에 인덱스 순으로 뒤에 있는 숫자가 값이 같은 앞선 숫자가 선택되지 않았는데 선택되는 상황은 발생하면 안 된다. -> 가 ```!isSelected[i-1]``` 조건이 됩니다. 요렇게 정리했습니다.

코드에는 두 버전 다 넣었어요 아까워서ㅎ..
