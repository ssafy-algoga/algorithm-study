# 15663번 N과 M (9)
[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계
백트래킹을 이용해서 N개의 수에서 M개를 뽑아 순열을 구하는 문제입니다.

추가적으로 중복된 형태의 순열을 제외하고 순열을 사전순으로 출력해야합니다.

---
### 1. 선언부
```java
static int N, M;
static int[] sub; // 뽑은 수열
static boolean[] isSelected; // 수의 사용 여부
static int[] nSet; // N개의 수의 집합
static LinkedHashSet<String> sqSet = new LinkedHashSet<>(); // 입력 순서대로 수열 저장
```
boolean 배열로 수의 사용 여부를 확인하여 백트래킹을 하였고

중복된 수열을 제외하여 저장하기 위해 HashSet을 사용하였습니다.

단, 사전순으로 수열을 저장하고 읽어오기 위해 순서를 기억하는 LinkedHashSet을 사용했습니다.

---

### 2. 메인 함수
```java
//입력...

// 사전 순 수열 탐색을 위해서 오름차순 정렬 
Arrays.sort(nSet);
// 모든 수열 뽑기
permutation(0);

//출력
String[] arr  = sqSet.toArray(new String[0]);
for(String s : arr)
	System.out.println(s);
```
사전순으로 수열을 만들고 저장하기 위해

순열을 구하기 전에 오름차순으로 수를 정렬합니다.

---

### 3. 순열 구하기
```java
//nPm : 모든 수열 경우의 수
	static void permutation(int idx) {
		// 기저조건
		if(idx==M) { 
			sqSet.add(arrayToString(sub)); // Set에 저장해 중복 제거
			return;
		}
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;

			sub[idx] = nSet[i];
			isSelected[i] = true;
			
			permutation(idx+1);
			isSelected[i] = false;
		}
	}
```

재귀방식으로 수열을 완성하고 String으로 전환하여 Set에 저장했습니다.

아래는 수열을 문자열로 바꾸어 리턴해주는 arrayToString함수입니다.

```java
//수열을 문자열로 변환하는 메소드
	static String arrayToString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i: arr) sb.append(i).append(" ");
		return sb.deleteCharAt(sb.lastIndexOf(" ")).toString();
	}
```

---

### 4.  전체 코드


```java
package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class boj_15663_back {
	static int N, M;
	static int[] sub; // 뽑은 수열
	static boolean[] isSelected; // 수의 사용 여부
	static int[] nSet; // N개의 수의 집합
	static LinkedHashSet<String> sqSet = new LinkedHashSet<>(); // 입력 순서대로 수열 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nSet = new int[N];
		sub = new int[M];
		isSelected = new boolean[N];
		// 수의 집합 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nSet[i] = Integer.parseInt(st.nextToken());
		}
		// 사전 순 수열 탐색을 위해서 오름차순 정렬 
		Arrays.sort(nSet);
		// 모든 수열 뽑기
		permutation(0);
		//출력
		String[] arr  = sqSet.toArray(new String[0]);
		for(String s : arr)
			System.out.println(s);
	}
	
	//nPm : 모든 수열 경우의 수
	static void permutation(int idx) {
		// 기저조건
		if(idx==M) { 
			sqSet.add(arrayToString(sub)); // Set에 저장해 중복 제거
			return;
		}
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;

			sub[idx] = nSet[i];
			isSelected[i] = true;
			
			permutation(idx+1);
			isSelected[i] = false;
		}
	}
	//수열을 문자열로 변환
	static String arrayToString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i: arr) sb.append(i).append(" ");
		return sb.deleteCharAt(sb.lastIndexOf(" ")).toString();
	}
}

```
## ✅ 후기

어제 코드의 99%를 작성했지만 사전식 출력에서 막혀서 하루를 넘겼습니다.

구글링을 통해 중복을 제한하고 데이터 순서를 유지하는 LinkedHashSet를 알게 되었고,

순열을 구하기 전에 정렬을 하고 HashSet을 LinkedHashSet으로 바꾸는 것으로

채워진 1%가 이 문제의 핵심이 아닐까 생각합니다.

저는 재귀랑 Set을 사용해서 오버헤드가 큰데 최적화가 된 다른 분들의 풀이가 기대됩니다.


