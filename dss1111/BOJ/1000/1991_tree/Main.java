import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static class Node{
		int left;
		int right;
		char value;
		Node(char v,int l,int r){
			value=v;
			left=l;
			right=r;
		}
	}
	static Node [] nodes = new Node[27];
	static StringBuilder result = new StringBuilder(); 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int n=0;n<N;n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char v = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			nodes[insert(v)]= new Node(v,insert(l),insert(r));
		}
		pre(1);
		result.append("\n");
		in(1);
		result.append("\n");
		post(1);
		System.out.println(result);
	}
	static int insert(char n) {
		if(n=='.')
			return 0;
		else
			return n-64;
	}
	static void pre(int n) {
		if(n>26)return;
		if(n==0)return;
		result.append(nodes[n].value);
		pre(nodes[n].left);
		pre(nodes[n].right);
	}
	static void in(int n) {
		if(n>26)return;
		if(n==0)return;
		in(nodes[n].left);
		result.append(nodes[n].value);
		in(nodes[n].right);
	}
	static void post(int n) {
		if(n>26)return;
		if(n==0)return;
		post(nodes[n].left);
		post(nodes[n].right);
		result.append(nodes[n].value);
	}
}
