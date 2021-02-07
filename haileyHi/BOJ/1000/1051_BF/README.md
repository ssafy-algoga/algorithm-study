# 1051번 숫자 정사각형
[문제 보러가기](https://www.acmicpc.net/problem/1051)

## 🅰 설계
N*M크기 꼭짓점 모두 같은 가장 큰 사각형 찾기
1. n,m 내에서 돌면서 한번에 꼭짓점 네 개의 위치를 확인해서 사각형 사이즈 구하기.

```
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int check = square[i][j];
			for(int cnt = 1; cnt+i<N && cnt+j <M;cnt++){
			//square[i+cnt][j], square[i][j+cnt], square[i+cnt][j+cnt]가 모두 check와 같은지 확인하고 같으면 cnt+1시켜서 다시 확인하기
			maxSize = Math.max(maxSsize, (cnt + 1) * (cnt + 1));
			}
		}
	}
```
	


## ✅ 후기
설계한대로 코드를 짜서 단순하게 만들었습니다...
// 고생한 점
깃허브 fetch를 취소하다가 파일을 지워버렸어요... 리드미만 세번째 작성중이에요.. 조금 슬픈 밤입니다....