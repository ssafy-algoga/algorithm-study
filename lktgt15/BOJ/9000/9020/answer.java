import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static List<Integer> primes = new ArrayList<>();
	static boolean[] chk = new boolean[10001];
	static int LIMIT = 10000;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=2;i<=LIMIT;i++) {
			if(!chk[i]) {
				primes.add(i);
				for(int j=i*2;j<=LIMIT;j+=i) {
					chk[j] = true;
				}
			}
		}
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<t;i++) solve();
	}
	
	static void solve() throws IOException {
		
		int n = Integer.parseInt(br.readLine());
		int a = 0;
		int b = Integer.MAX_VALUE;
		for(int i=0;i<primes.size();i++) {
			if(primes.get(i) > n/2) break;
			int idx = Collections.binarySearch(primes, n-primes.get(i));
			if(idx>=0) {
				a = primes.get(i);
				b = primes.get(idx);
			}
		}
		System.out.println(a+" "+b);
	}
}