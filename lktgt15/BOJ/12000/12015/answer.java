import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> lis = new ArrayList<>(); // lis를 담는 배열
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(st.nextToken());
			int l = -1; // left 범위
			int r = lis.size(); // right 범위
			int mid = 0;
			while(l+1<r) {
				mid = (l+r)/2;
				if(lis.get(mid) < x) l = mid; // lis[mid]가 x보다 작으면 l범위를 좁힘
				else r = mid; // lis[mid]가 x보다 크거나 같으면 r범위를 좁힘
			}
			if(r == lis.size()) { // r 이 lis의 size면 r보다 크거나 같은 lis 원소가 존재하지 않음 
				lis.add(x);
			}
			else { // 그렇지 않으면 lis[r]이 x보다 크거나 같으면서 가장 작은 원소가 됨
				lis.set(r, x);
			}
		}
		System.out.println(lis.size());
	}
}