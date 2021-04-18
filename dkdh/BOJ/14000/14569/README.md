# 14569번 시간표짜기
[문제 보러가기](https://www.acmicpc.net/problem/14569)

## 🅰 설계
수업 시간들을 저장하고, 학생들의 빈 시간을 저장하고,   
각 학생마다 빈 시간에 들어가는 수업 시간이 몇 개인지 세는 문제입니다.

수업 시간과 학생들의 빈 시간을 저장할 때 비트마스킹을 이용했습니다.   
빈 시간은 0, 수업이 있는 시간은 1로 두고 50교시까지 있기 때문에 long 자료형을 이용했습니다.

이렇게 저장하고 각 학생마다 빈 시간에 수업 시간이 들어가는지 & 연산자로 계산해   
0이면 들어가는 거라서 들을 수 있는 수업 +1 해주고 아니면 넘어가는 식으로 구현했습니다.

### 코드
전체코드입니다.
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		long[] classes = new long[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				long time = 1;
				time <<= Integer.parseInt(st.nextToken());
				classes[i] |= time;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		long[] students = new long[M];
		Arrays.fill(students, Long.MAX_VALUE);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			for (int j = 0; j < p; j++) {
				long time = 1;
				time <<= Integer.parseInt(st.nextToken());
				students[i] ^= time;
			}
		}
		
		for (int i = 0; i < M; i++) {
			int possible = 0;
			for (int j = 0; j < N; j++) {
				if( (students[i] & classes[j]) == 0) {
					possible++;
				}
			}
			sb.append(possible).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}

```

## ✅ 후기
처음엔 비트 마스킹으로 저장할 때
```java
classes[i] |= (1 << Integer.parseInt(st.nextToken());
```
이런 식으로 했었는데 결과가 이상하게 나와서 확인해보니   
(1 << x) 가 int로 저장되는지 음수로 된 상태에서 long형인 classes에 저장되서 비트마스킹이 이상하게 됐었습니다.

그래서 우선 long 형으로 선언해주고 << 연산자로 민 다음 연산해주는 것으로 변경했습니다.
