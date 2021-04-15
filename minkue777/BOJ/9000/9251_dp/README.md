# 9251번 LCS
[문제 보러가기](https://www.acmicpc.net/problem/9251)

## 🅰 설계
다른분들이 이미 너무나도 자세하게 설명해 놓으셔서 딱히 더 적을게 없습니다.
굳이 특별한 점을 찾자면 저는 DP를 구현할 때 탑다운 방식을 선호하기 때문에
탑다운 방식으로 구현했다 정도입니다.

```java 
static int lcs(int idx1, int idx2) {
    if(idx1 == 0 || idx2 == 0) return 0;
    if(cache[idx1][idx2] != -1) return cache[idx1][idx2];
    if(s1.charAt(idx1-1) == s2.charAt(idx2-1)) {
        return cache[idx1][idx2] = lcs(idx1-1, idx2-1)+1;
    }
    return cache[idx1][idx2] = Math.max(lcs(idx1-1, idx2), lcs(idx1, idx2-1));
}
```

## ✅ 후기
DP는 코드짜기가 생명인 구현이나 시물레이션과 정반대의 느낌을 줍니다.
이번 문제도 그렇듯 전체문제와 Sub-problem의 관계만 정확히 찾아낸다면 
코드로 작성하는건 몹시 수월하달까요. 사실 lcs는 파생된 어려운 문제들도
많기 때문에 시간 여유가 있을 때 더 풀어보고 싶습니다.