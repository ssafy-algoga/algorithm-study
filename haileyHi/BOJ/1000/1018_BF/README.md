# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계
체스판을 다시 칠해야 하니까 체스판을 입력 받을 때 기존의 체스판과 다른 경우 배열에 1을 담고
브루트 포스에 따라서 이를 더해 최소를 구한다.

예를 들어 4*4를 기준으로 
BWBW
WBWB
BWBW
WBWB
이와 같은 배치를 기본이라 가정할 때
0. 입력 받은 칸이 이 배치와 다르면 해당 위치에 1을 넣어 주었다.

1. 
입력이
```
WWBW
WBWB
BWBW
WBWB
```
인 경우는 맨 왼쪽 상단의 W만 B로 바꿔 칠하면 된다. *map[0][0]만 1임*

2.
입력이
```BBWB
BWBW
WBWB
BWBW
```

인 경우는 맨 왼쪽 상단의 B만 W로 바꿔 칠하면 된다.
*0번에 따라 map[0][0]을 제외한 모든 칸이 1*

2번의 경우 구하고자 하는 범위의 map의 합은 15지만 사실 왼쪽 상단의 칸이 검은색이든 흰색이든 상관 없으므로 하나만 바꾸면 됨.
```
WBWB
BWBW
WBWB
BWBW
```
에 가까운 뒤집는 경우는 16개의 칸이 몽땅 가정과 다르므로 16-map 의 합으로 구하면 된다.

**모든 칸이 뒤바뀌었을 때는 전체 칸 - 합**
```java
Math.min( 구간 합, 64 - 구간 합)
```

**따라서**
1. 처음 입력 받을 때 임의로 (i+j)%2와 현재 칸이 흰색인지 검은색인지를 확인하고 배열에 1씩 담는다.
2. 그 다음은 for문으로 각 좌표에서 시작한 8*8 배열에서 다시 칠해야 할 최소 개수를 구하고 (i,j)
3. 다시 최소 중 최소를 구하고 출력해준다.

**코드**
```java
public class BOJ_ColoringChess_1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] chess = new char[M][N];
        int[][] map = new int[M][N];

        int cp = 0;
        int result = 100;
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                chess[i][j] = str.charAt(j);//보드 받아오기
                if((i+j)%2 == 0 && chess[i][j] == 'W') map[i][j]++;
                if((i+j)%2 == 1 && chess[i][j] == 'B') map[i][j]++;
            }
        }
        for (int i = 0; i < M-7; i++) {
            for (int j = 0; j < N-7; j++) {
                cp = 0;
                for(int x = 0; x<8;x++){
                    for(int y = 0; y<8; y++){
                        if(map[i+x][j+y] != 0) cp++;
                    }
                }
                cp = Math.min(cp, 64 - cp);
                result = Math.min(cp, result);
            }
        }
        System.out.println(result);
    }
}

```


## ✅ 후기
전형적인 방식으로 풀어서 조금 진부하지만 재미있게 풀었습니다.


