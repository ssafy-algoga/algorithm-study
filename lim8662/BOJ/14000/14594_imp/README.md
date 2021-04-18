# 14594번 동방 프로젝트(Small)
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계

1차원 배열로 벽의 상태를 저장하고

입력값대로 벽을 허문 후 남은 벽의 개수를 세어서 방의 수를 구했습니다.


---

### 1. main

```java
public static void main(String[] args) throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	int N = Integer.parseInt(br.readLine());
	int M = Integer.parseInt(br.readLine());
	int[] wall = new int[N];

	for (int i = 0; i < M; i++) {
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		for(int w = x; w < y; w++) {
			wall[w] = -1; // 벽 허뭄
		}
	}
	int cnt = 0;
	for(int i : wall) {
		if(i == 0) cnt++;
	}
	System.out.println(cnt);
}
```
벽을 N개를 만들고 입력 받은 x ,y 값을 통해서

index 1부터 벽의 상태를 변경했습니다.

---

## ✅ 후기

실버 3이지만 그보다 쉽게 느껴졌습니다..

이 문제도 정답자가 300명으로 적은 문제라서 난이도 검증이 덜 되었다고 생각합니다.

다음에는 1000단위 이상에서 최대한 정답자 많은 순으로 골라보겠습니다.


