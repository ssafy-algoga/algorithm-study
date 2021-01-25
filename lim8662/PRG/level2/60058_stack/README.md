# 60058번 kakao 2020 lv2 괄호변환
[문제 보러가기](https://programmers.co.kr/learn/courses/30/lessons/60058)

## 🅰 설계
<img src="https://user-images.githubusercontent.com/43156636/105741408-9927b880-5f7d-11eb-94ff-bb4c1ea8895c.jpg"  width="500" height="400">

### 출제 의도
주어진 로직을 그대로 구현할 수 있는지 파악

재귀함수를 이해하고 작성할 수 있는지 파악 

---

카카오 2020 2번 기출문제로 정답률은 23.1%인 문제입니다. 

문제에 주어진 과정에 따라 재귀적으로 구현만 하면 되는 문제입니다.

---

아래는 유일하게 구현 방법이 문제에서 기재되지 않은 괄호의 짝이 맞는지 확인하는 메소드입니다.
```jsx
public boolean isMatch(String s) {
	int pair = 0;
	for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				pair++;
			else
				pair--;
			if (pair < 0) {
				return false;
			}
	}
	return true;
}
```
## ✅ 후기

### 고생한 점
아주 쉬운 문제인데 어렵게 풀었습니다.

과정을 의미를 파악하고 알고리즘을 이해하려고 하다보니 시간이 많이 걸렸습니다.

그리고 git을 익히고 사용하는데 더 오랜 시간이 걸렸습니다...

아직도 모르겠어요...

### 새롭게 알게되거나 공유해서 알게된 점
빠른 풀이를 위해서는 처음에 문제 전체를 훑어보고 유형을 파악하는 습관을 길러야겠습니다.

