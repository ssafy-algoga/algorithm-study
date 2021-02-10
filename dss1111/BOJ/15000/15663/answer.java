import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static LinkedHashSet<String> set= new LinkedHashSet<String>();
	static boolean[] visited;
	static int[] arr;
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int [] resultArr;
		Arrays.sort(arr);
		for(int i=0;i<N;i++) {
			visited[i] = true;
			resultArr=new int[M];
			resultArr[0]=arr[i];
			permutation(resultArr,1);
			visited[i] = false;
		}
		
		for(String s : set) {
			System.out.println(s);
		}
	}
	public static void permutation(int[] resultArr,int level) {
		int i;
		if(level==M) {
			String str="";
			for(i=0;i<M;i++) {
				str+=Integer.toString(resultArr[i])+" ";
			}
			set.add(str);
			return;
		}
		for(i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				resultArr[level]=arr[i];
				permutation(resultArr,level+1);
				visited[i]=false;
			}
		}
	}
}
