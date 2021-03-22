# 17143번 낚시왕
[문제 보러가기](https://www.acmicpc.net/problem/17143)

## 🅰 설계
처음 이 문제를 봤을때부터 한눈에 봐도 복잡해보여서 '언젠간 풀겠다'라고 생각하고 미뤄왔던 문제였습니다.  

### 1. map과 update

제가 생각한 방식은 map\[r]\[c]에 상어 객체를 넣어주는 것이었습니다.  

모든 상어객체는 `List<shark> sharks`에 존재하고, map\[r]\[c]에는 해당 좌표에 존재하는 상어만 있게 됩니다.  

상어 객체는 *크기*,*속력*과 같은 정보에 추가로 *dead*라는 정보도 포함됩니다. 이를 통해 유효한 상어에 대해서만 작업을 할 수 있게 됩니다.  

이렇게 하면 낚시왕이 상어를 잡는 것을 구현할 때 직관적으로 열에서 한칸씩 내려가면서 상어를 잡을 수 있습니다.  

문제는 상어가 움직일 때 모든 상어를 한번에 움직일 수 없다는 겁니다.  

예를 들어 모든 상어가 움직일 때, map\[1]\[1]에 있던 상어를 움직여서 map\[2]\[1]에 도착했을 때 이미 map\[2]\[1]에 있는 상어는 원래 거기에 있던 상어인가? 아니면 움직여줘야 하는 상어인가? 를 생각해줘야 합니다.  

원래 거기에 있던 상어라면 두 상어가 동시에 map\[2]\[1]에 존재하는 것이 가능합니다. 그게 아니고 방금 map\[1]\[1]에 있던 상어가 움직여서 온 것처럼 이번에 움직여서 map\[2]\[1]에 온 상어라면 크기가 큰 상어만 남겨야합니다.  

이를 해결하는 방법은 2가지가 있을것 같습니다.  

#### i. next map\[r]\[c]를 만든다

현재 cur map\[r]\[c]에서 움직인 상어를 next map\[r]\[c]에 표시하고 이 next map을 cur map으로 바꿔주면 간단하게 해결할 수 있습니다.  

#### ii. cur map\[r]\[c]에 추가로 updated\[r]\[c]를 만든다

(r,c) 좌표에 업데이트된 시간을 표시하면 그 좌표에 있는 상어가 움직인 상어인지 움직여야하는 상어인지 판단할 수 있습니다.  
이렇게 하면 map\[r]\[c]에 추가로 updated\[r]\[c]까지 확인하여 그 (r,c)에 있는 상어가 유효한 상어인지 확인이 가능합니다.  

```java
map = new shark[r][c];
updated = new int[r][c];
r--;
c--;

for(int i=0;i<m;i++) {
	st = new StringTokenizer(br.readLine());
	int cr = Integer.parseInt(st.nextToken())-1;
	int cc = Integer.parseInt(st.nextToken())-1;
	int cs = Integer.parseInt(st.nextToken());
	int cd = Integer.parseInt(st.nextToken());
	int cz = Integer.parseInt(st.nextToken());
	sharks.add(new shark(cr,cc,cs,cd,cz));
	map[cr][cc] = sharks.get(i);
	updated[cr][cc] = time;
}
```
제가 선택한 방법은 2번 방법입니다.  

`List<shark> sharks`에 모든 상어를 넣고 `map[r][c]`에 그 좌표에 있는 상어 레퍼런스를 넣어주고 `updated[r][c]`에 업데이트된 시간을 표시합니다.  

### 2. 행동 쪼개기

직접 만들어야 할 행동들을 순서대로 쪼개보면 다음과 같습니다.  

1. 낚시왕이 오른쪽으로 한 칸 움직인다.  
2. 낚시왕이 같은 열에 있는 상어 중 가장 가까운 상어를 잡는다.  
3. 모든 상어가 각각의 *속력*, *방향*을 가지고 움직인다.  
4. 만약 같은 칸에 상어가 있을 경우 크기가 큰 상어만 남는다.  

```java
while(++fishking<=c) { // 낚시왕이 오른쪽으로 한칸 움직이고 격자판을 벗어나지 않음
	kill(); // 낚시왕이 해당 열에서 가장 가까운 상어를 잡음
	time++; // updated 배열을 위한 시간 증가
	moveall(); // 모든 상어가 움직임
}
```

이 부분만 떼어서 보면 위와 같은 코드가 됩니다.  

### 2-1. kill()

```java
static void kill() { // 낚시왕이 map[i = 0 to r][fishking]에서 상어를 잡음
	for(int i=0;i<=r;i++) {
		if(map[i][fishking] != null && !map[i][fishking].dead && updated[i][fishking] == time) {
			ans += map[i][fishking].cz;
			map[i][fishking].dead = true;
			map[i][fishking] = null;
			break;
		}
	}
}
```
낚시왕의 column에서 row를 늘려가면서 map\[r]\[c]에 상어가 죽은 상태가 아니고 updated\[r]\[c]가 현재 시간과 같다면 그 상어를 잡습니다.  

### 2-2. moveall()

```java
static void moveall() { // 죽은 상어가 아닌 모든 상어가 움직임
	for(shark sk : sharks) {
		if(!sk.dead) {
			sk.move();
		}
	}
}
```
상어 List에서 상어가 죽어있지 않으면 `move()`메소드를 호출합니다.  

### 2-3. shark와 move()
```java
static class shark{
	int cr,cc,cs,cd,cz;
	boolean dead;
	public shark(int cr,int cc,int cs,int cd,int cz) {
		this.cr = cr;
		this.cc = cc;
		this.cs = cs;
		this.cd = cd;
		this.cz = cz;
	}
```
상어 List에 들어갈 shark 객체입니다. 각자 행,열,속도,방향,크기, 죽음 여부의 정보를 가지고 있습니다.  

```java
public void move() {
	if(cd == 1) { // up
		int dr = Math.min(cr, cs); // 현재 방향의 끝 or 속도
		cr -= dr; // 그만큼 이동
		int tmpcs = cs - dr; // 이동한 뒤 남은 이동 수
		if(tmpcs > 0) {
			if((tmpcs/r)%2 == 0) { // r로 나눈 몫이 짝수면 현재 칸에서 남은만큼 이동
				cd = 2;
				cr = 0;
				cr += tmpcs%r;
			}
			else { // r로 나눈 몫이 홀수면 현재 칸의 정반대쪽에서 남은만큼 이동
				cd = 1;
				cr = r;
				cr -= tmpcs%r;
			}
		}
	}
```

`moveall()`에서 호출한 상어의 `move()`메소드입니다.  

현재 방향의 끝까지 간 다음 남은 칸을 방향에 따라 row 또는 column으로 나누고 남은 나머지만큼 이동시키는 방법을 사용했습니다.  

### 2-4 update와 fight

```java
if(map[cr][cc] != null && !map[cr][cc].dead && updated[cr][cc] == time) { // 이동한 (r,c)에 업데이트된 상어가 있는지 확인
	fight(map[cr][cc],this); // 크기가 큰 상어가 살아남음
}
else {
	map[cr][cc] = this; // 상어가 없으면 자신이 (r,c)에 남게됨
}
updated[cr][cc] = time; // updated[r][c]는 위의 결과와 관계없이 무조건 업데이트됨
```
위의 `move()`메소드에서 이어지는 코드입니다.  

이동한 상어가 낚시왕의 `kill()`메소드와 같이 이동한 (r,c)에 상어가 있는지 확인하고 싸우게 됩니다.  

```java
static void fight(shark a,shark b) {
	if(a.cz > b.cz) b.dead = true; // a가 크면 b가 죽음
	else { // b가 크면 a가 죽고 (r,c)에는 b가 남음
		a.dead = true;
		map[a.cr][a.cc] = b;
	}
}
```

`fight(shark a,shark b)`는 크기가 큰 상어가 map\[r]\[c]에 남게 되는 메소드입니다.

### 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int r,c,m,time = 1,fishking = -1,ans;
	static shark[][] map;
	static int[][] updated;
	static List<shark> sharks = new ArrayList<shark>();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new shark[r][c];
		updated = new int[r][c];
		r--;
		c--;
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int cr = Integer.parseInt(st.nextToken())-1;
			int cc = Integer.parseInt(st.nextToken())-1;
			int cs = Integer.parseInt(st.nextToken());
			int cd = Integer.parseInt(st.nextToken());
			int cz = Integer.parseInt(st.nextToken());
			sharks.add(new shark(cr,cc,cs,cd,cz));
			map[cr][cc] = sharks.get(i);
			updated[cr][cc] = time;
		}
		
		while(++fishking<=c) { // 낚시왕이 오른쪽으로 한칸 움직이고 격자판을 벗어나지 않음
			kill(); // 낚시왕이 해당 열에서 가장 가까운 상어를 잡음
			time++; // updated 배열을 위한 시간 증가
			moveall(); // 모든 상어가 움직임
		}
		System.out.println(ans);
		
	}
	static void moveall() { // 죽은 상어가 아닌 모든 상어가 움직임
		for(shark sk : sharks) {
			if(!sk.dead) {
				sk.move();
			}
		}
	}
	
	static void kill() { // 낚시왕이 map[i = 0 to r][fishking]에서 상어를 잡음
		for(int i=0;i<=r;i++) {
			if(map[i][fishking] != null && !map[i][fishking].dead && updated[i][fishking] == time) {
				ans += map[i][fishking].cz;
				map[i][fishking].dead = true;
				map[i][fishking] = null;
				break;
			}
		}
	}
	
	static class shark{
		int cr,cc,cs,cd,cz;
		boolean dead;
		public shark(int cr,int cc,int cs,int cd,int cz) {
			this.cr = cr;
			this.cc = cc;
			this.cs = cs;
			this.cd = cd;
			this.cz = cz;
		}
		
		public void move() {
			if(cd == 1) { // up
				int dr = Math.min(cr, cs);
				cr -= dr;
				int tmpcs = cs - dr;
				if(tmpcs > 0) {
					if((tmpcs/r)%2 == 0) {
						cd = 2;
						cr = 0;
						cr += tmpcs%r;
					}
					else {
						cd = 1;
						cr = r;
						cr -= tmpcs%r;
					}
				}
				
			}
			else if(cd == 2) { // down
				int dr = Math.min(r-cr, cs);
				cr += dr;
				int tmpcs = cs - dr;
				if(tmpcs > 0) {
					if((tmpcs/r)%2 == 0) {
						cd = 1;
						cr = r;
						cr -= tmpcs%r;
					}
					else {
						cd = 2;
						cr = 0;
						cr += tmpcs%r;
					}
				}
			}
			else if(cd == 3) { // right
				int dc = Math.min(c-cc, cs);
				cc += dc;
				int tmpcs = cs - dc;
				if(tmpcs > 0) {
					if((tmpcs/c)%2 == 0) {
						cd = 4;
						cc = c;
						cc -= tmpcs%c;
					}
					else {
						cd = 3;
						cc = 0;
						cc += tmpcs%c;
					}
				}
			}
			else { // left
				int dc = Math.min(cc, cs);
				cc -= dc;
				int tmpcs = cs - dc;
				if(tmpcs > 0) {
					if((tmpcs/c)%2 == 0) {
						cd = 3;
						cc = 0;
						cc += tmpcs%c;
					}
					else {
						cd = 4;
						cc = c;
						cc -= tmpcs%c;
					}
				}
			}
			if(map[cr][cc] != null && !map[cr][cc].dead && updated[cr][cc] == time) { // 이동한 (r,c)에 업데이트된 상어가 있는지 확인
				fight(map[cr][cc],this); // 크기가 큰 상어가 살아남음
			}
			else {
				map[cr][cc] = this; // 상어가 없으면 자신이 (r,c)에 남게됨
			}
			updated[cr][cc] = time; // updated[r][c]는 위의 결과와 관계없이 무조건 업데이트됨
		}
	}
	
	static void fight(shark a,shark b) {
		if(a.cz > b.cz) b.dead = true; // a가 크면 b가 죽음
		else { // b가 크면 a가 죽고 (r,c)에는 b가 남음
			a.dead = true;
			map[a.cr][a.cc] = b;
		}
	}
}
```

## ✅ 후기
처음에 딱 보고 쉽지않겠다 라고 생각했던 문제였습니다. 그러나 문제를 잘게 쪼개서 생각해보면 어려웠던 부분은 `move()`가 전부인 그런 문제였습니다. 이렇게 척 보고 쉽지 않아보이는 문제를 풀었을 때 더 성장하는 부분이 많은것 같습니다.  


