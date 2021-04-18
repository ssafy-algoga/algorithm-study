package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9020_골드바흐의추측 {
	
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			findPartition(n);
		}
		
		sb.setLength(sb.length()-1);
		System.out.print(sb.toString());
	}
	
	public static void findPartition(int n) {
		int a, b;
		
		a = b = n/2;
		
		for( ; a>0 && b<n; a--, b++) {
			if(a%2==0 && a!=2) continue;
			
			if(isPrime(a) && isPrime(b)) {
				sb.append(a).append(" ").append(b).append("\n");
				return;
			}
			
		}
	}
	
	public static boolean isPrime(int n) {
		int l = (int) Math.sqrt(n);
		
		for(int d=2; d<=l; d++) {
			if(d%2==0) continue;
			if(n%d==0) return false;
		}
		
		return true;
	}

}
