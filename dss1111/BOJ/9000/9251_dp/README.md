# 9251번 LCS
[문제 보러가기](https://www.acmicpc.net/problem/9251)

## 🅰 설계
LCS는 배열을 통해서 풀 수 있습니다. 0에서 부터 시작해서 문자가 일치하면 1을 증가시키고 아니라면 위의숫자나 왼쪽의 숫자들중  
큰 것을 써넣습니다. 배열 마지막 숫자가 LCS 길이입니다.

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 *       A C A Y K P
	 *    
	 *   C   0 1 1 1 1 1
	 *   A   1 1 2 2 2 2
	 *   P   1 1 2 2 2 3
	 *   C   1 2 2 2 2 3
	 *   A   1 2 3 3 3 3
	 *   K   1 2 3 3 4 4
	 *   
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char [] word1 = br.readLine().toCharArray();
		char [] word2 = br.readLine().toCharArray();
		int [][] table= new int[word2.length+1][word1.length+1]; 
		for(int i=0;i<word2.length;i++){
			for(int j=0;j<word1.length;j++) {
				table[i+1][j+1] = (word1[j]==word2[i])? table[i][j]+1: Math.max(table[i][j+1], table[i+1][j]);
				//table[i+1][j+1] = (word1[j]==word2[i])? table[i][j]+1: max(table[i][j+1], table[i+1][j]);
			}
		}
		System.out.println(table[word2.length][word1.length]);
	}
	/*
	public static int max(int a, int b) {
		return (a>b)?a:b;
	}
	*/
}
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
* Math.max를 몰라서 맨날 삼항연산했는데 이제 Math.max를 알게되었습니다.  
* LCS 푸는법을 외워서. 다신 안잊어버릴 것 같습니다.  
![image](https://user-images.githubusercontent.com/37682970/106101486-4fa7bb00-6181-11eb-80f3-a5873671fa9d.png)  
문제가 LCS길이를 찾는게 아니라 LCS를 찾는거로 바뀌면 오른쪽아래에서 부터 왼쪽으로 따라올라가서   
숫자가 증가하는 부분을 체크하면 찾을 수 있습니다.

### 고생한 점
* 푸는 방법을 도저히 생각을 못하겠어서 다른사람들 어떻게 풀었나 확인습니다. 그냥 풀이법을 외워야겠어요
