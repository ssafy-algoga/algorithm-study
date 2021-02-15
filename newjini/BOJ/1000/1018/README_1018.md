# 1018번 체스판 다시 칠하기

[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계

1. 맨 왼쪽이 검은색인 경우와 흰색인 경우를 미리 정의하고 해결했습니다.
2. 8X8의 빈 배열에 입력 배열 값을 넣어서 돌아다니면서 미리 정의한 배열과 비교합니다.

## 코드

``` java 
public class boj_1018 { // 체스판 다시 칠하기

	static char[][] white = { 
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' } };

	static char[][] black = { 
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M]; // 입력 배열
		char[][] eight = new char[8][8]; // 8x8 배열
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		int cnt = 2500; // 최종 최소 cnt
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int fy = i; // first y
				int fx = j; // first x 
				int endy = i + 7;
				int endx = j + 7; 
				int bcnt = 0, wcnt = 0; // 왼쪽맨끝 : 흑 , 백 일 때 바꿔지는 흑백갯수세기
				eight = new char[8][8];
				if (endy < N && endx < M) {
					for (int a = fy, c=0; a <= endy; a++,c++) { // 8X8 배열로 자르기
						for (int b = fx, d=0; b <= endx; b++,d++) {
							eight[c][d] = arr[a][b];
						}
					}
					for (int a = 0; a < 8; a++) { // white 배열과 검사
						for (int b = 0; b < 8; b++) {
							if (eight[a][b] != white[a][b]) {
								wcnt++; 	
							}
						}
					}
					for (int a = 0; a < 8; a++) { // black 배열과 검사
						for (int b = 0; b < 8; b++) {
							if (eight[a][b] != black[a][b]) {
								bcnt++;
							}
						}
					}
					cnt = Math.min(Math.min(wcnt, bcnt), cnt);
				}
			}
		}
		System.out.println(cnt);
	}

}
```

## ✅ 후기

그냥 배열을 미리 정의하고 풀었습니다.