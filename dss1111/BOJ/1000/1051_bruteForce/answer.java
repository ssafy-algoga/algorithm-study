import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Integer,Integer>map = new HashMap<>();
	static int N,M;
	static int[][]arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N][M];
		for(int i=0;i<N;i++) {
			String str=br.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j]=str.charAt(j)-'0';
			}
		}
		int min=Math.min(M, N);
		int result =1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int k=1;k<min;k++) {
					if(i+k<N && j+k<M && arr[i][j]==arr[i][j+k] && arr[i][j]==arr[i+k][j] && arr[i][j]==arr[i+k][j+k]) {
						result=Math.max(result,k+1);
					}
				}
			}
		}
		System.out.println(result*result);
	}
}
