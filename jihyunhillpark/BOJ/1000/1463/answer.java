import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int ret[] = new int[n+1];
		int min;
		// DP - bottom up
		// step2 and over
		for(int i = 1; i <= n ; i++) {
			// step1 - base case
			if(i == 1) ret[i] = 0;
			else if(i == 2 || i == 3) ret[i] = 1;
			else {  // step2 - inductive
				min = 1 + ret[i-1];
				if(i%3 == 0) {
					int temp = 1 + ret[i/3];
					min = (temp < min)? temp : min;
				}
				if(i%2 == 0) {
					int temp = 1 + ret[i/2];
					min = (temp < min)? temp : min;
				}
				ret[i] = min;
			}
		}
		// output
		System.out.println(ret[n]);
		//sc.close();
	}
}
