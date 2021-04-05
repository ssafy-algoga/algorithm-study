# 8911번 거북이
[문제 보러가기](https://www.acmicpc.net/problem/8911)

## 🅰 설계

그대로 구현했습니다.
거북이가 바라보는 방향에 따라 F, B 명령어 때 좌표 변화를 줄 수 있도록 dx, dy 배열을 이용했습니다.   
L, R 명령어가 왼쪽 오른쪽 90도여서 +1 -1로 할 수 있도록 북동남서 순서로 했습니다.
```java
// 북 동 남 서
int[] dx = {0, 1, 0, -1};
int[] dy = {1, 0, -1, 0};
```

test case 마다 거북이 방향, 위치, maxY, maxX, minY, minX 값을 초기화해주었습니다.
```java
// 거북이 방향
int d = 0;

// 거북이 위치
int x = 0;
int y = 0;

// maxY maxX minY minX
int[] mm = {0, 0, 0, 0};
```

F, B 명령어는 거북이를 움직입니다.   
F는 방향을 그대로 이용하고, B는 방향을 거꾸로 가야하기 때문에 d에 +2를 해줍니다.   
북쪽이면 y 좌표가 늘어나 maxY가 갱신될 가능성이 있고,   
동쪽이면 x 좌표가 늘어나 maxX가 갱신될 가능성이 있으며,   
남쪽이면 y 좌표가 감소해 minY가 갱신될 가능성이 있고,   
서쪽이면 x 좌표가 감소해 minX가 갱신될 가능성이 있고
그 외에는 없습니다.
따라서 방향에 따라 이에 맞는 값을 갱신해줍니다.

배열로 만든 건 방향과 인덱스를 통일해 뭔가 할 수 있지 않을까 싶었는데 max, min 함수랑 비교해야 하는 x, y 좌표가 다 달라서 딱히 의미가 없어졌습니다.

B 명령어는 뒤로 가는 것이지 방향이 달라지진 않기 때문에 B 명령어인 경우 방향을 다시 돌려놔줍니다.

```java
int p = program.charAt(i);
				
if(p == 'F' || p == 'B') {
  if(p == 'B') {
    d = (d+2)%4;
  }
  x += dx[d];
  y += dy[d];

  if(d == 0)
    mm[0] = Math.max(mm[0], y);
  else if(d==1)
    mm[1] = Math.max(mm[1], x);
  else if(d==2)
    mm[2] = Math.min(mm[2], y);
  else
    mm[3] = Math.min(mm[3], x);

  if(p=='B')
    d = (d+2)%4;
}
else if(p == 'L') {
  d = (d+3)%4;
}
else if(p == 'R') {
  d = (d+1)%4;
}
```

거북이가 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형은 거북이가 지나간 가장 크고 작은 x, y좌표를 모두 포함해야 하기 때문에 가장 크고 작은 x, y 값들이 직사각형의 경계가 됩니다.   
가로와 세로를 구해 곱해줍니다.
```java
int w = mm[1] - mm[3];
int h = mm[0] - mm[2];

sb.append(w*h).append("\n");
```

### 코드
전체 코드입니다.
```java
public class Main_8911_거북이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 북 동 남 서
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for (int t = 1; t <= T; t++) {			
			// 거북이 방향
			int d = 0;
			
			// 거북이 위치
			int x = 0;
			int y = 0;
			
			// maxY maxX minY minX
			int[] mm = {0, 0, 0, 0};
			
			String program = br.readLine();
			
			for (int i = 0, size=program.length(); i < size; i++) {
				int p = program.charAt(i);
				
				if(p == 'F' || p == 'B') {
					if(p == 'B') {
						d = (d+2)%4;
					}
					x += dx[d];
					y += dy[d];
					
					if(d == 0)
						mm[0] = Math.max(mm[0], y);
					else if(d==1)
						mm[1] = Math.max(mm[1], x);
					else if(d==2)
						mm[2] = Math.min(mm[2], y);
					else
						mm[3] = Math.min(mm[3], x);
					
					if(p=='B')
						d = (d+2)%4;
				}
				else if(p == 'L') {
					d = (d+3)%4;
				}
				else if(p == 'R') {
					d = (d+1)%4;
				}
				
			}
			
			int w = mm[1] - mm[3];
			int h = mm[0] - mm[2];
			
			sb.append(w*h).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}

```
