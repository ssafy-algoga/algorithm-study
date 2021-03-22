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