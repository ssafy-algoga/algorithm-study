# 15663 N과M(9)
[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계
<img src="https://user-images.githubusercontent.com/69133236/107168329-978ad580-69fe-11eb-9f99-c88e3da1919e.jpg" height=500>
주어진 수 M개 만큼 뽑아 만든 수열을 만드는 것,
순서가 의미가 있기때문에 수열로 접근하였다.

주의해야할 점은 중복된 수가 있을경우 다른 자리에서는 사용해도 되지만 수열의 중복을 막기위해, 같은자리에서는 사용할 수 없다는 점이다.


## 주요 코드 설명
### 들어온 수열의 값을 카운팅하는 배열을 생성하여 접근하도록 하였다.
```java
static int[] numCnt;
```

### 수열의 카운팅 수에 따라 유도부분 설정
```java
private static void permutation(int cnt, String answer) {
		if (cnt == M) {
			sb.append(answer+"\n");
			return;
		}
		
        // 들어온 수열중 최대 값 까지만 반복, 값을 인덱스로 사용한다.
		for (int i = 1; i <= max; i++) {
			if (numCnt[i] == 0)
				continue;
			
			numCnt[i]--;
			permutation(cnt + 1, answer + i + " ");
			numCnt[i]++;
		}
	}
```


## ✅ 후기
### 처음 풀었을땐 상당히 어렵게 생각하여, 전체 자리에서 Sel체크 + 현재 자리에서 Sel체크를 동시에 하여 중복사용 체크를 하였다.
### 수업 시간에 배운 Flat한 사고를 제대로 하지 못하여 발생한 일 같다. 좀더 Flat하게 재귀함수를 구현하는 연습을 해야겠다...