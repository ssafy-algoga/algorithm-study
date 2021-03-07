import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] notPrime=new boolean[10001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		int test = Integer.parseInt(br.readLine());
		for(int t=0;t<test;t++) {
			int n = Integer.parseInt(br.readLine());
			int half = n/2;
			for(int i=0;i<n/2;i++) {
				if(!notPrime[half-i] && !notPrime[half+i]) {
					System.out.println((half-i)+" "+(half+i));
					break;
				}
			}
		}
	}
	static void init() { //에라토스테네스의 체
		notPrime[0]=true;
		notPrime[1]=true;
		for(int i=2;i<10001;i++) {
			for(int j=2;i*j<10001;j++) {
				notPrime[i*j]=true;
			}
		}
	}
}
