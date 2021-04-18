# 9251번 LCS
[문제 보러가기](https://www.acmicpc.net/problem/9251)

## 🅰 설계

### 1. 작은 문제 선정
첫번째 문자열 F[] = "ABC", 두번째 문자열 S[] = "DA" 면
```
    0 1 2 3
F :   A B C
S :   A B 
```
LCS(x,y)는 F[x]와 S[y]를 끝으로 하는 두 문자열의 LCS 길이라 하면
 ```
LCS(0,0) = 0  
LCS(0,1) = 0
LCS(1,1) = 1
LCS(1,2) = 1 
LCS(2,1) = 1
LCS(2,2) = 2
LCS(3,2) = 2  
```
하나의 문자열이라도 길이가 0이면 LCS도 0이고

마지막 문자가 같으면 해당 문자를 제외한 문자열의 LCS길이에서 1증가합니다.

마지막 문자가 다르면 두 문자열의 이전 글자의 LCS중 큰 값을 가집니다.
### 2. 점화식 도출
1. x = 0 or y = 0이면 LCS(x,y) = 0

2. F[x] = S[y] 이면 LCS(x,y) = LCS(x-1,y-1) + 1

3. F[x] != S[y] 이면 LCS(x,y) = MAX(LCS(x-1,y), LCS(x, y-1)) 

### 3. 전체 코드
```java
public class boj_9251 {

	static int[][] dp;
	static char[] f, s;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		f = br.readLine().toCharArray();
		s = br.readLine().toCharArray();
		dp = new int[f.length + 1][s.length + 1];
		
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < s.length; j++) {
				dp[i + 1][j + 1] = ( f[i] == s[j] ) ? dp[i][j] + 1 : Math.max(dp[i+1][j], dp[i][j+1]) ;
			}
		}
		System.out.println(dp[f.length][s.length]);	
	}
}
```
## ✅ 후기
성급한 일반화를 하여 맞왜틀에 고통받다가 포기하고 다른분들의 풀이를 보았습니다.

제가 DP에 취약하다는 것을 느꼈고 DP 유형의 문제를 많이 접하고 생각하는 과정을 연습해야겠습니다.

