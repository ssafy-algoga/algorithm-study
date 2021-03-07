import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		p[] arr = new p[m+1];
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new p(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		arr[m] = new p(n,n); // 마지막 [curl,curr]을 처리하기 위해 dummy 넣음
		
		Arrays.sort(arr); // l을 기준으로 오름차순 정렬
		
		int r = 0;
		int cnt = 0;
		for(int i=0;i<=m;i++) {
			int curl = arr[i].l;
			int curr = arr[i].r;
			
			if(r < curl) { // 방이 하나가 될 수 없음( [...,r] - [...] - [curl,curr] )
				cnt += curl-r-1; // r 부터 curl 사이의 방의 개수
				cnt++; // 다음으로 하나가 되는 방  ( [curl,r] )
				r = curr; // 다음으로 하나가 되는 방의 끝 범위
			}
			else r = Math.max(r, curr); // [...,r]에 [curl,curr]이 포함되면 하나로 합침
		}
		System.out.println(cnt);
	}
	
	static class p implements Comparable<p>{
		int l,r;
		public p(int l,int r) {
			this.l = l;
			this.r = r;
		}
		@Override
		public int compareTo(p o) {
			return this.l-o.l;
		}
	}
}