import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static HashMap<Integer,Integer> convertIdx = new HashMap(); // 좌표 압축 mapping
	static HashSet<Integer>[] path = new HashSet[101]; // 해당 번호의 path
	static int[] sameLine = new int[11];
	static int n,idx,k;
	static Queue<Integer> q = new ArrayDeque();
	static boolean[] visit = new boolean[101];
	
	public static void main(String[] args) throws IOException {
		System.out.println(solve());
	}
	
	static int solve() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		idx = 0;
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			for(int j=0;j<k;j++) {
				int curnum = Integer.parseInt(st.nextToken());
				if(convertIdx.containsKey(curnum)) {
					sameLine[j] = convertIdx.get(curnum);
					continue;
				}
				convertIdx.put(curnum, idx); // 번호 부여
				sameLine[j] = idx;
				path[idx++] = new HashSet<Integer>(); // 그 번호의 path 리스트 생성
			}

			buildPath();
		}
		
		int dest = convertIdx.get(Integer.parseInt(br.readLine()));
		int start = convertIdx.get(0);

		for(int nxt : path[start]) {
			q.add(nxt); q.add(0);
			visit[nxt] = true;
		}
		while(!q.isEmpty()) {
			int cur = q.poll();
			int dist = q.poll();
			if(cur == dest) {
				return dist;
			}
			
			for(int nxt : path[cur]) {
				if(visit[nxt]) continue;
				q.add(nxt); q.add(dist+1);
				visit[nxt] = true;
			}
		}
		
		return -1;
	}
	
	static void buildPath() {
		for(int i=0;i<k;i++) {
			int curp = sameLine[i];
			for(int j=0;j<k;j++) {
				path[curp].add(sameLine[j]);
			}
		}
	}
}