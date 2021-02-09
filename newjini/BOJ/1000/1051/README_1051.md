# 1051번 숫자 정사각형

[문제 보러가기](https://www.acmicpc.net/problem/1051)

## 🅰 설계

1. 가능한 정사각형의 최대크기를 구하면서 반복문을 돕니다.
2. `[i][j]` 와 `[i+size][j], [i][j+size], [i+size][j+size]`비교
3. size 제곱한 값 중 최댓값 찾기

![1051](https://media.discordapp.net/attachments/802048763232780321/808486641281794068/image.png)

## 코드

```
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for(int size = 0; size < Math.min(N-i, M-j); size++) {
					if(arr[i][j] == arr[i+size][j] && arr[i][j] == arr[i][j+size] && arr[i][j] == arr[i+size][j+size]) {
						res = (int) Math.max(Math.pow(size+1, 2), res);
					}
				}
			}
		}
		System.out.println(res);
```

## ✅ 후기

완전탐색으로 하는 건 알았지만 최대 정사각형 크기에 대한 for문을 돌려야할 지 많이 고민했던 문제였습니다.  
