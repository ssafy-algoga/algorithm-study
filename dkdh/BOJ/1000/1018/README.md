# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계
완전탐색으로 풀었습니다.

메인에서는 먼저 입력을 받고, B와 W를 각각 1과 0으로 저장한 int 배열을 만들었습니다.   
그 후 반복문으로 배열의 인덱스를 돌며 함수를 호출합니다.

함수는 인덱스를 받아 해당 인덱스를 포함한 오른쪽 8칸, 아래 8칸에 대해   
해당 인덱스의 색이 검정일 때와 하양일 때 새롭게 칠해야 하는 정사각형 수를 세서   
더 적은 쪽을 리턴합니다.

처음 시작이 검정일 때와 하양일 때, 체스판의 각 칸이 가져야 할 원래 색은 모두 정반대이기 때문에   
각 칸에 대해 해당 칸의 색과 처음 시작이 검정일 때 이 칸이 가져야 할 색이   
같으면 처음 시작이 하양일 때 칠해야 할 정사각형 수를 ++하고   
다르면 처음 시작이 검정일 때 칠해야 할 정사각형 수를 ++하는 식으로 풀었습니다.

지금 생각해보니까 완전 정반대니까 그냥 처음 시작이 검정일 때에 대해서 칠해야 할 정사각형 수cnt를 구하고  
cnt랑 64-cnt 중에서 작은 쪽을 반환해도 됐겠네요


### 코드
```jsx
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1018_체스판다시칠하기 {

	public static int N;
	public static int M;
	
	public static int[][] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j)=='B' ? 1 : 0;
			}
		}
		// 입력 완료
		
		int min_cnt = Integer.MAX_VALUE;
		
		for (int i = 0; i <= N-8; i++) {
			for (int j = 0; j <= M-8; j++) {
				min_cnt = Math.min(min_cnt, getBoxCnt(i, j));
			}
		}
		
		System.out.print(min_cnt);
		
	}

	public static int getBoxCnt(int rStart, int cStart) {
		int wCnt = 0;
		int bCnt = 0;
		
    		// 처음 시작이 검정일 때 각 칸이 가져야 할 색을 저장할 변수
		int bRight = 1;
		
    		// 입력된 보드의 칸의 색을 저장할 변수
		int cur;
		
		for (int i = rStart; i < rStart + 8; i++) {
			for (int j = cStart; j < cStart + 8; j++) {
        			// 현재 칸의 색
				cur = board[i][j];
				
				if(cur != bRight) {
					bCnt++;
				}
				else {
					wCnt++;
				}
				
				bRight ^= 1;
			}
			bRight ^= 1;
		}
		
		return Math.min(wCnt, bCnt);
	}
}

```

## ✅ 후기
완전탐색 말고 뭔가 더 최적화할 방법은 없나 고민해봤는데 떠오르지 않았습니다
