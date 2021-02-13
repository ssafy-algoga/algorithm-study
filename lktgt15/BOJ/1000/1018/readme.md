# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계
W,B,W,B... 으로 칠해보고, B,W,B,W로도 칠해보면서 모든 경우의 수를 체크해보면 되는 문제였습니다  
8 x 8 배열을 2개 만들어서 체크해보는 가장 간단한 방법이 처음으로 떠올랐습니다  
실전이었으면 가장 먼저 떠오르는 방법으로 했겠지만.. 공부할때는 두번째 방법을 시도하는 편이라 비트를 이용하는 방법으로 해 봤습니다  

초기 체스판에서 B나 W를 1로, 다른 것을 0으로 초기화해주고 y,x의 번호를 0,1로 맞춰주어 번갈아서 XOR하면 얼마나 다시 칠해야하는지 알 수 있습니다  
i+j부분이 0인지 1인지는 생각할 필요가 없습니다.. 어차피 W,B,W,B인 경우와 B,W,B,W 의 경우 둘 다 세어볼테니까요  

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int[][] map = new int[50][50];
	static int n,m,ans;
	static char[] in;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for(int i=0;i<n;i++) {
			in = br.readLine().toCharArray();
			for(int j=0;j<m;j++) {
				if(in[j] == 'B') map[i][j] = 1; // B인경우 1로 초기화, W로 바꿔도 상관 없음
			}
		}
		
		ans = Integer.MAX_VALUE;
		for(int i=0;i<n-7;i++) for(int j=0;j<m-7;j++) f(i,j);
		System.out.println(ans);
	}
	
	static void f(int cury,int curx) {
		int subans1 = 0, subans2 = 0;
		for(int i=0;i<8;i++) for(int j=0;j<8;j++) {
			subans1 += map[cury+i][curx+j]^((i+j)&1); // i+j가 홀수인 경우 1, 아니면 0과 XOR
			subans2 += map[cury+i][curx+j]^((i+j+1)&1);// i+j가 홀수인 경우 0, 아니면 1과 XOR
		}
		ans = Math.min(ans,Math.min(subans1, subans2)); // 가장 적은게 답이 됨
	}
}
```


## ✅ 후기
간단한 연산은 비트로 풀리는게 은근히 많은 것 같습니다  
