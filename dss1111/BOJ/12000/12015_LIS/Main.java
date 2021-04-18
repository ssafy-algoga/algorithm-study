import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [] arr;
	static int [] table;
	static int index;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		table = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) {
			if(i==0) {
				table[index]=arr[0];
			}
			else {
				if(table[index]<arr[i]) {
					table[++index]=arr[i];
					continue;
				}
				int temp = index;
				while(temp>=0 && table[temp]>=arr[i]) {
					temp--;
				}
				table[++temp]=arr[i];
			}
		}
		System.out.println(index+1);
	}
}
