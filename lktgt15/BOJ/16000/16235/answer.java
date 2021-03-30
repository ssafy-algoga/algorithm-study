package algoga.week6;

import java.io.*;
import java.util.*;

public class 나무재테크 {
	static BufferedReader br;
	static StringTokenizer st;

	static PriorityQueue<tree> trees = new PriorityQueue<>((e1,e2) -> e1.age - e2.age); // 현재 계산할 나무들
	static PriorityQueue<tree> nxttrees = new PriorityQueue<>((e1,e2) -> e1.age - e2.age); // 다음 계산될 나무들
	static List<tree> autumntrees = new ArrayList<>(); // 가을에 번식할 나무들
	static List<tree> deadtrees = new ArrayList<>(); // 죽은 나무들
	static int[][] map = new int[11][11],addtomap = new int[11][11]; // (r,c)의 양분, winter()에 추가될 양분
	static int[] dy= {-1,-1,-1,0,0,1,1,1}, dx= {-1,0,1,-1,1,-1,0,1}; 
	static int n,m,k;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				addtomap[i][j] = Integer.parseInt(st.nextToken()); // winter()에 사용될 추가 양분
				map[i][j] = 5; // 맵의 초기 양분
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x,y,z;
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			trees.offer(new tree(y,x,z));
		}
		
		for(int i=0;i<k;i++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		System.out.println(trees.size());
		
	}
	
	static void spring() {
		while(!trees.isEmpty()) {
			tree cur = trees.poll();
			if(map[cur.y][cur.x] >= cur.age) { // 나무가 있는 map[cur.y][cur.x]에 나무의 나이만큼 양분이 있으면
				map[cur.y][cur.x] -= cur.age;
				cur.age++;
				
				if(cur.age%5 == 0) { // 나무의 나이가 5의 배수이면 autumntrees에도 넣음
					autumntrees.add(cur);
				}
				nxttrees.add(cur); // 공통적으로 nxttrees에 넣음
			}
			else {
				deadtrees.add(cur); // 양분이 충분하지 않다면 deadtrees에 넣음
			}
		}
		while(!nxttrees.isEmpty()) { // 임시 저장소 nxttrees에서 trees로 옮김
			trees.offer(nxttrees.poll());
		}
		
	}
	
	static void summer() {
		for(tree cur : deadtrees) { // 모든 deadtrees에 있는 나무를 확인하여 map[cur.y][cur.x]에 나이/2만큼 양분을 늘려줌
			map[cur.y][cur.x] += cur.age/2; 
		}
		deadtrees = new ArrayList<>();
	}
	
	static void autumn() {
		for(tree cur : autumntrees) { // 모든 autumntrees에 있는 나무를 확인하여 번식시킴
			int cy = cur.y;
			int cx = cur.x;
			for(int i=0;i<8;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(isValid(ny,nx)) { // 격자 범위를 넘어가지 않으면 trees에 넣음
					trees.offer(new tree(ny,nx,1));
				}
			}
		}
		autumntrees = new ArrayList<>();
	}
	
	static void winter() {
		for(int i=1;i<=n;i++) for(int j=1;j<=n;j++) {
			map[i][j] += addtomap[i][j];
		}
	}
	
	static boolean isValid(int y,int x) {
		return y>=1 && y<=n && x>=1 && x<=n;
	}
	
	static class tree{
		int age,y,x;
		public tree(int y,int x,int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}
	}
}