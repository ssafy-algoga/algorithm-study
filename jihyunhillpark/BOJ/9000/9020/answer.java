import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int size = 10000;
	static boolean[] notPrime = new boolean[size+1];
	public static void main(String[] args) throws NumberFormatException, IOException {
		eratostenes();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < T ; i++) {
			int input = Integer.parseInt(in.readLine());
			for(int j = input/2 ; 2 <= j ; j--) { //차이가 적은 수의 조합을 찾아야 하기 때문에 둘로 나눈 수부터 차감시켜 찾아 나가도록 합니다.
				if(notPrime[j]) continue;
				int counter = input-j;
				if(notPrime[counter]) continue; //소수가 아닐 때 다음과정을 진행하기 위함
				sb.append(j); //아래 네줄은 출력에 담기 위함입니다.
				sb.append(" ");
				sb.append(counter);
				sb.append("\n");
				break; //두 수가 모두 소수일 때 작업 중지한다.
			}
		}
		System.out.print(sb);
	}
	private static void eratostenes() {
		int root = 100; //제곱근까지만 수의 배수를 제거하는 작업할 것이다.
		for(int i = 2 ; i <= root; i++) {
			int pos = i+i; //2배수부터 시작하여 소거한다.
			while(pos <= size) {
				notPrime[pos] = true;
				pos += i;
			}
		}
	}
}
