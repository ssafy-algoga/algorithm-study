import java.io.*;
import java.util.*;

class answer {

/*
<ab cd>ef gh<ij kl>
*/
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		int power = N.length()-1;
		
		int B = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N.length(); i++) {
			char num = N.charAt(i);
			if(num >= 'A' && num <= 'Z') {
				ans += Math.pow(B, power--) * (num - 55);
			} 
			else {
				ans += Math.pow(B, power--) * (num - 0);
			}
		}
		System.out.println(ans);
	}
	
}
