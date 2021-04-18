# 16562 친구비
[문제 보러가기](https://www.acmicpc.net/problem/16562)

## 🅰 설계

1. 친구그룹에 속한 어느 한 명과 친구가 되면 해당 그룹의 모든 학생과 모두 친구가 될 수 있습니다.
2. 하지만 친구가 되기 위해서는 친구비용을 지불해야하고 각 학생마다 친구 비용을 가지고 있습니다.
3. 그래서 친구 그룹을 합치는 union 과정에서 친구비용이 더 작은 학생을 대표자로 설정하였습니다.
4. 대표자를 구분해줘야 하기때문에 그룹의 기본값을 음수값으로 설정하여 대표자들이 음수값을 갖습니다.
5. 모든 학생을 그룹화 한 뒤 각 그룹의 대표자의 친구비용을 더하여 정답을 도출하였습니다.




## 주요 코드 설명
### 변수
```java
N = Integer.parseInt(st.nextToken()); // 학생 수
M = Integer.parseInt(st.nextToken()); // 친구관계 수 (간선 수)
K = Integer.parseInt(st.nextToken()); // 가진 돈
friendGroup = new int[N + 1]; // 친구 그룹을 나타낸다.
money = new int[N + 1]; // 친구비용
```



### 친구비 셋팅

```java
// 친구비 셋팅
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}
```



### make() : 단위집합을 생성한다

```java
private static void make() {
    for (int i = 0; i <= N; i++) {
        friendGroup[i] = -1;
    }
}
```

### findRoot() : 대표자를 찾아 반환한다

```java
static int findRoot(int s) {
    if (friendGroup[s] < 0)
        return s;

    return friendGroup[s] = findRoot(friendGroup[s]);
}
```

### union() : 학생의 친구그룹을 합친다.

```java
static boolean union(int s1, int s2) {
    int s1Root = findRoot(s1);
    int s2Root = findRoot(s2);

    if (s1Root == s2Root) // 둘이 친구 그룹이 같으면
        return false;

    int small = 0;
    int big = 0;
    if (money[s1Root] < money[s2Root]) {
        small = s1Root;
        big = s2Root;
    } else {
        small = s2Root;
        big = s1Root;
    }

    friendGroup[big] = small;	//친구비가 큰놈의 대장을 친구비가 작은학생으로 바꿔준다
    return true;
}
```

### 전체코드

```java
package 스터디._3월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16562친구비 {
	static int N, M, K;
	static int[] friendGroup, money;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 친구관계 수 (간선 수)
		K = Integer.parseInt(st.nextToken()); // 가진 돈
		friendGroup = new int[N + 1]; // 친구 그룹을 나타낸다.
		money = new int[N + 1]; // 친구비 용

		// 친구비 셋팅
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}

		// 친구 그룹 작업셋팅
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}

		int price = 0;

		for (int i = 1; i <= N; i++) {
			if (friendGroup[i] < 0) {
				price += money[i];
				if (price > K) {
					System.out.println("Oh no");
					return;
				}
			}
		}
		System.out.println(price);
	}

	private static void make() {
		for (int i = 0; i <= N; i++) {
			friendGroup[i] = -1;
		}
	}

	static boolean union(int s1, int s2) {
		int s1Root = findRoot(s1);
		int s2Root = findRoot(s2);

		if (s1Root == s2Root) // 둘이 친구 그룹이 같으면
			return false;

		int small = 0;
		int big = 0;
		if (money[s1Root] < money[s2Root]) {
			small = s1Root;
			big = s2Root;
		} else {
			small = s2Root;
			big = s1Root;
		}

		friendGroup[big] = small;	//친구비가 큰놈의 대장을 친구비가 작은학생으로 바꿔준다
		return true;
	}

	static int findRoot(int s) {
		if (friendGroup[s] < 0)
			return s;

		return friendGroup[s] = findRoot(friendGroup[s]);
	}
}

```



## ✅ 후기

#### 어려웠던 점 : 없었습니다.

#### 느낀점 : 배우고 나니 정말 쉽다는 생각이 들었고, 풀이법을 까먹지 않도록 복습해야겠습니다.